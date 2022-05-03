package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Сериализация объекта в XML и десериализация
 *
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) throws Exception {
        final Car car = new Car(false, 2085, "Hatchback", new Driver("Ivan", 35),
                new Driver[] {new Driver("Oleg", 29), new Driver("Fedor", 45)});

        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Car.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
