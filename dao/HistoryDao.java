package dao;

import bean.History;

import java.sql.*;

public class HistoryDao {
    public HistoryDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inv-sys?characterEncoding=UTF-8&serverTimezone=GMT%2B8", "root", "w00x02h11");
    }

    public History selectHis(String idshare){
        try (Connection c=getConnection()){
            String sql="SELECT new_price,D1,D2,D3,D4 FROM `inv-sys`.share where idshare=?";
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setString(1,idshare);
            ResultSet rs = ps.executeQuery();
            History history=new History();
            if (rs.next()){
                history.setIdshare(idshare);
                history.setNewday(rs.getDouble(1));
                history.setD1(rs.getDouble(2));
                history.setD2(rs.getDouble(3));
                history.setD3(rs.getDouble(4));
                history.setD4(rs.getDouble(5));
            }
            return history;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
