package lk.ijse.gdse.dao;

import lk.ijse.gdse.util.DataSource;
import lk.ijse.gdse.model.Complaint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {
    public boolean insertComplaint(Complaint complaint) {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO complaints (user_id, subject, description, status) VALUES (?, ?, ?, 'Pending')");
            pst.setInt(1, complaint.getUserId());
            pst.setString(2, complaint.getSubject());
            pst.setString(3, complaint.getDescription());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Complaint> getComplaintsByUserId(int userId) {
        List<Complaint> list = new ArrayList<>();

        try (Connection connection = DataSource.getConnection()){
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM complaints WHERE user_id=?");
            pst.setInt(1, userId);

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                Complaint c = new Complaint();
                c.setId(rs.getInt("id"));
                c.setUserId(rs.getInt("user_id"));
                c.setSubject(rs.getString("subject"));
                c.setDescription(rs.getString("description"));
                c.setStatus(rs.getString("status"));
                c.setRemarks(rs.getString("remarks"));
                c.setCreated_at(rs.getTimestamp("created_at"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Complaint getComplaintById(int id) {
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM complaints WHERE id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Complaint c = new Complaint();
                c.setId(rs.getInt("id"));
                c.setUserId(rs.getInt("user_id"));
                c.setSubject(rs.getString("subject"));
                c.setDescription(rs.getString("description"));
                c.setStatus(rs.getString("status"));
                c.setRemarks(rs.getString("remarks"));
                c.setCreated_at(rs.getTimestamp("created_at"));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateComplaint(Complaint complaint) {
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement pst = conn.prepareStatement("UPDATE complaints SET subject = ?, description = ? WHERE id = ?");
            pst.setString(1, complaint.getSubject());
            pst.setString(2, complaint.getDescription());
            pst.setInt(3, complaint.getId());

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteComplaint(int id) {
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement pst = conn.prepareStatement("DELETE FROM complaints WHERE id = ?");
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Complaint> getAllComplaints() {
        List<Complaint> list = new ArrayList<>();
        try (Connection con = DataSource.getConnection()) {
            String sql = "SELECT * FROM complaints";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Complaint c = new Complaint();
                c.setId(rs.getInt("id"));
                c.setUserId(rs.getInt("user_id"));
                c.setSubject(rs.getString("subject"));
                c.setDescription(rs.getString("description"));
                c.setStatus(rs.getString("status"));
                c.setRemarks(rs.getString("remarks"));
                c.setCreated_at(rs.getTimestamp("created_at"));

                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateAdminComplaint(int id, String status, String remarks) {
        try (Connection con = DataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE complaints SET status=?, remarks=? WHERE id=?");
            ps.setString(1, status);
            ps.setString(2, remarks);
            ps.setInt(3, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
