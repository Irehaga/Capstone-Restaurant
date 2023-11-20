package com.tamnguyen.restaurant.serviceImp;

import com.tamnguyen.restaurant.service.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Tam Nguyen
 */

@Service
public class EmailServiceImp implements EmailService {
    private final JavaMailSender emailSender;

    @Autowired
    public EmailServiceImp( JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    @Transactional
    public void sendEmail(String to, String subject, String content, String replyTo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(replyTo);
        message.setTo(to);
        message.setReplyTo(replyTo);
        message.setSubject(subject);
        message.setText(content);
        emailSender.send(message);
    }
}
