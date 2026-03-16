# Tugas Pemrograman Berorientasi Objek: Sistem Manajemen Kontak (CRUD)

Tugas ini merupakan implementasi sistem manajemen buku alamat berbasis terminal yang menggunakan bahasa pemrograman Java dan database SQLite untuk penyimpanan data secara permanen.

## Identitas Mahasiswa

- **Nama**: Ade Ariansyah Anggoro
- **NIM**: 250315034
- **Kelas**: TRPL 1B
- **Mata Kuliah**: Pemrograman Berorientasi Objek

---

## Deskripsi Proyek

Proyek ini adalah aplikasi "Buku Alamat" yang memungkinkan pengguna untuk mengelola data kontak secara efisien. Aplikasi ini menerapkan prinsip **CRUD (Create, Read, Update, Delete)** dan menggunakan **JDBC (Java Database Connectivity)** untuk berinteraksi dengan database SQLite.

### Fitur Utama:

1. **Tambah Kontak**: Menyimpan nama, nomor telepon, dan email ke dalam database.
2. **Tampilkan Kontak**: Mengambil dan menampilkan seluruh daftar kontak yang tersimpan.
3. **Update Kontak**: Mengubah data kontak yang sudah ada berdasarkan ID spesifik.
4. **Hapus Kontak**: Menghapus data kontak secara permanen dengan fitur konfirmasi keamanan `(y/n)`.
5. **Persistensi Data**: Data tidak hilang saat aplikasi ditutup karena tersimpan di file `kontak_data.db`.

---

## Teknologi yang Digunakan

- **Bahasa**: Java (JDK 21)
- **Build Tool**: Apache Maven
- **Database**: SQLite
- **Library**: SQLite JDBC Driver

---

## Struktur Kode

Aplikasi ini dibagi menjadi beberapa bagian untuk menerapkan prinsip enkapsulasi dan tanggung jawab tunggal (_Single Responsibility Principle_):

- **`DatabaseConfig.java`**: Menangani koneksi ke database SQLite dan inisialisasi tabel otomatis saat aplikasi pertama kali dijalankan.
- **`Kontak` (Class)**: Representasi objek kontak dengan atribut `id`, `nama`, `telepon`, dan `email` menggunakan akses privat (Encapsulation).
- **`BukuAlamat` (Class)**: Berisi logika bisnis (DAO - Data Access Object) untuk melakukan operasi database.
- **`ProgramKontak` (Main Class)**: Menangani interaksi pengguna melalui menu di terminal dan validasi input menggunakan `Scanner` dan `try-catch`.

---

## Tutorial Menjalankan Proyek

### 1. Prasyarat

Pastikan sistem Anda sudah terinstal:

- Java JDK 21.
- Apache Maven.

### 2. Kompilasi dan Build

Buka terminal di folder root proyek (folder yang berisi `pom.xml`), lalu jalankan perintah:

```bash
mvn clean package

```

Perintah ini akan menghasilkan file executable JAR di dalam folder `target/`.

### 3. Menjalankan Aplikasi

Gunakan perintah berikut untuk menjalankan program melalui file JAR yang sudah dibuat:

```bash
java -jar target/mini-project3-1.0-SNAPSHOT-jar-with-dependencies.jar

```

Atau jika ingin menjalankan langsung melalui Maven:

```bash
mvn exec:java -Dexec.mainClass="ProgramKontak"

```

---

## Skema Database

Aplikasi secara otomatis membuat tabel `kontak` dengan skema sebagai berikut:

- `id`: INTEGER PRIMARY KEY (Auto Increment)
- `nama`: TEXT NOT NULL
- `telepon`: TEXT NOT NULL
- `email`: TEXT (Opsional/Boleh Kosong)

---

### Catatan Pengujian

Tangkapan layar hasil pengujian aplikasi (Fitur 1-5) dapat dilihat pada folder `output_program/` di dalam arsip ini.

---
