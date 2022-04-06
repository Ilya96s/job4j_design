package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Чтение и запись с помощью буферизированных оберток BufferedReader и BufferedOutputStream
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class LogFilter {

    /**
     * Метод фильтрует файл используя буферизированный поток
     * @param file Имя файла
     * @return Отфильтрованный список
     */
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader buff = new BufferedReader(new FileReader("log.txt"))) {
            buff.lines()
                    .filter(line -> line.contains(" 404 "))
                    .forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Метод принимает список и записывает данные в файл
     * @param log Список, данные которого необходимо записать
     * @param file Имя файла
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String str : log) {
                out.printf("%s%n", str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}


