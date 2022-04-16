package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * Именованные аргументы
 * Прием массива параметров и разбиение их на пары: ключ=значение
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    /**
     * Метод возвращает значение по ключу
     * @param key Ключ
     * @return Значение ключа
     */
    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Invalid key");
        }
        return values.get(key);
    }

    /**
     * Метод принимает массив параметров и разбивает на пары ключ=значение
     * @param args Массив параметров
     */
    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Parameters does not exist");
        }
        for (String el : args) {
            String[] split = el.split("=", 2);
            if (split[1].isEmpty()) {
                throw new IllegalArgumentException("Argument should be: key=value");
            }
            StringBuilder sb = new StringBuilder(split[0]);
            if (sb.charAt(0) == '-') {
                sb.deleteCharAt(0);
            }
            values.put(sb.toString(), split[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}
