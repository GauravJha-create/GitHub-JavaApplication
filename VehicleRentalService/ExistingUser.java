package VehicleRentalService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class ExistingUser {

    // Database information
    public static String JDBC_URL = "jdbc:mysql://localhost:3306/";
    public static String user = {YOUR USER NAME};
    public static String password = {YOUR USER PASSWORD};
    public static String DBname = "TakeTaxi";
    public static String tName = "LoginInfo";
    public static String userID = "", userpass = "", username = "";

    public static String aadharText = "", licNo = "", addressDet = "", phoneNo = "";

    public static void main(String[] args) {

        JFrame fr = new JFrame("Login");
        fr.setSize(600, 450);
        fr.setLocationRelativeTo(null);
        fr.setLayout(new BorderLayout());
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel for image
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("BikeRentalService\\images\\login.png"));
        Image i2 = backgroundIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setHorizontalAlignment(SwingConstants.LEFT);
        imagePanel.add(img, BorderLayout.CENTER);

        // Panel for login form
        JPanel log = new JPanel();
        log.setLayout(new BoxLayout(log, BoxLayout.Y_AXIS));
        log.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        log.setOpaque(false);

        JLabel ex = new JLabel("EXISTING USER", SwingConstants.CENTER);
        ex.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
        ex.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usr = new JLabel("Username:");
        usr.setFont(new Font("Dialog", Font.PLAIN, 16));
        JTextField txtuserid = new JTextField(20);
        Dimension fieldSize = new Dimension(200, 25); // Set desired width and height
        txtuserid.setPreferredSize(fieldSize);
        txtuserid.setMaximumSize(fieldSize);
        txtuserid.setMinimumSize(fieldSize);

        JLabel pss = new JLabel("Password:");
        pss.setFont(new Font("Dialog", Font.PLAIN, 16));
        JPasswordField txtpassword = new JPasswordField(20);
        txtpassword.setPreferredSize(fieldSize);
        txtpassword.setMaximumSize(fieldSize);
        txtpassword.setMinimumSize(fieldSize);

        JPanel btPnl = new JPanel();
        btPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton submit = new JButton("Login");
        JButton signup = new JButton("Signup");
        btPnl.add(submit);
        btPnl.add(signup);
        btPnl.setOpaque(false);

        log.add(ex);
        log.add(Box.createVerticalStrut(20));
        log.add(usr);
        log.add(txtuserid);
        log.add(Box.createVerticalStrut(10));
        log.add(pss);
        log.add(txtpassword);
        log.add(Box.createVerticalStrut(20));
        log.add(btPnl);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(imagePanel, BorderLayout.WEST);
        mainPanel.add(log, BorderLayout.CENTER);
        fr.add(mainPanel);

        // Event listeners
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userID = txtuserid.getText();
                userpass = new String(txtpassword.getPassword()); // Get password securely

                String pwd = checkDB(userID);
                if (userpass.equals(pwd)) {
                    fr.dispose();
                    new UserHomePage().main(args); // Navigate to home page
                } else if (userID.equals("admin") && userpass.equals("admin")) {
                    fr.dispose();
                    new AdminPage().main(args); // Navigate to admin page
                } else if (pwd.equals("null")) {
                    JOptionPane.showMessageDialog(null, "This UserID is not registered.");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password");
                }

                try {
                    Connection con = DriverManager.getConnection(JDBC_URL, user, password);
                    PreparedStatement db = con.prepareStatement("Use TAKETAXI");
                    db.execute();

                    PreparedStatement ps1 = con.prepareStatement(
                            "Select AADHAR,LICENSE_NUMBER,ADDRESS,PHONE_NUMBER from TAKETAXIUSERS where userid like '"
                                    + userID + "';");
                    ResultSet RS = ps1.executeQuery();

                    while (RS.next()) {
                        aadharText = RS.getString("AADHAR");
                        licNo = RS.getString("LICENSE_NUMBER");
                        addressDet = RS.getString("ADDRESS");
                        phoneNo = RS.getString("PHONE_NUMBER");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr.dispose();
                new NewUserPage().main(args); // Navigate to signup page
            }
        });

        JButton home = new JButton("Home");
        home.setBounds(10, 10, 90, 20);
        fr.add(home, BorderLayout.NORTH);

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr.dispose();
                new TakeTaxiHomepage().main(args); // Navigate to home page
            }
        });

        fr.setVisible(true);
    }

    static String checkDB(String id) {
        String pwd = "null";
        try (Connection con = DriverManager.getConnection(JDBC_URL + DBname, user, password);
                PreparedStatement ps = con
                        .prepareStatement("SELECT Password, Username FROM " + tName + " WHERE UserID = ?")) {
            ps.setString(1, id);
            ResultSet pass = ps.executeQuery();
            if (pass.next()) {
                pwd = pass.getString("Password");
                username = pass.getString("Username");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return pwd;
    }
}
