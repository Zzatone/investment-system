package dao;

import bean.Email;

import java.sql.*;
import java.util.ArrayList;

public class EmailDao {
    public EmailDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inv-sys?characterEncoding=UTF-8&serverTimezone=GMT%2B8", "root", "w00x02h11");
    }

    public ArrayList<Email> selectAllEmail_ifexist()  {
        try (Connection c=getConnection()){
            String sql="SELECT name,Email FROM `inv-sys`.user where Email is not null";
            PreparedStatement ps=c.prepareStatement(sql);
            ArrayList<Email> emails=new ArrayList<>();
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Email email=new Email();
                email.setName(rs.getString(1));
                email.setEmail(rs.getString(2));
                emails.add(email);
            }
            return emails;
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return null;
    }

    public Email selectEmail_byname(String name){
        try (Connection c=getConnection()){
            String sql="select name,Email from `inv-sys`.`user` where name=?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,name);
            Email email=new Email();
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                email.setName(rs.getString(1));
                if(rs.getString(2)!=null) {
                    email.setEmail(rs.getString(2));
                }
            }
            return email;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

