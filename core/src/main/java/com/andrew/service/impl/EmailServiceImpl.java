package com.andrew.service.impl;

import com.andrew.service.EmailService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class EmailServiceImpl implements EmailService {
    @Override
    public void sendMessage(String to, String subject, String text) throws MessagingException, IOException {
        Properties properties = new Properties();
        properties.load(Objects.requireNonNull(EmailServiceImpl.class.getClassLoader().getResourceAsStream("mail.properties")));
        final String admin = properties.getProperty("mail.smtps.user");
        final String password = properties.getProperty("mail.smtps.pass");

        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(admin));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setText(text, "UTF-8");
        message.setSubject(subject, "UTF-8");
        Transport transport = mailSession.getTransport();
        transport.connect(admin, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
