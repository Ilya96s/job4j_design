package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Отдел HR
 *
 * Отдел HR попросил выводить сотрудников в порядке убывания зарплаты и убрать поля даты найма и увольнения
 *
 * @author Ilya Kaltygin
 */
public class ReportHR implements Report {
    private Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    /**
     * Сортировка сотрудников в порядке убывания зарплаты
     * @param employees Список сотрудников
     * @return Отсортированный список
     */
    private List<Employee> sortByDesc(List<Employee> employees) {
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        return employees;
    }

    /**
     * Генерация отчета
     * @param filter Предикат
     * @return Отчет
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> employees = store.findBy(filter);
        employees = sortByDesc(employees);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
