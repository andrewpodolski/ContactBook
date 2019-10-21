package com.andrew.service;

import com.andrew.entity.AttachmentInfo;
import com.andrew.entity.Attachment;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface AttachmentService {
    String getPath(Integer id, String fileName) throws IOException;

    HashMap<Integer, String> getContactIdAndFileName(Integer attachmentId);

    void deleteContactsFolder(Integer id) throws IOException;

    List<AttachmentInfo> getJsonAttachmentsById(Integer id) throws JsonProcessingException;

    void createAttachment(Attachment attachment);

    void uploadAttachment(Attachment attachment, Integer attachmentId) throws IOException;

    void updateAttachment(AttachmentInfo attachmentInfo);

    void deleteAttachments(Integer attachmentId);

    void deleteAttachmentsFromFolder(Integer attachmentId) throws IOException;

}
