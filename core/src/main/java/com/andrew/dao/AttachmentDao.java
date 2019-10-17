package com.andrew.dao;

import com.andrew.connection.PoolConnection;
import com.andrew.entity.AttachmentInfo;
import com.andrew.entity.Attachment;
import com.andrew.service.impl.AttachmentServiceImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AttachmentDao {

    private static Logger logger = Logger.getLogger(AttachmentDao.class);

    public static List<AttachmentInfo> getAllAttachmentsById(Integer id) {
        List<AttachmentInfo> attachmentInfos = new ArrayList<>();
        try (Connection connection = PoolConnection.getInstance().getBasicDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("select * from attachment where contact_id=" + id)) {
            while (result.next()) {
                Integer attachmentID = result.getInt(1);
                String state = result.getString(3);
                String fileName = result.getString(4);
                String loadedDate = result.getString(5);
                String comment = result.getString(6);
                AttachmentInfo attachmentInfo = new AttachmentInfo(attachmentID, id, state, fileName, loadedDate, comment);
                attachmentInfos.add(attachmentInfo);
            }

        } catch (Exception e) {
            logger.error("Get all attachments by id - " + id);
            logger.error(e);
        }
        return attachmentInfos;
    }

    public static String getFileNameByAttachmentId(Integer attachmentId) {
        String fileName = null;
        try (Connection connection = PoolConnection.getInstance().getBasicDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("select file_name from attachment " +
                     "where attachment_id=" + attachmentId)) {
            while (result.next()) {
                fileName = result.getString(1);
            }
        } catch (Exception e) {
            logger.error("Get file name by attachment id - " + attachmentId);
            logger.error(e);
        }
        return fileName;
    }

    public static Integer getContactIdByAttachmentId(Integer attachmentId) {
        Integer contactId = null;
        try (Connection connection = PoolConnection.getInstance().getBasicDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("select contact_id from attachment " +
                     "where attachment_id=" + attachmentId)) {
            while (result.next()) {
                contactId = result.getInt(1);
            }
        } catch (Exception e) {
            logger.error("Get contact by attachment id - " + attachmentId);
            logger.error(e);
        }
        return contactId;
    }

    public static void insert(Attachment attachment) {
        try (Connection connection = PoolConnection.getInstance().getBasicDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into attachment(contact_id, state, file_name, loaded_date, comment) " +
                             "values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, attachment.getFileInfo().getContactId());
            preparedStatement.setString(2, attachment.getFileInfo().getState());
            preparedStatement.setString(3, attachment.getFileInfo().getFileName());
            preparedStatement.setObject(4, attachment.getFileInfo().getLoadedDate());
            preparedStatement.setObject(5, attachment.getFileInfo().getComment());
            preparedStatement.executeUpdate();

            int autoId = 0;
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                autoId = resultSet.getInt(1);
            }

            new AttachmentServiceImpl().uploadAttachment(attachment, autoId);

        } catch (Exception e) {
            logger.error("Insert attachment - " + attachment.getFileInfo().getFileName() + " " + attachment.getFileInfo().getContactId());
            logger.error(e);
            e.printStackTrace();
        }
    }

    public static void update(AttachmentInfo attachmentInfo) {
        try (Connection connection = PoolConnection.getInstance().getBasicDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update attachment set " +
                     "file_name=?, comment=? where attachment_id=?")) {
            preparedStatement.setString(1, attachmentInfo.getFileName());
            preparedStatement.setObject(2, attachmentInfo.getComment());
            preparedStatement.setInt(3, attachmentInfo.getAttachmentId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("Update attachment - " + attachmentInfo.getFileName() + " with contact id - " + attachmentInfo.getContactId());
            logger.error(e);

        }
    }

    public static void delete(Integer attachmentId) {
        try (Connection connection = PoolConnection.getInstance().getBasicDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from attachment " +
                     "where attachment_id=?")) {
            preparedStatement.setInt(1, attachmentId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("Delete attachment - " + attachmentId);
            logger.error(e);
        }
    }
}
