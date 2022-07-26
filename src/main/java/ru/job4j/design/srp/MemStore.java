package ru.job4j.design.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Работа с хранилищем
 *
 * @author Ilya Kaltygin
 */
public class MemStore implements Store {
    private final List<Employee> employees = new ArrayList<>();

    /**
     * Добавление сотрудника в хранилище
     * @param em Сотрудник
     */
    public void add(Employee em) {
        employees.add(em);
    }

    /**
     * Получение сотрудников из хранилища
     * @param filter Предикат
     * @return Список сотрудников
     */
    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter).collect(Collectors.toList());
    }
}
