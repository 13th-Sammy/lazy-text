package lazy.text;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try(Connection conn=DBConnection.getConnection()) {
            System.out.println("Werks");
        } catch (SQLException | RuntimeException e) {
            System.err.println("Could not connect to Supabase");
        }
    }
}