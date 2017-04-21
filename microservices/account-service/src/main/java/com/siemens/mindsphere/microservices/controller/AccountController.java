package com.siemens.mindsphere.microservices.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.mindsphere.microservices.domain.Account;
import com.siemens.mindsphere.microservices.repository.AccountRepository;



@RestController
@RequestMapping(value = "/accounts")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private AccountRepository repository;

    @Autowired
    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Account> accounts() {
        return repository.findAll();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public Account add(@RequestBody @Valid Account account) {
        logger.info("Adding account " + account.getAccountNumber());
        return repository.save(account);
    }

    @ResponseBody	
    @RequestMapping(method = RequestMethod.POST)
    public Account update(@RequestBody @Valid Account account) {
        logger.info("Updating account " + account.getAccountNumber());
        return repository.save(account);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getById(@PathVariable String id) {
        logger.info("Getting account " + id);
        return repository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable String id) {
        logger.info("Deleting account " + id);
        repository.delete(id);
    }
}