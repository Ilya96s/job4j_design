package ru.job4j.map;

import java.util.*;

/**
 * Создать модель User с 3 полями и конструктором
 * Переопределить только hashCode
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
