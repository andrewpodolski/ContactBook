package com.andrew.commands.impl;

import com.andrew.commands.Command;
import com.andrew.connection.PoolConnection;
import com.andrew.entity.Address;
import com.andrew.entity.AttachmentInfo;
import com.andrew.entity.Contact;
import com.andrew.entity.Attachment;
import com.andrew.entity.Phone;
import com.andrew.service.impl.AttachmentServiceImpl;
import com.andrew.service.impl.ContactServiceImpl;
import com.andrew.service.impl.PhoneServiceImpl;
import com.andrew.validation.AddressValidation;
import com.andrew.validation.ContactValidation;
import com.andrew.validation.HasNumberValidation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@MultipartConfig
public class EditContact implements Command {
    private Logger logger = Logger.getLogger(EditContact.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ContactServiceImpl contactServiceImpl = new ContactServiceImpl();
            AttachmentServiceImpl attachmentServiceImpl = new AttachmentServiceImpl();
            List<String> errors = new ArrayList<>();

            int maxFileSize = 5000 * 1024;
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(maxFileSize);
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            upload.setSizeMax(maxFileSize);

            List<FileItem> items = upload.parseRequest(request);
            Map<String, String> allParams = new LinkedHashMap<>();

            for (FileItem item : items) {
                if (item.isFormField()) {
                    allParams.put(item.getFieldName(), item.getString("UTF-8"));
                } else {
                    String attachmentInfo = URLDecoder.decode(item.getFieldName(), "UTF-8");
                    AttachmentInfo attachment = new ObjectMapper().readValue
                            (attachmentInfo, new TypeReference<AttachmentInfo>() {
                            });
                    Attachment pair = new Attachment(attachment, item.getInputStream());
                    new AttachmentServiceImpl().createAttachment(pair);
                }
            }


            if (!HasNumberValidation.isNumber(allParams.get("id"))) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                Integer id = Integer.parseInt(allParams.get("id"));
                String name = allParams.get("name");
                String surname = allParams.get("surname");
                String patronymic = allParams.get("patronymic");
                String birthday = allParams.get("year") + "-" + allParams.get("month") + "-" + allParams.get("day");
                String nationality = allParams.get("nationality");
                String gender = allParams.get("gender");
                String maritalStatus = allParams.get("maritalStatus");
                String webSite = allParams.get("webSite");
                String email = allParams.get("email").toLowerCase();
                String placeOfWork = allParams.get("placeOfWork");
                String country = allParams.get("country");
                String city = allParams.get("city");
                String street = allParams.get("street");
                String houseNumber = allParams.get("houseNumber");
                String flatNumber = allParams.get("flatNumber");
                String zipCode = allParams.get("zipCode");
                Address address = new Address(country, city, street, houseNumber, flatNumber, zipCode);
                Contact contact = new Contact(id, name, surname, patronymic, birthday, nationality, gender,
                        maritalStatus, webSite, email, placeOfWork, address);
                contactServiceImpl.setEmptyFieldsToNull(contact);

                errors.addAll(ContactValidation.validate(contact));
                errors.addAll(AddressValidation.validate(address));

                String jsonPhones = allParams.get("allPhones");
                ArrayList<Phone> allPhones = objectMapper.readValue(jsonPhones, new TypeReference<ArrayList<Phone>>() {
                });
                List<String> phonesErrors = new ArrayList<>(new PhoneServiceImpl().savingPhones(allPhones));

                String photo = allParams.get("photo");
                contactServiceImpl.updatePhoto(id, photo);

                String jsonAttachments = allParams.get("allAttachments");
                ArrayList<AttachmentInfo> allAttachmentInfos = new ObjectMapper().readValue(jsonAttachments, new TypeReference<ArrayList<AttachmentInfo>>() {
                });
                for (AttachmentInfo attachmentInfo : allAttachmentInfos) {
                    if (attachmentInfo.getState().equals("edit")) {
                        attachmentServiceImpl.updateAttachment(attachmentInfo);
                    }
                    if (attachmentInfo.getState().equals("deleted")) {
                        attachmentServiceImpl.deleteAttachmentsFromFolder(attachmentInfo.getAttachmentId());
                        attachmentServiceImpl.deleteAttachments(attachmentInfo.getAttachmentId());
                    }
                }
                if (!PoolConnection.isConnectionExists()) {
                    errors.add("Connection refused.");
                }

                if (errors.isEmpty() && phonesErrors.isEmpty()) {
                    contactServiceImpl.updateContact(contact);
                    response.setStatus(HttpServletResponse.SC_OK);
                }
                if (!errors.isEmpty() && !phonesErrors.isEmpty()) {
                    errors.addAll(phonesErrors);
                    response.getWriter().write(objectMapper.writeValueAsString(errors));
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                } else if (errors.isEmpty() && !phonesErrors.isEmpty()) {
                    contactServiceImpl.updateContact(contact);
                    response.getWriter().write(objectMapper.writeValueAsString(phonesErrors));
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
                if (!errors.isEmpty() && phonesErrors.isEmpty()) {
                    response.getWriter().write(objectMapper.writeValueAsString(errors));
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            }

        } catch (Exception e) {
            logger.error(e);
        }
    }

}