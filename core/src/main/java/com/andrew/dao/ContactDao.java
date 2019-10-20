package com.andrew.dao;

import com.andrew.connection.PoolConnection;
import com.andrew.entity.Address;
import com.andrew.entity.Contact;
import com.andrew.service.impl.ContactServiceImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {
    public static String searchQuery;
    private static Logger logger = Logger.getLogger(ContactDao.class);


    public static Integer countContacts() {
        int num = 0;
        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("select count(*) from contact")) {
            while (result.next()) {
                num = result.getInt(1);
            }
        } catch (Exception e) {
            logger.error("Counting contacts number - ");
            logger.error(e);
        }
        return num;
    }

    public static List<Contact> getAllContactsWithPagination(Integer page, Integer index) {
        List<Contact> contacts = new ArrayList<>();
        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("select * from contact left join " +
                     "address on id=contact_id order by id limit " + index * (page - 1) + ", " + index)) {
            while (result.next()) {
                Integer id = result.getInt(1);
                String name = result.getString(2);
                String surname = result.getString(3);
                String patronymic = result.getString(4);
                String birthday = result.getString(5);
                String nationality = result.getString(6);
                String gender = result.getString(7);
                String maritalStatus = result.getString(8);
                String webSite = result.getString(9);
                String email = result.getString(10);
                String placeOfWork = result.getString(11);
                String country = result.getString(13);
                String city = result.getString(14);
                String street = result.getString(15);
                String houseNumber = result.getString(16);
                String flatNumber = result.getString(17);
                String zipCode = result.getString(18);
                Address address = new Address(country, city, street, houseNumber, flatNumber, zipCode);
                Contact contact = new Contact(id, name, surname, patronymic, birthday, nationality, gender,
                        maritalStatus, webSite, email, placeOfWork, address);
                contacts.add(contact);
            }
        } catch (Exception e) {
            logger.error("Page - " + page + " index - " + index);
            logger.error(e);
            e.printStackTrace();
        }
        return contacts;
    }

    public static Contact getContactById(Integer id) {
        Contact contact = new Contact();
        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("select * from contact left join " +
                     "address on id=contact_id where id=" + id)) {
            while (result.next()) {
                contact.setId(id);
                contact.setName(result.getString(2));
                contact.setSurname(result.getString(3));
                contact.setPatronymic(result.getString(4));
                contact.setBirthday(result.getString(5));
                contact.setNationality(result.getString(6));
                contact.setGender(result.getString(7));
                contact.setMaritalStatus(result.getString(8));
                contact.setWebSite(result.getString(9));
                contact.setEmail(result.getString(10));
                contact.setPlaceOfWork(result.getString(11));
                String country = result.getString(13);
                String city = result.getString(14);
                String street = result.getString(15);
                String houseNumber = result.getString(16);
                String flatNumber = result.getString(17);
                String zipCode = result.getString(18);
                Address address = new Address(country, city, street, houseNumber, flatNumber, zipCode);
                contact.setAddress(address);
            }
        } catch (Exception e) {
            logger.error("Contact id - " + id);
            logger.error(e);
        }
        return contact;
    }

    public static void delete(Integer id) {
        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate("set FOREIGN_KEY_CHECKS = 0;");
            statement.executeUpdate("delete from contact where id=" + id);
            statement.executeUpdate("delete from address where contact_id=" + id);
            statement.executeUpdate("delete from phone where contact_id=" + id);
            statement.executeUpdate("delete from attachment where contact_id=" + id);
            connection.commit();
        } catch (Exception e) {
            logger.error("Contacts id's to delete - " + id);
            logger.error(e);
        }
    }

    public static void update(Contact contact) {
        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update contact set name=?, " +
                     "surname=?, patronymic=?, birthday=?, nationality=?, gender=?, marital_status=?, web_site=?, " +
                     "email=?, place_of_work=? where id=?");
             PreparedStatement preparedStatement2 = connection.prepareStatement("update address set country=?, " +
                     "city=?, street=?, house_number=?, flat_number=?, zip_code=? where contact_id=?")) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurname());
            preparedStatement.setObject(3, contact.getPatronymic());
            preparedStatement.setObject(4, contact.getBirthday());
            preparedStatement.setObject(5, contact.getNationality());
            preparedStatement.setObject(6, contact.getGender());
            preparedStatement.setObject(7, contact.getMaritalStatus());
            preparedStatement.setObject(8, contact.getWebSite());
            preparedStatement.setObject(9, contact.getEmail());
            preparedStatement.setObject(10, contact.getPlaceOfWork());
            preparedStatement.setInt(11, contact.getId());
            preparedStatement.executeUpdate();

            preparedStatement2.setObject(1, contact.getAddress().getCountry());
            preparedStatement2.setObject(2, contact.getAddress().getCity());
            preparedStatement2.setObject(3, contact.getAddress().getStreet());
            preparedStatement2.setObject(4, contact.getAddress().getHouseNumber());
            preparedStatement2.setObject(5, contact.getAddress().getFlatNumber());
            preparedStatement2.setObject(6, contact.getAddress().getZipCode());
            preparedStatement2.setObject(7, contact.getId());
            preparedStatement2.executeUpdate();

            connection.commit();
        } catch (Exception e) {
            logger.error("Updating contact with id - " + contact.getId() + " and email - " + contact.getEmail());
            logger.error(e);
        }
    }

    public static void insert(Contact contact) {
        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into contact(name,surname," +
                     "patronymic,birthday, nationality,gender,marital_status,web_site,email,place_of_work) " +
                     "values (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedStatement2 = connection.prepareStatement("insert into address values(" +
                     "?,?,?,?,?,?,?)")) {
            connection.setAutoCommit(false);
            statement.executeUpdate("set FOREIGN_KEY_CHECKS = 0;");
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurname());
            preparedStatement.setObject(3, contact.getPatronymic());
            preparedStatement.setObject(4, contact.getBirthday());
            preparedStatement.setObject(5, contact.getNationality());
            preparedStatement.setObject(6, contact.getGender());
            preparedStatement.setObject(7, contact.getMaritalStatus());
            preparedStatement.setObject(8, contact.getWebSite());
            preparedStatement.setObject(9, contact.getEmail());
            preparedStatement.setObject(10, contact.getPlaceOfWork());
            preparedStatement.executeUpdate();

            Integer autoId = 0;
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                autoId = resultSet.getInt(1);
            }

            preparedStatement2.setInt(1, autoId);
            preparedStatement2.setObject(2, contact.getAddress().getCountry());
            preparedStatement2.setObject(3, contact.getAddress().getCity());
            preparedStatement2.setObject(4, contact.getAddress().getStreet());
            preparedStatement2.setObject(5, contact.getAddress().getHouseNumber());
            preparedStatement2.setObject(6, contact.getAddress().getFlatNumber());
            preparedStatement2.setObject(7, contact.getAddress().getZipCode());
            preparedStatement2.executeUpdate();

            new ContactServiceImpl().updatePhoto(autoId, contact.getImageName());

            connection.commit();
        } catch (Exception e) {
            logger.error("Inserting contact with id - " + contact.getId() + " and email - " + contact.getEmail());
            logger.error(e);
        }
    }

    public static String getEmailById(Integer id) {
        String email = null;
        try (Connection connection = PoolConnection.getInstance().getBasicDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("select email from contact where id=" + id)) {
            while (result.next()) {
                email = result.getString(1);
            }
        } catch (Exception e) {
            logger.error("Get email by id - " + id);
            logger.error(e);
        }
        return email;
    }

    public static List<String> getAllEmail() {
        List<String> allEmail = new ArrayList<>();
        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("select email from contact")) {
            while (result.next()) {
                String email = result.getString(1);
                allEmail.add(email);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return allEmail;
    }

    public static Integer searchCount() {
        Integer counter = 0;
        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(ContactDao.searchQuery)) {
            while (result.next()) {
                counter++;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return counter;
    }

    public static List<Contact> searchContacts(Integer page, Integer index) {
        List<Contact> contacts = new ArrayList<>();
        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(ContactDao.searchQuery +
                     " order by id limit " + index * (page - 1) + ", " + index)) {
            while (result.next()) {
                Address address = new Address(result.getString(13), result.getString(14),
                        result.getString(15), result.getString(16),
                        result.getString(17), result.getString(18));
                Contact contact = new Contact(result.getInt(1), result.getString(2),
                        result.getString(3), result.getString(4), result.getString(5),
                        result.getString(6), result.getString(7), result.getString(8),
                        result.getString(9), result.getString(10),
                        result.getString(11), address);
                contacts.add(contact);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return contacts;
    }

    public static List<Contact> getAllContactsByDate(LocalDate date) {
        String[] fullDate = date.toString().split("-");
        String month = fullDate[1];
        String day = fullDate[2];
        List<Contact> contacts = new ArrayList<>();
        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("select name, surname, birthday from contact")) {
            while (result.next()) {
                String name = result.getString(1);
                String surname = result.getString(2);
                String birthday = result.getString(3);
                if (birthday != null) {
                    String[] array = birthday.split("-");
                    if (array[1].equals(month) && array[2].equals(day)) {
                        Contact contact = new Contact(name, surname);
                        contacts.add(contact);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return contacts;
    }
}