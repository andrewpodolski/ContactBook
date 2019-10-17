package com.andrew.commands.impl;

import com.andrew.commands.Command;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.andrew.service.impl.AttachmentServiceImpl;
import com.andrew.service.impl.ContactServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

public class DeleteContacts implements Command {
    private Logger logger = Logger.getLogger(DeleteContacts.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<Integer> contactsId = new ObjectMapper().readValue
                    (request.getReader().readLine(), new TypeReference<ArrayList<Integer>>() {
                    });
            new ContactServiceImpl().deleteContacts(contactsId);
            AttachmentServiceImpl attachmentServiceImpl = new AttachmentServiceImpl();
            for (Integer id : contactsId) {
                attachmentServiceImpl.deleteContactsFolder(id);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
}