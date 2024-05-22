package exercise;

import java.sql.*;
import java.util.Scanner;

import static exercise.Constants.*;

public class P04AddMinion {
    final private static String ADD_NEW_SERVANT =
            """
                    INSERT INTO minions_villains (minion_id, villain_id)
                    VALUES ((SELECT id FROM minions WHERE name = ?),
                            (SELECT id FROM villains WHERE name = ?))
                    """;
    final private static String GET_TOWN_BY_NAME ="SELECT t.id FROM towns t WHERE t.name = ? ";
    final private static String GET_VILLAIN_BY_NAME ="SELECT id FROM villains WHERE name = ? ";
    final private static String INSERT_INTO_TOWNS ="INSERT INTO towns (name) VALUE (?) ";
    private static final String INSERT_INTO_VILLAINS = "INSERT INTO villains (name, evilness_factor) VALUE (?, 'evil')";
    private static final String INSERT_MINION_BY_NAME = """
                                                      INSERT INTO minions (name, age, town_id)
                                                      VALUE (?,
                                                             ?,
                                                             (SELECT id FROM towns WHERE towns.name = ?))""";
    final private static String FORMAT_NEW_TOWN_ADD = "Town %s was added to the database.%n";
    final private static String FORMAT_NEW_VILLAIN_ADD = "Villain %s was added to the database.%n";
    final private static String FORMAT_ADD_SERVANT = "Successfully added %s to be minion of %s";

    public static void main(String[] args) throws SQLException {

        Connection connection = getSQLConnection();

        Scanner sc = new Scanner(System.in);
        String[] minionInfo = sc.nextLine().split(" ");
        String villainName = sc.nextLine().split(": ")[1];
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String minionTown = minionInfo[3];


        PreparedStatement townsStatement = connection.prepareStatement(GET_TOWN_BY_NAME);
        townsStatement.setString(1, minionTown);
        ResultSet townSet = townsStatement.executeQuery();

        if (!townSet.next()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_TOWNS);
            statement.setString(1, minionTown);
            statement.executeUpdate();
            System.out.printf(FORMAT_NEW_TOWN_ADD, minionTown);
        }

        PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_BY_NAME);
        villainStatement.setString(1, villainName);
        ResultSet villainSet = villainStatement.executeQuery();

        if (!villainSet.next()){
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_VILLAINS);
            statement.setString(1, villainName);
            statement.executeUpdate();
            System.out.printf(FORMAT_NEW_VILLAIN_ADD, villainName);
        }

        PreparedStatement minionStatement = connection.prepareStatement(INSERT_MINION_BY_NAME);
        minionStatement.setString(1, minionName);
        minionStatement.setInt(2, minionAge);
        minionStatement.setString(3, minionTown);

        PreparedStatement minions_villainsStatement = connection.prepareStatement(ADD_NEW_SERVANT);
        minions_villainsStatement.setString(1, minionName);
        minions_villainsStatement.setString(2, villainName);
        minions_villainsStatement.executeUpdate();

        System.out.printf(FORMAT_ADD_SERVANT, minionName, villainName);

        connection.close();


    }

}


















