package ru.job4j.io;

import java.io.File;

/**
 * Работа с классом File
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class Dir {

    /**
     * Метод выводит по заданному пути имена файлов и их размеры
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("No exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            System.out.println(String.format("File name: %s File size: %s%n", subfile.getName(), subfile.length()));
        }
    }
}
