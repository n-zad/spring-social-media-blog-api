package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@Controller
@ResponseBody
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    /**
     * Constructor for Spring to inject Service dependecies into an instantiated SocialMediaController.
     */
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    /**
     * Endpoint for POST localhost:8080/register. Uses accountService to persist a new account.
     * Username cannot be blank nor already exist, and the password must be at least 4 characters.
     * The API will return status code 409 (conflict) if the provided username is already in the databse.
     * If the registration is not successful for any other reason, the status code will be 400 (client error).
     * @param account the Account object deserialized from the request body.
     * @return Account including the account_id, if the registration was successful.
     */
    @PostMapping("/register")
    public Account postAccount(@RequestBody Account account) {
        return accountService.persistAccount(account);
    }

    /**
     * Endpoint for POST localhost:8080/login. Uses accountService to validate than an account exists.
     * If the given username and password doesn't match an existing account, the status code will be 401 (unauthorized).
     * @param account the Account object deserialized from the request body.
     * @return Account including the account_id, if the login was successful.
     */
    @PostMapping("/login")
    public Account verifyAccount(@RequestBody Account account) {
        return accountService.verifyAccount(account);
    }
}
