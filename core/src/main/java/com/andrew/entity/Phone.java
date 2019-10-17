package com.andrew.entity;

import java.util.Objects;

public class Phone {
    private Integer contactId;
    private Integer countryCode;
    private Integer operatorCode;
    private Long phoneNumber;
    private String type;
    private String comment;

    public Phone() {}

    public Phone(Integer contactId, Integer countryCode, Integer operatorCode, Long phoneNumber, String type, String comment) {
        this.contactId = contactId;
        this.countryCode = countryCode;
        this.operatorCode = operatorCode;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.comment = comment;
    }
    public Phone(Integer countryCode,Integer operatorCode,Long phoneNumber,String type,String comment){
        this.countryCode = countryCode;
        this.operatorCode = operatorCode;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.comment = comment;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(Integer operatorCode) {
        this.operatorCode = operatorCode;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(contactId, phone.contactId) &&
                Objects.equals(phoneNumber, phone.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, countryCode, operatorCode, phoneNumber, type, comment);
    }

    @Override
    public String toString() {
        return "(" + contactId + "; " + phoneNumber + ")";
    }
}
