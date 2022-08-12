package ru.job4j.design.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    /**
     * Демонстрация работы меню
     */
    @Test
    void whenAddWhenReturnSame() {
            Menu menu = new SimpleMenu();
            menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
            menu.add(Menu.ROOT, "Покормит собаку", STUB_ACTION);
            menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
            menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
            menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
            assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                    List.of("Купить продукты"), STUB_ACTION, "1."))
                    .isEqualTo(menu.select("Сходить в магазин").get());
            assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
            assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
            menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    /**
     * Проверка на вывод
     */
    @Test
    public void whenOutput() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream prev = System.out;
        PrintStream now = new PrintStream(output);
        System.setOut(now);
        new SimpleMenuPrinter().print(menu);
        System.setOut(prev);
        String expected = String.join(System.lineSeparator(),
        "1.Сходить в магазин",
        "---1.1.Купить продукты",
        "------1.1.1.Купить хлеб",
        "------1.1.2.Купить молоко",
        "2.Покормить собаку" + System.lineSeparator());
        assertThat(expected).isEqualTo(output.toString());

    }

    /**
     * Проверка метода select
     */
    @Test
    public void whenSelect() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        Menu.MenuItemInfo item = menu.select("Купить молоко").get();
        assertThat(item.getNumber()).isEqualTo("1.1.2.");
    }
}