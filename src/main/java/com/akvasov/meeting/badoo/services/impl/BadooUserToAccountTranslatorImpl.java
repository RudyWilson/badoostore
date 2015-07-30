package com.akvasov.meeting.badoo.services.impl;

import com.akvasov.meeting.badoo.model.domain.Account;
import com.akvasov.meeting.badoo.services.BadooUserToAccountTranslator;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by alex on 29.07.15.
 */
@Service
public class BadooUserToAccountTranslatorImpl implements BadooUserToAccountTranslator {

    /**
     {
         "user_id": "461729775",
         "name": "\u041e\u043b\u044c\u0433\u0430",
         "age": 26,
         "is_deleted": false,
         "photo_count": 2,
         "online_status": 3,
         "online_status_text": "\u0411\u044b\u043b\u0430 \u043d\u0430 \u0441\u0430\u0439\u0442\u0435 1 \u0447\u0430\u0441 \u043d\u0430\u0437\u0430\u0434",
         "profile_photo": {
             "id": "1312959516",
             "preview_url": "\/\/pcache-pv-eu1.badoocdn.com\/p45\/10198\/1\/5\/8\/461729775\/d1312959\/t1437421677\/c_ceOTgG0xQG3Xeh9vXBxgmyexHZ3c6h559vlTbcfW0Hk\/1312959516_180.jpg?t=30.0.1.2100000001&id=1312959516",
             "large_url": "\/\/pcache-eu1.badoocdn.com\/p46\/10198\/1\/5\/8\/461729775\/d1312959\/t1437421677\/c_q5fDe94-HeUEDLcLHdxKj34-8t9YdI0hbEwwtf5yCFc\/1312959516_920_o.jpg?t=30.0.1.2100000001&id=1312959516",
             "large_photo_size": {
                 "$gpb": "badoo.bma.PhotoSize",
                 "width": 0,
                 "height": 0
             }
         },
         "distance_long": "\u0421\u0430\u043d\u043a\u0442-\u041f\u0435\u0442\u0435\u0440\u0431\u0443\u0440\u0433",
         "has_mobile_app": true
     }
     */

    @Override
    public Account translate(JSONObject user) {
        Account account = new Account();
        account.setId(user.getString("user_id"));
        account.setName(user.getString("name"));
        account.setAge(user.getInt("age"));
        account.setIsDeleted(user.getBoolean("is_deleted"));
        account.setPhotoCount(user.getInt("photo_count"));
        account.setOnlineStatus(user.getInt("online_status"));
        account.setOnlineStatusText(user.getString("online_status_text"));
        account.setProfilePhoto(user.getJSONObject("profile_photo").getString("large_url"));
        return account;
    }
}
