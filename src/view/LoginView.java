package view;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import controller.LoginController;
import enums.UserRole;
import view.dashboard.*;

public class LoginView extends JFrame {
    private static LoginView frame;
    private static JDesktopPane desktopPane;
    private static JTextField tx_user;
    private static JPasswordField tx_pass;
    private static JButton btn_show_pass;
    private static JButton btn_login;

    private final LoginController loginController;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Inisialisasi halaman login
                LoginView login = new LoginView();
                login.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginView() {
        this.loginController = new LoginController(this);

    }

    public void init() {
        // Inisialisasi halaman login
        System.out.println("Initializing Login Page...");
        showLogin();
    }

    // Ini class Login
    private void showLogin() {
        System.out.println("Welcome to Login Page");
        // Implementasi lebih lanjut untuk halaman login

        // ### FRAME UTAMA
        frame = new LoginView();
        frame.setTitle("Traveliki");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400); // Ubah ukuran sesuai kebutuhan
        frame.setResizable(false);

        desktopPane = new JDesktopPane();
        frame.setContentPane(desktopPane); // Menetapkan desktopPane sebagai content pane

        // Menampilkan gambar
        ImageIcon imageIcon = new ImageIcon("src/resource/login.png");
        Image image = imageIcon.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH);

        // Menambahkan latar belakang gambar ke desktopPane
        JLabel backgroundLabel = new JLabel(new ImageIcon(image));
        backgroundLabel.setBounds(0, 0, 800, 400);
        desktopPane.add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));

        // Menempatkan frame di tengah layar
        frame.centerFrameOnScreen(frame);        

        // Menampilkan frame
        frame.setVisible(true);

        // ### KOMPONEN GUI
        // Username
        JLabel lbl_user = new JLabel("Username:");
        lbl_user.setFont(new Font("Poppins", Font.BOLD, 10));
        lbl_user.setForeground(Color.WHITE);
        lbl_user.setBounds(490, 135, 120, 10);
        desktopPane.add(lbl_user);

        tx_user = new JTextField(20);
        tx_user.setBounds(480, 150, 250, 30);
        desktopPane.add(tx_user);

        // Password
        JLabel lbl_pass = new JLabel("Password:");
        lbl_pass.setFont(new Font("Poppins", Font.BOLD, 10));
        lbl_pass.setForeground(Color.WHITE);
        lbl_pass.setBounds(490, 185, 120, 10);
        desktopPane.add(lbl_pass);

        tx_pass = new JPasswordField(20);
        tx_pass.setBounds(480, 200, 250, 30);
        desktopPane.add(tx_pass);

        // Show Password
        JLabel lbl_show_pass = new JLabel("Show password");
        lbl_show_pass.setFont(new Font("Poppins", Font.BOLD, 10));
        lbl_show_pass.setForeground(Color.WHITE);
        lbl_show_pass.setBounds(500, 240, 120, 10);
        desktopPane.add(lbl_show_pass);

        // Tombol untuk menampilkan atau menyembunyikan password
        btn_show_pass = new JButton();
        btn_show_pass.setFont(new Font("Poppins", Font.PLAIN, 10));
        btn_show_pass.setBackground(Color.WHITE); // Warna latar belakang tombol
        btn_show_pass.setBounds(480, 240, 15, 15);
        desktopPane.add(btn_show_pass);

        // Login Button
        btn_login = new JButton("Login");
        btn_login.setFont(new Font("Poppins", Font.PLAIN, 10));
        btn_login.setBackground(new Color(13, 108, 176));
        btn_login.setForeground(Color.WHITE);
        btn_login.setBounds(620, 240, 110, 30);
        desktopPane.add(btn_login);

        // Register Menu
        JLabel register = new JLabel("Don't have account?");
        register.setFont(new Font("Poppins", Font.BOLD, 10));
        register.setForeground(Color.WHITE);
        register.setBounds(490, 300, 120, 10);
        desktopPane.add(register);

        // Click here to register
        JLabel click_here = new JLabel("Click here!");
        click_here.setFont(new Font("Poppins", Font.BOLD, 10));
        click_here.setForeground(new Color(13, 108, 176));
        click_here.setBounds(600, 300, 120, 10);
        desktopPane.add(click_here);

        // ### ACTION LISTENER
        // Login button
        btn_login.addActionListener((ActionEvent e) -> {
            String username = tx_user.getText();
            String password = new String(tx_pass.getPassword());

            
            UserRole user_role = login.login(username, password);

            // Contoh validasi sederhana
            if (user_role != null) { // belum di ubah
                JOptionPane.showMessageDialog(frame, "Login Successful!");

                frame.dispose();

                if (user_role == UserRole.ADMIN) {
                    aDashboard = new AdminDashboard();
                    aDashboard.init();
                    JOptionPane.showMessageDialog(frame, "Welcome Admin!");

                    // Implementasi lebih lanjut untuk halaman admin

                } else if (user_role == UserRole.EMPLOYEE) {
                    eDashboard = new EmployeeDashboard();
                    eDashboard.init();
                    JOptionPane.showMessageDialog(frame, "Welcome Employee!");

                    // Implementasi lebih lanjut untuk halaman employee

                } else if (user_role == UserRole.CUSTOMER) {
                    cDashboard = new CustomerDashboard();
                    cDashboard.init();
                    JOptionPane.showMessageDialog(frame, "Welcome Customer!");

                    // Implementasi lebih lanjut untuk halaman customer
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Unknown role. Please contact support.");

                }
            } else {
                JOptionPane.showMessageDialog(frame, "Login Failed. Invalid username or password.");
                // Lakukan tindakan setelah login gagal di sini
            }
        });

        // Show password
        btn_show_pass.addActionListener((ActionEvent e) -> {
            // Toggle EchoChar untuk menampilkan/menyembunyikan password
            if (tx_pass.getEchoChar() == '\u2022') { // Jika saat ini ditampilkan dengan karakter tersembunyi
                tx_pass.setEchoChar((char) 0); // Tampilkan teks asli
                lbl_show_pass.setText("Hide Password");
                btn_show_pass.setBackground(new Color(13, 108, 176)); // Warna latar belakang tombol
            } else {
                tx_pass.setEchoChar('\u2022'); // Gunakan karakter tersembunyi (bintang)
                lbl_show_pass.setText("Show Password");
                btn_show_pass.setBackground(Color.WHITE); // Warna latar belakang tombol
            }
        });

    }

    public void showLoginSuccess(UserRole userRole) {
        JOptionPane.showMessageDialog(this, "Login Successful!");
        this.dispose(); // Tutup jendela login

        // Logika untuk menampilkan dashboard sesuai role
        // Pastikan kelas dashboard memiliki constructor yang bisa dipanggil tanpa static
        if (userRole == UserRole.ADMIN) {
            AdminDashboard aDashboard = new AdminDashboard();
            aDashboard.init();
            JOptionPane.showMessageDialog(null, "Welcome Admin!");
        } else if (userRole == UserRole.EMPLOYEE) {
            EmployeeDashboard eDashboard = new EmployeeDashboard();
            eDashboard.init();
            JOptionPane.showMessageDialog(null, "Welcome Employee!");
        } else if (userRole == UserRole.CUSTOMER) {
            CustomerDashboard cDashboard = new CustomerDashboard();
            cDashboard.init();
            JOptionPane.showMessageDialog(null, "Welcome Customer!");
        } else {
            JOptionPane.showMessageDialog(null, "Unknown role. Please contact support.");
        }
    }
    
    private void showRegister() {
        System.out.println("Welcome to Register Page");
        // Implementasi lebih lanjut untuk halaman register
    }

    // login error
    public void showLoginError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Login Error", JOptionPane.ERROR_MESSAGE);
    }

    // menempatkan frame di tengah layar
    private void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Mendapatkan ukuran layar saat ini
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int frameWidth = frame.getSize().width; // Mendapatkan ukuran frame
        int frameHeight = frame.getSize().height;

        int x = (screenWidth - frameWidth) / 2; // Menghitung posisi x dan y untuk frame di tengah layar
        int y = (screenHeight - frameHeight) / 2;

        frame.setLocation(x, y); // Menetapkan posisi frame di tengah layar
    }
    
}
 