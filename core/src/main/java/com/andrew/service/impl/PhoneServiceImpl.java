package com.andrew.service.impl;

import com.andrew.dao.PhoneDao;
import com.andrew.entity.Phone;
import com.andrew.service.PhoneService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneServiceImpl implements PhoneService {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getJsonPhonesById(Integer id) throws JsonProcessingException {
        List<Phone> allPhones = PhoneDao.getAllPhonesById(id);
        for (Phone phone : allPhones) {
            if (phone.getCountryCode() == 0)
                phone.setCountryCode(null);
            if (phone.getOperatorCode() == 0)
                phone.setOperatorCode(null);
        }
        return mapper.writeValueAsString(allPhones);
    }

    @Override
    public List<String> savingPhones(List<Phone> allPhones) {
        List<String> alreadyExistsPhones = new ArrayList<>();
        if (!allPhones.isEmpty()) {
            PhoneDao.delete(allPhones.get(0).getContactId());
            for (Phone phone : allPhones) {
                boolean isExists = PhoneDao.isExist(phone.getPhoneNumber());
                if (!isExists) {
                    PhoneDao.insert(phone);
                } else {
                    alreadyExistsPhones.add("This phone : " + phone.getPhoneNumber().toString() + " is already exists!");
                }
            }
        }
        return alreadyExistsPhones;
    }
}
