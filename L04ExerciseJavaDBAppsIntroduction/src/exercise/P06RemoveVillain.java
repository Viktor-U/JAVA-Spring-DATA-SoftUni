package exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import static exercise.Constants.getSQLConnection;

public class P06RemoveVillain {


    private static final String GET_VILLAIN_BY_ID = """ 
                                                     SELECT name FROM villains
                                                     WHERE id = ?""";
    private static final String DELETE_VILLAIN = """
                                                    DELETE villains FROM villains
                                                    WHERE id = ?""";
    private static final String DELETE_MINIONS_VILLAIN = """
                                                        DELETE minions_villains FROM minions_villains
                                                        WHERE villain_id = ?""";
    private static final String FORMAT_OUTPUT = "%s was deleted\n%d minions released";
    private static final String GET_MINION_COUNT = """
                                                   SELECT COUNT(*) FROM minions_villains
                                                   WHERE villain_id = ?""";

    public static void main(String[] args) throws SQLException {
        int villainId = Integer.parseInt((new Scanner(System.in)).nextLine());

        Connection connection = getSQLConnection();
        String villainName;
        int minionCount;
        try {
            PreparedStatement getVillainById = connection.prepareStatement(GET_VILLAIN_BY_ID);
            getVillainById.setInt(1,villainId);
            ResultSet villainNameSet = getVillainById.executeQuery();
            villainNameSet.next();

            PreparedStatement getMinionCount = connection.prepareStatement(GET_MINION_COUNT);
            getMinionCount.setInt(1, villainId);
            ResultSet minionCountNameSet = getMinionCount.executeQuery();
            minionCountNameSet.next();


            villainName = villainNameSet.getString(1);
            minionCount = Integer.parseInt(minionCountNameSet.getString(1));

            PreparedStatement deleteVillainStatement = connection.prepareStatement(DELETE_VILLAIN);
            deleteVillainStatement.setInt(1, villainId);
            PreparedStatement deleteMinion_villainsStatement = connection.prepareStatement(DELETE_MINIONS_VILLAIN);
            deleteMinion_villainsStatement.setInt(1, villainId);

            deleteMinion_villainsStatement.executeUpdate();
            deleteVillainStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("No such villain was found");
            connection.close();
            return;
        }
        System.out.printf(FORMAT_OUTPUT, villainName, minionCount);
        connection.close();


    }
}
