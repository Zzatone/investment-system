package dao;

import bean.Share;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class SubandHisDAO {
    public SubandHisDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inv-sys?characterEncoding=UTF-8&serverTimezone=GMT%2B8", "root", "w00x02h11");
    }

    public boolean insert_subscribe(String iduser,String idshare){
        try (Connection c=getConnection()){
            String sql="INSERT INTO `inv-sys`.`subhis` (`iduser`, `idshare`, `type`) VALUES ( ? , ? , '1')";
            PreparedStatement ps=c.prepareStatement(sql);
            int id_user=Integer.valueOf(iduser);
            ps.setInt(1,id_user);
            ps.setString(2,idshare);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public int select_type(String iduser,String idshare){
        try (Connection c=getConnection()){
            String sql="SELECT EXISTS(select type from `inv-sys`.`subhis` where iduser=? and idshare=? and type=1);";
            PreparedStatement ps=c.prepareStatement(sql);
            int id_user=Integer.valueOf(iduser);
            ps.setInt(1,id_user);
            ps.setString(2,idshare);
            ResultSet rs=ps.executeQuery();
            int type=0;
            while (rs.next()){
                type=rs.getInt(1);
            }
            return type;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public boolean delete_subscribe(String iduser,String idshare){
        try (Connection c=getConnection()){
            String sql="DELETE FROM `inv-sys`.`subhis` WHERE (iduser=? and idshare=? and type=1);";
            PreparedStatement ps=c.prepareStatement(sql);
            int id_user=Integer.valueOf(iduser);
            ps.setInt(1,id_user);
            ps.setString(2,idshare);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean CreateView_Sub(int iduser){
        try (Connection c=getConnection()){
            String sql="drop view if exists select_view_sub";
            String sql1="create view select_view_sub(idshare,share_name,new_price,old_price,turnover,turnover_price,pluse) as\n" +
                    "select share.idshare,share_name,new_price,old_price,turnover,turnover_price,pluse\n" +
                    "from `inv-sys`.`share`,`inv-sys`.`subhis`\n" +
                    "where (iduser=? and share.idshare=subhis.idshare and type=1)";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.execute();
            ps=c.prepareStatement(sql1);
            ps.setInt(1,iduser);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean CreateView_His(int iduser){
        try (Connection c=getConnection()){
            String sql="drop view if exists select_view_his";
            String sql1="create view select_view_his(id,idshare,share_name,new_price,old_price,turnover,turnover_price,pluse) as \n" +
                    "select subhis.id,share.idshare,share_name,new_price,old_price,turnover,turnover_price,pluse \n" +
                    "from `inv-sys`.`share`,`inv-sys`.`subhis` \n" +
                    "where (iduser=? and share.idshare=subhis.idshare and type=2)\n" +
                    "order by id desc;";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.execute();
            ps=c.prepareStatement(sql1);
            ps.setInt(1,iduser);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Share> select_subscribe(String iduser){
        try (Connection c=getConnection()){
            int id_user=Integer.valueOf(iduser);
            CreateView_Sub(id_user);
            String sql="SELECT * FROM `inv-sys`.select_view_sub";
            PreparedStatement ps=c.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            ArrayList<Share> shares=new ArrayList<>();
            while (rs.next()){
                Share share=new Share();
                share.setId(rs.getString(1));
                share.setSname(rs.getString(2));
                share.setNprice(rs.getDouble(3));
                share.setOprice(rs.getDouble(4));
                share.setTurnover(rs.getDouble(5));
                share.setTprice(rs.getDouble(6));
                share.setPluse(rs.getDouble(7));
                share.setCprice();
                share.setCreng();
                shares.add(share);
            }
            return shares;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Share> select_history(String iduser){
        try (Connection c=getConnection()){
            int id_user=Integer.valueOf(iduser);
            CreateView_His(id_user);
            String sql="SELECT * FROM `inv-sys`.select_view_his";
            PreparedStatement ps=c.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            ArrayList<Share> shares=new ArrayList<>();
            while (rs.next()){
                Share share=new Share();
                share.setId(rs.getString(2));
                share.setSname(rs.getString(3));
                share.setNprice(rs.getDouble(4));
                share.setOprice(rs.getDouble(5));
                share.setTurnover(rs.getDouble(6));
                share.setTprice(rs.getDouble(7));
                share.setPluse(rs.getDouble(8));
                share.setCprice();
                share.setCreng();
                shares.add(share);
            }
            return shares;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert_history(String iduser,String idshare){
        try (Connection c=getConnection()){
            String sql="INSERT INTO `inv-sys`.`subhis` (`iduser`, `idshare`, `type`) VALUES ( ? , ? , '2')";
            PreparedStatement ps=c.prepareStatement(sql);
            int id_user=Integer.valueOf(iduser);
            ps.setInt(1,id_user);
            ps.setString(2,idshare);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete_history_all(String iduser){
        try (Connection c=getConnection()){
            String sql="DELETE FROM `inv-sys`.`subhis` WHERE (iduser=? and type=2);";
            PreparedStatement ps=c.prepareStatement(sql);
            int id_user=Integer.valueOf(iduser);
            ps.setInt(1,id_user);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete_history(String iduser,String idshare){
        try (Connection c=getConnection()){
            String sql="DELETE FROM `inv-sys`.`subhis` WHERE (iduser=? and idshare=? and type=2);";
            PreparedStatement ps=c.prepareStatement(sql);
            int id_user=Integer.valueOf(iduser);
            ps.setInt(1,id_user);
            ps.setString(2,idshare);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }



}
