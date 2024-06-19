package MovieBooking;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

import VehicleRentalService.AdminPage;
import VehicleRentalService.NewUserPage;
import VehicleRentalService.UserHomePage;

public class ExistingUserMovie {
    public static String JDBC_URL = "jdbc:mysql://localhost:3306/";
    public static String user = "YOUR USERNAME";
    public static String password = "YOUR PASSWORD";
    public static String DBname = "SHOWTIX";
    public static String tName = "LoginInfo";
    public static String userID = "", userpass = "", username = "";

    public static void main(String[] args) {
        JFrame fr = new JFrame("Login");
        fr.setSize(600, 450);
        fr.setLocationRelativeTo(null);
        fr.setLayout(new BorderLayout());
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage;

            // Load the background image
            {
                backgroundImage = new ImageIcon("MovieBooking\\images\\existinguser.jpg").getImage();
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    // Scale the image to fit the panel
                    int width = getWidth();
                    int height = getHeight();
                    g.drawImage(backgroundImage, 0, 0, width, height, this);
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        fr.setContentPane(backgroundPanel);
        fr.setVisible(true);

        // Panel for login form
        JPanel log = new JPanel();
        log.setLayout(new BoxLayout(log, BoxLayout.Y_AXIS));
        log.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        log.setOpaque(true);
        log.setBackground(new Color(0, 0, 0, 200));

        JLabel ex = new JLabel("EXISTING USER", SwingConstants.CENTER);
        ex.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
        ex.setForeground(Color.WHITE);
        ex.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usr = new JLabel("Username:");
        usr.setFont(new Font("Dialog", Font.PLAIN, 16));
        usr.setForeground(Color.WHITE);
        JTextField txtuserid = new JTextField(20);
        Dimension fieldSize = new Dimension(200, 25); // Set desired width and height
        usr.setAlignmentX(Component.RIGHT_ALIGNMENT);
        txtuserid.setPreferredSize(fieldSize);
        txtuserid.setMaximumSize(fieldSize);

        JLabel pss = new JLabel("Password:");
        pss.setFont(new Font("Dialog", Font.PLAIN, 16));
        pss.setForeground(Color.WHITE);
        JPasswordField txtpassword = new JPasswordField(20);
        txtpassword.setMaximumSize(fieldSize);
        pss.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JPanel btPnl = new JPanel();
        btPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton submit = new JButton("Login");
        JButton signup = new JButton("Signup");
        btPnl.add(submit);
        btPnl.add(signup);
        btPnl.setOpaque(false);
        btPnl.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        log.add(ex);
        log.add(Box.createVerticalStrut(30));
        log.add(usr);
        log.add(txtuserid);
        log.add(Box.createVerticalStrut(30));
        log.add(pss);
        log.add(txtpassword);
        log.add(Box.createVerticalStrut(40));
        log.add(btPnl);

        JPanel log1 = new JPanel();
        log1.setLayout(new BorderLayout());
        log1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        log1.setOpaque(false);

        log1.add(log, BorderLayout.WEST);
        fr.add(log1, BorderLayout.CENTER);

        // Event listeners
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userID = txtuserid.getText();
                userpass = new String(txtpassword.getPassword()); // Get password securely

                String pwd = checkDB(userID);
                if (userpass.equals(pwd)) {
                    fr.dispose();
                    new UserHomePageMovie().main(args); // Navigate to home page
                } else if (pwd.equals("null")) {
                    JOptionPane.showMessageDialog(null, "This UserID is not registered.");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password");
                }

            }
        });

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr.dispose();
                new NewUser().main(args); // Navigate to signup page
            }
        });
    }

    static String checkDB(String id) {
        String pwd = "";
        try {
            Connection con = DriverManager.getConnection(JDBC_URL + DBname, user, password);
            Statement stat = con.createStatement();
            stat.executeUpdate("USE SHOWTIX");
            PreparedStatement ps = con
                    .prepareStatement("SELECT Password, Username FROM LOGININFO WHERE UserID LIKE '" + id + "'");
            ResultSet pass = ps.executeQuery();
            if (pass.next()) {
                pwd = pass.getString("Password");
                username = pass.getString("USERNAME");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return pwd;
    }
}
