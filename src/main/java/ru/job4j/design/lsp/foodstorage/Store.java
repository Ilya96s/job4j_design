package ru.job4j.design.lsp.foodstorage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Абстрактное хранилище
 *
 * @author Ilya kaltygin
 */
public interface Store {
    /**
     * Добавить продукт в хранилище
     * @param food Продукт
     */
    boolean add(Food food);

    /**
     * Проверить срок годности по заданным условиям
     * @param food
     * @return
     */
    boolean check(Food food);

    /**
     * Вернуть список продуктов из хранилища
     * @return Список с продуктами
     */
    List<Food> getAll();

    /**
     * Вернуть продукт изхранилища
     * @return Продукт
     */
    Food get(int index);

    /**
     * Подсчет процента срока годности продукта
     * @param food  Продукт
     * @return      Текущий процент срока годности
     */
    default double getPercentLifeExpired(Food food) {
        double total = food.getCreateDate().until(food.getExpiryDate(), ChronoUnit.DAYS);
        double current = food.getCreateDate().until(LocalDate.now(), ChronoUnit.DAYS);
        return (current / total) * 100;
    }
}
