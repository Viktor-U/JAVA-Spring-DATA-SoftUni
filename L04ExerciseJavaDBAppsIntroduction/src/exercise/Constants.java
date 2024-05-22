package exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum Constants {
    ;
    final public static String PROPERTY_USER_LABEL = "user";
    final public static String PROPERTY_PASSWORD_LABEL = "password";
    final public static String PROPERTY_USER = "root";
    final public static String PROPERTY_PASSWORD = "001154";
    final public static String URL = "jdbc:mysql://localhost:3306/minions_db";

    static Connection getSQLConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty(PROPERTY_USER_LABEL, PROPERTY_USER);
        properties.setProperty(PROPERTY_PASSWORD_LABEL, PROPERTY_PASSWORD);
        return DriverManager.getConnection(URL, properties);
    }
}
