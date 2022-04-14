package ru.job4j.io.duplicates;

import java.util.Objects;

/**
 * Меодель данный файла, которая описывается 2 свойствами: размером и именем
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class FileProperty {

    private long size;

    private String name;

    public FileProperty(long size, String name) {
        this.size = size;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return size == that.size && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, name);
    }
}
