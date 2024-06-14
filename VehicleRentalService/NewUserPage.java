package VehicleRentalService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class NewUserPage {

    // Database information
    public static String JDBC_URL = "jdbc:mysql://localhost:3306/";
    public static String user = {YOUR USER NAME};
    public static String password = {YOUR USER PASSWORD};
    public static String DBname = "TakeTaxi";
    public static String tName = "LoginInfo";
    public static String addUserId = "", addUsername = "";
    public static int fl = 0;

    public static void main(String[] args) {

        try (Connection con = DriverManager.getConnection(JDBC_URL, user, password);
                Statement stat = con.createStatement()) {
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet resultSet = dbm.getCatalogs();

            String useDB = "USE " + DBname;
            stat.executeUpdate(useDB);

            String createTable = "CREATE TABLE IF NOT EXISTS " + tName
                    + "(ID INT auto_increment unique key, UserID varchar(50) PRIMARY KEY, USERNAME varchar(50), PASSWORD varchar(50))";
            stat.executeUpdate(createTable);
            JFrame fr = new JFrame("Signup");
            fr.setSize(600, 450);
            fr.setLocationRelativeTo(null);
            fr.setLayout(new BorderLayout());
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Panel for image
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BorderLayout());
            ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("BikeRentalService/new.png"));
            Image i2 = backgroundIcon.getImage().getScaledInstance(150, 400, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel img = new JLabel(i3);
            img.setHorizontalAlignment(SwingConstants.LEFT);
            imagePanel.add(img, BorderLayout.CENTER);

            // Panel for login form
            JPanel log = new JPanel();
            log.setLayout(new BoxLayout(log, BoxLayout.Y_AXIS));
            log.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            log.setOpaque(false);

            JLabel newu = new JLabel("NEW USER");
            newu.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
            newu.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel usrname = new JLabel("Create Username:");
            usrname.setFont(new Font("Dialog", Font.PLAIN, 16));
            JTextField txtusername = new JTextField(20);
            Dimension fieldSize = new Dimension(300, 25); // Set desired width and height
            txtusername.setPreferredSize(fieldSize);
            txtusername.setMaximumSize(fieldSize);
            txtusername.setMinimumSize(fieldSize);

            JLabel usrid = new JLabel("Create UserID:");
            usrid.setFont(new Font("Dialog", Font.PLAIN, 16));
            JTextField txtuserid = new JTextField(20);
            txtuserid.setPreferredSize(fieldSize);
            txtuserid.setMaximumSize(fieldSize);
            txtuserid.setMinimumSize(fieldSize);

            JLabel pss = new JLabel("Password:");
            pss.setFont(new Font("Dialog", Font.PLAIN, 16));
            JPasswordField txtpassword = new JPasswordField(20);
            txtpassword.setPreferredSize(fieldSize);
            txtpassword.setMaximumSize(fieldSize);
            txtpassword.setMinimumSize(fieldSize);

            JLabel conf = new JLabel("Confirm Password:");
            conf.setFont(new Font("Dialog", Font.PLAIN, 16));
            JPasswordField txtconf = new JPasswordField(20);
            txtconf.setPreferredSize(fieldSize);
            txtconf.setMaximumSize(fieldSize);
            txtconf.setMinimumSize(fieldSize);

            JPanel btPnl = new JPanel();
            btPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            JButton login = new JButton("Login");
            JButton exist = new JButton("Existing User");
            btPnl.add(login);
            btPnl.add(exist);
            btPnl.setOpaque(false);

            log.add(newu);
            log.add(Box.createVerticalStrut(20));
            log.add(usrname);
            log.add(txtusername);
            log.add(Box.createVerticalStrut(10));
            log.add(usrid);
            log.add(txtuserid);
            log.add(Box.createVerticalStrut(10));
            log.add(pss);
            log.add(txtpassword);
            log.add(Box.createVerticalStrut(20));
            log.add(conf);
            log.add(txtconf);
            log.add(Box.createVerticalStrut(10));
            log.add(btPnl);

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(imagePanel, BorderLayout.WEST);
            mainPanel.add(log, BorderLayout.CENTER);
            fr.add(mainPanel);

            // Event listeners
            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addUserId = txtuserid.getText();
                    addUsername = txtusername.getText();
                    String addPassword = new String(txtpassword.getPassword());
                    String confirmPassword = new String(txtconf.getPassword());
                    if (addPassword.equals(confirmPassword)) {
                        information(addUserId, addUsername, addPassword, fr);
                        if (fl == 0) {
                            JFrame det = new JFrame("Add Details");
                            det.setSize(400, 350);
                            det.setLocationRelativeTo(null);
                            det.setLayout(new BorderLayout());
                            det.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                            JPanel detPnl = new JPanel();
                            detPnl.setLayout(new BoxLayout(detPnl, BoxLayout.Y_AXIS));
                            detPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                            detPnl.setOpaque(false);

                            JLabel personal = new JLabel("ADD PERSONAL DETAILS", SwingConstants.CENTER);
                            personal.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
                            det.add(personal, BorderLayout.NORTH); // Add personal label to the top center

                            JLabel aadhar = new JLabel("Enter AADHAR NUMBER", SwingConstants.LEFT);
                            aadhar.setFont(new Font("Dialog", Font.PLAIN, 16));
                            aadhar.setAlignmentX(Component.LEFT_ALIGNMENT);
                            JTextField txtaadhar = new JTextField(20);
                            txtaadhar.setPreferredSize(new Dimension(300, 25));
                            txtaadhar.setMaximumSize(new Dimension(300, 25));
                            txtaadhar.setMinimumSize(new Dimension(300, 25));
                            txtaadhar.setAlignmentX(Component.LEFT_ALIGNMENT);

                            JLabel license = new JLabel("Enter LICENSE NUMBER", SwingConstants.LEFT);
                            license.setFont(new Font("Dialog", Font.PLAIN, 16));
                            license.setAlignmentX(Component.LEFT_ALIGNMENT);
                            JTextField txtlicense = new JTextField(20);
                            txtlicense.setPreferredSize(new Dimension(300, 25));
                            txtlicense.setMaximumSize(new Dimension(300, 25));
                            txtlicense.setMinimumSize(new Dimension(300, 25));
                            txtlicense.setAlignmentX(Component.LEFT_ALIGNMENT);

                            JLabel address = new JLabel("ADDRESS", SwingConstants.LEFT);
                            address.setFont(new Font("Dialog", Font.PLAIN, 16));
                            address.setAlignmentX(Component.LEFT_ALIGNMENT);
                            JTextField txtaddress = new JTextField(100);
                            txtaddress.setPreferredSize(new Dimension(300, 25));
                            txtaddress.setMaximumSize(new Dimension(300, 25));
                            txtaddress.setMinimumSize(new Dimension(300, 25));
                            txtaddress.setAlignmentX(Component.LEFT_ALIGNMENT);

                            JLabel phone = new JLabel("Enter Phone Number", SwingConstants.LEFT);
                            phone.setFont(new Font("Dialog", Font.PLAIN, 16));
                            phone.setAlignmentX(Component.LEFT_ALIGNMENT);
                            JTextField txtphone = new JTextField(20);
                            txtphone.setPreferredSize(new Dimension(300, 25));
                            txtphone.setMaximumSize(new Dimension(300, 25));
                            txtphone.setMinimumSize(new Dimension(300, 25));
                            txtphone.setAlignmentX(Component.LEFT_ALIGNMENT);

                            detPnl.add(Box.createVerticalStrut(10)); // Spacing
                            detPnl.add(aadhar);
                            detPnl.add(txtaadhar);
                            detPnl.add(Box.createVerticalStrut(10));
                            detPnl.add(license);
                            detPnl.add(txtlicense);
                            detPnl.add(Box.createVerticalStrut(10));
                            detPnl.add(address);
                            detPnl.add(txtaddress);
                            detPnl.add(Box.createVerticalStrut(10));
                            detPnl.add(phone);
                            detPnl.add(txtphone);
                            detPnl.add(Box.createVerticalStrut(10));

                            JButton confirm = new JButton("CONFIRM");
                            JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
                            btnPnl.add(confirm);
                            btnPnl.setOpaque(false);

                            det.add(detPnl, BorderLayout.CENTER);
                            det.add(btnPnl, BorderLayout.SOUTH); // Add the button panel to the bottom center

                            det.setVisible(true);

                            confirm.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try (Connection con = DriverManager.getConnection(JDBC_URL, user, password);
                                            Statement state = con.createStatement()) {
                                        String useDB = "USE TakeTaxi";
                                        state.executeUpdate(useDB);
                                        String createTable = "CREATE TABLE IF NOT EXISTS TAKETAXIUSERS (ID INT AUTO_INCREMENT UNIQUE, USERID varchar(20), AADHAR varchar(12) PRIMARY KEY, LICENSE_NUMBER VARCHAR(10), ADDRESS VARCHAR(100), PHONE_NUMBER VARCHAR(10))";
                                        state.executeUpdate(createTable);

                                        String aadharString = txtaadhar.getText();
                                        String licenseString = txtlicense.getText();
                                        String addressString = txtaddress.getText();
                                        String phoneString = txtphone.getText();

                                        if (aadharString.length() != 12) {
                                            JOptionPane.showMessageDialog(det,
                                                    "Invalid AADHAR details. AADHAR must be 12 digits.");
                                        } else if (phoneString.length() != 10) {
                                            JOptionPane.showMessageDialog(det,
                                                    "Invalid phone number. Phone number must be 10 digits.");
                                        } else {
                                            try {
                                                String enterData = "INSERT INTO TAKETAXIUSERS (USERID, AADHAR, LICENSE_NUMBER, ADDRESS, PHONE_NUMBER) VALUES (?, ?, ?, ?, ?)";
                                                PreparedStatement preparedStatement = con.prepareStatement(enterData);

                                                preparedStatement.setString(1, addUserId);
                                                preparedStatement.setString(2, aadharString);
                                                preparedStatement.setString(3, licenseString);
                                                preparedStatement.setString(4, addressString);
                                                preparedStatement.setString(5, phoneString);
                                                preparedStatement.executeUpdate();
                                                JOptionPane.showMessageDialog(det, "Data added successfully!");
                                                det.dispose();
                                                fr.dispose();
                                                new ExistingUser().main(args);
                                            } catch (SQLException ex) {
                                                ex.printStackTrace();
                                                JOptionPane.showMessageDialog(det,
                                                        "An error occurred while adding data to the database.");
                                            }
                                        }

                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });
                        } else {
                            fr.dispose();
                            new ExistingUser().main(args);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Password does not match. Try Again.");
                    }
                }
            });

            exist.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    new ExistingUser().main(args); // Navigate to signup page
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void information(String addUserID, String addUsername, String addPassword, JFrame fr) {
        try {
            Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            Statement stat = con.createStatement();
            String useDB = "USE TakeTaxi";
            stat.executeUpdate(useDB);

            PreparedStatement ps = con.prepareStatement("Select Userid from LOGININFO;");
            ResultSet rs = ps.executeQuery();
            fl = 0;
            while (rs.next()) {
                String ID = rs.getString("USERID");
                if (ID.equals(addUserID)) {
                    JOptionPane.showMessageDialog(fr, "UserID Already Exists");
                    fl = 1;
                }
            }

            if (fl == 0) {
                String insData = "INSERT INTO LoginInfo (USERID, USERNAME, PASSWORD) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(insData);

                preparedStatement.setString(1, addUserID);
                preparedStatement.setString(2, addUsername);
                preparedStatement.setString(3, addPassword);

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(fr, "User added successfully!");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(fr, "Error adding user: " + e1.getMessage());
        }

    }
}
