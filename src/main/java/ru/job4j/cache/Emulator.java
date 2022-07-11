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

    public static final String INCORRECT_COMM = "Неверная команда";
    public static final String ENTER_FILENAME = "Введите имя файла:";
    public static final String ENTER_DIR = "Укажите кэшируемую директорию:";
    public static final String LOAD_DATA = "1";
    public static final String GET_DATA = "2";
    public static final String EXIT = "3";
    public static final String MENU = "1. Загрузить данные файла в кэш \n"
                                    + "2. Получить данные файла \n"
                                    + "3. Выход из программы";

    public static void main(String[] args) {
        boolean flag = true;
        DirFileCache dirFileCache = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println(ENTER_DIR);
        String directory = scanner.nextLine();
        while (flag) {
            System.out.println(MENU);
            switch (scanner.nextLine()) {
                case LOAD_DATA:
                    dirFileCache = new DirFileCache(directory);
                    System.out.println(ENTER_FILENAME);
                    String fileName = scanner.nextLine();
                    dirFileCache.get(fileName);
                    break;
                case GET_DATA:
                    System.out.println(ENTER_FILENAME);
                    String findNameFile = scanner.nextLine();
                    System.out.println(dirFileCache.get(findNameFile));
                    break;
                case EXIT:
                    flag = false;
                    break;
                default:
                    System.out.println(INCORRECT_COMM + scanner.nextLine());
                    break;
            }
        }
    }
}
