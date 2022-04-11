package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тестирование IO. Интеграционные тесты
 * @author Ilya Kaltygin
 * @version 1.1
 */
public class AnalizyTest {

    /**
     * Для создания файлов во временной директории нужно использовать
     * класс org.unit.rules.TemporaryFolder.
     */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter pr = new PrintWriter(source)) {
            pr.println("200 10:56:01");
            pr.println("200 10:57:01");
            pr.println("400 10:58:01");
            pr.println("200 10:59:01");
        }
            Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(target))) {
            br.lines()
                    .forEach(sb::append);
        }
        assertThat(sb.toString(), is("10:58:01;10:59:01;"));
    }

    @Test
    public void whenServerAlwaysRunning() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter pr = new PrintWriter(source)) {
            pr.println("200 10:56:01");
            pr.println("200 10:57:01");
            pr.println("200 10:58:01");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(target))) {
            br.lines()
                    .forEach(sb::append);
        }
        assertThat(sb.toString(), is(""));
    }
}