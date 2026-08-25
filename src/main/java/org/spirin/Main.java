package org.spirin;

import java.util.Scanner;

public class Main {
    static void main() {

        int option;
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("--- ContactBook ---\n" +
                    "1. Добавить контакт\n" +
                    "2. Удалить контакт\n" +
                    "3. Посмотреть все контакты\n" +
                    "4. Найти контакт\n" +
                    "5. Посмотреть все контакты по группе\n" +
                    "6. Выход\n");

            option = scanner.nextInt();
            scanner.nextLine();
        }
    }
}
