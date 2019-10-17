package com.andrew.entity;

import java.util.Objects;

public class AttachmentInfo {
    private Integer attachmentId;
    private Integer contactId;
    private String state;
    private String fileName;
    private String loadedDate;
    private String comment;

    public AttachmentInfo() {}

    public AttachmentInfo(Integer attachmentId, Integer contactId, String state, String fileName, String loadedDate, String comment) {
        this.attachmentId = attachmentId;
        this.contactId = contactId;
        this.state = state;
        this.fileName = fileName;
        this.loadedDate = loadedDate;
        this.comment = comment;
    }

    public AttachmentInfo(Integer contactId, String state, String fileName, String loadedDate, String comment) {
        this.contactId = contactId;
        this.state = state;
        this.fileName = fileName;
        this.loadedDate = loadedDate;
        this.comment = comment;
    }

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLoadedDate() {
        return loadedDate;
    }

    public void setLoadedDate(String loadedDate) {
        this.loadedDate = loadedDate;
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
        AttachmentInfo that = (AttachmentInfo) o;
        return Objects.equals(attachmentId, that.attachmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attachmentId, contactId, state, fileName, loadedDate, comment);
    }

    @Override
    public String toString() {
        return "(" + attachmentId + "; " + state + "; " + fileName + ")";
    }
}
