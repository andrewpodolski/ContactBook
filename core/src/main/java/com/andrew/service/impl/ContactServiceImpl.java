package com.andrew.service.impl;

import com.andrew.dao.ContactDao;
import com.andrew.entity.Address;
import com.andrew.entity.Contact;
import com.andrew.service.ContactService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactServiceImpl implements ContactService {
    private Properties properties = new Properties();
    private InputStream inputStream = ContactServiceImpl.class.getClassLoader().getResourceAsStream("path.properties");

    private String pathToImages() {
        return properties.getProperty("images.path");
    }

    private String pathToDefaultImage() {
        return properties.getProperty("default.image.path");
    }

    @Override
    public Integer getJsonCount() {
        return ContactDao.countContacts();
    }

    @Override
    public List<Contact> getAllJsonContacts(Integer page, Integer index) {
        return ContactDao.getAllContactsWithPagination(page, index);
    }

    @Override
    public Contact getJsonContactById(Integer id) {
        Contact contact = ContactDao.getContactById(id);
        return contact;
    }

    @Override
    public void deleteContacts(ArrayList<Integer> list) {
        for (Integer id : list) {
            ContactDao.delete(id);
        }
    }

    @Override
    public void createContact(Contact contact) {
        ContactDao.insert(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        ContactDao.update(contact);
    }

    @Override
    public void updatePhoto(Integer id, String photo) throws Exception {
        String base64Image = photo.split(",")[1];
        byte[] bytesArray = Base64.getDecoder().decode(base64Image);
        properties.load(inputStream);
        String path = pathToImages();
        String imageName = id + ".png";
        FileOutputStream stream = new FileOutputStream(new File(path + imageName));
        stream.write(bytesArray);
        stream.close();
    }

    @Override
    public String getJsonByteArrayPhoto(Integer id) throws Exception {
        properties.load(inputStream);
        String path = pathToImages();
        File file = new File(path + id + ".png");
        FileInputStream stream = new FileInputStream(file);
        byte[] bytesArray = new byte[(int) file.length()];
        stream.read(bytesArray);
        stream.close();
        return Base64.getEncoder().encodeToString(bytesArray);
    }

    @Override
    public String defaultJsonByteArrayPhoto() throws Exception {
        properties.load(inputStream);
        String path = pathToDefaultImage();
        File file = new File(path + "default.png");
        FileInputStream stream = new FileInputStream(file);
        byte[] bytesArray = new byte[(int) file.length()];
        stream.read(bytesArray);
        stream.close();
        return Base64.getEncoder().encodeToString(bytesArray);
    }

    @Override
    public List<Contact> getJsonSearchContacts(Integer page, Integer index) {
        List<Contact> contacts = ContactDao.searchContacts(page, index);
        return contacts;
    }

    @Override
    public Integer getJsonSearchCount() {
        return ContactDao.searchCount();
    }

    @Override
    public void setEmptyFieldsToNull(Contact contact) {
        if (contact.getPatronymic().equals(""))
            contact.setPatronymic(null);
        if (contact.getBirthday().equals("year-month-day"))
            contact.setBirthday(null);
        if (contact.getNationality().equals(""))
            contact.setNationality(null);
        if (contact.getWebSite().equals(""))
            contact.setWebSite(null);
        if (contact.getEmail().equals(""))
            contact.setEmail(null);
        if (contact.getGender().equals("")) {
            contact.setGender(null);
        }
        if (contact.getMaritalStatus().equals("")) {
            contact.setMaritalStatus(null);
        }
        if (contact.getPlaceOfWork().equals(""))
            contact.setPlaceOfWork(null);
        Address address = contact.getAddress();
        if (address.getCountry().equals(""))
            address.setCountry(null);
        if (address.getCity().equals(""))
            address.setCity(null);
        if (address.getStreet().equals(""))
            address.setStreet(null);
        if (address.getHouseNumber().equals(""))
            address.setHouseNumber(null);
        if (address.getFlatNumber().equals(""))
            address.setFlatNumber(null);
        if (address.getZipCode().equals(""))
            address.setZipCode(null);
        contact.setAddress(address);
    }
}
