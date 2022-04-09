package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
    String path = "data/correctKey.properties";
    Config config = new Config(path);
    config.load();
    assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyIsWrong() {
        String path = "data/illegalKey.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is(Matchers.nullValue()));
    }
}