package ru.job4j.design.isp.menu;

import java.util.Scanner;

/**
 * построения и вывода списка задач пользователя
 *
 * @author Ilya Kaltygin
 */
public class TODOApp {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    private static final String ADD = "1";
    private static final String ADD_SUB = "2";
    private static final String PRINT_TASK = "3";
    private static final String EXIT = "4";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        while (true) {
            System.out.println("Введите команду:\n"
                    + ADD + " - добавить задачу\n"
                    + ADD_SUB + " - добавить подзадачу\n"
                    + PRINT_TASK + " - вывести список задач\n"
                    + EXIT + " - выход");
            String request = scanner.nextLine();
            if (EXIT.equals(request)) {
                break;
            } else if (ADD.equals(request)) {
                System.out.println("Введите имя новой основной задачи");
                menu.add(Menu.ROOT, scanner.nextLine(), STUB_ACTION);
            } else if (ADD_SUB.equals(request)) {
                System.out.println("Введите имя существующей задачи");
                String task = scanner.nextLine();
                System.out.println("Введите имя новой подзадачи");
                menu.add(task, scanner.nextLine(), STUB_ACTION);
            } else if (PRINT_TASK.equals(request)) {
                printer.print(menu);
            } else {
                System.out.println("Неизвестная команда, повторите ввод");
            }
        }
    }
}
