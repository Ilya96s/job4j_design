package ru.job4j.design.isp.menu;

import java.util.Scanner;

/**
 * построения и вывода списка задач пользователя
 *
 * @author Ilya Kaltygin
 */
public class TODOApp {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static final String MENU_ACTIONS =
            """
                    Введите команду:
                    1 - Добавить задачу
                    2 - Добавить подзадачу
                    3 - Вывести список задач
                    4 - Выход
                    """;
    private static final String ADD = "1";
    private static final String ADD_SUB = "2";
    private static final String PRINT_TASK = "3";
    private static final String EXIT = "4";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        while (true) {
            System.out.println(MENU_ACTIONS);
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
