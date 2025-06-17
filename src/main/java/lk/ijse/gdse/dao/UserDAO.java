package lk.ijse.gdse.dao;

import lk.ijse.gdse.util.DataSource;
import lk.ijse.gdse.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public User checkLogin(String username, String password) {
        User user = null;

        try(Connection connection = DataSource.getConnection()){
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

    public boolean registerUser(String username, String password, String role) {
        try (Connection con = DataSource.getConnection()) {
            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, password); // In real apps, hash this!
                ps.setString(3, role);
                ps.executeUpdate();
                return true;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            return false; // username duplicate
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        }
        return users;
    }

    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                return new User(id, username, password, role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(int id, String username, String password, String role) {
        String sql = "UPDATE users SET username = ?, password = ?, role = ? WHERE id = ?";
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, role);
            pst.setInt(4, id);

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
