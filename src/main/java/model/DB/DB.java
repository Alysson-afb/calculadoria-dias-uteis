package model.DB;

import java.sql.*;

public class DB {

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SACProjeto_DB",
                    "root",
                    "");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
