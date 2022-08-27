package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Утверждения с исключениями
 *
 * @author Ilya Kaltygin
 */
class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                /*Проверяем класс исключения*/
                .isInstanceOf(IllegalStateException.class)
                /*Проверяем наличие конкретных слов в сообщении*/
                .hasMessageContaining("no data");
    }

    @Test
    void checkParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                /*Проверяем класс исключения*/
                .isInstanceOf(IllegalArgumentException.class)
                /*Проверяем наличие конкретных слов в сообщении*/
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void whenNameDoesNotContainTheEqualsCharacter() {
        NameLoad nameLoad = new NameLoad();
        String name = "example";
        assertThatThrownBy(() -> nameLoad.parse(name))
                /*Проверяем класс исключения*/
                .isInstanceOf(IllegalArgumentException.class)
                /*Проверяем, что в сообщении есть соответствующие параметры:*/
                .hasMessageContaining(name)
                /*Проверяем наличие конкретных слов в сообщении*/
                .hasMessageContaining("does not contain the symbol \"=\"");
    }

    @Test
    void whenNameStartWithEqualsCharacter() {
        NameLoad nameLoad = new NameLoad();
        String name = "=example";
        assertThatThrownBy(() -> nameLoad.parse(name))
                /*Проверяем класс исключения*/
                .isInstanceOf(IllegalArgumentException.class)
                /*Проверяем, что в сообщении есть соответствующие параметры:*/
                .hasMessageContaining(name)
                /*Проверяем наличие конкретных слов в сообщении*/
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void whenNameEndWithEqualsCharacter() {
        NameLoad nameLoad = new NameLoad();
        String name = "example=";
        assertThatThrownBy(() -> nameLoad.parse(name))
                /*Проверяем класс исключения*/
                .isInstanceOf(IllegalArgumentException.class)
                /*Проверяем, что в сообщении есть соответствующие параметры:*/
                .hasMessageContaining(name)
                /*Проверяем наличие конкретных слов в сообщении*/
                .hasMessageContaining("does not contain a value");
    }

}