package exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static exercise.Constants.*;

public class P07PrintAllMinionsNames {


    private static final String GET_ALL_MINIONS_ASC = """
                                                      SELECT name FROM minions
                                                      ORDER BY id""";
    private static final String GET_ALL_MINIONS_DESC = """
                                                      SELECT name FROM minions
                                                      ORDER BY id DESC""";
    private static final String COUNT_MINIONS = """
                                                SELECT COUNT(*)
                                                FROM minions""";

    public static void main(String[] args) throws SQLException {
        Connection connection = getSQLConnection();
        PreparedStatement countMinions = connection.prepareStatement(COUNT_MINIONS);
        countMinions.executeQuery();
        ResultSet minionsCount = countMinions.getResultSet();
        minionsCount.next();
        int minions = minionsCount.getInt(1);


        PreparedStatement allMinionsASC = connection.prepareStatement(GET_ALL_MINIONS_ASC);
        allMinionsASC.executeQuery();
        ResultSet minionsASC = allMinionsASC.getResultSet();
        PreparedStatement allMinionsDesc = connection.prepareStatement(GET_ALL_MINIONS_DESC);
        allMinionsDesc.executeQuery();
        ResultSet minionsDESC = allMinionsDesc.getResultSet();


        for (int i = 0; i < minions / 2; i++) {
            minionsASC.next();
            minionsDESC.next();

            System.out.println(minionsASC.getString(1));
            System.out.println(minionsDESC.getString(1));

        }

        connection.close();

    }
}
