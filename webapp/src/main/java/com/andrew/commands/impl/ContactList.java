package com.andrew.commands.impl;

import com.andrew.commands.Command;
import com.andrew.connection.PoolConnection;
import com.andrew.service.impl.ContactServiceImpl;
import com.andrew.validation.HasNumberValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ContactList implements Command {
    private Logger logger = Logger.getLogger(ContactList.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            boolean pageIsNum = HasNumberValidation.isNumber(request.getParameter("page"));
            boolean indexIsNum = HasNumberValidation.isNumber(request.getParameter("index"));
            if (pageIsNum && indexIsNum) {
                response.setHeader("Content-Type", "application/json; charset=UTF-8");
                Integer page = Integer.parseInt(request.getParameter("page"));
                Integer index = Integer.parseInt(request.getParameter("index"));

                if (!PoolConnection.isConnectionExists()) {
                    response.getWriter().write(new ObjectMapper().writeValueAsString("Contact list can't be loaded"));
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                } else {
                    response.getWriter().write(new ContactServiceImpl().getAllJsonContacts(page, index));
                    response.setStatus(HttpServletResponse.SC_OK);
                }

            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
