package ru.job4j.design.dip;

import java.util.HashMap;
import java.util.Map;

/**
 * Пример №3
 *
 * Нарушение принципа DIP заключается в том, что класс зависит от конкретной реализации хранилища HashMap
 *
 * @author Ilya Kaltygin
 */
public class ExampleThree {

    static class SomeClass {
        private Map<Integer, Person> persons = new HashMap<>();

        public boolean add(Person person) {
            return persons.put(person.id, person) != null;
        }

    }

    private Map<Integer, Person> persons = new HashMap<>();

    static class Person {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
