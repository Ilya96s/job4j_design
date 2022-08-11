package ru.job4j.design.dip;

import java.util.ArrayList;
import java.util.List;

/**
 * Пример №3
 *
 * Нарушение принципа DIP заключается в том, что класс SomeClass зависит от конкретной реализации хранилища
 *
 * @author Ilya Kaltygin
 */
public class ExampleThree {

    static class SomeClass {
        private Store1 store1;

        public SomeClass(Store1 store1) {
            this.store1 = store1;
        }

        public boolean add(Person person) {
            return store1.save(person);
        }

    }

    static class Person {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class Store1 {
        private List<Person> personList = new ArrayList<>();

        public boolean save(Person person) {
            return personList.add(person);
        }
    }
}
