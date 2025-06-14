package lk.ijse.gdse.dao;

import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public User checkLogin(String username, String password) {
        User user = null;

        try(Connection connection = DBConnection.getConnection()){
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            pst.setString(1, username.trim());
            pst.setString(2, password.trim());

            ResultSet rst = pst.executeQuery();
            if(rst.next()){
                user = new User();
                user.setId(rst.getInt("id"));
                user.setUsername(rst.getString("username"));
                user.setPassword(rst.getString("password"));
                user.setRole(rst.getString("role"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
