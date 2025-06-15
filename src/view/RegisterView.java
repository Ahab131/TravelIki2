package view;

import javax.swing.*;

import controller.AuthController;
import enums.UserRole;
import view.dashboard.CustomerDashboard;

import java.awt.*;
import java.awt.event.*;

public class RegisterView extends JFrame {
    private static RegisterView frame;
    private static JDesktopPane desktopPane;
    private static JTextField tx_username;
    private static JPasswordField tx_password;
    private static JPasswordField tx_confirmPassword;
    private static JButton btn_register;
    private static JButton btn_cancel;

    private final AuthController authController;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegisterView registerView = new RegisterView();
        });
    }

    public RegisterView() {
        this.authController = new AuthController(this);
        initializeComponents();
    }

    // Inisialisasi komponen GUI
    private void initializeComponents() {
        System.out.println("Welcome to Registration Page");

        // ### FRAME UTAMA
        frame = this;
        frame.setTitle("Traveliki - Register");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setResizable(false);

        desktopPane = new JDesktopPane();
        frame.setContentPane(desktopPane);

        ImageIcon imageIcon = new ImageIcon("src/resource/login.png"); // ganti dengan gambar yang sesuai
        Image image = imageIcon.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH);

        JLabel backgroundLabel = new JLabel(new ImageIcon(image));
        backgroundLabel.setBounds(0, 0, 800, 400);
        desktopPane.add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));

        // ### KOMPONEN GUI
        JLabel lbl_username = new JLabel("Username:");
        lbl_username.setBounds(50, 100, 100, 30);
        desktopPane.add(lbl_username);

        tx_username = new JTextField();
        tx_username.setBounds(150, 100, 200, 30);
        desktopPane.add(tx_username);

        // Tambah email Label dan field

        JLabel lbl_password = new JLabel("Password:");
        lbl_password.setBounds(50, 150, 100, 30);
        desktopPane.add(lbl_password);

        tx_password = new JPasswordField();
        tx_password.setBounds(150, 150, 200, 30);
        desktopPane.add(tx_password);

        JLabel lbl_confirmPassword = new JLabel("Confirm Password:");
        lbl_confirmPassword.setBounds(50, 200, 120, 30);
        desktopPane.add(lbl_confirmPassword);

        tx_confirmPassword = new JPasswordField();
        tx_confirmPassword.setBounds(180, 200, 200, 30);
        desktopPane.add(tx_confirmPassword);

        btn_register = new JButton("Register");
        btn_register.setBounds(150, 250, 100, 30);
        desktopPane.add(btn_register);

        btn_cancel = new JButton("Cancel");
        btn_cancel.setBounds(270, 250, 100, 30);
        desktopPane.add(btn_cancel);

        JLabel lbl_title = new JLabel("Register New User");
        lbl_title.setFont(new Font("Poppins", Font.BOLD, 20));
        lbl_title.setForeground(Color.WHITE);
        lbl_title.setBounds(50, 50, 200, 30);
        desktopPane.add(lbl_title);

        JLabel lbl_note = new JLabel("Please fill in the details below to register.");
        lbl_note.setFont(new Font("Poppins", Font.PLAIN, 12));
        lbl_note.setForeground(Color.WHITE);
        lbl_note.setBounds(50, 70, 300, 20);
        desktopPane.add(lbl_note);

        // ### ACTION LISTENER
        btn_register.addActionListener((ActionEvent e) -> {
            String username = tx_username.getText().trim();
            String password = new String(tx_password.getPassword()).trim();
            String confirmPassword = new String(tx_confirmPassword.getPassword()).trim();

            authController.handleRegister(username, password, confirmPassword);
        });

        btn_cancel.addActionListener((ActionEvent e) -> {
            handleCancel();
        });

        // ### KEY LISTENER
        tx_username.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tx_password.requestFocus();
                }
            }
        });

        tx_password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tx_confirmPassword.requestFocus();
                }
            }
        });

        tx_confirmPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String username = tx_username.getText().trim();
                    String password = new String(tx_password.getPassword()).trim();
                    String confirmPassword = new String(tx_confirmPassword.getPassword()).trim();

                    authController.handleRegister(username, password, confirmPassword);
                }
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

    private void handleCancel() {
        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to cancel registration?",
                "Cancel Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            frame.dispose();

            LoginView loginView = new LoginView();
            loginView.setVisible(true);
        }
    }

    public void showRegisterSuccess(UserRole userRole) {
        JOptionPane.showMessageDialog(this, "Registration Successful!");
        this.dispose(); 

        if (userRole == UserRole.CUSTOMER) {
            CustomerDashboard cDashboard = new CustomerDashboard();
            JOptionPane.showMessageDialog(null, "Welcome New Customer!");

        } else {
            JOptionPane.showMessageDialog(null, "Unknown role. Please contact support.");
        }
    }

    public void showRegisterError(String message) {
        JOptionPane.showMessageDialog(this, message, "Registration Error", JOptionPane.ERROR_MESSAGE);
    }

    private void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }

}
