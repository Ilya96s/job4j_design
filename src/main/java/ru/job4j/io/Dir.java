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
        File file = new File("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("No exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            System.out.println(String.format("File name: %s File size: %s%n", subfile.getName(), subfile.length()));
        }
    }
}
