package ru.job4j.design.lsp.foodstorage;

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
    private static final int COEFF_75 = 75;
    private static final int COEFF_25 = 25;
    private static final int COEFF_100 = 100;
    private List<Food> shopFoodList = new ArrayList<>();

    /**
     * Добавление продукта в хранилище
     * @param food Продукт
     */
    @Override
    public boolean add(Food food) {
        if (check(food)) {
            shopFoodList.add(food);
        }
        return true;
    }

    /**
     * Метод определеяет попадет ли продукт в данное хранилище
     * @param food Продукт
     * @return     true если срок годности удовлетворяет требованиям данного хранилища, иначе false
     */
    @Override
    public boolean check(Food food) {
        boolean rsl = false;
        if (getPercentLifeExpired(food) >= COEFF_25 && getPercentLifeExpired(food) <= COEFF_75) {
            rsl = true;
        } else if (getPercentLifeExpired(food) > COEFF_75 && getPercentLifeExpired(food) < COEFF_100) {
            food.setPrice(food.getPrice() - food.getDiscount());
            rsl = true;
        }
        return rsl;
    }

    /**
     * Получение всех продуктов
     * @return Список с продуктами
     */
    @Override
    public List<Food> getAll() {
        return List.copyOf(shopFoodList);
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
