package org.spirin;

import java.util.Objects;

public class Contact {

    private String name;
    private String phone;
    private String group;

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Contact contact = (Contact) obj;

        return Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(phone);
    }

    @Override
    public String toString()
    {
        return "| Имя: " + name + " | Телефон: " + phone + " | Группа: " + group + " |";
    }
}
