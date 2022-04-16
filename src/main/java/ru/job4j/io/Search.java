package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Сканирование файловой системы
 * @author Ilya Kaltygin
 * @version 1.1
 */
public class Search {
    public static void main(String[] args) {
        parameterCheck(args);
        Path start = Paths.get(args[0]);
        search(start, file -> file.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    /**
     * Метод поиска файлов по определенному предикату
     * @param root Директория с которой начинается обход дерева файлов
     * @param condition Предикат для поиска файлов
     * @return Список найденных файлов
     */
    public static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPath();
    }

    /**
     * Валидация параметров
     * @param args массив параметров, которые указаны в Program arguments
     */
    public static void parameterCheck(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        File file = new File(args[0]);

        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Path does not exist %s", file.getAbsolutePath()));
        }

        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Invalid file extension setting");
        }
    }
}
