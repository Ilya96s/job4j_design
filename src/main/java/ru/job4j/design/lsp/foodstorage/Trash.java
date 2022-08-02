package ru.job4j.design.lsp.foodstorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс хранилище продуктов
 *
 * Хранит продукты у которых срок годности израсходован на 100%
 *
 * @author Ilya Kaltygin
 */
public class Trash implements Store {
    private static final int COEFF_100 = 100;
    private List<Food> trashFoodList = new ArrayList<>();

    /**
     * Добавление продукта в хранилище
     * @param food Продукт
     */
    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (check(food)) {
            trashFoodList.add(food);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод определеяет попадет ли продукт в данное хранилище
     * @param food Продукт
     * @return     true если срок годности удовлетворяет требованиям данного хранилища, иначе false
     */
    @Override
    public boolean check(Food food) {
        return getPercentLifeExpired(food) >= COEFF_100;
    }

    /**
     * Получение всех продуктов
     * @return Список с продуктами
     */
    @Override
    public List<Food> getAll() {
        return List.copyOf(trashFoodList);
    }

    /**
     * Получение продукта по индексу
     * @param index Индекс продукта
     * @return      Продукт
     */
    @Override
    public Food get(int index) {
        return trashFoodList.get(index);
    }
}
