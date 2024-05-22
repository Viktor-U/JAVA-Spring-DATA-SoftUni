package exercise;


import java.sql.*;
import static exercise.Constants. *;

public class P02GetVillainsNames {


    final private static String SQL_COMMAND =
            """
                    SELECT name, COUNT(distinct mv.minion_id) AS c FROM villains V
                    join minions_db.minions_villains mv on V.id = mv.villain_id
                    GROUP BY name
                    HAVING c > 15
                    ORDER BY c DESC""";
    final private static String OUTPUT_FORMAT = "%s %d";

    public static void main(String[] args) throws SQLException {

        final Connection connection = getSQLConnection();

        PreparedStatement ps = connection.prepareStatement(SQL_COMMAND);

        ResultSet rs = ps.executeQuery();
        rs.next();
        System.out.printf(OUTPUT_FORMAT, rs.getString(1), rs.getInt(2));

        connection.close();

    }
}





















