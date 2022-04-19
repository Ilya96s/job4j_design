package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Архивировать проект
 * Утилита для архивации папки
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class Zip {

    /**
     * Метод архивирует файлы с помощью аргументов
     * @param source Список файлов
     * @param target Имя и расширение архива
     */
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод архивирует файлы с помощью аргументов
     * @param sources Список файлов
     * @param target Имя и расширение архива
     */
    public void packFiles(List<File> sources, File target) {
        for (File file : sources) {
            packSingleFile(file, target);
        }
    }

    /**
     * Валидация аргументов
     * @param args Объект с аргементами
     */
    private static void argumentsValidation(ArgsName args) {
        if (args.get("d") == null || args.get("e") == null || args.get("o") == null) {
            throw new IllegalArgumentException("Parameters does not exist");
        }
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        ArgsName arguments = ArgsName.of(args);
        argumentsValidation(arguments);
        List<Path> pathList = Search.search(Paths.get(arguments.get("d")),
                path -> !path.toFile()
                        .getName()
                        .endsWith(arguments.get("e")));
        List<File> fileList = new ArrayList<>();
        for (Path file : pathList) {
            fileList.add(file.toFile());
        }
        zip.packFiles(fileList, new File(arguments.get("o")));
    }
}

