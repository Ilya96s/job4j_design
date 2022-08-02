package ru.job4j.design.lsp.foodstorage;

import java.time.LocalDate;
import java.util.List;

/**
 * Контроль качества
 *
 * Класс переаспределяет продукты по хранилищамв зависимости от срока годности
 *
 * @author Ilya Kaltygin
 */
public class ControlQuality {
    /**
     * Список хранилищ
     */
    private List<Store> store;

    public ControlQuality(List<Store> store) {
        this.store = store;
    }

    /**
     * Метод распределяет продукты по хранилищам
     * @param food Продукт
     */
    public void allocation(Food food) {
        for (Store storage : store) {
                storage.add(food);
        }
    }
}
