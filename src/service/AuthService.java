package service;

import enums.UserRole;
import model.Pengguna; // Asumsi ada kelas User (Model)
import repository.PenggunaRepository; // Asumsi ada kelas UserRepository (DAO)

public class AuthService {

    private final PenggunaRepository penggunaRepository; // Referensi ke lapisan DAO

    public AuthService() {
        this.penggunaRepository = new PenggunaRepository(); // Inisialisasi UserRepository
    }

    // public static void main(String[] args) {
    //     AuthService authService = new AuthService();
    //     UserRole role = authService.authenticateUser("admin", "admin123");
    //     System.out.println("Authenticated role: " + (role != null ? role : "Authentication failed"));
    // }

    // Logika bisnis untuk autentikasi pengguna
    public UserRole authenticateUser(String username, String password) {
        // Validasi lebih lanjut di Service (misal: format username/password, kebijakan keamanan)
        if (username.length() < 3 || password.length() < 6) {
            // Ini contoh validasi di service, biasanya lebih kompleks
            System.out.println("Service: Username too short or password too weak.");
            return null; // Autentikasi gagal
        }

        // Panggil DAO untuk mencari pengguna di database
        Pengguna user = penggunaRepository.getByUsername(username); // Asumsi ada method findByUsername di DAO

        if (user != null && user.getPassword().equals(password)) { // Perlu hashing password asli!
            // Autentikasi sukses, kembalikan role pengguna
            return user.getRole();
        } else {
            // Autentikasi gagal
            return null;
        }
    }
}