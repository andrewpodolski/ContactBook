package com.andrew.validation;

import com.andrew.entity.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneValidation {
    public static List<String> validatePhone(Phone phone) {
        List<String> errors = new ArrayList<>();
        String countryCode = String.valueOf(phone.getCountryCode());
        String operatorCode = String.valueOf(phone.getOperatorCode());
        String phoneNumber = String.valueOf(phone.getPhoneNumber());
        String comment = phone.getComment();

        if (countryCode.length() > 5) {
            errors.add("Country code can't be more then 5 digits");
        }
        if (operatorCode.length() > 5) {
            errors.add("Operator code can't be more then 5 digits");
        }
        if (phoneNumber.length() > 10) {
            errors.add("Phone number can't be more then 10 digits");
        }
        if (comment.length() > 45) {
            errors.add("Comment can't be more then 45 symbols");
        }
        return errors;
    }
}
