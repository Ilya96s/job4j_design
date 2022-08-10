package ru.job4j.design.dip;

/**
 * Пример №2
 *
 * Нарушение принципа DIP заключается в том, что логирование напрямую зависит от консольного вывода
 *
 * @author Ilya Kaltygin
 */
public class ExampleTwo {

    static class Person {
        private int age;
        private String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public void print() {
            System.out.println("Age: " + age + " ; " + "name: " + name);
        }
    }
}
