package ru.job4j.srp;

import java.util.Scanner;
/**
 * Нарушение принципа единственной ответственности
 *
 * Каждый метод должен реализовывать спосбо достижения одной ответственности,
 *
 * в моем случае в одном методе у меня осуществлен и ввод с клавиатуры и валидация данных
 *
 * @author Ilya Kaltygin
 */
public class Example {

    public static void someMethod(String[] args) throws Exception {
        /* Ввод с клавиатуры */
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());
        /* Валидация */
        if (args.length == 0) {
            throw new Exception("Некорректные параметры");
        }
    }
}
