package com.akvasov.meeting.badoo.controller;

import com.akvasov.meeting.badoo.model.domain.Account;
import com.akvasov.meeting.badoo.model.repository.AccountDao;
import com.akvasov.meeting.badoo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        model.addAttribute("accounts", accountDao.getAll());
        return "hello";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String printFilter(ModelMap model) {
        List<Account> accounts = accountDao.getFilterAll();
        model.addAttribute("message", "Hello world! " + accounts.size());
        model.addAttribute("accounts", accounts);
        return "hello";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String getAnswer(ModelMap model) throws Exception {
        accountService.update(0, 1000);
        model.addAttribute("message", "Hello world!");
        return "answer";
    }

}