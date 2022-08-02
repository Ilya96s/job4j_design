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
    private List<Food> wareHouseFoodList = new ArrayList<>();

    /**
     * Добавление продукта в хранилище
     * @param food Продукт
     */
    @Override
    public void add(Food food) {
        wareHouseFoodList.add(food);
    }

    /**
     * Метод определеяет попадет ли продукт в данное хранилище
     * @param food Продукт
     * @return     true если срок годности удовлетворяет требованиям данного хранилища, иначе false
     */
    @Override
    public boolean check(Food food, LocalDate currentDate) {
        double total = food.getCreateDate().until(food.getExpiryDate(), ChronoUnit.DAYS);
        double current = currentDate.until(food.getExpiryDate(), ChronoUnit.DAYS);
        double percent = (current / total) * 100;
        return percent > 75;
    }

    /**
     * Получение всех продуктов
     * @return Список с продуктами
     */
    @Override
    public List<Food> getAll() {
        return wareHouseFoodList;
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
