package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.function.Predicate;

/**
 * Генерация отчета в формате JSON
 *
 * @author Ilya Kalttgin
 */
public class ReportJSON implements Report {
    private Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    /**
     * Генерация отчета
     * @param filter Предикат
     * @return Отчет
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        return jsonFormat(store.findBy(filter));
    }

    /**
     * Сериализация объектов в JSON
     * @param employee Список объектов
     * @return JSON формат
     */
    private static String jsonFormat(List<Employee> employee) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(employee);
    }
}
