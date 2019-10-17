package com.andrew.commands.impl;

import com.andrew.commands.Command;
import com.andrew.service.impl.ContactServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DefaultImage implements Command {
    private Logger logger = Logger.getLogger(DefaultImage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setHeader("Content-Type", "application/json; charset=UTF-8");
            response.getWriter().write(new ContactServiceImpl().defaultJsonByteArrayPhoto());
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
