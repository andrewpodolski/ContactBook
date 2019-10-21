package com.andrew.commands.impl;

import com.andrew.commands.Command;
import com.andrew.service.ContactService;
import com.andrew.service.impl.ContactServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DefaultImage implements Command {
    private Logger logger = Logger.getLogger(DefaultImage.class);
    private ContactService contactService = new ContactServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setHeader("Content-Type", "application/json; charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(contactService.defaultJsonByteArrayPhoto()));
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
