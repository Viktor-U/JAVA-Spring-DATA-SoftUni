package exercise;

import java.sql.*;
import java.util.Scanner;

import static exercise.Constants.*;

public class P03GetMinionNames {

    final private static String SQL_COMMAND =
            """
                    SELECT v.name, m.name, m.age FROM villains v
                    JOIN minions_db.minions_villains mv on v.id = mv.villain_id
                    JOIN minions_db.minions m on m.id = mv.minion_id
                    WHERE v.id = ?""";
    final private static String VILLAIN_FORMAT = "Villain: %s%n";
    final private static String MINION_FORMAT = "%d. %s %s%n";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Connection connection = getSQLConnection();

        PreparedStatement ps = connection.prepareStatement(SQL_COMMAND);
        String id = sc.nextLine();
        ps.setInt(1, Integer.parseInt(id));

        ResultSet rs = ps.executeQuery();


        boolean isExist = true;


        for (int i = 1; rs.next(); i++) {
            if (i == 1){
                isExist = false;
                System.out.printf(VILLAIN_FORMAT, rs.getString(1));
            }
            System.out.printf(MINION_FORMAT, i, rs.getString(2), rs.getInt(3));

        }
        if (isExist) {
            System.out.printf("No villain with ID %s exists in the database.", id);

        }


        connection.close();

    }
}















