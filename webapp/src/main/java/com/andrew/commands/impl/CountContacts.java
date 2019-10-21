package com.andrew.commands.impl;

import com.andrew.commands.Command;
import com.andrew.service.ContactService;
import com.andrew.service.impl.ContactServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CountContacts implements Command {
    private Logger logger = Logger.getLogger(CountContacts.class);
    private ContactService contactService = new ContactServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setHeader("Content-Type", "application/json; charset=UTF-8");
            response.getWriter().write(contactService.getJsonCount());
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
