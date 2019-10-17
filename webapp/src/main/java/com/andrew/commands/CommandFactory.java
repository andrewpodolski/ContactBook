package com.andrew.commands;

import com.andrew.commands.impl.ContactImage;
import com.andrew.commands.impl.ContactInfo;
import com.andrew.commands.impl.ContactList;
import com.andrew.commands.impl.ContactsAttachments;
import com.andrew.commands.impl.ContactsPhones;
import com.andrew.commands.impl.CountContacts;
import com.andrew.commands.impl.CreateContact;
import com.andrew.commands.impl.DefaultImage;
import com.andrew.commands.impl.DeleteContacts;
import com.andrew.commands.impl.DownloadAttachment;
import com.andrew.commands.impl.EditContact;
import com.andrew.commands.impl.Search;
import com.andrew.commands.impl.SearchResult;
import com.andrew.commands.impl.SendEmail;

import java.util.EnumMap;
import java.util.Map;

public class CommandFactory {
    private final Map<CommandType, Command> commandMap = new EnumMap<>(CommandType.class);

    public CommandFactory() {
        commandMap.put(CommandType.CONTACT_LIST, new ContactList());
        commandMap.put(CommandType.DELETE_CONTACTS, new DeleteContacts());
        commandMap.put(CommandType.EDIT_CONTACT, new EditContact());
        commandMap.put(CommandType.CREATE_CONTACT, new CreateContact());
        commandMap.put(CommandType.CONTACT_INFO, new ContactInfo());
        commandMap.put(CommandType.CONTACTS_PHONES, new ContactsPhones());
        commandMap.put(CommandType.SEARCH, new Search());
        commandMap.put(CommandType.CONTACT_IMAGE, new ContactImage());
        commandMap.put(CommandType.SEND_EMAIL, new SendEmail());
        commandMap.put(CommandType.CONTACTS_ATTACHMENTS, new ContactsAttachments());
        commandMap.put(CommandType.DOWNLOAD_ATTACHMENT, new DownloadAttachment());
        commandMap.put(CommandType.COUNT_CONTACTS, new CountContacts());
        commandMap.put(CommandType.DEFAULT_IMAGE, new DefaultImage());
        commandMap.put(CommandType.SEARCH_RESULT, new SearchResult());
    }

    public Command createCommand(String commandName) {
        return commandMap.get(CommandType.valueOf(commandName.toUpperCase()));
    }
}
