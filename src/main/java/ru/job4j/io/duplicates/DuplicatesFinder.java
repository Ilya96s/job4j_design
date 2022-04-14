package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *Запуск обхода файлов
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path file = Paths.get("./");
        Files.walkFileTree(file, new DuplicatesVisitor());
    }
}
