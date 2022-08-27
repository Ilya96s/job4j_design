package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list)
                .isNotEmpty()
                /*начинается с последовательности:*/
                .startsWith("first")
                /*заканчивается на последовательность:*/
                .endsWith("five")
                /*не содержит ни одного из:*/
                .doesNotContain("six")
                /*размер:*/
                .hasSize(5)
                /*содержит только это и только в указанном порядке:*/
                .containsExactly("first", "second", "three", "four", "five");

                /*хотя бы один элемент выполняет условие*/
                assertThat(list).anySatisfy(e -> {
                    assertThat(e).contains("first");
                });
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set)
                .hasSize(5)
                .containsExactlyInAnyOrder("second", "five", "first", "four", "three")
                .containsAnyOf("1", "2", "second");

        assertThat(set)
                .isNotNull()
                /*хотя бы один элемент выполняет условие*/
                .anySatisfy(e -> {
                    assertThat(e).startsWith("f");
                    assertThat(e).endsWith("e");
                })
                .allMatch(e -> e.length() > 3)
                .anyMatch(e -> e.startsWith("s"));
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map)
                .hasSize(5)
                /*содержит ключи*/
                .containsKeys("first", "five")
                /*содержит значения*/
                .containsValues(0, 1, 2, 3, 4)
                /*содержит пару ключ-значение*/
                .containsEntry("first", 0)
                /*содержит пару ключ-значение*/
                .containsEntry("second", 1);
    }

}