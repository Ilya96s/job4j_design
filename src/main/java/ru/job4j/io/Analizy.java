package ru.job4j.io;

import java.io.*;

/**
 * Анализ доступности сервера
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class Analizy {

    StringBuilder sb = new StringBuilder();

    /**
     * Метод находит диапазоны, когда сервер не работал
     * @param source Имя файла лога
     * @param target Имя результата анализа
     */
    public void unavailable(String source, String target) {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
        PrintWriter pr = new PrintWriter(new FileOutputStream(target))) {
            br.lines()
                    .forEach(line -> {
                        if (line.contains("400") || line.contains("500")) {
                          String[] rsl = line.split(" ");
                         sb.append(rsl[1]);
                        }
                    });
                    } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb);
    }
}
  