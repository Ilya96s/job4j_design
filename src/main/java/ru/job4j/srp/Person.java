package ru.job4j.srp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

/**
 * Нарушение принципа единственной ответственности
 *
 * В класс добавлены 2 дополнительных метода, которые не имеют никакого отношения к модели и  реализуют 2 различные цели
 *
 * @author Ilya Kaltygin
 */
public class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Чтение данных из файла
     */
    public String read(String dir, String path) {
        String str = "";
        try {
            str = Files.readString(Path.of(dir, path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * вывод текущей даты
     */
    public static void printDate() {
        System.out.println(LocalDate.now());

    }
}
