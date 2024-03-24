package PasswordManager;

import java.awt.*;
import java.beans.Statement;
import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OpenProfile {
    // database information
    public static String JDBC_URL = "jdbc:mysql://localhost:3306/";
    public static String user = "root";
    public static String password = "jhagaurav7500";
    public static String DBname = "PassXUsers";

    //obj creation
    public static ExistingUser eu=new ExistingUser();
    public static void main(String[] args) {
        try {
            Connection con=DriverManager.getConnection(JDBC_URL, user, password);
            PreparedStatement db=con.prepareStatement("Use Passxusers");
            db.execute();

            JFrame fr=new JFrame("Profile");
            fr.setSize(300,400);
            fr.setLayout(null);
            fr.setLocationRelativeTo(null);

            JLabel img = new JLabel();
            ImageIcon icon = new ImageIcon("PasswordManager\\padlock.jpg");
            img.setIcon(icon);
            img.setBounds(100, 100, icon.getIconWidth(), icon.getIconHeight());
            fr.add(img);

            JLabel uname=new JLabel("Name: ");
            uname.setBounds(20, 50, 50, 30);
            fr.add(uname);

            JLabel name=new JLabel(eu.username);
            name.setBounds(80, 50, 100, 30);
            fr.add(name);

            JLabel uid=new JLabel("UserID: ");
            uid.setBounds(20, 80, 50, 30);
            fr.add(uid);

            JLabel userid=new JLabel(eu.userID);
            userid.setBounds(80, 80, 100, 30);
            fr.add(userid);

            JLabel pc=new JLabel("Number of Stored Passwords: ");
            pc.setBounds(20, 110, 200, 30);
            fr.add(pc);

            PreparedStatement ps=con.prepareStatement("Select count(*) as count from "+eu.userID);
            ResultSet rs=ps.executeQuery();
            int count=0;

            while(rs.next()){
                count=rs.getInt("count");
            }

            JLabel countPass=new JLabel(String.valueOf(count));
            countPass.setBounds(200, 110, 100, 30);
            fr.add(countPass);

            fr.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
