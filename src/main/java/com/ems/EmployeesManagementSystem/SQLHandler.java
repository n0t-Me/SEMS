package com.ems.EmployeesManagementSystem;

import java.sql.*;

public class SQLHandler {
    public static String dbHost = "localhost";
    public static int dbPort = 3306;
    public static String dbName = "employees";
    public static String dbUserName = "xkr";
    public static String dbPassword = "password";

    public static SQLResult executeSQL(String query, boolean isUpdate) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mariadb://" + dbHost + ":" + dbPort + "/" + dbName,
                        dbUserName, dbPassword);
                Statement stmt = conn.createStatement()
        ) {
            System.out.println("[+] Query: " + query);
            System.out.println("[+] Query lenght: " + query.length());
            if (isUpdate) {
                int i = stmt.executeUpdate(query);
                SQLResult sqlResult = new SQLResult();
                sqlResult.count(i);
                return sqlResult;
            } else {
                ResultSet resultSet = stmt.executeQuery(query);
                SQLResult sqlResult = new SQLResult();
                sqlResult.result(resultSet);
                return sqlResult;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static boolean isValidSettings() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mariadb://" + dbHost + ":" + dbPort + "/" + dbName,
                    dbUserName, dbPassword);
            Statement stmt = conn.createStatement();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean setNewSettings(String dbHost, int dbPort, String dbName, String dbUserName, String dbPassword) {
        String old_dbHost = SQLHandler.dbHost;
        int old_dbPort = SQLHandler.dbPort;
        String old_dbName = SQLHandler.dbName;
        String old_dbUserName = SQLHandler.dbUserName;
        String old_dbPassword = SQLHandler.dbPassword;
        SQLHandler.dbHost = dbHost;
        SQLHandler.dbPort = dbPort;
        SQLHandler.dbPassword = dbPassword;
        SQLHandler.dbName = dbName;
        SQLHandler.dbUserName = dbUserName;
        if (SQLHandler.isValidSettings()) {
            return true;
        } else {
            SQLHandler.dbHost = old_dbHost;
            SQLHandler.dbPort = old_dbPort;
            SQLHandler.dbPassword = old_dbPassword;
            SQLHandler.dbName = old_dbName;
            SQLHandler.dbUserName = old_dbUserName;
            return false;
        }
    }
}
//jdbc handler