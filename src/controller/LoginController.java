package controller;

import enums.UserRole;
import service.AuthService; // Import AuthService
import view.LoginView; // Import View Login

public class LoginController {

    private final AuthService authService; // Referensi ke lapisan Service
    private final LoginView loginView; // Referensi ke View

    // Konstruktor menerima instance View
    public LoginController(LoginView loginView) {
        this.authService = new AuthService(); // Inisialisasi AuthService
        this.loginView = loginView;
    }

    // Method yang dipanggil oleh View saat tombol login diklik
    public void handleLogin(String username, String password) {
        // Lakukan validasi dasar input di Controller (misal: cek kosong)
        if (username.isEmpty() || password.isEmpty()) {
            loginView.showLoginError("Username and password cannot be empty.");
            return; // Hentikan proses jika validasi gagal
        }

        // Delegasikan logika autentikasi ke Service
        UserRole userRole = authService.authenticateUser(username, password);

        // Setelah Service mengembalikan hasil, Controller yang memutuskan apa yang harus ditampilkan View
        if (userRole != null) {
            loginView.showLoginSuccess(userRole); // Meminta View untuk menampilkan sukses
        } else {
            loginView.showLoginError("Invalid username or password."); // Meminta View untuk menampilkan error
        }
    }
}