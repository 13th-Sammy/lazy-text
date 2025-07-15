package lazy.text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try(Connection conn=DBConnection.getConnection()) {
            try(PreparedStatement ps=conn.prepareStatement("SELECT * FROM users")) {
                try(ResultSet rs=ps.executeQuery()) {
                    while(rs.next()) {
                        System.out.println(rs.getString("username"));
                    }
                }
            }
        } catch (SQLException | RuntimeException e) {
            System.err.println("Could not connect to Supabase: "+e.getMessage());
        }
    }
}