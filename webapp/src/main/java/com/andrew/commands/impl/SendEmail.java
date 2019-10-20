package com.andrew.commands.impl;

import com.andrew.commands.Command;
import com.andrew.entity.Message;
import com.andrew.service.EmailService;
import com.andrew.service.impl.EmailServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendEmail implements Command {
    private Logger logger = Logger.getLogger(SendEmail.class);
    private EmailService emailService = new EmailServiceImpl();

   @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            Message messageObject = new ObjectMapper().readValue(request.getReader().readLine(), new TypeReference<Message>() {
            });
            String[] recipients = messageObject.getRecipients().split(" ");
            if (recipients.length != 0) {
                String template = messageObject.getTemplate();
                if (template.equals("-")) {
                        String message = messageObject.getText();
                        for (String mail : recipients) {
                            if (!mail.equals("") && !message.equals(""))
                                emailService.sendMessage(mail, messageObject.getSubject(), message);
                        }
                } else {
                    StringTemplate templateMessage = new StringTemplate(template, DefaultTemplateLexer.class);
                    for (String mail : recipients) {
                        if (!mail.equals("")) {
                            templateMessage.setAttribute("mail", mail);
                            emailService.sendMessage(mail, messageObject.getSubject(), templateMessage.toString());
                        }
                    }
                }
            }
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
