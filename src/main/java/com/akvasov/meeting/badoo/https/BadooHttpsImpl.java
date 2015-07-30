package com.akvasov.meeting.badoo.https;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.BytesContentProvider;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by alex on 29.07.15.
 */
@Service("badoo_http")
public class BadooHttpsImpl implements Badoo {

    private static String cookies = "aid=376383076; statt=1434519135.07%7C376383076; statj=t%3Ab4%2Ch%3A9f76aad1c0724%2Cs%3A9195692cf70e6%2Cu%3A166f2664; statf=ci%3A166f2664%2Cfp%3A71f6baa5f7d74%2Cs%3A67fa4cc1a7afa%2Cu%3A166f2664; s1=4wQ8ihVoA4_kuYc1cCOUot3WAmSockkn4; TS=1437497443%7C2; has_secure_session=1; email=kvsanya%40yandex.ru; honbd=1";

    private String doPost(String param, String content, String refer) {
        SslContextFactory sslContextFactory = new SslContextFactory();
        HttpClient client = new HttpClient(sslContextFactory);

        try {
            client.start();

            ContentResponse response = client.POST("https://eu1.badoo.com/api.phtml")
                    .header("Host", "eu1.badoo.com")
                    .header("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:30.0) Gecko/20100101 Firefox/30.0")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .header("Accept-Language", "en-US,en;q=0.5")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Content-Type", "json; charset=UTF-8")
                    .header("X-Session-id", "4wQ8ihVoA4_kuYc1cCOUot3WAmSockkn4")
                    .header("X-User-id", "376383076")
                    .header("Referer", refer)
                    .header("Content-Length", Integer.valueOf(content.length()).toString())
                    .header("Cookie", cookies)
                    .header("Connection", "keep-alive")
                    .param(param, "")
                    .content(new BytesContentProvider(content.getBytes()))
                    .send();

            return response.getContentAsString();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                client.stop();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return "";
    }

    @Override
    public JSONArray getAccounts(Integer offset, Integer count) {
        String answer = doPost("SERVER_GET_USER_LIST", String.format("{\"$gpb\":\"badoo.bma.BadooMessage\",\"version\":1,\"message_type\":245,\"message_id\":12,\"body\":[{\"$gpb\":\"badoo.bma.MessageBody\",\"message_type\":245,\"server_get_user_list\":{\"$gpb\":\"badoo.bma.ServerGetUserList\",\"folder_id\":25,\"user_field_filter\":{\"$gpb\":\"badoo.bma.UserFieldFilter\",\"projection\":[250,200,210,310,330,540,340,331,680]},\"offset\":%d,\"preferred_count\":%d}}]}", offset, count), "https://eu1.badoo.com/search");
        System.out.println("answer = " + answer);
        JSONObject obj = new JSONObject(answer);
        JSONArray users = obj.getJSONArray("body").getJSONObject(0).getJSONObject("client_user_list").getJSONArray("section").getJSONObject(0).getJSONArray("users");
        return users;
    }

    @Override
    public JSONObject getAccount(String userId) {
        return null;
    }
}
