package pl.coderslab.warsztat2krajeew04.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    public static Connection getConnection()
            throws SQLException {
        Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/warsztat2krks05?useSSL=false",
                        "root", "coderslab");
        return conn;
    }

}
