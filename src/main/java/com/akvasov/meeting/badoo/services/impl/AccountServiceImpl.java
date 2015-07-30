package com.akvasov.meeting.badoo.services.impl;

import com.akvasov.meeting.badoo.https.Badoo;
import com.akvasov.meeting.badoo.model.domain.Account;
import com.akvasov.meeting.badoo.model.repository.AccountDao;
import com.akvasov.meeting.badoo.services.AccountService;
import com.akvasov.meeting.badoo.services.BadooUserToAccountTranslator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by alex on 29.07.15.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private BadooUserToAccountTranslator translator;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    @Qualifier("badoo_http")
    private Badoo badoo;

    @Override
    public void update(Integer offset, Integer count) {
        JSONArray users = badoo.getAccounts(offset, count);
        processAccounts(users);
    }

    @Override
    public Integer processAccounts(JSONArray users) {
        Integer result = 0;
        for (int i = 0; i < users.length(); ++i) {
            if (processAccount(users.getJSONObject(i))) result++;
        }
        return result;
    }

    @Override
    public Boolean processAccount(JSONObject user) {
        Account account = translator.translate(user);
        if (account.getIsDeleted() != Boolean.FALSE) return Boolean.FALSE;
        accountDao.update(account);

        return Boolean.TRUE;
    }
}
