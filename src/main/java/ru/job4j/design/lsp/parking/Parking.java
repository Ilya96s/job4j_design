package ru.job4j.design.lsp.parking;

/**
 * Парковка машин
 *
 * Класс реализация парковки
 *
 * @author Ilya Kaltygin
 */
public class Parking implements ParkStore {

    /**
     * @param auto Объект типа Auto
     * @return     Возвращает true, если машина встала на парковку, иначе false;
     */
    @Override
    public boolean add(Auto auto) {
        return false;
    }
}
