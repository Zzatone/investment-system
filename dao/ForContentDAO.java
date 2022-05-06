package dao;

import bean.ForContent;
import bean.ForTitle;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ForContentDAO {
    public ForContentDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inv-sys?characterEncoding=UTF-8&serverTimezone=GMT%2B8", "root", "w00x02h11");
    }

    public ForContent select_master(int idforum){
        try (Connection c=getConnection()){
            String sql="SELECT * FROM `inv-sys`.forumcontent where type=1 and idforum=?";
            PreparedStatement ps=c.prepareStatement(sql);
            ForContent forContent=new ForContent();
            ps.setInt(1,idforum);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                forContent.setIdcontent(rs.getInt(1));
                forContent.setContent(rs.getString(2));
                forContent.setName(rs.getString(3));
                forContent.setTime(rs.getString(4));
                forContent.setIdforum(rs.getInt(5));
                forContent.setType(rs.getInt(6));
            }
            return forContent;
        }catch (SQLException e){

        }
        return null;
    }

    public ArrayList<ForContent> select_request(int idforum){
        try (Connection c=getConnection()){
            String sql="SELECT * FROM `inv-sys`.forumcontent where type=2 and idforum=?";
            PreparedStatement ps=c.prepareStatement(sql);
            ArrayList<ForContent> forContents=new ArrayList<>();
            ps.setInt(1,idforum);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                ForContent forContent=new ForContent();
                forContent.setIdcontent(rs.getInt(1));
                forContent.setContent(rs.getString(2));
                forContent.setName(rs.getString(3));
                forContent.setTime(rs.getString(4));
                forContent.setIdforum(rs.getInt(5));
                forContent.setType(rs.getInt(6));
                forContents.add(forContent);
            }
            return forContents;
        }catch (SQLException e){

        }
        return null;
    }

    public boolean insert_request(String content,String name,int idforum){
        try (Connection c=getConnection()){
            String sql="INSERT INTO `inv-sys`.`forumcontent` (`content`, `name`, `time`, `idforum`, `type`) VALUES (?, ?, ?, ?, '2');";
            PreparedStatement ps=c.prepareStatement(sql);
            Date date=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String datef=sdf.format(date);
            ps.setString(1,content);
            ps.setString(2,name);
            ps.setString(3,datef);
            ps.setInt(4,idforum);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean insert_master(String content,String name,int idforum){
        try (Connection c=getConnection()){
            String sql="INSERT INTO `inv-sys`.`forumcontent` (`content`, `name`, `time`, `idforum`, `type`) VALUES (?, ?, ?, ?, '1');";
            PreparedStatement ps=c.prepareStatement(sql);
            Date date=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String datef=sdf.format(date);
            ps.setString(1,content);
            ps.setString(2,name);
            ps.setString(3,datef);
            ps.setInt(4,idforum);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<ForContent> select_MyRequest(String name){
        try (Connection c=getConnection()){
            String sql="SELECT * FROM `inv-sys`.forumcontent where type=2 and name=?";
            PreparedStatement ps=c.prepareStatement(sql);
            ArrayList<ForContent> forContents=new ArrayList<>();
            ps.setString(1,name);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                ForContent forContent=new ForContent();
                forContent.setIdcontent(rs.getInt(1));
                forContent.setContent(rs.getString(2));
                forContent.setName(rs.getString(3));
                forContent.setTime(rs.getString(4));
                forContent.setIdforum(rs.getInt(5));
                forContent.setType(rs.getInt(6));
                forContents.add(forContent);
            }
            return forContents;
        }catch (SQLException e){

        }
        return null;
    }

    public boolean delete_content(int idcontent){
        try (Connection c=getConnection()){
            String sql="DELETE FROM `inv-sys`.`forumcontent` WHERE (`idcontent` = ?);";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setInt(1,idcontent);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
