package ru.job4j.io.findfile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Сканирование файловой системы
 *
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class Search {
    private List<Path> list;
    private Predicate<Path> predicate;

    /**
     * Метод определяет предикат в зависимости от типа поиска
     * @param arg объект с параметрами
     * @return Предикат, подходящий для типа поиска
     */
    public static Predicate<Path> choiceOfPredicate(Arguments arg) {
        Predicate<Path> predicate = null;
        Pattern pattern;
        String argument = arg.get("t");
        if (("name").equals(argument)) {
            predicate = (s -> s.toFile().getName().equals(arg.get("n")));
        }
        if (("regex").equals(argument)) {
            pattern = Pattern.compile(arg.get("n"));
            predicate = (s -> s.toFile().getName().matches(pattern.toString()));
        }
//        if (("mask").equals(argument)) {
//            predicate = (s -> s.toFile().getName())
//        }
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

    public static void main(String[] args) throws IOException {
        Arguments arg = Arguments.of(args);
        Path start = Paths.get(arg.get("d"));
        Predicate<Path> predicate = choiceOfPredicate(arg);
        search(start, predicate);
    }
}
