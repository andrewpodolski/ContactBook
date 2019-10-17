package com.andrew.entity;

import java.io.InputStream;
import java.util.Objects;

public class Attachment {
    private AttachmentInfo fileInfo;
    private InputStream stream;

    public Attachment() {
    }

    public Attachment(AttachmentInfo fileInfo, InputStream stream) {
        this.fileInfo = fileInfo;
        this.stream = stream;
    }

    public AttachmentInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(AttachmentInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment attachment = (Attachment) o;
        return Objects.equals(fileInfo, attachment.fileInfo) &&
                Objects.equals(stream, attachment.stream);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileInfo, stream);
    }

    @Override
    public String toString() {
        return fileInfo.toString();
    }
}
