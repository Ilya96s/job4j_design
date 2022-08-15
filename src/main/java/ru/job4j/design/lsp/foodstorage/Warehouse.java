package ru.job4j.design.lsp.foodstorage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс хранилище продуктов
 *
 * Хранит продукты у которых срок годности от израсходован меньше чем на 25%
 *
 * @author Ilya Kaltygin
 */
public class Warehouse implements Store {
    private static final int COEFF_25 = 25;
    private List<Food> wareHouseFoodList = new ArrayList<>();

    /**
     * Удаление всех продуктов из хранилища
     */
    @Override
    public void deleteAllProduct() {
        wareHouseFoodList.clear();
    }

    /**
     * Добавление продукта в хранилище
     * @param food Продукт
     */
    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (check(food)) {
            wareHouseFoodList.add(food);
            rsl =  true;
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
        return getPercentLifeExpired(food) < COEFF_25;
    }

    /**
     * Получение всех продуктов
     * @return Список с продуктами
     */
    @Override
    public List<Food> getAll() {
        return List.copyOf(wareHouseFoodList);
    }

    /**
     * Получение продукта по индексу
     * @param index Индекс продукта
     * @return      Продукт
     */
    @Override
    public Food get(int index) {
        return wareHouseFoodList.get(index);
    }
}
