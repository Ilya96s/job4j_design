package ru.job4j.design.dip;

/**
 * Пример №1
 *
 * Нарушение принципа DIP заключается в том, что модуль верхнего уровня(Calculator) зависит от модулей нижнего уровня(Add, Subtract, Multiply, Divide)
 *
 * @author Ilya Kaltygin
 */
public class ExampleOne {

    static class Calculator {
        public enum Operation {
            ADD, SUBTRACT, MULTIPLY, DIVIDE
        }

        public double calculate(double a, double b, Operation operation) {
            double result = 0;
            switch (operation) {
                case ADD:
                    Add add = new Add();
                    result = add.add(a, b);
                    break;
                case SUBTRACT:
                    Subtract subtract = new Subtract();
                    result = subtract.subtract(a, b);
                    break;
                case MULTIPLY:
                    Multiply multiply = new Multiply();
                    result = multiply.multiply(a, b);
                    break;
                case DIVIDE:
                    Divide divide = new Divide();
                    result = divide.divide(a, b);
                    break;
                default:
                    return result;
            }
            return result;
        }
    }

    static class Add {
        public double add(double a, double b) {
            return  a + b;
        }
    }

    static class Subtract {
        public double subtract(double a, double b) {
            return  a - b;
        }
    }

    static class Multiply {
        public double multiply(double a, double b) {
            return a * b;
        }
    }

    static class Divide {
        public double divide(double a, double b) {
            return a / b;
        }
    }
}
