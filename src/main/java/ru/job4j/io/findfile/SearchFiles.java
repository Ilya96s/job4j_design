package ru.job4j.io.findfile;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Класс содержайщий логику обхода дерева файлов
 *
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class SearchFiles extends SimpleFileVisitor<Path> {
    private Predicate<Path> predicate;
    private List<Path> pathList = new ArrayList<>();

    public SearchFiles(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    /**
     * Метод срабатывает перед обращением к элементам директории
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (predicate.test(file)) {
            System.out.println(file);
            pathList.add(file);
        }
        return CONTINUE;
    }

    /**
     * Метод возвращает список с файлами, которые удовлетворяют условиям поиска
     */
    public List<Path> getPathList() {
        return pathList;
    }
}
