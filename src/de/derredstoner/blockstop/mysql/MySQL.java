package de.derredstoner.blockstop.mysql;

import de.derredstoner.blockstop.BlocksTop;

import java.sql.*;

public class MySQL {

    private static Connection connection;

    public static boolean connect() {
        String host = BlocksTop.getInstance().getConfig().getString("mysql.host");
        int port = BlocksTop.getInstance().getConfig().getInt("mysql.port");
        String user = BlocksTop.getInstance().getConfig().getString("mysql.user");
        String database = BlocksTop.getInstance().getConfig().getString("mysql.database");
        String password = BlocksTop.getInstance().getConfig().getString("mysql.password");

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&autoReconnectForPools=true&useSSL=false&interactiveClient=true&characterEncoding=UTF-8", user, password);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static ResultSet getEntries() {
        try {
            String query = "SELECT * FROM `playerblocks`";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
