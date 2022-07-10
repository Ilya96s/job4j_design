package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Файловый класс
 *
 * @author Ilya Kaltygin
 */
public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    /**
     * Конструктор принимает директорию с файлами для кэширования
     * @param cachingDir директория где расположены кэшируемые файлы
     */
    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    /**
     * Метод загружает данные файла в кэш
     * @param key Имя файла
     * @return Содержимое файла
     */
    @Override
    protected String load(String key) {
        String result = "";
        File directory = new File(cachingDir);
        try (BufferedReader reader = new BufferedReader(new FileReader(directory + key))) {
            StringBuilder builder = new StringBuilder();
            reader.lines()
                    .forEach(s -> builder.append(s).append(System.lineSeparator()));
            result = builder.toString();
            put(key, result);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return result;
    }
}
