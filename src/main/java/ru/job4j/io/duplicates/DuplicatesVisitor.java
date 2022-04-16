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
     * Карта, куда записываются файлы
     */
    Map<FileProperty, List<Path>> fileMap = new HashMap<>();

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
        if (!fileMap.containsKey(fileProperty)) {
            fileMap.put(fileProperty, new ArrayList<>());
        }
        List<Path> list = fileMap.get(fileProperty);
        list.add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    /**
     * Метод проходит по всем значениям мапы и если список содержит больше 1 элемента,
     * то выводит элементы списка
     */
    public void printDuplicateFiles() {
        for (FileProperty x : fileMap.keySet()) {
            List<Path> list = fileMap.get(x);
            if (list.size() > 1) {
                for (Path el : list) {
                    System.out.println(el);
                }
            }
        }
    }
}
