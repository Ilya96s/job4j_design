package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * При добавлении объектов в мапу с помощью метода put() рассчитывается хэш-код для каждого ключа
 * с помощью метода hashCode(). Далее с помощью функции хэш происходит смешивание старших
 * битов с младшими для улучшенного распределения по бакетам. Расчитывается индекс бакета. Создается объект Node и добавляется в
 * полученный индекс бакета.
 * Так как хеш коды у объектов разные, значит 2 объекта попадут в разные бакеты без вызова метода equals() т.к коллизий не возникло.
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class UsageUser {
    public static void main(String[] args) {
        User user1 = new User("Ivan", 2,
                new GregorianCalendar(1994, Calendar.AUGUST, 24));
        User user2 = new User("Ivan", 2,
                new GregorianCalendar(1994, Calendar.AUGUST, 24));

        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(map.values());
    }
}
