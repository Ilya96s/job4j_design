package ru.job4j.design.lsp.foodstorage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        Store trash = new Trash();
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        List<Store> storages = List.of(
                trash, shop, warehouse
        );
        ControlQuality controlQuality = new ControlQuality(storages);
        LocalDate expiryDate = LocalDate.now().plusDays(400);
        LocalDate createDate = LocalDate.now().minusDays(10);
        Food food = new Milk("Milk", expiryDate, createDate, 50, 0.5);
        controlQuality.allocation(food);
        assertThat(warehouse.getAll()).isEqualTo(List.of(food));
    }

    /**
     * Проверка, что продукт попадет в хранилище Shop
     */
    @Test
    void whenFoodGoIntoTheShop() {
        Store trash = new Trash();
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        List<Store> storages = List.of(
                trash, shop, warehouse
        );
        ControlQuality controlQuality = new ControlQuality(storages);
        LocalDate expiryDate = LocalDate.now().plusDays(100);
        LocalDate createDate = LocalDate.now().minusDays(100);
        Food food = new Milk("Milk", expiryDate, createDate, 50, 0.5);
        controlQuality.allocation(food);
        assertThat(shop.getAll()).isEqualTo(List.of(food));
    }

    /**
     * Проверка, что продукт попадет в хранилище Trash
     */
    @Test
    void whenFoodGoIntoTheTrash() {
        Store trash = new Trash();
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        List<Store> storages = List.of(
                trash, shop, warehouse
        );
        ControlQuality controlQuality = new ControlQuality(storages);
        LocalDate expiryDate = LocalDate.now().minusDays(100);
        LocalDate createDate = LocalDate.now().minusDays(200);
        Food food = new Milk("Milk", expiryDate, createDate, 50, 0.5);
        controlQuality.allocation(food);
        assertThat(trash.getAll()).isEqualTo(List.of(food));
    }

    /**
     * Цена продукта изменилась с учетом скидки, потому что срок годности израсходован больше 75%
     */
    @Test
    void whenExpiredDateLessThan75ThenNewPrice() {
        Store trash = new Trash();
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        List<Store> storages = List.of(
                trash, shop, warehouse
        );
        ControlQuality controlQuality = new ControlQuality(storages);
        LocalDate expiryDate = LocalDate.now().plusDays(5);
        LocalDate createDate = LocalDate.now().minusDays(50);
        Food food = new Milk("Milk", expiryDate, createDate, 50, 30);
        controlQuality.allocation(food);

        assertThat(food.getPrice()).isEqualTo(20);
    }

}