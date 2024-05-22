package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnector {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "mini_orm_exercise_db";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "001154";


    public static void createConnection() throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", USERNAME);
        properties.setProperty("password", PASSWORD);

        connection = DriverManager.getConnection(URL + DATABASE_NAME, properties);

    }

    public static Connection getConnection() {
        return connection;
    }
}
