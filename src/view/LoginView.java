package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.AuthController; // Import LoginController
import enums.UserRole;
import view.dashboard.*;

public class LoginView extends JFrame {
    private static LoginView frame;
    private static JDesktopPane desktopPane;
    private static JTextField tx_user;
    private static JPasswordField tx_pass;
    private static JButton btn_show_pass;
    private static JButton btn_login;

    private final AuthController authController;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
        });
    }

    public LoginView() {
        // Inisialisasi LoginController di sini
        this.authController = new AuthController(this); 
        initializeComponents(); 
    }

    // Inisialisasi komponen GUI
    private void initializeComponents() {
        System.out.println("Welcome to Login Page");

        // ### FRAME UTAMA
        frame = this;
        frame.setTitle("Traveliki - Login");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setResizable(false);

        desktopPane = new JDesktopPane();
        frame.setContentPane(desktopPane);

        ImageIcon imageIcon = new ImageIcon("src/resource/login.png");
        Image image = imageIcon.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH);

        JLabel backgroundLabel = new JLabel(new ImageIcon(image));
        backgroundLabel.setBounds(0, 0, 800, 400);
        desktopPane.add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));

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

        tx_pass = new JPasswordField(20);
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

            authController.handleLogin(username, password);
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

        // ### KEY LISTENER
        tx_user.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tx_pass.requestFocus(); // Pindahkan fokus ke field password
                }
            }
        });

        tx_pass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String username = tx_user.getText();
                    String password = new String(tx_pass.getPassword());

                    authController.handleLogin(username, password); // Panggil method login);
                }
            }
        });


        // ### MOUSE LISTENER
        click_here.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showRegister();
            }
        });
        
        click_here.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                click_here.setForeground(new Color(13, 108, 176).darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                click_here.setForeground(new Color(13, 108, 176));
            }
        });
        
        click_here.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                click_here.setForeground(Color.WHITE);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                click_here.setForeground(new Color(13, 108, 176).darker());
            }
        });

        // ### WINDOW LISTENER
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // ### CENTER FRAME
        centerFrameOnScreen(frame);
        frame.setVisible(true);
    }

    public void showLoginSuccess(UserRole userRole) {
        JOptionPane.showMessageDialog(this, "Login Successful!");
        this.dispose(); // Tutup jendela login

        // Tampilkan dashboard sesuai dengan role pengguna
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

    private void showRegister() {
        frame.dispose();

        RegisterView registerView = new RegisterView();
        registerView.setVisible(true);
    }

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