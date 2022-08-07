package ru.job4j.design.lsp.parking;

import java.util.HashSet;
import java.util.Set;
import static ru.job4j.design.lsp.parking.PassengerAuto.SIZE;

/**
 * Парковка машин
 *
 * Класс реализация парковки
 *
 * @author Ilya Kaltygin
 */
public class Parking implements ParkStore {

    /**
     * Хранилища для легковых и грузовых автомобилей
     */
    private Set<Auto> passengerAuto  = new HashSet<>();
    private Set<Auto> truckAuto = new HashSet<>();
    /**
     * Места для легковых и грузовых автомобилей
     */
    private int passSeat;
    private int truckSeat;

    public Parking(int passSeat, int truckSeat) {
        this.passSeat = passSeat;
        this.truckSeat = truckSeat;
    }

    /**
     * Добавление автомобиля на парковку
     * @param auto Объект типа Auto
     * @return     Возвращает true, если машина встала на парковку, иначе false;
     */
    @Override
    public boolean add(Auto auto) {
        boolean rsl = false;
        if (!checkPark(auto)) {
            if (auto.getSize() == SIZE && checkPassSeat()) {
                passengerAuto.add(auto);
                passSeat--;
                rsl = true;
            } else if (auto.getSize() > SIZE && checkTruckSeat()) {
                truckAuto.add(auto);
                truckSeat--;
                rsl = true;
            } else if (checkPassSeatForTruck(auto)) {
                passengerAuto.add(auto);
                passSeat--;
                return true;
            }
        }
        return rsl;
    }

    /**
     * Метод проверяет наличие места для легкового автомобиля
     * @return True если место есть, иначе false
     */
    private boolean checkPassSeat() {
        return passSeat != 0;
    }

    /**
     * Метод проверяет наличие места для грузового автомобля
     * @return True если место есть, иначе false
     */
    private boolean checkTruckSeat() {
        return truckSeat != 0;
    }

    /**
     * Метод проверят налачие мест для легковых автомобилей, что бы на их место можно было запарковать грузовой
     * @param auto Объект типа Auto
     * @return     True если кол-во свободных мест для легковых автомобилей >= размеру грузового автомобиля, иначе false
     */
    private boolean checkPassSeatForTruck(Auto auto) {
        return truckSeat == 0 && passSeat >= auto.getSize();
    }

    /**
     * Проверка хранилищ на наличие автомобиля
     * @param auto Объект типа Auto
     * @return     True если такого автомобиля нет на парковке, иначе false
     */
    private boolean checkPark(Auto auto) {
        return passengerAuto.contains(auto) || truckAuto.contains(auto);
    }
}
