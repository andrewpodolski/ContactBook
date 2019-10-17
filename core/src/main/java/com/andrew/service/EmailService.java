package com.andrew.service;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {
      void sendMessage(String to, String subject, String text) throws MessagingException, IOException;
}
