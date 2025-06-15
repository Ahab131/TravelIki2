package controller;

import model.Pengguna;
import service.AuthService;
import view.LoginView;
import view.RegisterView; 

public class AuthController {

    private final AuthService authService;
    private LoginView loginView;
    private RegisterView registerView;

    public AuthController(LoginView loginView) {
        this.authService = new AuthService(); 
        this.loginView = loginView;
    }

    public AuthController(RegisterView registerView) {
        this.authService = new AuthService(); 
        this.registerView = registerView;
    }

    public void handleLogin(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            loginView.showLoginError("Username and password cannot be empty.");
            return;
        }

        Pengguna activeUser = authService.authenticateUser(username, password);

        if (activeUser != null) {
            loginView.showLoginSuccess(activeUser);
        } else {
            loginView.showLoginError("Invalid username or password."); 
        }
    }

    public void handleRegister(String username, String password, String confirmPassword) {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            registerView.showRegisterError("All fields are required.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            registerView.showRegisterError("Passwords do not match.");
            return;
        }

        Pengguna activeUser = authService.registerUser(username, password); 

        if (activeUser.getRole() != null) {
            registerView.showRegisterSuccess(activeUser); 
        } else {
            registerView.showRegisterError("Registration failed. Username may already exist.");
        }
    }
}