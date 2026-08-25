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
    private static int countContactsToRemove = 0;

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
        countContactsToRemove = 0;
        while (iterator.hasNext())
        {
            Contact contact = iterator.next();
            if(contact.getName().equals(name))
            {
                countContactsToRemove++;
                result.append(countContactsToRemove).append(". ");
                result.append(contact).append("\n");
            }
        }
        if (countContactsToRemove == 0)
        {
            return null;
        }
        return result.toString();
    }

    public void contactRemove(int removeNumber, String name)
    {
        if (removeNumber < 0 || removeNumber > countContactsToRemove)
        {
            throw new IllegalArgumentException("Ошибка: Введите число от 0 до " + countContactsToRemove + ".\n");
        }

        Iterator<Contact> iterator = contactList.iterator();
        int count = 0;
        while (iterator.hasNext())
        {
            Contact contact = iterator.next();
            if(contact.getName().equals(name))
            {
                count++;
                if (count == removeNumber)
                {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    public String getAllContacts()
    {
        if (contactList.isEmpty())
        {
            return "Список контактов пуст\n";
        }

        Iterator<Contact> iterator = contactList.iterator();
        StringBuilder result = new StringBuilder();
        while (iterator.hasNext())
        {
            Contact contact = iterator.next();
            result.append(contact).append("\n");
        }
        return result.toString();
    }
}
