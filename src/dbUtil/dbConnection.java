package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnection {

    Connection c=null;
    Statement stmt = null;
    private static final String USERNAME="root";
    private static final String PASSWORD="1234";
    private static final String URL = "jdbc:mysql://localhost:3306/hotelsystem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   

    public static dbConnection con;
    public static Connection getConnection() throws SQLException
    {

                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    return DriverManager.getConnection(URL,USERNAME,PASSWORD);

                }
                catch(ClassNotFoundException ex){
                    ex.printStackTrace();
                }
                return null;
    }

}
