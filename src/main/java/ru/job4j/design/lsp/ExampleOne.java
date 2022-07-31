package ru.job4j.design.lsp;

/**
 * Нарушение принципа LSP. Класс наследник не должен генерировать исключения не описанные в базовом классе
 *
 * @author Ilya Kaltygin
 */
public class ExampleOne {

    public static class Cat {

        private String color;

        public Cat(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

    }

    public static class Validation {

        public boolean isValid(Cat cat) {
            return !cat.getColor().equals("Black");
        }

    }

    public static class OtherValidation extends Validation {

        @Override
        public boolean isValid(Cat cat) {
            if (cat.getColor().equals("Black")) {
                /* Генерация исключения, которое не описано в базовом классе */
                throw new IllegalArgumentException("Unsuitable cat");
            }
            return true;
        }
    }
}
