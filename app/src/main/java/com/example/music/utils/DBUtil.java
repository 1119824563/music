package com.example.music.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBUtil
{

    private  String name=null;
    private  String pass=null;

    public DBUtil(String m,String p){
        this.name=m;
        this.pass=p;
    }
    private static Connection getSQLConnection(String ip, String user, String pwd, String db)
    {
        Connection con = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:jtds:sqlserver://" + ip + ":1433/" + db + ";charset=utf8", user, pwd);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return con;
    }

    public String QuerySQL()
    {
        String result = "";
        try
        {
            Connection conn= getSQLConnection("13.94.60.177", "sa", "jinyintao159.", "music");
            String sql = "select Username from usename_password where Username=? and  Password=?";

            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, name);
            stat.setString(2, pass);
            ResultSet rs = stat.executeQuery();
            while (rs.next())
            {
                result= "1" ;
                String s1 = rs.getString("Username");
                result += s1 + "  -  "  + "\n";
                System.out.println(s1 + "  -  " );
            }
            rs.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            result += "查询数据异常!" + e.getMessage();
        }
        return result;
    }
    /*public static String testSQL()
    {
        String result = "字段1  -  字段2\n";
        try
        {
            Connection conn = getSQLConnection("13.94.60.177", "sa", "jinyintao159.", "music");
            String sql = "select * from usename_password";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                // <code>ResultSet</code
                String s1 = rs.getString("Username");
                String s2 = rs.getString("Password");
                result += s1 + "  -  " + s2 + "\n";
                System.out.println(s1 + "  -  " + s2);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            //result += "查询数据异常!" + e.getMessage();
        }
        return result;
        //return bool;
    }*/

    public static void main(String[] args)
    {
        DBUtil db=new DBUtil("1","123");
        db.QuerySQL();
    }
}

