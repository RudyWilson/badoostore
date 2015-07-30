package com.akvasov.meeting.badoo.services;

import com.akvasov.meeting.badoo.model.domain.Account;
import org.json.JSONObject;

/**
 * Created by alex on 29.07.15.
 */
public interface BadooUserToAccountTranslator {

    Account translate(JSONObject user);
}
