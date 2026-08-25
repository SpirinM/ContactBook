package org.spirin;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static void main() {

        int option = 0;
        Scanner scanner = new Scanner(System.in);
        Service service = new Service();

        while (true)
        {
            System.out.println("--- ContactBook ---\n" +
                    "1. Добавить контакт\n" +
                    "2. Удалить контакт\n" +
                    "3. Посмотреть все контакты\n" +
                    "4. Найти контакт\n" +
                    "5. Посмотреть все контакты по группе\n" +
                    "6. Выход\n");

            try
            {
                option = scanner.nextInt();
                scanner.nextLine();
                if (option < 1 || option > 6)
                {
                    throw new IllegalArgumentException("Ошибка: Введите число от 1 до 6\n");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Ошибка: Введите число от 1 до 6\n");
                scanner.nextLine();
            }
            catch (IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }

            if (option == 1)
            {
                System.out.println("Введите данные контакта:");
                System.out.print("Имя: ");
                String name = scanner.nextLine();
                System.out.print("Номер телефона: ");
                String phone = scanner.nextLine();
                System.out.print("Группа: ");
                String group = scanner.nextLine();

                try
                {
                    service.addContact(name, phone, group);
                    System.out.println("Контакт добавлен\n");
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println(e.getMessage() + "\n");
                }
            }
        }
    }
}
