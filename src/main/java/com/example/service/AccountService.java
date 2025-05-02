package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.exception.DuplicateAccountException;
import com.example.exception.InvalidAccountException;

@Service
public class AccountService {
    AccountRepository accountRepository;

    /**
     * Constructor for Spring to inject an AccountRepository into an instantiated AccountService.
     */
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Given a new valid account (unique non-empty username and a password that has at least 4 characters),
     * use accountRepository to persist the account to the database.
     * @param account the new account to be added.
     * @return Account the persisted account.
     * @throws DuplicateAccountException if the username already exists.
     * @throws InvalidAccountException if the account is invalid for any other reason.
     */
    public Account persistAccount(Account account) throws DuplicateAccountException, InvalidAccountException {
        if (accountRepository.findByUsername(account.getUsername()) != null) {
            throw new DuplicateAccountException();
        } else if (account.getUsername().length() == 0 || account.getPassword().length() < 4) {
            throw new InvalidAccountException();
        } else {
            return accountRepository.save(account);
        }
    }
}
