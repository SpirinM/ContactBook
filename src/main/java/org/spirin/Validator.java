package org.spirin;

import java.util.InputMismatchException;

public class Validator {

    public static void contactValidator(Contact contact)
    {
        String phoneNumbers = "+*#1234567890";
        if (contact.getName().isBlank())
        {
            throw new IllegalArgumentException("Поле \"Имя\" не может быть пустым.");
        }
        if (contact.getPhone().isBlank())
        {
            throw new IllegalArgumentException("Поле \"Номер телефона\" не может быть пустым.");
        }
        for (int i = 0; i < contact.getPhone().length(); i++)
        {
            if (!(phoneNumbers.contains(String.valueOf(contact.getPhone().charAt(i)))))
            {
                throw new IllegalArgumentException("Номер телефона может содержать только цифры и спец.символы: '+', '*', '#'.");
            }
        }
    }
}
