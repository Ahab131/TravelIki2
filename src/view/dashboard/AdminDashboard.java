package view.dashboard;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.Pengguna;
import service.AuthService;
import view.LoginView;

public class AdminDashboard extends JFrame {
    private static AdminDashboard frame;
    private static LoginView loginView;
    private static JDesktopPane desktopPane;
    private static JButton btn_logout;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AuthService authService = new AuthService(); // for Testing
            AdminDashboard aDashboard = new AdminDashboard(authService.getPengguna());
            aDashboard.setVisible(true);
        });
    }

    public AdminDashboard(Pengguna activeUser) {
        initializeComponents(activeUser);
    }

    // Inisialisasi komponen GUI
    private void initializeComponents(Pengguna activeUser) {
        System.out.println("Welcome to Admin Dashboard");

        // ### FRAME UTAMA
        frame = this;
        frame.setTitle("Traveliki - Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(810, 485);
        frame.setResizable(false);

        desktopPane = new JDesktopPane();
        frame.setContentPane(desktopPane);

        ImageIcon imageIcon = new ImageIcon("src/resource/dashboard.png");
        Image image = imageIcon.getImage().getScaledInstance(800, 450, Image.SCALE_SMOOTH);

        JLabel backgroundLabel = new JLabel(new ImageIcon(image));
        backgroundLabel.setBounds(0, 0, 800, 450);
        desktopPane.add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));

        // ### KOMPONEN GUI

// ======================================================================
        // KARI ISINE TOK KI
        JLabel kontol = new JLabel("KONTOL DASHBOARD");
        kontol.setFont(new Font("Poppins", Font.BOLD, 40));
        kontol.setForeground(Color.WHITE);
        kontol.setBounds(180, 200, 450, 50);
        desktopPane.add(kontol);

        kontol.addMouseMotionListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Math.random();
                kontol.setForeground(new Color((int) (Math.random() * 0x1000000)));
            }
        });

        kontol.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "KARI GANTI ISI E TOK KI!", "Info",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
// ======================================================================

        JLabel role_as = new JLabel("Login as : ");
        role_as.setFont(new Font("Poppins", Font.BOLD, 10));
        role_as.setForeground(Color.WHITE);
        role_as.setBounds(20, 420, 70, 20);
        desktopPane.add(role_as);

        JLabel log_role = new JLabel(activeUser.getRole().toString().toUpperCase());
        log_role.setFont(new Font("Poppins", Font.BOLD, 10));
        log_role.setForeground(Color.WHITE);
        log_role.setBounds(80, 420, 100, 20);
        desktopPane.add(log_role);

        JLabel user_as = new JLabel("Username : ");
        user_as.setFont(new Font("Poppins", Font.BOLD, 10));
        user_as.setForeground(Color.WHITE);
        user_as.setBounds(170, 420, 100, 20);
        desktopPane.add(user_as);

        JLabel log_user = new JLabel(activeUser.getUsername());
        log_user.setFont(new Font("Poppins", Font.BOLD, 10));
        log_user.setForeground(Color.WHITE);
        log_user.setBounds(230, 420, 200, 20);
        desktopPane.add(log_user);

        // Logout
        btn_logout = new JButton("Logout");
        btn_logout.setFont(new Font("Poppins", Font.PLAIN, 10));
        btn_logout.setBackground(new Color(43, 150, 227));
        btn_logout.setForeground(Color.WHITE);
        btn_logout.setBounds(720, 420, 65, 20);
        desktopPane.add(btn_logout);

        // ### ACTION LISTENER
        btn_logout.addActionListener((ActionEvent e) -> {
            loginView = new LoginView();
            loginView.setVisible(true);

            frame.dispose();

        });

        // ### CENTER FRAME
        centerFrameOnScreen(frame);
        frame.setVisible(true);
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
