package ru.job4j.design.lsp;
/**
 * Нарушение принципа LSP. Использование методов getClass(), instance of
 *
 * @author Ilya Kaltygin
 */
public class ExampleFour {

    public void someMethod(Animal animal) {
        if (animal.getClass() == Cat.class) {
            System.out.println("Meow");
        }
        if (animal instanceof Dog) {
            System.out.println("Woof");
        }
    }

    public static class Animal {

    }

    public static class Cat extends Animal {

    }

    public static class Dog extends Animal {

    }
}
