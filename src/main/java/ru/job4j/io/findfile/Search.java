package ru.job4j.io.findfile;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Сканирование файловой системы
 *
 * @author Ilya Kaltygin
 * @version 1.1
 */
public class Search {

    /**
     * Метод определяет предикат в зависимости от типа поиска
     * @param arg объект с параметрами
     * @return Предикат, подходящий для типа поиска
     */
    public static Predicate<Path> choiceOfPredicate(Arguments arg) {
        Predicate<Path> predicate = null;
        Pattern pattern;
        PathMatcher matcher;
        String argument = arg.get("t");
        if (("name").equals(argument)) {
            predicate = (s -> s.toFile().getName().equals(arg.get("n")));
        } else if (("regex").equals(argument)) {
            pattern = Pattern.compile(arg.get("n"));
            predicate = (s -> s.toFile().getName().matches(pattern.toString()));
        } else if (("mask").equals(argument)) {
            matcher = FileSystems.getDefault().getPathMatcher("glob:" + arg.get("n"));
            predicate = (matcher::matches);
        }
        return predicate;
    }

    /**
     * Метод поиска файлов по определенному предикату
     * @param root Директория с которой начинается обход дерева файлов
     * @param predicate Предикат для поиска файлов
     * @return Список найдных файловен
     * @throws IOException исключение, которое может быть выброшено
     */
    public static List<Path> search(Path root, Predicate<Path> predicate) throws IOException {
        SearchFiles searcher = new SearchFiles(predicate);
        Files.walkFileTree(root, searcher);
        return searcher.getPathList();
    }

    /**
     * Запись найденных файлов в файл
     * @param log файл для записи
     * @param list список найденных файлов
     */
    public static void writeFoundFiles(String log, List<Path> list) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(log))) {
            for (Path file : list) {
                writer.println(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Arguments arg = Arguments.of(args);
        Path start = Paths.get(arg.get("d"));
        String log = arg.get("o");
        Predicate<Path> predicate = choiceOfPredicate(arg);
        List<Path> list = search(start, predicate);
        writeFoundFiles(log, list);
    }
}
