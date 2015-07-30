package com.akvasov.meeting.badoo.https;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by alex on 29.07.15.
 */
public interface Badoo {

    JSONArray getAccounts(Integer offset, Integer Count);

    JSONObject getAccount(String userId);

}
