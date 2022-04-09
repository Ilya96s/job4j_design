package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Чтение файла конфигурации
 * @author Ilya Kaltygin
 * @version 1.1
 */
public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод считывает всю ключи ключи и значения в карту values
     * Комментарии начинающиеся с # и пустые строки нужно пропустить
     */
    public void load() {
        try (BufferedReader br = new BufferedReader(
                new FileReader("app.properties")
        )) {
            br.lines()
                    .filter(line -> !line.contains("#") && line.contains("="))
                    .map(line -> line.split("="))
                    .forEach(arrayLine -> values.put(arrayLine[0], arrayLine[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает знаение по ключу
     * @param key Ключ
     * @return Значение ключа
     */
    public String value(String key) {
        if (!values.containsKey(key) || key == null) {
            throw new IllegalArgumentException("Incorrect key");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
