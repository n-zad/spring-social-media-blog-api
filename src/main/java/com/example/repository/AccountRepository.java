package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // JPARepository provides the interface methods for standard CRUD operations.

    /**
     * Property expression to find an account by its username.
     * @param username of the account.
     * @return Account the queried account (since usernames are distinct there will at most one account queried).
     */
    public Account findByUsername(String username);
}
