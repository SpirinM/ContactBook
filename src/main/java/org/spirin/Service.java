package org.spirin;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;

public class Service {

    private Set<Contact> contactList = new LinkedHashSet<>();
    private Map<String, Set<Contact>> contactGroups = new HashMap<>();
    private static int countContactsToRemove = 0;

    public void addContact(String name, String phone, String group)
    {
        if (group.isBlank() || group.equals("-"))
        {
            group = "-";
        }

        Contact contact = new Contact(name, phone, group);

        Validator.contactValidator(contact);
        if (!(contactList.add(contact)))
        {
            throw new IllegalArgumentException("Контакт с таким номером телефона уже есть.");
        }

        contactList.add(contact);

        if (!(group.equals("-")))
        {
            Set<Contact> contacts = contactGroups.get(group);
            if (contacts == null)
            {
                contacts = new HashSet<>();
                contactGroups.put(group, contacts);
            }
            contacts.add(contact);
        }
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
            return "Контакт \"" + name + "\" не найден.\n";
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

                    if (contactGroups.get(contact.getGroup()).size() == 1)
                    {
                        contactGroups.remove(contact.getGroup());
                    }
                    else {
                        Iterator<Contact> i = contactGroups.get(contact.getGroup()).iterator();
                        while (i.hasNext())
                        {
                            Contact contactFromGroup = i.next();
                            if (contactFromGroup.getPhone().equals(contact.getPhone()))
                            {
                                i.remove();
                            }
                        }
                    }
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

    public String getContactsByGroup(String group)
    {
        if (group.isBlank() || group.equals("-"))
        {
            return "Ошибка: Для поиска контактов нужно ввести название группы.\n";
        }

        if (contactGroups.isEmpty())
        {
            return "Группа с названием \"" + group + "\" отсутствует.\n";
        }

        Iterator<String> iterator = contactGroups.keySet().iterator();
        StringBuilder result = new StringBuilder();
        while (iterator.hasNext())
        {
            String groupKey = iterator.next();
            if (groupKey.equals(group))
            {
                Iterator<Contact> i = contactGroups.get(group).iterator();
                while(i.hasNext())
                {
                    Contact contactFromGroup = i.next();
                    result.append(contactFromGroup).append("\n");
                }
            }
        }
        if (result.isEmpty())
        {
            return "Группа с названием \"" + group + "\" отсутствует.\n";
        }
        return result.toString();
    }
}
