package ru.job4j.design.lsp.foodstorage;

import java.time.LocalDate;
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
    void add(Food food);

    /**
     * Проверить срок годности по заданным условиям
     * @param food
     * @return
     */
    boolean check(Food food, LocalDate currentDate);

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
}
