package PasswordManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;

public class NewUser {

    //database information
    public static String JDBC_URL = "jdbc:mysql://localhost:3306/";
    public static String user = "root";
    public static String password = "jhagaurav7500";
    public static String DBname = "PassX";
    public static String tName = "LoginInfo";

    public static void main(String[] args) {

        // creating the database for login info
        try (Connection con = DriverManager.getConnection(JDBC_URL, user, password);
                Statement stat = con.createStatement()) {

            DatabaseMetaData dbm = con.getMetaData();
            ResultSet resultSet = dbm.getCatalogs();

            String useDB = "USE " + DBname;
            stat.executeUpdate(useDB);

            String createTable = "CREATE TABLE IF NOT EXISTS " + tName
                    + "(ID INT auto_increment unique key, UserID varchar(50) PRIMARY KEY, USERNAME varchar(50), PASSWORD varchar(50))";
            stat.executeUpdate(createTable);
            System.out.println("TABLE LoginInfo CREATED SUCCESSFULLY");

            //PasswordManagerHome object created
            PasswordManagerHome pHome = new PasswordManagerHome();

            //creating a JFrame and adding components
            JFrame fr = new JFrame("Signup");
            fr.setSize(400, 450);
            fr.setVisible(true);
            fr.setLocationRelativeTo(null);
            fr.setLayout(null);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JButton home=new JButton("HOME");
            home.setBounds(10, 10, 90, 20);
            fr.add(home);

            home.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    pHome.main(args);
                }
            });

            JLabel lbl = new JLabel("Create Account");
            lbl.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
            lbl.setBounds(130, 20, 300, 50);
            fr.add(lbl);

            JLabel tx1 = new JLabel("ENTER USERNAME");
            tx1.setFont(new Font("Dialog", Font.PLAIN, 16));
            tx1.setBounds(125, 80, 300, 20);
            fr.add(tx1);

            JTextField txtFld1 = new JTextField(50);
            txtFld1.setBounds(120, 110, 150, 20);
            fr.add(txtFld1);

            JLabel userid = new JLabel("ENTER USERID");
            userid.setFont(new Font("Dialog", Font.PLAIN, 16));
            userid.setBounds(140, 140, 300, 20);
            fr.add(userid);

            JTextField txtuserid = new JTextField(50);
            txtuserid.setBounds(120, 170, 150, 20);
            fr.add(txtuserid);

            JLabel tx2 = new JLabel("PASSWORD");
            JLabel txPass = new JLabel("Enter password");
            tx2.setFont(new Font("Dialog", Font.PLAIN | Font.ROMAN_BASELINE, 16));
            txPass.setFont(new Font("Serif", Font.PLAIN, 16));
            tx2.setBounds(150, 210, 300, 20);
            txPass.setBounds(120, 240, 300, 20);
            fr.add(tx2);
            fr.add(txPass);

            JPasswordField txtFld2 = new JPasswordField(50);
            txtFld2.setBounds(120, 265, 150, 20);
            fr.add(txtFld2);

            JLabel tx4 = new JLabel("Re-enter password");
            tx4.setFont(new Font("Serif", Font.PLAIN, 16));
            tx4.setBounds(120, 290, 300, 20);
            fr.add(tx4);

            JPasswordField txtFld3 = new JPasswordField(50);
            txtFld3.setBounds(120, 315, 150, 20);
            fr.add(txtFld3);

            JButton submit = new JButton("SUBMIT");
            submit.setBounds(145, 360, 100, 20);
            fr.add(submit);

            //defining function of the SUBMIT button
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String addUserId = txtuserid.getText();
                    String addUsername = txtFld1.getText();
                    String addPassword = new String(txtFld2.getPassword());
                    String confirmPassword=new String(txtFld3.getPassword());
                    if (addPassword.equals(confirmPassword)) {
                        information(addUserId, addUsername, addPassword, fr);
                        fr.dispose();
                        pHome.main(args);
                    }
                    
                    //adding an error message pop-up
                    else {
                        JOptionPane.showMessageDialog(null, "Password doesnot match. Try Again.");
                    }
                }
            });
        }
         catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    static void information(String addUserID, String addUsername, String addPassword, JFrame fr){
        try {
            Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            Statement stat = con.createStatement();
            String useDB = "USE PassX";
            stat.executeUpdate(useDB);

            PreparedStatement ps=con.prepareStatement("Select Userid from LOGININFO;");
            ResultSet rs=ps.executeQuery();
            int fl=0;
            while(rs.next()){
                String ID=rs.getString("USERID");
                if(ID.equals(addUserID)){
                    JOptionPane.showMessageDialog(fr, "UserID Already Exists");
                    fl=1;
                }
            }

            if(fl==0){
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
