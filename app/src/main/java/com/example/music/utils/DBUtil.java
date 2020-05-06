package com.example.music.utils;

import com.example.music.manager.NetmusicManager;
import com.example.music.model.netmusic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil
{
   //实现连接
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

    //登录
    public String QuerySQL(String name,String pass)
    {
        String result = "0";
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

    //找回密码
    public String FindSQL(String name)
    {
        String result = "0";
        try
        {
            Connection conn= getSQLConnection("13.94.60.177", "sa", "jinyintao159.", "music");
            String sql = "select Password from usename_password where Username=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, name);
            ResultSet rs = stat.executeQuery();
            while (rs.next())
            {
                result= rs.getString("Password");
                //System.out.println(result);
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

    //注册
    public String register(String name,String pass)
    {
        String result = "0";
        try
        {
            Connection conn= getSQLConnection("13.94.60.177", "sa", "jinyintao159.", "music");
            String sql = "insert into usename_password(Username,Password) values(?,?)";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, name);
            stat.setString(2, pass);
            int low = stat.executeUpdate();
            if(low>0)
            {
                result="1";
                //System.out.println("成功");
            }
            stat.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    //存放本地音乐数据
    public String store(int musicid,String musicname,String singername,String album,String duration,String path,String lrcpath)
    {
        String result = "0";
        try
        {
            Connection conn= getSQLConnection("13.94.60.177", "sa", "jinyintao159.", "music");
            String sql = "insert into Localmusic(musicid,musicname,singername,album,duration,path,lrcpath) values(?,?,?,?,?,?,?)";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, musicid);
            stat.setString(2, musicname);
            stat.setString(3, singername);
            stat.setString(4, album);
            stat.setString(5, duration);
            stat.setString(6, path);
            stat.setString(7, lrcpath);
            int low = stat.executeUpdate();
            if(low>0)
            {
                result="1";
                //System.out.println("成功");
            }
            stat.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    //存放网络音乐数据
    public String storenet(int musicid,String musicname,String singername,String imageurl)
    {
        String result = "0";
        try
        {
            Connection conn= getSQLConnection("13.94.60.177", "sa", "jinyintao159.", "music");
            String sql = "insert into NETmusic(musicid,musicname,singername,imageurl) values(?,?,?,?) " +
                    "select * from NETmusic a where not exists(select 1 from NETmusic b where a.musicid=b.musicid)";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, musicid);
            stat.setString(2, musicname);
            stat.setString(3, singername);
            stat.setString(4, imageurl);
            int low = stat.executeUpdate();
            if(low>0)
            {
                result="1";
                //System.out.println("成功");
            }
            stat.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    //存放歌曲url
    public String storeurl(int musicid,String musicname,String musicurl)
    {
        String result = "0";
        try
        {
            Connection conn= getSQLConnection("13.94.60.177", "sa", "jinyintao159.", "music");
            String sql = "insert into NETurl(musicid,musicname,musicurl) values(?,?,?) " +
                    "select * from NETurl a where not exists(select 1 from NETurl b where a.musicid=b.musicid)";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, musicid);
            stat.setString(2, musicname);
            stat.setString(3, musicurl);
            int low = stat.executeUpdate();
            if(low>0)
            {
                result="1";
                //System.out.println("成功");
            }
            stat.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    //存放歌词
    public String storelrc(int musicid,String musicname,String lrcurl)
    {
        String result = "0";
        try
        {
            Connection conn= getSQLConnection("13.94.60.177", "sa", "jinyintao159.", "music");
            String sql = "insert into NETlrc(musicid,musicname,lrcurl) values(?,?,?) " +
                    "select * from NETlrc a where not exists(select 1 from NETlrc b where a.musicid=b.musicid)";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, musicid);
            stat.setString(2, musicname);
            stat.setString(3, lrcurl);
            int low = stat.executeUpdate();
            if(low>0)
            {
                result="1";
                //System.out.println("成功");
            }
            stat.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    //存放专辑
    public String storeablum(int musicid,String musicname,String singername,String album)
    {
        String result = "0";
        try
        {
            Connection conn= getSQLConnection("13.94.60.177", "sa", "jinyintao159.", "music");
            String sql = "insert into NETalbum(musicid,musicname,singername,album) values(?,?,?,?) " +
                    "select * from NETalbum a where not exists(select 1 from NETalbum b where a.musicid=b.musicid)";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, musicid);
            stat.setString(2, musicname);
            stat.setString(3, singername);
            stat.setString(4, album);
            int low = stat.executeUpdate();
            if(low>0)
            {
                result="1";
                //System.out.println("成功");
            }
            stat.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    //存放收藏列表
    public String storelove(int musicid,String musicname,String singername)
    {
        String result = "0";
        try
        {
            Connection conn= getSQLConnection("13.94.60.177", "sa", "jinyintao159.", "music");
            String sql = "insert into lovemusic(lovemusicname,lovesingername,lovemusicid) values(?,?,?)" +
                    "select * from lovemusic a where not exists(select 1 from lovemusic b where a.lovemusicid=b.lovemusicid)";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, musicname);
            stat.setString(2, singername);
            stat.setInt(3, musicid);
            int low = stat.executeUpdate();
            if(low>0)
            {
                result="1";
                //System.out.println("成功");
            }
            stat.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    //存放推荐列表
    public String storerec(int musicid,String musicname,String singername)
    {
        String result = "0";
        try
        {
            Connection conn= getSQLConnection("13.94.60.177", "sa", "jinyintao159.", "music");
            String sql = "insert into NETnewsong(musicid,musicname,musicsinger) values(?,?,?)" +
                    "select * from NETnewsong a where not exists(select 1 from NETnewsong b where a.musicid=b.musicid)";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, musicid);
            stat.setString(2, musicname);
            stat.setString(3, singername);
            int low = stat.executeUpdate();
            if(low>0)
            {
                result="1";
                //System.out.println("成功");
            }
            stat.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    //存放歌手热门
    public String storesingermusic(String musicname,String singername)
    {
        String result = "0";
        try
        {
            Connection conn= getSQLConnection("13.94.60.177", "sa", "jinyintao159.", "music");
            String sql = "insert into NETsingermusic(singername,singermusic) values(?,?)";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, musicname);
            stat.setString(2, singername);
            int low = stat.executeUpdate();
            if(low>0)
            {
                result="1";
                //System.out.println("成功");
            }
            stat.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    //获取收藏列表
    public String getlove()
    {
        String result = "0";
        try
        {
            Connection conn= getSQLConnection("13.94.60.177", "sa", "jinyintao159.", "music");
            String sql = "select * from lovemusic";
            PreparedStatement stat = conn.prepareStatement(sql);
            ResultSet rs = stat.executeQuery();
            while (rs.next())
            {
                int a=rs.getInt("lovemusicid");
                String b=rs.getString("lovemusicname");
                String c=rs.getString("lovesingername");
                netmusic netmusic=new netmusic();
                netmusic.setMusicid(a);
                netmusic.setSongname(b);
                netmusic.setSingername(c);
                NetmusicManager.getInstance().lovemusicList.add(netmusic);
                /*System.out.println(netmusic.getMusicid());
                System.out.println(netmusic.getSongname());*/
                result= "1" ;
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


    public static void main(String[] args)
    {
        DBUtil db=new DBUtil();
        //db.QuerySQL("1","123");
        //db.FindSQL("1");
        //db.register("5","123");
        db.getlove();
    }
}

