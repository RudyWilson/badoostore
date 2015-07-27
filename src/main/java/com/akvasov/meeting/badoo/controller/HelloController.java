package com.akvasov.meeting.badoo.controller;

import com.akvasov.meeting.badoo.services.AccountService;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class HelloController {

    @Resource(name="accountService")
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        model.addAttribute("accounts", accountService.getAll());
        return "hello";
    }

    @RequestMapping(value = "/answer", method = RequestMethod.GET)
    public String getAnswer(ModelMap model) throws Exception {

        HttpClient client = new HttpClient();
        client.start();

        Request request = client.POST("http://eu1.badoo.com/search");
        request = request
                .header("Host", "eu1.badoo.com")
                .header("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:30.0) Gecko/20100101 Firefox/30.0")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .header("Accept-Language","en-US,en;q=0.5")
                .header("Accept-Encoding","gzip, deflate")
                .header("Cookie", "aid=376383076; statt=1434519135.07%7C376383076; statj=t%3Ab4%2Ch%3A9f76aad1c0724%2Cs%3A9195692cf70e6%2Cu%3A166f2664; statf=ci%3A166f2664%2Cfp%3A71f6baa5f7d74%2Cs%3A67fa4cc1a7afa%2Cu%3A166f2664; s1=4wQ8ihVoA4_kuYc1cCOUot3WAmSockkn4; TS=1437497443%7C2; has_secure_session=0; email=kvsanya%40yandex.ru; honbd=1")
                .header("Connection", "keep-alive");

        ContentResponse response = request.param("page", "7").send();

        String answer = response.getContentAsString();

        model.addAttribute("message", answer);

        client.stop();
        return "hello";
    }

}