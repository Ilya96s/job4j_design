package ru.job4j.design.lsp.foodstorage;

import java.time.LocalDate;

/**
 * @author Ilya kaltygin
 */
public class Chocolate extends Food {

    /**
     * Конструктор
     * @param name       Название продукта
     * @param expiryDate Срок годности
     * @param createDate Дата создания
     * @param price      Цена
     * @param discount   Скидка
     */
    public Chocolate(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
