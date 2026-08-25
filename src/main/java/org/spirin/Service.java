package org.spirin;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;

public class Service {

    private Set<Contact> contactList = new LinkedHashSet<>();
    private Map<String, List<Contact>> contactGroups = new HashMap<>();

    public void addContact(String name, String phone, String group)
    {
        Contact contact = new Contact(name, phone, group);

        Validator.contactValidator(contact);
        if (!(contactList.add(contact)))
        {
            throw new IllegalArgumentException("Такой контакт уже есть.");
        }

        contactList.add(contact);

        List<Contact> contacts = contactGroups.get(group);
        if (contacts == null)
        {
            contacts = new ArrayList<>();
            contactGroups.put(group, contacts);
        }
        contacts.add(contact);
    }

    public String searchContactByName(String name)
    {
        Iterator<Contact> iterator = contactList.iterator();
        StringBuilder result = new StringBuilder();
        int count = 0;
        while (iterator.hasNext())
        {
            Contact contact = iterator.next();
            if(contact.getName().equals(name))
            {
                count++;
                result.append(count).append(". ");
                result.append(contact).append("\n");
            }
        }
        if (count == 0)
        {
            return null;
        }
        return result.toString();
    }
}
