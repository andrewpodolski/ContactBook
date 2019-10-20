package com.andrew.dao;

import com.andrew.connection.PoolConnection;
import com.andrew.entity.Phone;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PhoneDao {
    private static Logger logger = Logger.getLogger(PhoneDao.class);


    public static List<Phone> getAllPhonesById(Integer id) {
        List<Phone> phones = new ArrayList<>();
        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("select * from phone where contact_id=" + id)) {
            while (result.next()) {
                Integer countryCode = result.getInt(2);
                Integer operatorCode = result.getInt(3);
                Long phoneNumber = result.getLong(4);
                String type = result.getString(5);
                String comment = result.getString(6);
                Phone phone = new Phone(id, countryCode, operatorCode, phoneNumber, type, comment);
                phones.add(phone);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return phones;
    }

    public static void delete(Integer id) {
        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("delete from phone where contact_id=" + id);
        } catch (Exception e) {
            logger.error("Deleting phone with id - " + id);
            logger.error(e);
        }
    }

    public static void insert(Phone phone) {
        try (Connection connection = PoolConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into phone values(" +
                     "?,?,?,?,?,?)")) {
            preparedStatement.setInt(1, phone.getContactId());
            preparedStatement.setObject(2, phone.getCountryCode());
            preparedStatement.setObject(3, phone.getOperatorCode());
            preparedStatement.setLong(4, phone.getPhoneNumber());
            preparedStatement.setObject(5, phone.getType());
            preparedStatement.setObject(6, phone.getComment());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("Inserting phone" + phone.getCountryCode() + phone.getOperatorCode() + phone.getPhoneNumber());
            logger.error(e);
        }
    }

    public static boolean isExist(Long phoneNumber) {
        boolean b = false;
        try (Connection connection = PoolConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.
                     executeQuery("select count(1) from phone where phone_number=" + phoneNumber)) {
            while (result.next()) {
                b = result.getInt(1) == 1;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return b;
    }
}
