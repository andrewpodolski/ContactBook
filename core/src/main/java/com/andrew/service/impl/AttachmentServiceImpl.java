package com.andrew.service.impl;

import com.andrew.dao.AttachmentDao;
import com.andrew.entity.AttachmentInfo;
import com.andrew.entity.Attachment;
import com.andrew.service.AttachmentService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachmentServiceImpl implements AttachmentService {
    private ObjectMapper mapper = new ObjectMapper();
    private Properties properties = new Properties();
    private InputStream inputStream = AttachmentServiceImpl.class.getClassLoader().getResourceAsStream("path.properties");

    @Override
    public String getPath(Integer id, String fileName) throws IOException {
        properties.load(inputStream);
        return properties.getProperty("attachments.path") + "contact_" + id + File.separator + fileName;
    }

    @Override
    public HashMap<Integer, String> getContactIdAndFileName(Integer attachmentId) {
        HashMap<Integer, String> pair = new HashMap<>();
        pair.put(AttachmentDao.getContactIdByAttachmentId(attachmentId), AttachmentDao.getFileNameByAttachmentId(attachmentId));
        return pair;
    }

    @Override
    public void deleteContactsFolder(Integer id) throws IOException {
        properties.load(inputStream);
        String fullPath = properties.getProperty("attachments.path") + "contact_" + id;
        File delete = new File(fullPath);
        if (delete.exists()) {
            FileUtils.deleteDirectory(delete);
        }
    }

    @Override
    public String getJsonAttachmentsById(Integer id) throws JsonProcessingException {
        List<AttachmentInfo> allAttachmentInfos = AttachmentDao.getAllAttachmentsById(id);
        return mapper.writeValueAsString(allAttachmentInfos);
    }

    @Override
    public void createAttachment(Attachment attachment) {
        attachment.getFileInfo().setState("old");
        AttachmentDao.insert(attachment);
    }

    @Override
    public void uploadAttachment(Attachment attachment, Integer attachmentId) throws IOException {
        properties.load(inputStream);
        String pathForAttachments = properties.getProperty("attachments.path");
        String contactFolder = "contact_" + attachment.getFileInfo().getContactId() + File.separator;
        Path folder = Paths.get(pathForAttachments + contactFolder);
        if (!Files.exists(folder)) {
            Files.createDirectories(folder);
        }
        String extension = attachment.getFileInfo().getFileName().split(File.separator + ".")[0];

        String fullPath = pathForAttachments + contactFolder + attachmentId + "." + extension;
        Files.copy(attachment.getStream(), Paths.get(fullPath));
        File file = new File(fullPath);
        file.createNewFile();
    }

    @Override
    public void updateAttachment(AttachmentInfo attachmentInfo) {
        attachmentInfo.setState("old");
        AttachmentDao.update(attachmentInfo);
    }

    @Override
    public void deleteAttachments(Integer attachmentId) {
        AttachmentDao.delete(attachmentId);
    }

    @Override
    public void deleteAttachmentsFromFolder(Integer attachmentId) throws IOException {
        properties.load(inputStream);
        String pathForAttachments = properties.getProperty("attachments.path");
        String contactFolder = "contact_" + AttachmentDao.getContactIdByAttachmentId(attachmentId) + File.separator;
        String extension = AttachmentDao.getFileNameByAttachmentId(attachmentId).split(File.separator + ".")[0];
        File file = new File(pathForAttachments + contactFolder + attachmentId + "." + extension);
        if (file.exists()) {
            file.delete();
        }
    }
}
