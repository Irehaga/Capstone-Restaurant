package com.tamnguyen.restaurant.service;

/**
 * @author Tam Nguyen
 */
public interface EmailService {
    void sendEmail(String to, String subject, String content, String replyTo);
}
