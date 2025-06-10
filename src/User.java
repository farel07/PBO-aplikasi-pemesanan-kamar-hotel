/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Farrel
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;

    // Constructor kosong
    public User() {}

    // Constructor pakai parameter
    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getter dan Setter
    public String getRole() { return role; }

    public static User login(String username, String password) {
        try {
            Connection conn = Database.getConnection();
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password); // produksi: hash password
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("idUser"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

