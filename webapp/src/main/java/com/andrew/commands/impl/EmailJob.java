package com.andrew.commands.impl;

import com.andrew.dao.ContactDao;
import com.andrew.entity.Contact;
import com.andrew.service.EmailService;
import com.andrew.service.impl.EmailServiceImpl;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class EmailJob implements Job {
    private Logger logger = Logger.getLogger(EmailJob.class);
    private EmailService emailService = new EmailServiceImpl();
    private Properties properties = new Properties();

    @Override
    public void execute(JobExecutionContext context) {
        LocalDate localDate = LocalDate.now();
        List<Contact> birthdayMen = ContactDao.getAllContactsByDate(localDate);
        try {
            properties.load(Objects.requireNonNull(EmailJob.class.getClassLoader().getResourceAsStream("mail.properties")));
        } catch (IOException e) {
            logger.info("Loading properties in EmailJob");
            logger.error(e);
        }
        String admin = properties.getProperty("admin.email");
        if (!birthdayMen.isEmpty()) {
            StringBuilder list = new StringBuilder();
            for (Contact contact : birthdayMen) {
                list.append(contact.getSurname()).append(" ").append(contact.getName()).append("\n");
            }
            StringTemplate congratulations =
                    new StringTemplate("Birthday today:\n $list$", DefaultTemplateLexer.class);
            congratulations.setAttribute("list", list.toString());
            try {
                emailService.sendMessage(admin, "Birthday", congratulations.toString());
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }
}