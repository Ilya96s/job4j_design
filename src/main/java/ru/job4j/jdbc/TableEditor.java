package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

/**
 *  Закомство с интерфейсом Statement. Создание sql запросов
 *
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    /**
     * Метод регистрирует драйвер в системе и создает подключение к базе данных
     * @return Объект соединения с базой данных
     * @throws Exception Исключение
     */
    private Connection getConnection() throws Exception {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, login, password);
    }

    /**
     * Инициализация соединения с базой данных
     * @throws Exception Исключение
     */
    private void initConnection() throws Exception {
        connection = getConnection();
    }

    /**
     * Метод создает пустую таблицу с указанным именем
     * @param tableName Имя таблицы
     * @throws Exception Исключение
     */
    public void createTable(String tableName) throws Exception {
        String sql = String.format("create table %s();", tableName);
        createStatement(sql);
    }

    /**
     * Метод удаляет таблицу по указанному имени
     * @param tableName Имя таблицы
     * @throws Exception Исключение
     */
    public void dropTable(String tableName) throws Exception {
        String sql = String.format("drop table %s;", tableName);
        createStatement(sql);
    }

    /**
     * Метод добавляет столбец в таблицу
     * @param tableName Имя таблицы
     * @param columnName Имя столбца
     * @param type Тип столбца
     * @throws Exception Исключение
     */
    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format("alter table %s add column %s %s",
                tableName, columnName, type);
        createStatement(sql);
    }

    /**
     * Метод удалет столбец из таблицы
     * @param tableName Имя таблицы
     * @param columnName Имя столбца
     * @throws Exception Исключение
     */
    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format("alter table %s drop column %s",
                tableName, columnName);
        createStatement(sql);
    }

    /**
     * Метод изменяет имя столбца в таблицы
     * @param tableName Имя таблицы
     * @param columnName Имя столбца, который необходимо изменить
     * @param newColumnName Новое имя столбца
     * @throws Exception Исключение
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "alter table %s rename column %s to %s",
                tableName, columnName, newColumnName);
        createStatement(sql);
    }

    /**
     * Метод выводит схему таблицы
     */
    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Метод выполнения запроса
     * @param sql SQL запрос
     * @throws Exception Исключение
     */
    private void createStatement(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            try (Connection connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("login"),
                    config.getProperty("password"))) {
                TableEditor tableEditor = new TableEditor(config);
                tableEditor.createTable("test_table");
                System.out.println("Добавление столбца в талицу");
                tableEditor.addColumn("test_table", "column_1", "varchar(50)");
                System.out.println(getTableScheme(connection, "test_table"));
                System.out.println("Изменение столбца таблицы");
                tableEditor.renameColumn("test_table", "column_1", "new_column_2");
                System.out.println(getTableScheme(connection, "test_table"));
                System.out.println("Удаление столбца из таблицы");
                tableEditor.dropColumn("test_table", "new_column_2");
                System.out.println(getTableScheme(connection, "test_table"));
                tableEditor.dropTable("test_table");
            }
        }
    }
}
