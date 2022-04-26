package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Scanner. Знакомство с форматом CSV.
 * Класс, задача которого прочитать данные из файла CSV и записать их в файл.
 *  В качестве входных данных задается путь к файлу path, разделитель delimiter, приемник данных out и фильтр по столбцам filter
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class CSVReader {

    /**
     * Метод читает данные из CSV Файла, фильтрует столбцы и записывает необходимые данные в файл
     * @param argsName Объект
     * @throws IOException Возможное исключение
     */
    public static void handle(ArgsName argsName) throws IOException {
        try (PrintWriter writer = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(argsName.get("out"))))) {
            String[] filter = argsName.get("filter").split(",");
            int[] columnIndex = new int[filter.length];
            Path file = Paths.get(argsName.get("path"));
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] columnNames = scanner.nextLine().split(argsName.get("delimiter"));
                for (int i = 0; i < columnNames.length; i++) {
                    String rsl = columnNames[i];
                    for (int j = 0; j < filter.length; j++) {
                        if (rsl.equals(filter[j])) {
                            columnIndex[j] = i;
                        }
                    }
                }
                for (int index : columnIndex) {
                    if (index < columnIndex.length - 1) {
                        writer.write(columnNames[index] + ";");
                    } else {
                        writer.write(columnNames[index]);
                    }
                }
                writer.println();
            }
        }
    }

    /**
     * Валидация входных параметров
     * @param args параметры
     */
    private void parameterCheck(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        CSVReader csvReader = new CSVReader();
        csvReader.parameterCheck(args);
        CSVReader.handle(argsName);
    }
}
