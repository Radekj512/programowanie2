package library.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect_db {
    static Connection con=null;
    public static Connection getConnection()
    {
        if (con != null) return con;
        // get db, user, pass from settings file
        return getConnection("LKZJ3Ic98j", "LKZJ3Ic98j", "lVtTIHxTvn");
    }

    private static Connection getConnection(String db_name,String user_name,String password)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/"+db_name+"?user="+user_name+"&password="+password+"&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return con;
    }
}
