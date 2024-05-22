package exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static exercise.Constants.getSQLConnection;



public class P05ChangeTownNamesCasing {

    private static final String SQL_COMMAND =
            """
                    SELECT UPPER(name) FROM towns
                    WHERE country = ?
                    """;
    private static final String FORMAT_AFFECTED_TOWNS = "%d town names were affected.\n" ;
    private static final String TEXT_NO_AFFECTED_TOWNS = "No town names were affected." ;

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String country = sc.nextLine();
        Connection connection = getSQLConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_COMMAND);
        preparedStatement.setString(1, country);
        ResultSet rs = preparedStatement.executeQuery();

        int countTowns = 0;
        List<String> towns = new ArrayList<>();

        while (rs.next()){
            countTowns++;
            towns.add(rs.getString(1));
        }
        if (!towns.isEmpty()) {
            System.out.printf(FORMAT_AFFECTED_TOWNS, countTowns);
            System.out.println(towns);
        }else {
            System.out.println(TEXT_NO_AFFECTED_TOWNS);
        }

        connection.close();
    }
}
