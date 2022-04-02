package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Определение разницы между начальным и измененным состоянием множества
 * @author  Ilya Kaltygin
 * @version 1.0
 */
public class Analize {

    /**
     * Метод для анализа данных
     * Определяет сколько новых пользователей добавлено, сколько изменено, сколько удалено.
     * @param previous Множество начальных состояний
     * @param current Множество текущий состояний
     * @return Возвращает информацию об изменениях
     */
    public static Info diff(Set<User> previous, Set<User> current) {

        Info info = new Info(0, 0, 0);

        Map<Integer, String> map = new HashMap<>();

        for (User el : previous) {
            map.put(el.getId(), el.getName());
        }

        for (User el :current) {
            if (!map.containsKey(el.getId())) {
                info.added++;
            } else if (!map.containsValue(el.getName())) {
                info.changed++;
            }
        }

        info.deleted = previous.size() - current.size() + info.added;

        return info;
    }
}
