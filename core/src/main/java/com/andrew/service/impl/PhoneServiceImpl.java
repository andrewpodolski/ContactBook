package com.andrew.service.impl;

import com.andrew.dao.PhoneDao;
import com.andrew.entity.Phone;
import com.andrew.service.PhoneService;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneServiceImpl implements PhoneService {

    @Override
    public List<Phone> getJsonPhonesById(Integer id) {
        List<Phone> allPhones = PhoneDao.getAllPhonesById(id);
        for (Phone phone : allPhones) {
            if (phone.getCountryCode() == 0)
                phone.setCountryCode(null);
            if (phone.getOperatorCode() == 0)
                phone.setOperatorCode(null);
        }
        return allPhones;
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
