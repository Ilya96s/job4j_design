package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Логика обхода дерева файлов
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    /**
     * Множество, куда записываются файлы
     */
    Set<FileProperty> setFile = new HashSet<>();

    /**
     * Метод вызывается каждый раз, когда происходит обращение к файлу
     * @param file Проверяемый файл
     * @param attrs Атрибуты проверяемого файла
     * @return CONTINUE
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!setFile.add(fileProperty)) {
            System.out.println(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}
