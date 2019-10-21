package com.andrew.commands.impl;

import com.andrew.commands.Command;
import com.andrew.dao.ContactDao;
import com.andrew.service.impl.ContactServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Search implements Command {
    private Logger logger = Logger.getLogger(Search.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            ContactDao.searchQuery = "select * from contact left join address on id = contact_id";

            if (!request.getParameterMap().isEmpty()) {
                ContactDao.searchQuery += " where ";
                if (request.getParameter("name") != null) {
                    ContactDao.searchQuery += ("name=" + "\'" + request.getParameter("name") + "\'" + " and ");
                }
                if (request.getParameter("surname") != null) {
                    ContactDao.searchQuery += ("surname=" + "\'" + request.getParameter("surname") + "\'" + " and ");
                }
                if (request.getParameter("patronymic") != null) {
                    ContactDao.searchQuery += ("patronymic=" + "\'" + request.getParameter("patronymic") + "\'" + " and ");
                }


                if (request.getParameter("ageComparison") != null) {
                    if (request.getParameter("ageComparison").equals("more")) {
                        String ageComparison = ">";
                        if (request.getParameter("year") != null)
                            ContactDao.searchQuery += ("year(birthday)" + ageComparison + request.getParameter("year") + " and ");
                        if (request.getParameter("month") != null)
                            ContactDao.searchQuery += ("month(birthday)" + ageComparison + request.getParameter("month") + " and ");
                        if (request.getParameter("day") != null)
                            ContactDao.searchQuery += ("day(birthday)" + ageComparison + request.getParameter("day") + " and ");
                    } else if (request.getParameter("ageComparison").equals("less")) {
                        String ageComparison = "<";
                        if (request.getParameter("year") != null)
                            ContactDao.searchQuery += ("year(birthday)" + ageComparison + request.getParameter("year") + " and ");
                        if (request.getParameter("month") != null)
                            ContactDao.searchQuery += ("month(birthday)" + ageComparison + request.getParameter("month") + " and ");
                        if (request.getParameter("day") != null)
                            ContactDao.searchQuery += ("day(birthday)" + ageComparison + request.getParameter("day") + " and ");

                    } else if (request.getParameter("ageComparison").equals("equals")) {
                        String ageComparison = "=";
                        if (request.getParameter("year") != null)
                            ContactDao.searchQuery += ("year(birthday)" + ageComparison + request.getParameter("year") + " and ");
                        if (request.getParameter("month") != null)
                            ContactDao.searchQuery += ("month(birthday)" + ageComparison + request.getParameter("month") + " and ");
                        if (request.getParameter("day") != null)
                            ContactDao.searchQuery += ("day(birthday)" + ageComparison + request.getParameter("day") + " and ");
                    }

                }
                if (request.getParameter("nationality") != null) {
                    ContactDao.searchQuery += ("nationality=" + "\'" + request.getParameter("nationality") + "\'" + " and ");
                }
                if (request.getParameter("gender") != null) {
                    ContactDao.searchQuery += ("gender=" + "\'" + request.getParameter("gender") + "\'" + " and ");
                }
                if (request.getParameter("maritalStatus") != null) {
                    ContactDao.searchQuery += ("marital_status=" + "\'" + request.getParameter("maritalStatus") + "\'" + " and ");
                }
                if (request.getParameter("webSite") != null) {
                    ContactDao.searchQuery += ("web_site=" + "\'" + request.getParameter("webSite") + "\'" + " and ");
                }
                if (request.getParameter("email") != null) {
                    ContactDao.searchQuery += ("email=" + "\'" + request.getParameter("email") + "\'" + " and ");
                }
                if (request.getParameter("placeOfWork") != null) {
                    ContactDao.searchQuery += ("place_of_work=" + "\'" + request.getParameter("placeOfWork") + "\'" + " and ");
                }
                if (request.getParameter("country") != null) {
                    ContactDao.searchQuery += ("country=" + "\'" + request.getParameter("country") + "\'" + " and ");
                }
                if (request.getParameter("city") != null) {
                    ContactDao.searchQuery += ("city=" + "\'" + request.getParameter("city") + "\'" + " and ");
                }
                if (request.getParameter("street") != null) {
                    ContactDao.searchQuery += ("street=" + "\'" + request.getParameter("street") + "\'" + " and ");
                }
                if (request.getParameter("houseNumber") != null) {
                    ContactDao.searchQuery += ("house_number=" + "\'" + request.getParameter("houseNumber") + "\'" + " and ");
                }
                if (request.getParameter("flatNumber") != null) {
                    ContactDao.searchQuery += ("flat_number=" + "\'" + request.getParameter("flatNumber") + "\'" + " and ");
                }
                if (request.getParameter("zipCode") != null) {
                    ContactDao.searchQuery += ("zip_code=" + "\'" + request.getParameter("zipCode") + "\'" + " and ");
                }
                ContactDao.searchQuery = ContactDao.searchQuery.substring(0, ContactDao.searchQuery.length() - 5);
                String req = request.getParameterMap().toString();

            }
            response.setHeader("Content-Type", "application/json; charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(new ContactServiceImpl().getJsonSearchCount()));
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
