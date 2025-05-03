package com.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // JPARepository provides the interface methods for standard CRUD operations.

    /**
     * Property expression to find a message by its messageId.
     * @param messageId of the message.
     * @return Message the queried message.
     */
    public Message findByMessageId(int messageId);

    /**
     * Property expression to find all messages with the given postedBy.
     * @param postedBy the accountId of the account that posted the messages.
     * @return List<Message> the queried messages.
     */
    public List<Message> findByPostedBy(int postedBy);
}
