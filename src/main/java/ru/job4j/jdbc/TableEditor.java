package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private Connection getConnection() throws Exception {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, login, password);
    }

    private void initConnection() throws Exception {
        connection = getConnection();
    }

    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "create table if not exist %s();",
                    tableName
            );
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "drop table %s;",
                    tableName
            );
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table %s add column %s %s",
                    tableName, columnName, type
            );
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table %s drop column %s",
                    tableName, columnName
            );
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table %s rename column %s to %s",
                    tableName, columnName, newColumnName
            );
            statement.execute(sql);
        }
    }

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

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = new FileInputStream("app.properties")) {
            properties.load(in);
        }
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable("table_test");
        System.out.println(getTableScheme(tableEditor.connection, "test_table"));
    }
}
