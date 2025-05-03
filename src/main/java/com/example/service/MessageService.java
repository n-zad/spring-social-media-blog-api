package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.InvalidMessageException;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    MessageRepository messageRepository;

    /**
     * Constructor for Spring to inject an MessageRepository into an instantiated MessageService.
     */
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Given a new valid message (non-blank text that is less than 255 characters),
     * use messageRepository to persist the message to the database.
     * @param message the new message to be posted.
     * @return Message the persisted message.
     * @throws InvalidMessageException if the message is not valid.
     */
    public Message persistMessage(Message message) throws InvalidMessageException {
        String text = message.getMessageText();
        if (text.length() == 0 || text.length() >= 255) {
            throw new InvalidMessageException();
        } else {
            return messageRepository.save(message);
        }
    }

    /**
     * Use messageRepository to query every message in the database.
     * @return List<Message> the queried messages.
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /**
     * Use messageRepository to query a message by its messageId.
     * @param messageId of the message to query.
     * @return Message the queried message.
     */
    public Message getMessageById(int messageId) {
        return messageRepository.findByMessageId(messageId);
    }

    /**
     * Use messageRepository to delete a message by its messageId.
     * @param messageId of the message to delete.
     * @return int the number of rows affected.
     */
    public int deleteMessageById(int messageId) {
        Message message = getMessageById(messageId);
        if (message != null) {
            messageRepository.delete(message);
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Use messageRepository to update a message's text by its messageId.
     * @param messageId of the message to update.
     * @param messageText the new message text.
     * @return int the number of rows affected.
     * @throws InvalidMessageExeption if the message doesn't exist or the new message text is invalid.
     */
    public int updateMessageById(int messageId, String messageText) throws InvalidMessageException {
        Message existing = getMessageById(messageId);
        if (messageText.length() == 0 || messageText.length() >= 255 || existing == null) {
            throw new InvalidMessageException();
        } else {
            existing.setMessageText(messageText);
            messageRepository.save(existing);
            return 1;
        }
    }

    /**
     * Use messageRepository to query every message in the database posted by the given accountId.
     * @param accountId to query the messages by.
     * @return List<Message> the queried messages.
     */
    public List<Message> getAllMessagesByAccount(int accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
}
