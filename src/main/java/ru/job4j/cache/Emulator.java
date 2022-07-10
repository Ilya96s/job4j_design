package ru.job4j.cache;

import java.util.Scanner;

/**
 * Эмулятор для работы с пользователем. Предоставляет возможности:
 * - указать кэшируемую директорию
 * - загрузить содержимое файла в кэш
 * - получить содержимое файла из кэша
 *
 * @author Ilya Kaltygin
 */
public class Emulator {
    public static void main(String[] args) {
        boolean flag = true;
        DirFileCache dirFileCache = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите кэшируемую директорию:");
        String directory = scanner.nextLine();
        while (flag) {
            System.out.println("1. Загрузить данные файла в кэш");
            System.out.println("2. Получить данные файла");
            System.out.println("3. Выход из программы");
            switch (scanner.nextLine()) {
                case "1":
                    dirFileCache = new DirFileCache(directory);
                    System.out.println("Введите имя файла:");
                    String fileName = scanner.nextLine();
                    dirFileCache.load("\\" + fileName);
                    break;
                case "2":
                    System.out.println("Введите имя файла:");
                    String findNameFile = scanner.nextLine();
                    System.out.println(dirFileCache.get("\\" + findNameFile));
                    break;
                case "3":
                    flag = false;
                    break;
                default:
                    System.out.println("Неверная команда");
                    break;
            }
        }
    }
}
