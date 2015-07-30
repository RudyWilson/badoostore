package com.akvasov.meeting.badoo.services;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by alex on 29.07.15.
 */
public interface AccountService {

    void update(Integer offset, Integer count);

    Integer processAccounts(JSONArray users);

    Boolean processAccount(JSONObject user);

}