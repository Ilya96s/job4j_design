package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Чтение из файла
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class EvenNumberFile {

    /**
     * Класс FileInputStream предназначен для чтения байтов из файла
     */
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            /**
             * Метод read() возвращает один байт, поэтому помещаем в цикл, что бы прочитать целиком
             */
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] arrayWords = text.toString().split(System.lineSeparator());
            for (String num : arrayWords) {
                boolean rsl = Integer.parseInt(num) % 2 == 0;
                System.out.println(rsl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
