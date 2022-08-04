package ru.job4j.design.lsp.parking;

/**
 * Парковка машин
 *
 * Интерфейс парковки. Определяет размер, которое занимает транспортное средство
 *
 * @author Ilya Kaltygin
 */
public interface ParkStore {

    /**
     * @param auto Объект типа Auto
     * @return     Возвращает true, если машина встала на парковку, иначе false;
     */
    boolean add(Auto auto);

}
