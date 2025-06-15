package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    // Konstanta untuk koneksi database
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://Localhost:3306/traveliki"; // Ganti dengan "traveliki"
    private static final String USER = "root";
    private static final String PASS = "";  

    private static Database instance;

    // Singleton pattern untuk memastikan hanya ada satu instance Database
    private Database() {
        try {
            Class.forName(JDBC_DRIVER); // Load JDBC driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal memuat driver JDBC");
        }
    }

    // Metode untuk mendapatkan instance singleton
    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    // Method untuk mendapatkan koneksi ke database
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // Method untuk menutup koneksi
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
