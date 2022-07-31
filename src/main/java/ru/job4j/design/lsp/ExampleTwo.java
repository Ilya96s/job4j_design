package ru.job4j.design.lsp;

/**
 * Нарушение принципа LSP. При использовании производного класса AndroidDeveloper мы получим поведение системы отлчное от ожидаемого, т.к. метод writeCode() возвращает null
 *
 * @author Ilya Kaltygin
 */
public class ExampleTwo {

    public static class Developer {

        public String writeCode() {
            return "Write code";
        }
    }

    public static class Frontend extends Developer {

        @Override
        public String writeCode() {
            return "Write code";
        }
    }

    public static class Backend extends Developer {

        @Override
        public String writeCode() {
            return "Write code";
        }
    }

    public static class AndroidDeveloper extends Developer {

        @Override
        public String writeCode() {
            return null;
        }
    }
}