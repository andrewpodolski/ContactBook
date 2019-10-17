package com.andrew.service;

import com.andrew.entity.Phone;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface PhoneService {
    String getJsonPhonesById(Integer id) throws JsonProcessingException;

    List<String> savingPhones(List<Phone> allPhones);
}
