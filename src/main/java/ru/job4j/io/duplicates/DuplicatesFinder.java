package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *Запуск обхода файлов
 * @author Ilya Kaltygin
 * @version 1.1
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor dv = new DuplicatesVisitor();
        Path file = Paths.get("C:\\Users\\IULIA\\Desktop\\X");
        Files.walkFileTree(file, dv);
        dv.printDuplicateFiles();
    }
}
