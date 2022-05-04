package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс описывающий водителя
 *
 * @author Ilya kaltygin
 * @version 1.0
 */

@XmlRootElement(name = "driver")
public class Driver {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;

    public Driver() {

    }

    public Driver(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
