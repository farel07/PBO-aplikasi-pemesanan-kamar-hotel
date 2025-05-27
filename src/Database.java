/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Farrel
 */
public class Database {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String user = "root"; // ganti sesuai user MySQL
        String pass = "";     // ganti sesuai password MySQL
        return DriverManager.getConnection(url, user, pass);
    }
}
