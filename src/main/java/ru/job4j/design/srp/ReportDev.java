package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

/**
 * Отдел программистов
 *
 * Отдел программистов потребовал отчеты в виде html
 *
 * @author Ilya Kaltygin
 */
public class ReportDev implements Report {
    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private Store store;

    public ReportDev(Store store) {
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
        text.append("<html>")
                .append(System.lineSeparator())
                .append("<head>Name; Hired; Fired; Salary;</head>")
                .append(System.lineSeparator())
                .append("<body>");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired().getTime()).append(";")
                    .append(employee.getFired().getTime()).append(";")
                    .append(employee.getSalary() * 1.2).append(";")
                    .append(System.lineSeparator());
        }
        text.append("</body>")
                .append(System.lineSeparator())
                .append("</html>");
        return text.toString();
    }
}
