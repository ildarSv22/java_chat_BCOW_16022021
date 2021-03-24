package server;

import java.sql.*;

public class DBHandler {
    private static Connection connection;
    private static PreparedStatement psGetNickname;
    private static PreparedStatement psRegistration;


    public static boolean connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB.chat.db");
            prepareAllStatements();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static  void prepareAllStatements () throws SQLException {
        psGetNickname = connection.prepareStatement("SELECT nickname FROM users Where login = ? AND password = ?");
        psRegistration = connection.prepareStatement("INSERT INTO users (login, password, nickname) VALUES (?, ?, ?);");
    }

    public static String getNickNameByLoginAndPassword (String login, String password){
        String nickname = null;
        try {
            psGetNickname.setString(1,login);
            psGetNickname.setString(2, password);
            ResultSet result = psGetNickname.executeQuery();
                while (result.next()){
                     nickname = result.getString(1);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } return nickname;

    }

    public static boolean registration (String login, String password, String nickname) {
        try {
            psRegistration.setString(1, login);
            psRegistration.setString(2,password);
            psRegistration.setString(3, nickname);
            psRegistration.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void disconnect() {
        try {
            psRegistration.close();
            psGetNickname.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
