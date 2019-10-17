package com.andrew.service;

import com.andrew.entity.Contact;

import java.util.ArrayList;

public interface ContactService {
    String getJsonCount() throws Exception;

    String getAllJsonContacts(Integer page, Integer index) throws Exception;

    String getJsonContactById(Integer id) throws Exception;

    void deleteContacts(ArrayList<Integer> list);

    void createContact(Contact contact);

    void updateContact(Contact contact);

    void updatePhoto(Integer id, String photo) throws Exception;

    String getJsonByteArrayPhoto(Integer id) throws Exception;

    String defaultJsonByteArrayPhoto() throws Exception;

    String getJsonSearchContacts(Integer page, Integer index) throws Exception;

    String getJsonSearchCount() throws Exception;

    void setEmptyFieldsToNull(Contact contact);
}
