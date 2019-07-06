package com.byrfb.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

import com.byrfb.database.IDatabaseConnector;

/*sqllite datbasenin sahip olmasý gereken connect metotu burda bulunmakta
 * 
 * 
 */
public class SqLiteDatabase implements IDatabaseConnector {
	
	static String url = "jdbc:sqlite:" +System.getProperty("user.dir")+ "/db/chinook.db";
	
    /**
     * Connect to a sample database
     */
	   public Connection connect() {
	        // SQLite connection string
	        String url = SqLiteDatabase.url;
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
    

}
