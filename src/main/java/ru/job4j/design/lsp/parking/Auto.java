package ru.job4j.design.lsp.parking;

/**
 * Парковка машин
 *
 * Интерфейс транспортного средства. Определяет размер, которое занимает транспортное средство
 *
 * @author Ilya Kaltygin
 */
public interface Auto {

    /**
     *
     * @return Метод возвращает кол-во занимаемых мест на парковке
     */
    int getSize();

}
