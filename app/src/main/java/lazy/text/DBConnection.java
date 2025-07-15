package lazy.text;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static volatile Connection conn=null;

    public static Connection getConnection() throws SQLException {
        if(conn==null || conn.isClosed()) {
            synchronized (DBConnection.class) {
                if(conn==null || conn.isClosed()) {
                    Properties props=new Properties();
                    try(FileInputStream fis=new FileInputStream("config.properties")) {
                        props.load(fis); 
                    } catch (IOException e) {
                        throw new RuntimeException("Could not load db properties", e);
                    }

                    String url=props.getProperty("db.url");
                    String username=props.getProperty("db.username");
                    String password=props.getProperty("db.password");
                    conn=DriverManager.getConnection(url, username, password);
                    System.out.println("Connected to Supabase");
                }
            }
        }
        return conn;
    }
}