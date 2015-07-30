package com.akvasov.meeting.badoo.model.repository;

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
@Service
@Transactional
public class AccountDao {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<Account> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Account");
        return query.list();
    }

    public List<Account> getFilterAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Account where onlineStatusText not like 'Была на сайте более недели назад'");
        return query.list();
    }

    public void update(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(account);
    }

}
