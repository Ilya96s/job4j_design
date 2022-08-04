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
     * Места для легковых и грузовых автомобилей
     */
    private Auto[] passengerAuto;
    private Auto[] truckAuto;

    public Parking(int passSeat, int truckSeat) {
        passengerAuto = new Auto[passSeat];
        truckAuto = new Auto[truckSeat];
    }

    /**
     * @param auto Объект типа Auto
     * @return     Возвращает true, если машина встала на парковку, иначе false;
     */
    @Override
    public boolean add(Auto auto) {
        return false;
    }
}
