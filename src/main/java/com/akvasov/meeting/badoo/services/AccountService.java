package com.akvasov.meeting.badoo.services;

import com.akvasov.meeting.badoo.model.domain.Account;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by alex on 27.07.15.
 */
@Service("accountService")
@Transactional
public class AccountService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public List<Account> getAll() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM  Account");

        return query.list();
    }

}
