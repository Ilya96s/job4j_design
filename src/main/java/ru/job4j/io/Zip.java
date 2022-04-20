package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Архивировать проект
 * Утилита для архивации папки
 * @author Ilya Kaltygin
 * @version 1.2
 */
public class Zip {

    /**
     * Метод архивирует файлы с помощью аргументов
     * @param sources Список файлов
     * @param target Имя и расширение архива
     */
    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
     * Валидация аргументов
     * @param args Объект с аргементами
     */
    private  void argumentsValidation(ArgsName args) {
        Path root = Paths.get(args.get("d"));
        if (!root.toFile().isDirectory()) {
            throw new IllegalArgumentException("Root is not a directory");
        }
        String extension = args.get("e");
        if (!extension.startsWith(".")) {
            throw new IllegalArgumentException("Extension should be start with .extension");
        }
        Path zip = Paths.get(args.get("o"));
        String[] nameZip = zip.toFile().getName().split("\\.");
        if (nameZip.length < 2 || nameZip[1].isEmpty()) {
            throw new IllegalArgumentException("Archive name should be format: name.extension");
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Missing argument, use construction: java -jar pack.jar ARG1 ARG2 ARG3");
        }
        Zip zip = new Zip();
        ArgsName arguments = ArgsName.of(args);
        zip.argumentsValidation(arguments);
        List<Path> pathList = Search.search(Paths.get(arguments.get("d")),
                path -> !path.toFile()
                        .getName()
                        .endsWith(arguments.get("e")));
        for (Path file : pathList) {
            zip.packFiles(Collections.singletonList(file.toFile()), new File(arguments.get("o")));
        }
    }
}

