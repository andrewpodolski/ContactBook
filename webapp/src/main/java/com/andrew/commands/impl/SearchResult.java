package com.andrew.commands.impl;

import com.andrew.commands.Command;
import com.andrew.service.impl.ContactServiceImpl;
import com.andrew.validation.HasNumberValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SearchResult implements Command {
    private Logger logger = Logger.getLogger(SearchResult.class);

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
                response.getWriter().write(new ContactServiceImpl().getJsonSearchContacts(page, index));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(new ObjectMapper().writeValueAsString("Contact list can't be loaded"));
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
