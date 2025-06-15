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
    public Pengguna authenticateUser(String username, String password) {
        // Validasi lebih lanjut di Service (misal: format username/password, kebijakan keamanan)
        if (username.length() < 3 || password.length() < 6) {
            // Ini contoh validasi di service, biasanya lebih kompleks
            System.out.println("Service: Username too short or password too weak.");
            return null; // Autentikasi gagal
        }

        // Panggil DAO untuk mencari pengguna di database
        Pengguna activeUser = penggunaRepository.getByUsername(username); // Asumsi ada method findByUsername di DAO

        if (activeUser != null && activeUser.getPassword().equals(password)) { // Perlu hashing password asli!
            // Autentikasi sukses, kembalikan role pengguna
            return activeUser;
        } else {
            // Autentikasi gagal
            return null;
        }
    }

    public Pengguna registerUser(String username, String password) {
        if (username.length() < 3 || password.length() < 6) {
            System.out.println("Service: Username too short or password too weak.");
            return null; 
        }

        if (penggunaRepository.getByUsername(username) != null) {
            System.out.println("Service: Username already exists.");
            return null; 
        }

        String email = "asdasd"; // Jangan Lupa dihapus

        Pengguna activeUser = new Pengguna(0, username, email, password, UserRole.CUSTOMER);
        penggunaRepository.create(activeUser);

        return activeUser; 
    }

    // for Testing
    public Pengguna getPengguna() {
        return penggunaRepository.getByUsername("admin"); // Ganti dengan username yang sesuai untuk testing
    }
}