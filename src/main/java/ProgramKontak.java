/**
 * Nama     : Ade Ariansyah Anggoro
 * NIM      : 250315034
 * Kelas    : TRPL 1B
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Kontak {
    // 1. Deklarasi atribut
    private int id;
    private String nama;
    private String telepon;
    private String email;
    
    // 2. Constructor untuk mengisi nilai atribut
    public Kontak(String nama, String telepon, String email) {
        this.nama = nama;
        this.telepon = telepon;
        this.email = email;
    }

    // 3. Constructor untuk mengambil data
    public Kontak(int id, String nama, String telepon, String email) {
        this.id = id;
        this.nama = nama;
        this.telepon = telepon;
        this.email = email;
    }

    // 4. Getter methods untuk mengambil nilai masing-masing atribut
    public int getId() {
        return id;
    }
    
    public String getNama() {
        return nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public String getEmail() {
        return email;
    }
}

class BukuAlamat {
    // 1. Constructor untuk menginisialisasi tempat penyimpanan
    public BukuAlamat() {
    }
    // 2. Method tambahKontak
    public void tambahKontak(Kontak kontakBaru) {
        String insertSQL = "INSERT INTO kontak (nama, telepon, email) values (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, kontakBaru.getNama());
                pstmt.setString(2, kontakBaru.getTelepon());
                pstmt.setString(3, kontakBaru.getEmail());

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("[BERHASIL] Kontak atas nama " + kontakBaru.getNama() + " telah ditambahkan.");

                } else {
                    System.out.println("[GAGAL] Pastikan data nama, telepon, dan email sesuai.");
                }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // 3. Method tampilkanKontak
    public void tampilKontak() {
        String readSQL = "SELECT * FROM kontak";

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(readSQL)) {
            ResultSet rs = pstmt.executeQuery();

            System.out.println("======= Daftar Kontak =======");

            boolean adaData = false;
            
            while (rs.next()) {
                adaData = true;
                
                System.out.println("> ID      : " + rs.getInt("id") + "\n> Nama    : " + rs.getString("nama") + "\n> Telepon : " + rs.getString("telepon") + "\n> Email   : " + rs.getString("email"));
                System.out.println("=============================");
            }

            if (!adaData) {
                System.out.println("> Belum ada kontak yang tersimpan.");
                System.out.println("=============================");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 4. method update data kontak
    public void updateKontak(int id, Kontak ubahKontak) {
        String editSQL = "UPDATE kontak SET nama = ?, telepon = ?, email = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(editSQL)) {

             pstmt.setString(1, ubahKontak.getNama());
             pstmt.setString(2, ubahKontak.getTelepon());
             pstmt.setString(3, ubahKontak.getEmail());
             pstmt.setInt(4, id);

             int rowsUpdated = pstmt.executeUpdate();
             if (rowsUpdated > 0) {
                System.out.println("[BERHASIL] Data kontak dengan ID "+ id + " berhasil diubah.");
             } else {
                System.out.println("[GAGAL] User tidak ditemukan. Pastikan ID benar.");
             }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 5. method menghapus data kontak
    public void hapusKontak(int id) {
        String deleteSQL = "DELETE FROM kontak WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            
            pstmt.setInt(1, id);
            
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("[BERHASIL] Data kontak dengan ID "+ id + " berhasil dihapus.");
            } else {
                System.out.println("[GAGAL] Kontak tidak ditemukan. Pastikan ID benar.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

public class ProgramKontak {
    public static void main(String[] args) {
        DatabaseConfig.setupTable();

        // 1. Buat objek BukuAlamat
        BukuAlamat bukuAll = new BukuAlamat();
        try (Scanner input = new Scanner(System.in)) {
            boolean menu = true;

            while (menu) {
                System.out.println("\n===== Menu Buku Alamat ====");
                System.out.println("1. Tambah Kontak");
                System.out.println("2. Tampilkan Semua Kontak");
                System.out.println("3. Edit Kontak");
                System.out.println("4. Hapus Kontak");
                System.out.println("5. Keluar");
                System.out.print("Pilih menu: ");

                String opsi = input.nextLine();

                switch (opsi) {
                    // menu tambah kontak
                    case "1":
                        System.out.println("\n<=== Tambah Kontak Baru ===>");
                        System.out.print("Masukkan Nama: ");
                        String namaBaru = input.nextLine();
                        System.out.print("Masukkan Nomor Telepon: ");
                        String teleponBaru = input.nextLine();
                        System.out.print("Masukkan email: ");
                        String emailBaru = input.nextLine();

                        if (namaBaru.trim().isEmpty() || teleponBaru.trim().isEmpty()) {
                            System.out.println("[GAGAL] Nama dan Telepon tidak boleh kosong!");
                        } else {
                            Kontak inputKontak = new Kontak(namaBaru, teleponBaru, emailBaru);

                            bukuAll.tambahKontak(inputKontak);
                        }
                        break;
                    // menu tampil kontak
                    case "2":
                        bukuAll.tampilKontak();
                        break;
                    // menu edit kontak
                    case "3":
                        System.out.println("\n<=== Edit Kontak ===>");
                        System.out.print("Masukkan ID kontak yang dirubah: ");
                        try {
                            int targetID = Integer.parseInt(input.nextLine());

                            System.out.print("Masukkan Nama: ");
                            String updateNama = input.nextLine();
                            System.out.print("Masukkan Nomor Telepon: ");
                            String updateTelepon = input.nextLine();
                            System.out.print("Masukkan email: ");
                            String updateEmail = input.nextLine();

                            if (updateNama.trim().isEmpty() || updateTelepon.trim().isEmpty()) {
                                System.out.println("[GAGAL] Nama dan Telepon tidak boleh kosong!");
                            } else {
                                Kontak inputKontak = new Kontak(updateNama, updateTelepon, updateEmail);

                                bukuAll.updateKontak(targetID, inputKontak);
                            }
                        } catch (Exception e) {
                            System.out.println("[Error] Input ID harus berupa angka!");
                        }
                        break;
                    // menu hapus kontak
                    case "4":
                        System.out.println("\n<=== Hapus Kontak ===>");
                        System.out.print("Masukkan ID kontak yang akan dihapus: ");
                        try {
                            int targetID = Integer.parseInt(input.nextLine());

                            System.out.print("Apakah Anda yakin ingin menghapus ID " + targetID + "? (y/n): ");
                            String konfirmasi = input.nextLine();
                            if (konfirmasi.equalsIgnoreCase("y")) {
                                bukuAll.hapusKontak(targetID);
                            } else {
                                System.out.println("Penghapusan kontak dibatalkan.");
                            }
                        } catch (Exception e) {
                            System.out.println("[Error] Input ID harus berupa angka!");
                        }
                        break;
                    // menu keluar program
                    case "5":
                        menu = false;
                        System.out.println("Keluar dari program. Terima kasih!");
                        break;
                    default:
                        System.out.println("Pilihan tidak ada. Silakan coba lagi.");
                }
            }
        }
    }
}