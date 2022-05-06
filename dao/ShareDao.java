package dao;

import bean.Share;
import dao.UserDao;
import util.UserCF;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class ShareDao {
    public ShareDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inv-sys?characterEncoding=UTF-8&serverTimezone=GMT%2B8", "root", "w00x02h11");
    }

    public ArrayList<Share> SelectAll() throws SQLException {
        try (Connection c=getConnection()){
            String sql="SELECT * FROM `inv-sys`.share";
            PreparedStatement ps=c.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            ArrayList<Share> arr=new ArrayList<>();
            while (rs.next()){
                Share share=new Share();
                share.setId(rs.getString(1));
                share.setSname(rs.getString(2));
                share.setNprice(rs.getDouble(3));
                share.setOprice(rs.getDouble(4));
                share.setTurnover(rs.getDouble(5));
                share.setTprice(rs.getDouble(6));
                share.setPluse(rs.getDouble(7));
                share.setP(rs.getDouble(8));
                share.setCprice();
                share.setCreng();
                arr.add(share);
            }
            return arr;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Share> Select_CF(int P,int rev){
        try (Connection c=getConnection()){
            String sql="SELECT * FROM `inv-sys`.share where P=? and new_price<=?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,Integer.toString(P));
            ps.setString(2,Integer.toString(rev));
            ResultSet rs=ps.executeQuery();
            ArrayList<Share> arr=new ArrayList<>();
            int number = new Random().nextInt(4) + 1;
            int i=1;
            while (rs.next()){
                if (number==5){
                    number=1;
                    continue;
                }else {
                    number+=2;
                }
                Share share=new Share();
                share.setId(rs.getString(1));
                share.setSname(rs.getString(2));
                share.setNprice(rs.getDouble(3));
                share.setOprice(rs.getDouble(4));
                share.setTurnover(rs.getDouble(5));
                share.setTprice(rs.getDouble(6));
                share.setPluse(rs.getDouble(7));
                share.setP(rs.getDouble(8));
                share.setCprice();
                share.setCreng();
                arr.add(share);
                i++;
                if (i>5) break;;
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Share> Select_CF_two(int P1,int P2,int rev){
        try (Connection c=getConnection()){
            String sql="SELECT * FROM `inv-sys`.share where (P=? or P=?)and new_price<=?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,Integer.toString(P1));
            ps.setString(2,Integer.toString(P2));
            ps.setString(3,Integer.toString(rev));
            ResultSet rs=ps.executeQuery();
            ArrayList<Share> arr=new ArrayList<>();
            int number = new Random().nextInt(3) + 1;
            int i=1;
            while (rs.next()){
                if (number==6){
                    number=1;
                    continue;
                }else {
                    number+=1;
                }
                Share share=new Share();
                share.setId(rs.getString(1));
                share.setSname(rs.getString(2));
                share.setNprice(rs.getDouble(3));
                share.setOprice(rs.getDouble(4));
                share.setTurnover(rs.getDouble(5));
                share.setTprice(rs.getDouble(6));
                share.setPluse(rs.getDouble(7));
                share.setP(rs.getDouble(8));
                share.setCprice();
                share.setCreng();
                arr.add(share);
                i++;
                if (i>5) break;;
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Share getShareById(String idshare){
        try (Connection connection=getConnection()){
            String sql="SELECT * FROM `inv-sys`.share where idshare=?";
            Share share=new Share();
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1,idshare);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                share.setId(idshare);
                share.setSname(rs.getString(2));
                share.setNprice(rs.getDouble(3));
                share.setOprice(rs.getDouble(4));
                share.setTurnover(rs.getDouble(5));
                share.setTprice(rs.getDouble(6));
                share.setPluse(rs.getDouble(7));
                share.setP(rs.getDouble(8));
                share.setCreng();
                share.setCprice();
                return share;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Share> getShareByPartId(String idshare) {
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM `inv-sys`.share where idshare regexp ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, idshare);
            ResultSet rs = ps.executeQuery();
            ArrayList<Share> shares=new ArrayList<>();
            while (rs.next()) {
                Share share = new Share();
                share.setId(rs.getString(1));
                share.setSname(rs.getString(2));
                share.setNprice(rs.getDouble(3));
                share.setOprice(rs.getDouble(4));
                share.setTurnover(rs.getDouble(5));
                share.setTprice(rs.getDouble(6));
                share.setPluse(rs.getDouble(7));
                share.setP(rs.getDouble(8));
                share.setCreng();
                share.setCprice();
                shares.add(share);
            }
            return shares;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Share> SelectFiveShareByRevenue(int rev){
        try (Connection c=getConnection()){
            String sql="SELECT * FROM `inv-sys`.share where new_price<=? order by rand() limit 5";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,Integer.toString(rev));
            ResultSet rs=ps.executeQuery();
            ArrayList<Share> arr=new ArrayList<>();
            while (rs.next()){
                Share share=new Share();
                share.setId(rs.getString(1));
                share.setSname(rs.getString(2));
                share.setNprice(rs.getDouble(3));
                share.setOprice(rs.getDouble(4));
                share.setTurnover(rs.getDouble(5));
                share.setTprice(rs.getDouble(6));
                share.setPluse(rs.getDouble(7));
                share.setP(rs.getDouble(8));
                share.setCprice();
                share.setCreng();
                arr.add(share);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Share> SelectFiveShare(){
        try (Connection c=getConnection()){
            String sql="SELECT * FROM `inv-sys`.share order by rand() limit 5";
            PreparedStatement ps=c.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            ArrayList<Share> arr=new ArrayList<>();
            while (rs.next()){
                Share share=new Share();
                share.setId(rs.getString(1));
                share.setSname(rs.getString(2));
                share.setNprice(rs.getDouble(3));
                share.setOprice(rs.getDouble(4));
                share.setTurnover(rs.getDouble(5));
                share.setTprice(rs.getDouble(6));
                share.setPluse(rs.getDouble(7));
                share.setP(rs.getDouble(8));
                share.setCprice();
                share.setCreng();
                arr.add(share);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateShare(){
        try (Connection c=getConnection()){
            ArrayList<Share> shares=SelectAll();
            for (Share share:shares) {
                String sql = "call update_data(?)";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1,share.getId());
                ps.execute();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Share> Select_CF_1(int P,int rev){
        try (Connection c=getConnection()){
            String sql="SELECT * FROM `inv-sys`.share where P=? and new_price<=? order by rand() limit 5";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,Integer.toString(P));
            ps.setString(2,Integer.toString(rev));
            ResultSet rs=ps.executeQuery();
            ArrayList<Share> arr=new ArrayList<>();
            while (rs.next()){
                Share share=new Share();
                share.setId(rs.getString(1));
                share.setSname(rs.getString(2));
                share.setNprice(rs.getDouble(3));
                share.setOprice(rs.getDouble(4));
                share.setTurnover(rs.getDouble(5));
                share.setTprice(rs.getDouble(6));
                share.setPluse(rs.getDouble(7));
                share.setP(rs.getDouble(8));
                share.setCprice();
                share.setCreng();
                arr.add(share);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Share> Select_CF_2(int P1,int P2,int rev){
        try (Connection c=getConnection()){
            String sql="SELECT * FROM `inv-sys`.share where (P=? or P=?)and new_price<=? order by rand() limit 5";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,Integer.toString(P1));
            ps.setString(2,Integer.toString(P2));
            ps.setString(3,Integer.toString(rev));
            ResultSet rs=ps.executeQuery();
            ArrayList<Share> arr=new ArrayList<>();
            while (rs.next()){
                Share share=new Share();
                share.setId(rs.getString(1));
                share.setSname(rs.getString(2));
                share.setNprice(rs.getDouble(3));
                share.setOprice(rs.getDouble(4));
                share.setTurnover(rs.getDouble(5));
                share.setTprice(rs.getDouble(6));
                share.setPluse(rs.getDouble(7));
                share.setP(rs.getDouble(8));
                share.setCprice();
                share.setCreng();
                arr.add(share);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Share> Select_CF_3(int P1,int P2,int P3,int rev){
        try (Connection c=getConnection()){
            String sql="SELECT * FROM `inv-sys`.share where (P=? or P=? or P=?)and new_price<=? order by rand() limit 5";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,Integer.toString(P1));
            ps.setString(2,Integer.toString(P2));
            ps.setString(3,Integer.toString(P3));
            ps.setString(4,Integer.toString(rev));
            ResultSet rs=ps.executeQuery();
            ArrayList<Share> arr=new ArrayList<>();
            while (rs.next()){

                Share share=new Share();
                share.setId(rs.getString(1));
                share.setSname(rs.getString(2));
                share.setNprice(rs.getDouble(3));
                share.setOprice(rs.getDouble(4));
                share.setTurnover(rs.getDouble(5));
                share.setTprice(rs.getDouble(6));
                share.setPluse(rs.getDouble(7));
                share.setP(rs.getDouble(8));
                share.setCprice();
                share.setCreng();
                arr.add(share);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Share> Select_CF_4(int P1,int P2,int P3,int P4,int rev){
        try (Connection c=getConnection()){
            String sql="SELECT * FROM `inv-sys`.share where (P=? or P=? or P=? or P=?)and new_price<=? order by rand() limit 5";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,Integer.toString(P1));
            ps.setString(2,Integer.toString(P2));
            ps.setString(3,Integer.toString(P3));
            ps.setString(4,Integer.toString(P4));
            ps.setString(5,Integer.toString(rev));
            ResultSet rs=ps.executeQuery();
            ArrayList<Share> arr=new ArrayList<>();
            while (rs.next()){
                Share share=new Share();
                share.setId(rs.getString(1));
                share.setSname(rs.getString(2));
                share.setNprice(rs.getDouble(3));
                share.setOprice(rs.getDouble(4));
                share.setTurnover(rs.getDouble(5));
                share.setTprice(rs.getDouble(6));
                share.setPluse(rs.getDouble(7));
                share.setP(rs.getDouble(8));
                share.setCprice();
                share.setCreng();
                arr.add(share);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Share> Select_CF_5(int P1,int P2,int P3,int P4,int P5,int rev){
        try (Connection c=getConnection()){
            String sql="SELECT * FROM `inv-sys`.share where (P=? or P=? or P=? or P=? or P=?)and new_price<=? order by rand() limit 5";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,Integer.toString(P1));
            ps.setString(2,Integer.toString(P2));
            ps.setString(3,Integer.toString(P3));
            ps.setString(4,Integer.toString(P4));
            ps.setString(5,Integer.toString(P5));
            ps.setString(6,Integer.toString(rev));
            ResultSet rs=ps.executeQuery();
            ArrayList<Share> arr=new ArrayList<>();
            while (rs.next()){
                Share share=new Share();
                share.setId(rs.getString(1));
                share.setSname(rs.getString(2));
                share.setNprice(rs.getDouble(3));
                share.setOprice(rs.getDouble(4));
                share.setTurnover(rs.getDouble(5));
                share.setTprice(rs.getDouble(6));
                share.setPluse(rs.getDouble(7));
                share.setP(rs.getDouble(8));
                share.setCprice();
                share.setCreng();
                arr.add(share);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getIDShare(String idshare) {
        try (Connection connection = getConnection()) {
            String sql = "SELECT idshare FROM `inv-sys`.share where idshare regexp ? limit 5";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, idshare);
            ResultSet rs = ps.executeQuery();
            ArrayList<String> result=new ArrayList<>();
            while (rs.next()) {
                String temp=rs.getString(1);
                result.add(temp);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
