package com.andrew.entity;

import java.util.Objects;

public class Contact {
    private Integer id;
    private String name;
    private String surname;
    private String patronymic;
    private String birthday;
    private String nationality;
    private String gender;
    private String maritalStatus;
    private String webSite;
    private String email;
    private String placeOfWork;
    private Address address;
    private String imageName;


    public Contact() {}

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Contact(Integer id, String name, String surname, String patronymic, String birthday,
                   String nationality, String gender, String maritalStatus, String webSite,
                   String email, String placeOfWork, Address address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.nationality = nationality;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.webSite = webSite;
        this.email = email;
        this.placeOfWork = placeOfWork;
        this.address = address;
    }

    public Contact(Integer id, String name, String surname, String patronymic, String birthday,
                   String nationality, String gender, String maritalStatus, String webSite,
                   String email, String placeOfWork, Address address, String imageName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.nationality = nationality;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.webSite = webSite;
        this.email = email;
        this.placeOfWork = placeOfWork;
        this.address = address;
        this.imageName = imageName;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id &&
                Objects.equals(name, contact.name) &&
                Objects.equals(surname, contact.surname) &&
                Objects.equals(patronymic, contact.patronymic) &&
                Objects.equals(birthday, contact.birthday) &&
                Objects.equals(nationality, contact.nationality) &&
                Objects.equals(gender, contact.gender) &&
                Objects.equals(maritalStatus, contact.maritalStatus) &&
                Objects.equals(webSite, contact.webSite) &&
                Objects.equals(email, contact.email) &&
                Objects.equals(placeOfWork, contact.placeOfWork) &&
                Objects.equals(address, contact.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, birthday, nationality, gender, maritalStatus, webSite, email, placeOfWork, address);
    }

    @Override
    public String toString() {
        return id + "," + name + "," + surname + "," + patronymic + "," + birthday + "," + nationality + "," +
                gender + "," + maritalStatus + "," + webSite + "," + email + "," + placeOfWork + "," + address;

    }
}