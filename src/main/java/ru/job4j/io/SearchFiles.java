package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.*;

/**
 * Класс, который имплементирует интерфейс FileVisitor и содержик логику обхода дерева файлов
 */
public class SearchFiles implements FileVisitor<Path> {
    /**
     * Список в который будут записываться файлы
     */
    private List<Path> pathList = new ArrayList<>();

    /**
     * Предикат для файлов
     */
    private Predicate<Path> predicate;

    public SearchFiles(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    /**
     * Метод возвращает список файлов
     * @return список всех файлов
     */
    public List<Path> getPath() {
        return pathList;
    }

    /**
     * Метод срабатывает перед обращением к элементам директории
     */
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if (dir.getFileName().equals("Не входить")) {
            return SKIP_SUBTREE;
        }
        System.out.println("Вход в папку: " + dir);
        return CONTINUE;
    }

    /**
     * Метод срабатывает при обращении к файлу
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (predicate.test(file)) {
            pathList.add(file);
            System.out.println(file.getFileName());
        }
        return CONTINUE;
    }

    /**
     * Метод срабатывает когда файл недоступен
     */
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Ошибка во время обращения к : " + file);
        return CONTINUE;
    }

    /**
     * Метод срабатывает после обращения ко всем элементам директории
     */
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        System.out.println("Выход из папки: " + dir);
        return CONTINUE;
    }
}
