package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class TruncateDao {
    public TruncateDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inv-sys?characterEncoding=UTF-8&serverTimezone=GMT%2B8", "root", "w00x02h11");
    }

    public boolean truncateDatabase(){
        try (Connection c=getConnection()){
            String sql_1 = "truncate table user";
            String sql_2 = "truncate table share";
            PreparedStatement ps=c.prepareStatement(sql_1);
            ps.execute();
            ps=c.prepareStatement(sql_2);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
