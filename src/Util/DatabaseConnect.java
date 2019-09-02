/*
 *
 *********** Appointment Scheduler **********
 ************ Frank Michael Tofani ***********
 ************* WGU Class C195 Final ***********
 *
*/

package Util;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author micha
 */

public class DatabaseConnect {
    private static Connection dbConnect;
    
    public DatabaseConnect() {}
    
    /* Establish connection with database
          Connection String
          Server name:  52.206.157.109 
          Database name:  U04xYO
          Username:  U04xYO
          Password:  53688376998
    */
    
    public static void dbConnect() {
        System.out.println("Connecting to database U04xYO");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbConnect = DriverManager.getConnection("jdbc:mysql://52.206.157.109/U04xYO", "U04xYO", "53688376998");
            System.out.println("MySQL database successfully connected");
        }catch (ClassNotFoundException ex) {
            System.out.println("MySQL Library not found \n"  + "Error: " + ex.getMessage());
        }catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
        }
    }
    
    public static Connection getDbConnection() {
        return dbConnect;
    }
    
    public static void closeDbConnection() {
        try {
            dbConnect.close();
            System.out.println("Database connection successfully closed");
        }catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
        }

    }

}
