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
 * @version 1.1
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    /**
     * Множество, куда записываются файлы
     */
    Map<FileProperty, Path> fileMap = new HashMap<>();

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
        if (fileMap.containsKey(fileProperty)) {
            System.out.println(file.toAbsolutePath());
        } else {
            fileMap.put(fileProperty, file);
        }
        System.out.println(file.getFileName());
        return super.visitFile(file, attrs);
    }
}
