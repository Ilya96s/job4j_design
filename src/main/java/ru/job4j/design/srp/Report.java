package ru.job4j.design.srp;

import java.util.function.Predicate;

/**
 * Интерфейс системы отчетов
 */
public interface Report {
    String generate(Predicate<Employee> filter);
}
