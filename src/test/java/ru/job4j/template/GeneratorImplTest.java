package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Шаблонизатор
 * Генератор получает шаблон вида "I am a ${name}, Who are ${subject}?"
 * Слова записанные в фигурных скобках ${..} - это ключи, которые нужно заменить.
 * В генератор передаются значения этих ключей в виде пары ключ - значение.
 * Генератор должен вернуть строку: "I am a Petr Arsentev, Who are you?"
 *  Программа должна учитывать, что в шаблоне есть ключи, которых нет в карте и кидать исключение.
 *  Программа должна учитывать, что в карте есть лишние ключи и тоже кидать исключение.
 *
 * @author Ilya Kaltygin
 */
public class GeneratorImplTest {

    @Ignore
    @Test
    public void whenAllCorrect() {
        Generator generator = new GeneratorImpl();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan");
        map.put("subject", "you");
        String template = "I am a ${name}, Who are ${subject}?";
        String expected = "I am a Ivan, Who are you?";
        String result = generator.produce(template, map);
        assertThat(result, is(expected));
    }

    /**
     * В шаблоне есть ключи, которых нет в карте
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenKeysAreNotIntTheMap() {
        Generator generator = new GeneratorImpl();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan");
        map.put("subject", "you");
        String template = "I am a ${names}, Who are ${subj}?";
        generator.produce(template, map);
    }

    /**
     * В карте лишние ключи
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenExtraKeysInTheMap() {
        Generator generator = new GeneratorImpl();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan");
        map.put("subject", "you");
        map.put("subj", "Example");
        String template = "I am a ${name}, Who are ${subject}?";
        generator.produce(template, map);
    }
}