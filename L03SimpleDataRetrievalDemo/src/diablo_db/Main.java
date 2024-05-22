package diablo_db;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
public class Main {

    final private static String PROPERTY_USER_LABEL = "user";
    final private static String PROPERTY_PASSWORD_LABEL = "password";
    final private static String PROPERTY_USER = "root";
    final private static String PROPERTY_PASSWORD = "001154";
    final private static String URL = "jdbc:mysql://localhost:3306/diablo";
    final private static String SQL_COMMAND =
                    "SELECT user_name, CONCAT(first_name, ' ', last_name), COUNT(ug.user_id) FROM users " +
                    "JOIN users_games ug on users.id = ug.user_id " +
                    "WHERE user_name = ? " +
                    "GROUP BY user_name, first_name, last_name";
    final private static String OUTPUT_FORMAT = "User: %s%n" +
                                                "%s has played %d games";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty(PROPERTY_USER_LABEL, PROPERTY_USER);
        properties.setProperty(PROPERTY_PASSWORD_LABEL, PROPERTY_PASSWORD);

        Connection connection = DriverManager.getConnection(URL, properties);

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_COMMAND);

        String user_name = sc.nextLine();
        preparedStatement.setString(1, user_name);

        ResultSet rs = preparedStatement.executeQuery();

        boolean next = rs.next();
        if (next) {
            System.out.printf(OUTPUT_FORMAT, rs.getString(1), rs.getString(2), rs.getInt(3));
        }else {
            System.out.println("No such user exists");
        }

        connection.close();


    }

}
