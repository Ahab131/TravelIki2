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
        // berfungsi untuk menampilkan semua pengguna
    }

    public Pengguna getById(int id) {
        // berfungsi untuk menampilkan pengguna berdasarkan id
    }

    public Pengguna getByUsername(String username) {
        // berfungsi untuk menampilkan pengguna berdasarkan username
    }

    public List<Pengguna> getByRole(String role) {
        // berfungsi untuk menampilkan pengguna berdasarkan role
    }

    public void createPengguna(String username, String email, String password, String role) {
        // berfungsi untuk menambahkan pengguna baru
    }

    public void updatePengguna(int id, String username, String email, String password, String role) {
        Pengguna pengguna = new Pengguna(id, username, email, password, UserRole.valueOf(role.toUpperCase()));
        penggunaService.update(pengguna);
    }

    public void deletePengguna(int id) {
        penggunaService.delete(id);
    }
}