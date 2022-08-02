package ru.job4j.design.lsp.foodstorage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для хранилища продуктов
 *
 * @author Ilya kaltygin
 */
class ControlQualityTest {

    /**
     * Проверка, что продукт попадет в хранилище Warehouse
     */
    @Test
    void whenFoodGoIntoTheWareHouse() {
        List<Store> storeList = List.of(
                new Warehouse(),
                new Shop(),
                new Trash()
        );
        LocalDate expiryDate = LocalDate.of(2022, 12, 31);
        LocalDate createDate = LocalDate.of(2022, 11, 5);
        Food milk = new Milk("Milk", expiryDate, createDate, 100, 0.5);
        ControlQuality quality = new ControlQuality(storeList);
        quality.allocation(milk, LocalDate.of(2022, 11, 5));
        Store store = storeList.get(0);
        assertThat(store.getAll()).isEqualTo(List.of(milk));
    }

    /**
     * Проверка, что продукт попадет в хранилище Shop
     */
    @Test
    void whenFoodGoIntoTheShop() {
        List<Store> storeList = List.of(
                new Warehouse(),
                new Shop(),
                new Trash()
        );
        LocalDate expiryDate = LocalDate.of(2022, 12, 31);
        LocalDate createDate = LocalDate.of(2022, 11, 5);
        Food milk = new Milk("Milk", expiryDate, createDate, 100, 0.5);
        ControlQuality quality = new ControlQuality(storeList);
        quality.allocation(milk, LocalDate.of(2022, 11, 30));
        Store store = storeList.get(1);
        assertThat(store.getAll()).isEqualTo(List.of(milk));
    }

    /**
     * Проверка, что продукт попадет в хранилище Trash
     */
    @Test
    void whenFoodGoIntoTheTrash() {
        List<Store> storeList = List.of(
                new Warehouse(),
                new Shop(),
                new Trash()
        );
        LocalDate expiryDate = LocalDate.of(2022, 12, 31);
        LocalDate createDate = LocalDate.of(2022, 11, 5);
        Food milk = new Milk("Milk", expiryDate, createDate, 100, 0.5);
        ControlQuality quality = new ControlQuality(storeList);
        quality.allocation(milk, LocalDate.of(2023, 1, 5));
        Store store = storeList.get(2);
        assertThat(store.getAll()).isEqualTo(List.of(milk));
    }

    /**
     * Цена продукта изменилась с учетом скидки, потому что срок годности израсходован больше 75%
     */
    @Test
    void whenExpiredDateLessThan75ThenNewPrice() {
        List<Store> storeList = List.of(
                new Warehouse(),
                new Shop(),
                new Trash()
        );
        LocalDate expiryDate = LocalDate.of(2022, 12, 31);
        LocalDate createDate = LocalDate.of(2022, 11, 5);
        Food milk = new Milk("Milk", expiryDate, createDate, 100, 0.5);
        ControlQuality quality = new ControlQuality(storeList);
        quality.allocation(milk, LocalDate.of(2022, 12, 20));
        Store store = storeList.get(1);
        assertThat(store.get(0).getPrice()).isEqualTo(50);
    }

}