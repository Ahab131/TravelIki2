package controller;

import enums.UserRole;
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

        UserRole userRole = authService.authenticateUser(username, password);

        if (userRole != null) {
            loginView.showLoginSuccess(userRole);
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

        UserRole userRole = authService.registerUser(username, password); 

        if (userRole != null) {
            registerView.showRegisterSuccess(userRole); 
        } else {
            registerView.showRegisterError("Registration failed. Username may already exist.");
        }
    }
}