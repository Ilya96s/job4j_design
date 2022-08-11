package ru.job4j.design.isp.menu;

/**
 * Вывод меню на консоль
 *
 * @author Ilya Kaltygin
 */
public class SimpleMenuPrinter implements MenuPrinter {

    private static final String SEPARATOR = "---";

    @Override
    public void print(Menu menu) {
        menu.forEach(item -> System.out.println(paragraphIndent(item.getNumber()) + item.getName()));

    }

    private String paragraphIndent(String number) {
        int index = number.length() / 2 - 1;
        return SEPARATOR.repeat(index) + number;
    }
}
