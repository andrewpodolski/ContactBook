package com.andrew.validation;

public class HasNumberValidation {
    public static boolean isNumber(String value) {
        return value.matches("^[0-9]+$");
    }
}
