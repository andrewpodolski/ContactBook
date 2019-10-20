package com.andrew.commands.impl;

import com.andrew.commands.Command;
import com.andrew.connection.PoolConnection;
import com.andrew.entity.Contact;
import com.andrew.service.impl.ContactServiceImpl;
import com.andrew.validation.AddressValidation;
import com.andrew.validation.ContactValidation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CreateContact implements Command {
    private Logger logger = Logger.getLogger(CreateContact.class);
    private ContactServiceImpl contactServiceImpl = new ContactServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            Contact contact = new ObjectMapper().readValue(request.getReader().readLine(), new TypeReference<Contact>() {
            });

            contactServiceImpl.setEmptyFieldsToNull(contact);
            List<String> errors = new ArrayList<>();
            errors.addAll(ContactValidation.validate(contact));
            errors.addAll(AddressValidation.validate(contact.getAddress()));
            if (ContactValidation.emailIsExists(contact.getEmail()))
                errors.add("Email " + contact.getEmail() + " already exists");
            if (PoolConnection.checkConnection()) {
                errors.add("Connection refused.");
            }
            if (errors.isEmpty()) {
                contactServiceImpl.createContact(contact);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.getWriter().write(new ObjectMapper().writeValueAsString(errors));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                logger.error(errors);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }


}
