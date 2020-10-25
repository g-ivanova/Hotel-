
package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnection {

    Connection c=null;
    Statement stmt = null;
    private static final String SQCONN = "jdbc:sqlite:HotelSystem.sqlite";

    public static dbConnection con;
    public static Connection getConnection() throws SQLException
    {

                try{
                    Class.forName("org.sqlite.JDBC");
                    return DriverManager.getConnection("jdbc:sqlite:HotelSystem.db");

                }
                catch(ClassNotFoundException ex){
                    ex.printStackTrace();
                }
                return null;
    }

}
