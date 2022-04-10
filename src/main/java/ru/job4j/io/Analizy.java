package ru.job4j.io;

import java.io.*;

/**
 * Анализ доступности сервера
 * @author Ilya Kaltygin
 * @version 1.1
 */
public class Analizy {

     boolean status = true;

    /**
     * Метод находит диапазоны, когда сервер не работал
     *
     * @param source Имя файла лога
     * @param target Имя результата анализа
     */
    public void unavailable(String source, String target) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            br.lines()
                    .forEach(line -> {
                        if (status && line.contains("400") || line.contains("500")) {
                            status = false;
                            String[] array = line.split(" ");
                            sb.append(array[1])
                                    .append(";");
                        }
                        if (!status && line.contains("200") || line.contains("300")) {
                            status = true;
                            String[] array = line.split(" ");
                            sb.append(array[1])
                                    .append(";")
                                    .append(System.lineSeparator());
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            pw.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
  