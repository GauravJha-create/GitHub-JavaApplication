package MovieBooking;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class NewUser {
    public static String JDBC_URL = "jdbc:mysql://localhost:3306/";
    public static String user = "root";
    public static String password = "jhagaurav7500";
    public static String DBname = "SHOWTIX";
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
            JPanel backgroundPanel = new JPanel() {
                private Image backgroundImage;

                // Load the background image
                {
                    backgroundImage = new ImageIcon("MovieBooking\\images\\newuser.jpg").getImage();
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

            // Panel for login form
            JPanel log1 = new JPanel();
            log1.setLayout(new BorderLayout());
            log1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            log1.setOpaque(false);

            JPanel log = new JPanel();
            log.setLayout(new BoxLayout(log, BoxLayout.Y_AXIS));
            log.setOpaque(true);
            log.setBackground(new Color(0, 0, 0, 200)); // Semi-transparent black
            log.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            JLabel newu = new JLabel("NEW USER");
            newu.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
            newu.setAlignmentX(Component.CENTER_ALIGNMENT);
            newu.setForeground(Color.WHITE);

            JLabel usrname = new JLabel("Create Username:");
            usrname.setFont(new Font("Dialog", Font.PLAIN, 16));
            usrname.setAlignmentX(Component.RIGHT_ALIGNMENT); // Align left
            usrname.setForeground(Color.WHITE);

            JTextField txtusername = new JTextField(20);
            Dimension fieldSize = new Dimension(350, 25); // Set desired width and height
            txtusername.setPreferredSize(fieldSize);
            txtusername.setMaximumSize(fieldSize);
            txtusername.setMinimumSize(fieldSize);

            JLabel usrid = new JLabel("Create UserID:");
            usrid.setAlignmentX(Component.RIGHT_ALIGNMENT); // Align left
            usrid.setForeground(Color.WHITE);
            usrid.setFont(new Font("Dialog", Font.PLAIN, 16));

            JTextField txtuserid = new JTextField(20);
            txtuserid.setPreferredSize(fieldSize);
            txtuserid.setMaximumSize(fieldSize);
            txtuserid.setMinimumSize(fieldSize);

            JLabel pss = new JLabel("Set Password:");
            pss.setAlignmentX(Component.RIGHT_ALIGNMENT); // Align left
            pss.setForeground(Color.WHITE);
            pss.setFont(new Font("Dialog", Font.PLAIN, 16));

            JPasswordField txtpassword = new JPasswordField(20);
            txtpassword.setPreferredSize(fieldSize);
            txtpassword.setMaximumSize(fieldSize);
            txtpassword.setMinimumSize(fieldSize);

            JLabel conf = new JLabel("Confirm Password:");
            conf.setAlignmentX(Component.RIGHT_ALIGNMENT); // Align left
            conf.setForeground(Color.WHITE);
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
            log.add(Box.createVerticalStrut(10));
            log.add(usrname);
            log.add(txtusername);
            log.add(Box.createVerticalStrut(10));
            log.add(usrid);
            log.add(txtuserid);
            log.add(Box.createVerticalStrut(10));
            log.add(pss);
            log.add(txtpassword);
            log.add(Box.createVerticalStrut(10));
            log.add(conf);
            log.add(txtconf);
            log.add(Box.createVerticalStrut(10));
            log.add(btPnl);

            log1.add(log, BorderLayout.EAST);

            backgroundPanel.add(log1, BorderLayout.CENTER);
            fr.setContentPane(backgroundPanel);
            fr.setVisible(true);

            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String addUserId = txtuserid.getText();
                    String addUsername = txtusername.getText();
                    String addPassword = new String(txtpassword.getPassword());
                    String confirmPassword = new String(txtconf.getPassword());
                    if (addPassword.equals(confirmPassword)) {
                        information(addUserId, addUsername, addPassword, fr);
                        fr.dispose();
                        new ExistingUserMovie().main(args);
                    }

                    // adding an error message pop-up
                    else {
                        JOptionPane.showMessageDialog(null, "Password doesnot match. Try Again.");
                    }
                }
            });

            exist.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    new ExistingUserMovie().main(args); // Navigate to signup page
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static void information(String addUserID, String addUsername, String addPassword, JFrame fr) {
        try {
            Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            Statement stat = con.createStatement();
            String useDB = "USE SHOWTIX";
            stat.executeUpdate(useDB);

            PreparedStatement ps = con.prepareStatement("Select Userid from LOGININFO;");
            ResultSet rs = ps.executeQuery();
            int fl = 0;
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
