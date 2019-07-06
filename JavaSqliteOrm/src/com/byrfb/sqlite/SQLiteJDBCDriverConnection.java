package com.byrfb.sqlite;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 

/*
 * 
 * 
 * bu avel s�n�ftai connect mtodu her t�rk� ba�lan�p kesild�i i�in i�lem g�rmedi 
 */
/**
 *
 * @author sqlitetutorial.net
 */
public class SQLiteJDBCDriverConnection {
     /**
     * Connect to a sample database
     */
    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:" +System.getProperty("user.dir")+ "/db/chinook.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        connect();
    }
}