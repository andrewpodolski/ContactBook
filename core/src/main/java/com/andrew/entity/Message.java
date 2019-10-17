package com.andrew.entity;

import java.util.Objects;

public class Message {
    private String recipients;
    private String subject;
    private String template;
    private String text;

    public Message() {}

    public Message(String recipients, String subject, String template, String text) {
        this.recipients = recipients;
        this.subject = subject;
        this.template = template;
        this.text = text;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(recipients, message.recipients) &&
                Objects.equals(subject, message.subject) &&

                Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipients, subject, text);
    }

    @Override
    public String toString() {
        return "Message{" +
                "recipients='" + recipients + '\'' +
                ", subject='" + subject + '\'' +

                ", text='" + text + '\'' +
                '}';
    }
}
