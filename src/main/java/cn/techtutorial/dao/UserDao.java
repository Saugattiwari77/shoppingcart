package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.techtutorial.model.User;

public class UserDao {
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    

    public UserDao(Connection con) {
        this.con = con;
    }

    public User userLogin(String email, String password) {
        User user = new User();
        try {
            String query = "SELECT * FROM users WHERE email=? AND password=?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, email);
                pst.setString(2, password);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("name"));
                        user.setEmail(rs.getString("email"));
                    }else {
                    	user = null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException appropriately, logging or throwing an exception as needed
        }
        return user;
    }
}
