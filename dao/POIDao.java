package dao;

import bean.POI;

import java.sql.*;
import java.util.ArrayList;

public class POIDao {
    public POIDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inv-sys?characterEncoding=UTF-8&serverTimezone=GMT%2B8", "root", "w00x02h11");
    }

    public ArrayList<POI> selectAllP(){
        try (Connection conn=getConnection()){
            String sql="SELECT iduser,P1,P2,P3,P4,P5 FROM `inv-sys`.user where iduser !=1";
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            ArrayList<POI> arrayList=new ArrayList<>();
            while (rs.next()){
                POI poi=new POI();
                poi.setId(rs.getInt(1));
                poi.setP1(rs.getInt(2));
                poi.setP2(rs.getInt(3));
                poi.setP3(rs.getInt(4));
                poi.setP4(rs.getInt(5));
                poi.setP5(rs.getInt(6));
                arrayList.add(poi);
            }
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public POI select_POI(String id){
        try (Connection c=getConnection()){
            String sql="SELECT iduser,P1,P2,P3,P4,P5 FROM `inv-sys`.user where iduser =?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs=ps.executeQuery();
            POI poi=new POI();
            while (rs.next()){
                poi.setId(rs.getInt(1));
                poi.setP1(rs.getInt(2));
                poi.setP2(rs.getInt(3));
                poi.setP3(rs.getInt(4));
                poi.setP4(rs.getInt(5));
                poi.setP5(rs.getInt(6));
            }
            return poi;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePOI(Double P,String name){
        try (Connection c=getConnection()){
            String sql1="update `inv-sys`.user set P1=P1+1 where name=?";
            String sql2="update `inv-sys`.user set P2=P2+1 where name=?";
            String sql3="update `inv-sys`.user set P3=P3+1 where name=?";
            String sql4="update `inv-sys`.user set P4=P4+1 where name=?";
            String sql5="update `inv-sys`.user set P5=P5+1 where name=?";
            if (P == 1.0) {
                PreparedStatement ps = c.prepareStatement(sql1);
                ps.setString(1, name);
                ps.execute();
            } else if (P == 2.0) {
                PreparedStatement ps = c.prepareStatement(sql2);
                ps.setString(1, name);
                ps.execute();
            } else if (P == 3.0) {
                PreparedStatement ps = c.prepareStatement(sql3);
                ps.setString(1, name);
                ps.execute();
            } else if (P == 4.0) {
                PreparedStatement ps = c.prepareStatement(sql4);
                ps.setString(1, name);
                ps.execute();
            } else {
                PreparedStatement ps = c.prepareStatement(sql5);
                ps.setString(1, name);
                ps.execute();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean reset_poi(String name){
        try(Connection c=getConnection()){
            String sql="UPDATE `inv-sys`.`user` SET  `P1` = '0',`P2` = '0',`P3` = '0', `P4` = '0', `P5` = '0' WHERE (name=?);";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,name);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
