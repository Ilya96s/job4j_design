package ru.job4j.io;

import java.io.FileOutputStream;

/**
 * Запись данных в файл
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class ResultFile {

    /**
     * Класс FileOutputStream предназначен для записи байтов в файл
     * С помощью блока операторов try-catch каждая операция проверяется на наличие исключений,
     * и если исключение возникает, то оно обрабатывается.
     * С помощью метода getBytes() происходит перевод данных в массив байтов
     */
    public static void main(String[] args) {
       try (FileOutputStream out = new FileOutputStream("result.txt")) {
           for (int i = 1; i < 11; i++) {
               for (int j = 1; j < 11; j++) {
                   out.write((i + " x " + j + " = " + i * j).getBytes());
                   out.write(System.lineSeparator().getBytes());
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
