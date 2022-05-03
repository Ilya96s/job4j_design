package ru.job4j.serialization.json;

/**
 * Класс описывающий водителя
 *
 * @author Ilya kaltygin
 * @version 1.0
 */
public class Driver {
    private String name;
    private int age;

    public Driver(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }
}
