package dao;

import bean.POI;
import bean.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    public UserDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inv-sys?characterEncoding=UTF-8&serverTimezone=GMT%2B8", "root", "w00x02h11");
    }

    public boolean checkValid(User user){
        try (Connection c = getConnection()){
            String sql = "select * from user where name = ? and password = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2,user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { return true;}
            else { return false;}
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public User select_User(String name){
        try (Connection c = getConnection()){
            User user=new User();
            String sql="select * from user where name = ?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                user.setId(rs.getString(1));
                user.setName(rs.getString(2));
                user.setRevenue(Integer.toString(rs.getInt(5)));
            }
            return user;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String select_User_rev(String name){
        try (Connection c = getConnection()){
            String revenus = null;
            String sql="select revenue from user where name = ?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                revenus=rs.getString(1);
            }
            return revenus;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean addUser(User user){
        try (Connection c=getConnection()){
            String sql="insert into user values(null,?,?,null,?,0,0,0,0,0,1)";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getRevenue());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int selectrow(){
        try (Connection c=getConnection()){
            String sql="SELECT COUNT(*) FROM user where iduser !=1";
            PreparedStatement ps=c.prepareStatement(sql);
            ResultSet resultSet=ps.executeQuery();
            int res=0;
            while (resultSet.next()) {
                res = resultSet.getInt(1);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int select_user_highP(int id){
        try (Connection connection=getConnection()){
            String sql="SELECT P1,P2,P3,P4,P5 FROM user where iduser = ?";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1,Integer.toString(id));
            ResultSet rs=ps.executeQuery();
            int temp=0;
            while (rs.next()) {
                for (int i = 1; i <= 5; i++) {
                    if (rs.getInt(i) > temp) {
                        temp = i;
                    }
                }
            }
            return temp;//可能返回0
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean checkNameRepeat(String name){
        try (Connection c = getConnection()){
            String sql = "select * from user where name = ? ";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { return false;}
            else {return true;}
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public POI select_userP_name(String name){
        try (Connection c=getConnection()){
            String sql="select iduser,P1,P2,P3,P4,P5 from user where name = ?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs=ps.executeQuery();
            POI poi=new POI();
            while (rs.next()) {
                poi.setId(rs.getInt(1));
                poi.setP1(rs.getInt(2));
                poi.setP2(rs.getInt(3));
                poi.setP3(rs.getInt(4));
                poi.setP4(rs.getInt(5));
                poi.setP5(rs.getInt(6));
            }
            return poi;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<User> getAlluser(){
        try (Connection connection=getConnection()){
            String sql="select name,password,revenue from user where name!=\"admin\"";
            PreparedStatement ps=connection.prepareStatement(sql);
            ArrayList<User> users=new ArrayList<>();
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                User user=new User();
                user.setName(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setRevenue(Integer.toString(rs.getInt(3)));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteUser(String name){
        try (Connection c=getConnection()){
            String sql="delete from user where name = ?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,name);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateEmail(String name,String email){
        try (Connection c=getConnection()){
            String sql="UPDATE `inv-sys`.`user` SET `Email` = ? WHERE name = ?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,name);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String seleteEmail(String name){
        try (Connection c=getConnection()){
            String sql="SELECT Email FROM `inv-sys`.user where name= ?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs= ps.executeQuery();
            String email = null;
            if (rs.next()){
                email=rs.getString(1);
            }
            return email;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePassword(String name,String pw){
        try (Connection c=getConnection()){
            String sql="UPDATE `inv-sys`.`user` SET `password` = ? WHERE (`name` = ?);";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,pw);
            ps.setString(2,name);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateRevenue(String name,int revenue){
        try (Connection c=getConnection()){
            String sql="UPDATE `inv-sys`.`user` SET `revenue` = ? WHERE name = ?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setInt(1,revenue);
            ps.setString(2,name);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int selectpow(String name){
        try (Connection c=getConnection()){
            String sql="SELECT pow FROM `inv-sys`.user where name = ?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs=ps.executeQuery();
            int pow=0;
            while (rs.next()){
                pow=rs.getInt(1);
            }
            return pow;
        }catch (SQLException e){
            e.printStackTrace();
        }return 0;
    }

    public boolean updatemes(String username){
        try (Connection c=getConnection()){
            String sql="update `inv-sys`.`user` SET mes=1 where name = ?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,username);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatepow_super(String username){
        try (Connection c=getConnection()){
            String sql="update `inv-sys`.`user` SET pow=2 where name = ?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,username);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatepow_normal(String username){
        try (Connection c=getConnection()){
            String sql="update `inv-sys`.`user` SET pow=1 where name = ?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,username);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<User> selectmes_super(){
        try (Connection c=getConnection()){
            String sql="select * from `inv-sys`.`user` where mes=1";
            PreparedStatement ps=c.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            ArrayList<User> users=new ArrayList<>();
            while (rs.next()){
                User user=new User();
                user.setName(rs.getString(2));
                users.add(user);
            }
            return users;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatemes_super(String username){
        try (Connection c=getConnection()){
            String sql="update `inv-sys`.`user` SET mes=0 where name = ?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,username);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    public int select_id(String name){
        try(Connection c=getConnection()){
            String sql="select iduser from `inv-sys`.`user` where name =?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,name);
            int result=0;
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                result=rs.getInt(1);
            }
            return result;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public int message_row(){
        try (Connection c=getConnection()){
            String sql="select count(*) from `inv-sys`.`user` where mes=1";
            PreparedStatement ps=c.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            int row=0;
            while (rs.next()){
                row=rs.getInt(1);
            }
            return row;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

}
