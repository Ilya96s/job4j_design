package ru.job4j.design.lsp.foodstorage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс хранилище продуктов
 *
 * Хранит продукты у которых срок годности израсходован от 25% до 75%, если больше 75%, то выставляется новая цена с учетом скидки
 *
 * @author Ilya Kaltygin
 */
public class Shop implements Store {
    private List<Food> shopFoodList = new ArrayList<>();

    /**
     * Добавление продукта в хранилище
     * @param food Продукт
     */
    @Override
    public void add(Food food) {
        shopFoodList.add(food);
    }

    /**
     * Метод определеяет попадет ли продукт в данное хранилище
     * @param food Продукт
     * @return      true если срок годности удовлетворяет требованиям данного хранилища, иначе false
     */
    @Override
    public boolean check(Food food, LocalDate currentDate) {
        double total = food.getCreateDate().until(food.getExpiryDate(), ChronoUnit.DAYS);
        double current = currentDate.until(food.getExpiryDate(), ChronoUnit.DAYS);
        double percent = (current / total) * 100;
        if (percent <= 75 && percent >= 25) {
            return true;
        } else if (percent < 25) {
            food.setDiscount(0.5);
            food.setPrice(food.getPrice() * food.getDiscount());
            return true;
        }
        return false;
    }

    /**
     * Получение всех продуктов
     * @return Список с продуктами
     */
    @Override
    public List<Food> getAll() {
        return shopFoodList;
    }

    /**
     * Получение продукта по индексу
     * @param index Индекс продукта
     * @return      Продукт
     */
    @Override
    public Food get(int index) {
        return shopFoodList.get(index);
    }
}
