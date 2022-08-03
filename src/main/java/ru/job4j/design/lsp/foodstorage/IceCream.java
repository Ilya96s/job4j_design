package ru.job4j.design.lsp.foodstorage;

import java.time.LocalDate;

/**
 *
 * @author Ilya Kaltygin
 */
public class IceCream extends Food {

    /**
     * Конструктор
     * @param name       Название продукта
     * @param expiryDate Срок годности
     * @param createDate Дата создания
     * @param price      Цена
     * @param discount   Скидка
     */
    public IceCream(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
