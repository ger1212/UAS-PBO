package src;
import java.sql.*;

public class DataViewer {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/toko";
        String user = "root";
        String password = ""; // Ganti jika pakai password

        try (Connection conn = DriverManager.getConnection(jdbcURL, user, password)) {
            System.out.println("Koneksi berhasil.");

            String sql = "SELECT * FROM view_barang";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\nData Barang:");
            System.out.println("----------------------------------------------------------------");
            System.out.printf("%-10s %-20s %-10s %-10s %-15s%n", 
                "Kode", "Nama", "Harga", "Stok", "Total Nilai");

            System.out.println("----------------------------------------------------------------");

            while (rs.next()) {
                String kode = rs.getString("kode");
                String nama = rs.getString("nama");
                int harga = rs.getInt("harga");
                int stok = rs.getInt("stok");
                int total = rs.getInt("total_harga");

                System.out.printf("%-10s %-20s %-10d %-10d %-15d%n",
                    kode, nama, harga, stok, total);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println("Gagal koneksi / query: " + e.getMessage());
        }
    }
}
