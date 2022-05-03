package ru.job4j.serialization.json;

import java.util.Arrays;

/**
 * Класс описывающий автомобиль
 *
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class Car {
    private boolean isElectroCar;
    private int weight;
    private String type;
    private Driver currentDriver;
    private Driver[] previousDrivers;

    public Car(boolean isElectroCar, int weight, String type, Driver currentDriver, Driver[] previousDrivers) {
        this.isElectroCar = isElectroCar;
        this.weight = weight;
        this.type = type;
        this.currentDriver = currentDriver;
        this.previousDrivers = previousDrivers;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isElectroCar=" + isElectroCar
                + ", weight=" + weight
                + ", type='" + type + '\''
                + ", currentDriver=" + currentDriver
                + ", previousDrivers=" + Arrays.toString(previousDrivers)
                + '}';
    }
}
