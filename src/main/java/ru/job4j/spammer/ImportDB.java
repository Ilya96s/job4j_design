package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Знакомство с интерфейсом PrepareStatement. Создание параметризованных SQL запросов
 *
 * @author Ilya Kaltygin
 * @version 1.2
 */
public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * Метод считывает данные из файла, создает объекты на основе полученных данных и сохраняет их в список
     * @return Список объектов типа User
     * @throws Exception Исключение
     */
    public List<User> load() throws Exception {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            String line;
            while ((line = rd.readLine()) != null) {
                lineValidation(line);
                String[] array = line.split(";", 2);
                StringBuilder sb = new StringBuilder(array[1]);
                if (sb.charAt(sb.length() - 1) == ';') {
                    sb.deleteCharAt(sb.length() - 1);
                }
                User user = new User(array[0], sb.toString());
                users.add(user);
            }
        }
        return users;
    }

    /**
     * Метод проверяет строку на соответсиве шаблона
     * @param line Проверяемая строка
     */
    private static void lineValidation(String line) {
        String[] array = line.split(";", 2);
        if (array[0].isEmpty() || array[1].isEmpty()) {
            throw new IllegalArgumentException("data format should be: name;email;");
        }
    }

    /**
     * Метод принимает на вход список с объектами, создает подключение в базе данных и заносит объекты в базу данных
     * @param users Список объектов
     * @throws ClassNotFoundException Исключение
     * @throws SQLException Исключение
     */
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users(name, email) values(?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "data/spammer/dump.txt");
        db.save(db.load());
    }
}
