package ru.job4j.design.lsp.foodstorage;

import java.util.ArrayList;
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

    /**
     * Метод извлекает все продукты и перераспределяет их заново
     */
    public void resort() {
        List<Food> foodList = new ArrayList<>();
        for (Store storage : store) {
            foodList.addAll(storage.getAll());
            storage.deleteAllProduct();
        }
        for (Food food : foodList) {
            allocation(food);
        }
    }
}
