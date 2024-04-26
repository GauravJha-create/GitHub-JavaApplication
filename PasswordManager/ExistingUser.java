package PasswordManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class ExistingUser {

    // database information
    public static String JDBC_URL = //your value;
    public static String user = //your values;
    public static String password = //your values;
    public static String DBname = /your values;
    public static String tName = //your values;
    public static String userID="", userpass="",username="";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            Statement stat = con.createStatement()) {

                DatabaseMetaData dbm = con.getMetaData();
                ResultSet resultSet = dbm.getCatalogs();

                String useDB = "USE " + DBname;
                stat.executeUpdate(useDB);

                PasswordManagerHome pHome = new PasswordManagerHome();
                NewUser nu = new NewUser();
                HomePage hp=new HomePage();

                JFrame fr = new JFrame("Login");
                fr.setSize(400, 450);
                fr.setVisible(true);
                fr.setLocationRelativeTo(null);
                fr.setLayout(null);
                fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JLabel lbl = new JLabel("EXISTING USER");
                lbl.setFont(new Font("TIMES NEW ROMAN", Font.BOLD | Font.HANGING_BASELINE, 20));
                lbl.setBounds(120, 20, 300, 50);
                fr.add(lbl);

                JButton home = new JButton("HOME");
                home.setBounds(10, 10, 90, 20);
                fr.add(home);

                home.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fr.dispose();
                        pHome.main(args);
                    }
                });

                JLabel tx1 = new JLabel("USERID");
                tx1.setFont(new Font("Dialog", Font.PLAIN, 16));
                tx1.setBounds(150, 100, 300, 20);
                fr.add(tx1);

                JTextField txtuserid = new JTextField(50);
                txtuserid.setBounds(120, 130, 150, 20);
                fr.add(txtuserid);

                JLabel tx2 = new JLabel("PASSWORD");
                tx2.setFont(new Font("Dialog", Font.PLAIN | Font.ROMAN_BASELINE, 16));
                tx2.setBounds(150, 170, 300, 20);
                fr.add(tx2);

                JPasswordField txtpassword = new JPasswordField(50);
                txtpassword.setBounds(120, 200, 150, 20);
                fr.add(txtpassword);

                JButton submit = new JButton("SUBMIT");
                submit.setBounds(145, 240, 100, 20);
                fr.add(submit);

                JButton signup = new JButton("SIGNUP");
                signup.setBounds(155, 300, 80, 20);
                fr.add(signup);

                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        userID = txtuserid.getText();
                        userpass = txtpassword.getText();

                        String pwd=checkDB(userID);
                        
                         if(userpass.equals(pwd)){
                           fr.dispose();
                           hp.main(args);
                        }

                        else if(pwd.equals("null"))
                        JOptionPane.showMessageDialog(null, "This UserID is not registered.");

                        else{
                            JOptionPane.showMessageDialog(null, "Incorrect Password");
                        }
                    }   
                });

                signup.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fr.dispose();
                        nu.main(args);
                    }
                });

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    static String checkDB(String id){
        String pwd="null";
        try {
            Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            Statement stat = con.createStatement();
            stat.executeUpdate("USE PASSX");
            PreparedStatement ps = con.prepareStatement("select Password, Username from LOGININFO where UserID like '"+id+"';");
            ResultSet pass=ps.executeQuery();
            while (pass.next()) {
                pwd=pass.getString("Password");
                username=pass.getString("Username");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return pwd;
    }
}
