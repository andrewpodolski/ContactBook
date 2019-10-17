package com.andrew.commands.impl;

import com.andrew.commands.Command;
import com.andrew.service.impl.PhoneServiceImpl;
import com.andrew.validation.HasNumberValidation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactsPhones implements Command {
    private Logger logger = Logger.getLogger(ContactsPhones.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            if (HasNumberValidation.isNumber(request.getParameter("contact_id"))) {
                response.setHeader("Content-Type", "application/json; charset=UTF-8");
                Integer id = Integer.parseInt(request.getParameter("contact_id"));
                response.getWriter().write(new PhoneServiceImpl().getJsonPhonesById(id));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
}