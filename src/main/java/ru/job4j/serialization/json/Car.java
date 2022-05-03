package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
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

    public static void main(String[] args) throws JAXBException {
        final Car car = new Car(false, 2085, "Hatchback", new Driver("Ivan", 35),
                new Driver[] {new Driver("Oleg", 29), new Driver("Fedor", 45)});
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
