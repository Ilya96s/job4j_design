package ru.job4j.io.findfile;

import java.util.HashMap;
import java.util.Map;

/**
 * Прием массива параметров и разбиение их на пары: -key=value
 *
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class Arguments {

    private Map<String, String> values = new HashMap<>();

    /**
     * Метод возвращает значение по ключу
     * @param key ключ
     * @return значение
     */
    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Invalid key");
        }
        return values.get(key);
    }

    /**
     * Метод принимает массив параметров, разбивает их на пары -key=value и помещает в Map
     * @param args параметры
     */
    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments does not exist");
        }
        for (String argument : args) {
            validation(argument);
            String[] split = argument.split("=", 2);
            StringBuilder stringBuilder = new StringBuilder(split[0]);
            if (stringBuilder.charAt(0) == '-') {
                stringBuilder.deleteCharAt(0);
            }
            values.put(stringBuilder.toString(), split[1]);
        }
    }

    /**
     * Методвозвращает объект Arguments в котором находиться структура данных Map с парами -key=value
     * полученными из массива параметров
     * @param args параметры
     * @return
     */
    public static Arguments of(String[] args) {
        Arguments arguments = new Arguments();
        arguments.parse(args);
        return arguments;
    }

    /**
     * Валидация параметров
     * @param str входные параметры
     */
    private static void validation(String str) {
        String[] split = str.split("=", 2);
        if (split[0].isEmpty() || !split[0].startsWith("-") || split[1].isEmpty()) {
            throw new IllegalArgumentException("Argument should be: -key=value");
        }
    }
}
