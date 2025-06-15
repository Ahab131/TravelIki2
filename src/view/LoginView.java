package view;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import controller.LoginController; // Import LoginController
import enums.UserRole;
import view.dashboard.*;

public class LoginView extends JFrame {
    private static LoginView frame;
    private static JDesktopPane desktopPane;
    private static JTextField tx_user;
    private static JPasswordField tx_pass;
    private static JButton btn_show_pass;
    private static JButton btn_login;

    // Tambahkan referensi ke LoginController
    private final LoginController loginController;

    public static void main(String[] args) {
        // Gunakan EventQueue.invokeLater untuk memastikan Swing GUI di-render di Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            // loginView.init();
        });
    }

    // Konstruktor diubah untuk menginisialisasi LoginController
    public LoginView() {
        // Inisialisasi LoginController di sini
        this.loginController = new LoginController(this); // Mengirim referensi diri (this) ke Controller
        initializeComponents(); // Panggil method untuk inisialisasi komponen GUI
    }

    public void init() {
        System.out.println("Initializing Login Page...");
        // showLogin() sekarang dipanggil dari konstruktor melalui initializeComponents()
        // Jadi, method init() ini bisa Anda gunakan untuk logika setup lain jika ada.
    }

    // Method baru untuk menginisialisasi semua komponen GUI
    private void initializeComponents() {
        System.out.println("Welcome to Login Page");

        // ### FRAME UTAMA
        frame = this; // 'this' merujuk pada instance Login saat ini
        frame.setTitle("Traveliki");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setResizable(false);

        desktopPane = new JDesktopPane();
        frame.setContentPane(desktopPane);

        ImageIcon imageIcon = new ImageIcon("src/resource/login.png");
        Image image = imageIcon.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH);

        JLabel backgroundLabel = new JLabel(new ImageIcon(image));
        backgroundLabel.setBounds(0, 0, 800, 400);
        desktopPane.add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));

        centerFrameOnScreen(frame);
        frame.setVisible(true);

        // ### KOMPONEN GUI
        JLabel lbl_user = new JLabel("Username:");
        lbl_user.setFont(new Font("Poppins", Font.BOLD, 10));
        lbl_user.setForeground(Color.WHITE);
        lbl_user.setBounds(490, 135, 120, 10);
        desktopPane.add(lbl_user);

        tx_user = new JTextField(20);
        tx_user.setBounds(480, 150, 250, 30);
        desktopPane.add(tx_user);

        JLabel lbl_pass = new JLabel("Password:");
        lbl_pass.setFont(new Font("Poppins", Font.BOLD, 10));
        lbl_pass.setForeground(Color.WHITE);
        lbl_pass.setBounds(490, 185, 120, 10);
        desktopPane.add(lbl_pass);

        tx_pass = new JPasswordField(20); // Pastikan ini tidak dikomentari
        tx_pass.setBounds(480, 200, 250, 30);
        desktopPane.add(tx_pass);

        JLabel lbl_show_pass = new JLabel("Show password");
        lbl_show_pass.setFont(new Font("Poppins", Font.BOLD, 10));
        lbl_show_pass.setForeground(Color.WHITE);
        lbl_show_pass.setBounds(500, 240, 120, 10);
        desktopPane.add(lbl_show_pass);

        btn_show_pass = new JButton();
        btn_show_pass.setFont(new Font("Poppins", Font.PLAIN, 10));
        btn_show_pass.setBackground(Color.WHITE);
        btn_show_pass.setBounds(480, 240, 15, 15);
        desktopPane.add(btn_show_pass);

        btn_login = new JButton("Login");
        btn_login.setFont(new Font("Poppins", Font.PLAIN, 10));
        btn_login.setBackground(new Color(13, 108, 176));
        btn_login.setForeground(Color.WHITE);
        btn_login.setBounds(620, 240, 110, 30);
        desktopPane.add(btn_login);

        JLabel register = new JLabel("Don't have account?");
        register.setFont(new Font("Poppins", Font.BOLD, 10));
        register.setForeground(Color.WHITE);
        register.setBounds(490, 300, 120, 10);
        desktopPane.add(register);

        JLabel click_here = new JLabel("Click here!");
        click_here.setFont(new Font("Poppins", Font.BOLD, 10));
        click_here.setForeground(new Color(13, 108, 176));
        click_here.setBounds(600, 300, 120, 10);
        desktopPane.add(click_here);

        // ### ACTION LISTENER
        btn_login.addActionListener((ActionEvent e) -> {
            String username = tx_user.getText();
            String password = new String(tx_pass.getPassword());

            // Panggil method di controller, mendelegasikan tugas login
            loginController.handleLogin(username, password);
        });

        btn_show_pass.addActionListener((ActionEvent e) -> {
            if (tx_pass.getEchoChar() == '\u2022') {
                tx_pass.setEchoChar((char) 0);
                lbl_show_pass.setText("Hide Password");
                btn_show_pass.setBackground(new Color(13, 108, 176));
            } else {
                tx_pass.setEchoChar('\u2022');
                lbl_show_pass.setText("Show Password");
                btn_show_pass.setBackground(Color.WHITE);
            }
        });
    }

    // Method yang dipanggil oleh Controller untuk menampilkan pesan sukses/gagal login
    public void showLoginSuccess(UserRole userRole) {
        JOptionPane.showMessageDialog(this, "Login Successful!");
        this.dispose(); // Tutup jendela login

        // Logika untuk menampilkan dashboard sesuai role
        // Pastikan kelas dashboard memiliki constructor yang bisa dipanggil tanpa static
        if (userRole == UserRole.ADMIN) {
            AdminDashboard aDashboard = new AdminDashboard();
            JOptionPane.showMessageDialog(null, "Welcome Admin!");

        } else if (userRole == UserRole.EMPLOYEE) {
            EmployeeDashboard eDashboard = new EmployeeDashboard();
            JOptionPane.showMessageDialog(null, "Welcome Employee!");

        } else if (userRole == UserRole.CUSTOMER) {
            CustomerDashboard cDashboard = new CustomerDashboard();
            JOptionPane.showMessageDialog(null, "Welcome Customer!");

        } else {
            JOptionPane.showMessageDialog(null, "Unknown role. Please contact support.");
        }
    }

    public void showLoginError(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Failed", JOptionPane.ERROR_MESSAGE);
    }

    // Method untuk menampilkan halaman register (tetap di View)
    private void showRegister() {
        System.out.println("Welcome to Register Page");
        // Implementasi lebih lanjut untuk halaman register
    }

    // Method untuk menempatkan frame di tengah layar (tetap di View, karena ini utilitas tampilan)
    private void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int frameWidth = frame.getSize().width;
        int frameHeight = frame.getSize().height;

        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;

        frame.setLocation(x, y);
    }
}