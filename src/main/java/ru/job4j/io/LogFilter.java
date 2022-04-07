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
    public List<String> filter(String file) {
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

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.forEach(System.out::println);
    }
}


