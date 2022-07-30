package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.design.srp.ReportEngine.DATE_FORMAT;
import static ru.job4j.design.srp.ReportAcc.INDEX;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    /**
     * Отчет для HR
     */
    @Test
    public void whenGeneratedReportFromHR() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 150);
        Employee worker2 = new Employee("Sergey", now, now, 130);
        store.add(worker1);
        store.add(worker2);
        Report engine = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    /**
     * Отчет для бухгалтерии
     */
    @Test
    public void whenGeneratedReportFromAccounting() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 150);
        store.add(worker);
        Report engine = new ReportAcc(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(ReportAcc.DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(ReportAcc.DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary() * INDEX).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    /**
     * Отчет для программистов
     */
    @Test
    public void whenGeneratedReportFromDevelopers() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportDev(store);
        StringBuilder expect = new StringBuilder()
                .append("<html>")
                .append(System.lineSeparator())
                .append("<head>Name; Hired; Fired; Salary;</head>")
                .append(System.lineSeparator())
                .append("<body>")
                .append(worker.getName()).append(";")
                .append(worker.getHired().getTime()).append(";")
                .append(worker.getFired().getTime()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append("</body>")
                .append(System.lineSeparator())
                .append("</html>");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    /**
     * Отчет в формате XML
     */
    @Test
    public void whenGenerateReportInXmlFormat() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportXML(store);
        String fired = ReportXML.DATE_FORMAT.format(worker.getFired().getTime());
        String hired = ReportXML.DATE_FORMAT.format(worker.getHired().getTime());
        String exp = """
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<employees>
    <employees>
        <employee>
            <fired>%s</fired>
            <hired>%s</hired>
            <name>%s</name>
            <salary>%s</salary>
        </employee>
    </employees>
</employees>     
""".formatted(fired, hired, worker.getName(), worker.getSalary());
        assertThat(engine.generate(em -> true)).isEqualTo(exp);
    }

    /**
     * Отчет в формате JSON
     */
    @Test
    public void whenGenerateReportInJSONFormat() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportJSON(store);
        Gson gson = new GsonBuilder().create();
        String fired = gson.toJson(worker.getFired());
        String hired = gson.toJson(worker.getHired());
        String exp = """
                [{"name":"%s","hired":%s,"fired":%s,"salary":%s}]""".formatted(worker.getName(), hired, fired, worker.getSalary());
        assertThat(engine.generate(em -> true)).isEqualTo(exp);
    }
}