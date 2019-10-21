package com.andrew.service;

import com.andrew.entity.Contact;

import java.util.ArrayList;
import java.util.List;

public interface ContactService {
    Integer getJsonCount() throws Exception;

    List<Contact> getAllJsonContacts(Integer page, Integer index) throws Exception;

    Contact getJsonContactById(Integer id) throws Exception;

    void deleteContacts(ArrayList<Integer> list);

    void createContact(Contact contact);

    void updateContact(Contact contact);

    void updatePhoto(Integer id, String photo) throws Exception;

    String getJsonByteArrayPhoto(Integer id) throws Exception;

    String defaultJsonByteArrayPhoto() throws Exception;

    List<Contact> getJsonSearchContacts(Integer page, Integer index) throws Exception;

    Integer getJsonSearchCount() throws Exception;

    void setEmptyFieldsToNull(Contact contact);
}
