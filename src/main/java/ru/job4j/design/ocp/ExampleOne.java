package ru.job4j.design.ocp;

/**
 *
 * Пример № 1
 *
 * Нарушение принципа OCP заключается в том, что параматеры и возвращаемыйтип метода printInfo() зависят от реализации класса Person
 *
 * @author Ilya Kaltygin
 */
public class ExampleOne {

    public String printInfo(Person person) {
        return person.getName();
    }

    public static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
