package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Predicate;

/**
 * Генерация отчета в формате XML
 *
 * @author Ilya Kaltygin
 */
public class ReportXML implements Report {
    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    private Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    /**
     * Генерация отчета
     * @param filter Предикат
     * @return Отчет
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        String rsl = "";
        try {
            rsl = xmlFormat(store.findBy(filter));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * Сериализация объектов в XML
     * @param employees Список объектов
     * @return XML формат
     * @throws JAXBException Исключение
     */
    private static String xmlFormat(List<Employee> employees) throws JAXBException {
        String xml = "";
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(employees), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
