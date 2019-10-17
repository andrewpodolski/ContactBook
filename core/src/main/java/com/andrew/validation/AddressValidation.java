package com.andrew.validation;

import com.andrew.entity.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressValidation {
    public static List<String> validate(Address address) {
        List<String> errors = new ArrayList<>();
        String country = address.getCountry();
        String city = address.getCity();
        String street = address.getStreet();
        String houseNumber = address.getHouseNumber();
        String flatNumber = address.getFlatNumber();
        String zipCode = address.getZipCode();
        final String haveDigits = "[a-z-A-Z-а-я-А-Я]*";

        if (country != null) {
            if (country.length() > 30)
                errors.add("Max 30 for field country!");
            if (!country.matches(haveDigits))
                errors.add("Country can't have digits and spaces");
        }

        if (city != null) {
            if (city.length() > 30)
                errors.add("Max 30 for field city!");
            if (!city.matches(haveDigits))
                errors.add("City can't have digits and spaces");
        }

        if (street != null) {
            if (street.length() > 30)
                errors.add("Max 30 for field street!");
        }

        if (houseNumber != null) {
            if (houseNumber.length() > 10)
                errors.add("Max 10 for field house number!");
        }

        if (flatNumber != null) {
            if (flatNumber.length() > 10)
                errors.add("Max 10 for field flat number!");
        }

        if (zipCode != null) {
            if (zipCode.length() > 10)
                errors.add("Max 10 for field zipcode!");
        }

        return errors;
    }
}
