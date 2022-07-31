package ru.job4j.design.lsp;
/**
 * Нарушение принципа LSP. В наследнике ослабляется постусловие
 *
 * @author Ilya Kaltygin
 */
public class ExampleThree {

    public static class Class1 {

        public int someMethod(int number) {
            if (number < 100) {
                number += 200;
            }
            return number;
        }
    }

    public static class Class2 extends Class1 {

        @Override
        public int someMethod(int number) {
            return number;
        }
    }
}
