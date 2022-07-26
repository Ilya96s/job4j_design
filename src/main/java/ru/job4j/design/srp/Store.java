package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс хранилища данных
 *
 * @author Ilya Kaltygin
 */
public interface Store {
    List<Employee> findBy(Predicate<Employee> filter);
}
