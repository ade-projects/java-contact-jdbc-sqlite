import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    // URL Database
    static String url = "jdbc:sqlite:kontak_data.db";

    // Method koneksi
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
    // Method inisialisasi tabel
    public static void setupTable() {
        var sql = "CREATE TABLE IF NOT EXISTS kontak ("
                + "     id INTEGER PRIMARY KEY,"
                + "     nama TEXT NOT NULL,"
                + "     telepon TEXT NOT NULL,"
                + "     email TEXT"
                + ");";
        try (var conn = getConnection();
            var stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabel kontak siap digunakan.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}