package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

/**
 * Отдел бухгалтерии
 *
 * Отдел бухгалтерии попросил изменить вид зарплаты
 *
 * @author Ilya Kaltygin
 */
public class ReportAcc implements Report {
    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private Store store;

    public ReportAcc(Store store) {
        this.store = store;
    }

    /**
     * Генерация отчета
     * @param filter Предикат
     * @return Отчет
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary() * 1.2).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
