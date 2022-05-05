package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

/**
 * Класс описывающий автомобиль
 *
 * @author Ilya Kaltygin
 * @version 1.0
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private boolean isElectroCar;

    @XmlAttribute
    private int weight;
    @XmlAttribute
    private String type;
    private Driver currentDriver;
    private Driver[] previousDrivers;

    public Car() {

    }

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

    public boolean isElectroCar() {
        return isElectroCar;
    }

    public int getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }

    public Driver getCurrentDriver() {
        return currentDriver;
    }

    public Driver[] getPreviousDrivers() {
        return previousDrivers;
    }
}
