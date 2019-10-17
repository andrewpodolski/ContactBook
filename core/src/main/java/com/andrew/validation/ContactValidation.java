package com.andrew.validation;

import com.andrew.dao.ContactDao;
import com.andrew.entity.Contact;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ContactValidation {
    public static List<String> validate(Contact contact) throws Exception {
        List<String> errors = new ArrayList<>();
        Integer id = contact.getId();
        String name = contact.getName();
        String surname = contact.getSurname();
        String patronymic = contact.getPatronymic();
        String birthday = contact.getBirthday();
        String nationality = contact.getNationality();
        String gender = contact.getGender();
        String maritalStatus = contact.getMaritalStatus();
        String webSite = contact.getWebSite();
        String email = contact.getEmail();
        String placeOfWork = contact.getPlaceOfWork();
        final String haveDigits = "[a-z-A-Z-а-я-А-Я]*";

        if (id == null)
            errors.add("ID can't be null");

        if (name == null)
            errors.add("Name can't be empty");
        else {
            if (name.length() < 1 || name.length() > 20)
                errors.add("Name must be less then 20 symbols!");
            if (!name.matches(haveDigits))
                errors.add("Name can't have digits and spaces");
        }

        if (surname == null)
            errors.add("Surname can't be null");
        else {
            if (surname.length() < 3 || surname.length() > 20)
                errors.add("Surname must be between (3; 15)");
            if (!surname.matches(haveDigits))
                errors.add("Surname can't have digits and spaces");
        }

        if (patronymic != null) {
            if (patronymic.length() > 20)
                errors.add("Max 20 symbols for patronymic");
            if (!patronymic.matches(haveDigits))
                errors.add("Patronymic can't have digits and spaces!");
        }

        if (birthday != null) {
            if (!birthday.matches("^\\d{4}\\-([1-9]|0[1-9]|1[012])\\-([1-9]|0[1-9]|[12][0-9]|3[01])$"))
                errors.add("Date format - YYYY-MM-DD!");
            else {
                Calendar calendar = Calendar.getInstance();
                calendar.setLenient(false);
                calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
                Date time = calendar.getTime();
                calendar.setTime(time);
                String[] arr = birthday.split("-");
                int year = Integer.parseInt(arr[0]);
                int month = Integer.parseInt(arr[1]);
                int day = Integer.parseInt(arr[2]);
                if (year != calendar.get(Calendar.YEAR) || month != calendar.get(Calendar.MONTH) + 1 ||
                        day != calendar.get(Calendar.DAY_OF_MONTH)) {
                    errors.add(birthday + " doesn't exist");
                }
            }
        }

        if (nationality != null) {
            if (nationality.length() > 30)
                errors.add("Max 30 for field nationality!");
            if (!nationality.matches(haveDigits))
                errors.add("Nationality can't have digits and spaces");
        }

        if (gender != null) {
            if (!gender.matches("male|female"))
                errors.add("Gender can be male or female");
        }

        if (maritalStatus != null) {
            if (!maritalStatus.matches("single|married"))
                errors.add("Marital status can be single or married");
        }

        if (webSite != null) {
            if (webSite.length() > 45)
                errors.add("Max 45 symbols for web site");
        }

        if(email == null) {
            errors.add("Email can't be empty");
        } else {
            if (email.length() > 45)
                errors.add("Max 45 for field  email");
            if (!email.matches("^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" +
                    "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
                    "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\]?)|(?:[a-zA-Z0-9-]+\\.)" +
                    "+(?:[a-zA-Z]){2,}\\.?)$"))
                errors.add("Incorrect email address");
        }

        if (placeOfWork != null) {
            if (placeOfWork.length() > 30)
                errors.add("Max 30 for field place of work");
        }

        return errors;
    }

    public static boolean emailIsExists(String email) {
        boolean isExists = false;
        if (email != null) {
            List<String> allEmail = ContactDao.getAllEmail();
            if (allEmail.contains(email))
                isExists = true;
        }
        return isExists;
    }

}
