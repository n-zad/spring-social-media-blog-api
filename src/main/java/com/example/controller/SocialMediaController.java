package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.InvalidMessageException;
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
     * Endpoint for POST localhost:8080/register. Use accountService to persist a new account.
     * Username cannot be blank nor already exist, and the password must be at least 4 characters.
     * The API will return status code 409 (conflict) if the provided username is already in the databse.
     * If the registration is not successful for any other reason, the status code will be 400 (client error).
     * @param account the Account object deserialized from the request body.
     * @return Account including the accountId, if the registration was successful.
     */
    @PostMapping("/register")
    public Account postAccount(@RequestBody Account account) {
        return accountService.persistAccount(account);
    }

    /**
     * Endpoint for POST localhost:8080/login. Use accountService to validate than an account exists.
     * If the given username and password doesn't match an existing account, the status code will be 401 (unauthorized).
     * @param account the Account object deserialized from the request body.
     * @return Account including the accountId, if the login was successful.
     */
    @PostMapping("/login")
    public Account verifyAccount(@RequestBody Account account) {
        return accountService.verifyAccount(account);
    }

    /**
     * Endpoint for POST localhost:8080/messages. Use messageService to persist a new message (and accountService to check author).
     * If messageText is blank or over 255 characters, or postedBy doesn't refer to an existing user, the status code will be 400 (client error).
     * @param message the Message object deserialized from the request body.
     * @return Message including the messageId, if the message creation was successful.
     */
    @PostMapping("/messages")
    public Message postMessage(@RequestBody Message message) {
        if (accountService.getAccountById(message.getPostedBy()) == null) {
            throw new InvalidMessageException();
        } else {
            return messageService.persistMessage(message);
        }
    }

    /**
     * Endpoint for GET localhost:8080/messages. Use messageService to retreive every message.
     * @return List<Message> the retrieved messages.
     */
    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    /**
     * Endpoint for GET localhost:8080/messages/{messageId}. Use messageService to retreive a message by its messageId.
     * @param messageId the messageId of the message to retrieve.
     * @return Message the retrieved messages.
     */
    @GetMapping("/messages/{messageId}")
    public Message getMessageById(@PathVariable int messageId) {
        return messageService.getMessageById(messageId);
    }

    /**
     * Endpoint for DELETE localhost:8080/messages/{messageId}. Use messageService to delete a message by its messageId.
     * @param messageId the messageId of the message to delete.
     * @return ResponseEntity<Integer> the response body will be the number of rows affected (1).
     */
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId) {
        int rowsAffected = messageService.deleteMessageById(messageId);
        if (rowsAffected == 1) {
            return ResponseEntity.status(200).body(rowsAffected);
        } else {
            return ResponseEntity.status(200).build();
        }
    }

    /**
     * Endpoint for PATCH localhost:8080/messages/{messageId}. Use messageService to update a message's text by its messageId.
     * If the new messageText is blank or over 255 characters, the status code will be 400 (client error).
     * @param messageId the messageId of the message to update.
     * @param message a deserialized Message object containing the new message text.
     * @return ResponseEntity<Integer> the response body will be the number of rows affected (1).
     */
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageById(@PathVariable int messageId, @RequestBody Message message) {
        int rowsAffected = messageService.updateMessageById(messageId, message.getMessageText());
        return ResponseEntity.status(200).body(rowsAffected);
    }

    /**
     * Endpoint for GET localhost:8080/accounts/{accountId}/messages. Use messageService to retreive every message posted by the given account.
     * @param accountId the postedBy for the messages to retrieve.
     * @return List<Message> the retrieved messages.
     */
    @GetMapping("/accounts/{accountId}/messages")
    public List<Message> getAllMessagesByAccount(@PathVariable int accountId) {
        return messageService.getAllMessagesByAccount(accountId);
    }
}
