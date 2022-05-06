package dao;

import bean.ForTitle;

import javax.mail.Message;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ForTitleDAO {
    public ForTitleDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inv-sys?characterEncoding=UTF-8&serverTimezone=GMT%2B8", "root", "w00x02h11");
    }

    public ArrayList<ForTitle> select_all(){
        try (Connection c=getConnection()){
            String sql="SELECT * FROM `inv-sys`.forumtitle";
            PreparedStatement ps=c.prepareStatement(sql);
            ArrayList<ForTitle> forTitles=new ArrayList<>();
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                ForTitle forTitle=new ForTitle();
                forTitle.setIdforum(rs.getInt(1));
                forTitle.setName(rs.getString(2));
                forTitle.setTitle(rs.getString(3));
                forTitle.setTime(rs.getString(4));
                forTitles.add(forTitle);
            }
            return forTitles;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ForTitle selectById(int idforum){
        try (Connection c=getConnection()){
            String sql="SELECT * FROM `inv-sys`.forumtitle where idforum=?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setInt(1,idforum);
            ForTitle forTitle=new ForTitle();
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                forTitle.setIdforum(rs.getInt(1));
                forTitle.setName(rs.getString(2));
                forTitle.setTitle(rs.getString(3));
                forTitle.setTime(rs.getString(4));
            }
            return forTitle;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public int insert_title(String name,String title){
        try (Connection c=getConnection()){
            String sql="INSERT INTO `inv-sys`.`forumtitle` (`name`, `title`, `time`) VALUES (?, ?, ?);";
            PreparedStatement ps=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            java.util.Date date=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String datef=sdf.format(date);
            ps.setString(1,name);
            ps.setString(2,title);
            ps.setString(3,datef);
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            int idforun=0;
            if (rs.next()){
                idforun=rs.getInt(1);
            }
            return idforun;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<ForTitle> select_MyTitle(String name){
        try (Connection c=getConnection()){
            String sql="select * FROM `inv-sys`.forumtitle where (name =?)";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs=ps.executeQuery();
            ArrayList<ForTitle> forTitles=new ArrayList<>();
            while (rs.next()){
                ForTitle forTitle=new ForTitle();
                forTitle.setIdforum(rs.getInt(1));
                forTitle.setName(rs.getString(2));
                forTitle.setTitle(rs.getString(3));
                forTitle.setTime(rs.getString(4));
                forTitles.add(forTitle);
            }
            return forTitles;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete_title(int idforum){
        try (Connection c=getConnection()){
            String sql="DELETE FROM `inv-sys`.`forumtitle` WHERE (`idforum` = ?)";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setInt(1,idforum);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
