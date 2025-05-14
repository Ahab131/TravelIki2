package controller;

import java.util.List;

import enums.UserRole;
import model.Pengguna;
import service.PenggunaService;

//controller berfungsi untuk menghubungkan antara view dan service
//controller berfungsi untuk menerima input dari view dan mengirimkan output ke view
//controller berfungsi untuk memisahkan logika presentasi dari logika bisnis
//controller berfungsi untuk mengatur alur aplikasi dan menghubungkan antara view dan service

public class PenggunaController {
    private final PenggunaService penggunaService;

    public PenggunaController(PenggunaService penggunaService) {
        this.penggunaService = penggunaService;
    }

    public List<Pengguna> getAll() {
        // Validasi input
        if (penggunaService.getAll() == null || penggunaService.getAll().isEmpty()) {
            throw new IllegalArgumentException("[ Tidak ada pengguna yang ditemukan ]");
        }

        return penggunaService.getAll();
    }

    public Pengguna getById(int id) {
        // Validasi input
        if (id <= 0) {
            throw new IllegalArgumentException("[ ID tidak valid ]");
        }

        return penggunaService.getById(id);
    }

    public Pengguna getByUsername(String username) {
        // Validasi input
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("[ Username tidak boleh kosong ]");
        }
        
        return penggunaService.getByUsername(username);
    }

    public List<Pengguna> getByRole(String role) {
        return penggunaService.getByRole(role);
    }

    public void createPengguna(String username, String email, String password, String role) {
        // Validasi input
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("[ Username tidak boleh kosong ]");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("[ Email tidak boleh kosong ]");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("[ Password tidak boleh kosong ]");
        }
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("[ Role tidak boleh kosong ]");
        }
        if (!role.toUpperCase().equals(UserRole.ADMIN.toString())
                && !role.toUpperCase().equals(UserRole.CUSTOMER.toString())
                && !role.toUpperCase().equals(UserRole.EMPLOYEE.toString())) {
            throw new IllegalArgumentException("[ Role tidak valid ]");
        }

        // Cek apakah pengguna sudah ada
        if (penggunaService.getByUsername(username) != null) {
            throw new IllegalArgumentException("Username sudah ada");
        }
        if (penggunaService.getByUsername(email) != null) {
            throw new IllegalArgumentException("Email sudah ada");
        }
        
        // Buat pengguna baru
        Pengguna pengguna = new Pengguna(0, username, email, password, UserRole.valueOf(role.toUpperCase()));
        
        // Simpan pengguna ke database
        penggunaService.create(pengguna);
        
        // Tampilkan pesan sukses
        System.out.println("Pengguna berhasil dibuat");

        // Tampilkan detail pengguna
        System.out.println("ID: " + pengguna.getId());
        System.out.println("Username: " + pengguna.getUsername());
        System.out.println("Email: " + pengguna.getEmail());
        System.out.println("Role: " + pengguna.getRole());
        System.out.println("Password: " + pengguna.getPassword());
        System.out.println("====================================");
    }

    public void updatePengguna(int id, String username, String email, String password, String role) {
        Pengguna pengguna = new Pengguna(id, username, email, password, UserRole.valueOf(role.toUpperCase()));
        penggunaService.update(pengguna);
    }

    public void deletePengguna(int id) {
        penggunaService.delete(id);
    }
}