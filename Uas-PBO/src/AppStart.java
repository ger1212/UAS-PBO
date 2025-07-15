package src;
import java.sql.*;
import java.util.Scanner;

public class AppStart {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/toko";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(jdbcURL, user, password)) {
            Scanner input = new Scanner(System.in);
            System.out.print("Kode: ");
            String kode = input.nextLine();
            System.out.print("Nama: ");
            String nama = input.nextLine();
            System.out.print("Harga: ");
            int harga = input.nextInt();
            System.out.print("Stok: ");
            int stok = input.nextInt();

            CallableStatement cs = conn.prepareCall("{CALL insert_barang(?, ?, ?, ?)}");
            cs.setString(1, kode);
            cs.setString(2, nama);
            cs.setInt(3, harga);
            cs.setInt(4, stok);

            cs.execute();
            System.out.println("Data berhasil diinsert.");

            // Menampilkan data dari VIEW
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM view_barang");
            while (rs.next()) {
                System.out.printf("Kode: %s, Nama: %s, Harga: %d, Stok: %d, Total: %d%n",
                        rs.getString("kode"),
                        rs.getString("nama"),
                        rs.getInt("harga"),
                        rs.getInt("stok"),
                        rs.getInt("total_harga"));
            }

        } catch (SQLException e) {
            System.err.println("Koneksi atau query error: " + e.getMessage());
        }
    }
}
