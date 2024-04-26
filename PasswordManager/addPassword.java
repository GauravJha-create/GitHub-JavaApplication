package PasswordManager;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

import com.mysql.cj.xdevapi.Statement;

public class addPassword {

    // database information
    public static String JDBC_URL = //your value;
    public static String user = //your value;
    public static String password = //your values ;
    public static String DBname = //your value;

    //obj creation
    public static PasswordManagerHome pHome=new PasswordManagerHome();
    public static ExistingUser eu=new ExistingUser();
    public static NewUser nu=new NewUser();
    public static addPassword aP=new addPassword();

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            PreparedStatement db=con.prepareStatement("Use PASSXUSERS");
            db.executeUpdate();
         

        JFrame fr=new JFrame();
        fr.setSize(500, 350);
        fr.setVisible(true);
        fr.setLocationRelativeTo(null);


        JButton exit=new JButton("Back");
        exit.setBounds(20, 10, 65, 30);
        fr.add(exit);

        JLabel desc=new JLabel("ADD NEW PASSWORD");
        desc.setFont(new Font("Helvetica",Font.PLAIN,20));
        desc.setBounds(140, 20, 300, 30);
        fr.add(desc);

        JLabel sitename=new JLabel("Enter Site Name");
        sitename.setFont(new Font("Serif", Font.BOLD,14));
        sitename.setBounds(90, 65, 300, 30);
        fr.add(sitename);

        JTextField txtsite=new JTextField();
        txtsite.setBounds(90, 95, 200, 20);
        fr.add(txtsite);

        JLabel sitepass=new JLabel("Enter Site Password");
        sitepass.setFont(new Font("Serif", Font.BOLD,14));
        sitepass.setBounds(90, 130, 300, 30);
        fr.add(sitepass);

        JPasswordField txtpass=new JPasswordField();
        txtpass.setBounds(90, 160, 200, 20);
        fr.add(txtpass);

        JLabel siteURL=new JLabel("Enter Site URL");
        siteURL.setFont(new Font("Serif", Font.BOLD,14));
        siteURL.setBounds(90, 195, 300, 30);
        fr.add(siteURL);

        JTextField txtURL=new JTextField();
        txtURL.setBounds(90, 225, 200, 20);
        fr.add(txtURL);

        JButton submit=new JButton("Submit");
        submit.setBounds(200, 260, 90, 30);
        fr.add(submit);


        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String site=txtsite.getText();
                String pass=txtpass.getText();
                String url=txtURL.getText();

                try {
                   PreparedStatement st = con.prepareStatement("Insert into "+eu.userID+" (website, password, url) values(?,?,?);");
                st.setString(1,site);
                st.setString(2,pass);
                st.setString(3,url);

                st.executeUpdate();
                JOptionPane.showMessageDialog(fr, "Information Stored Successfully.");
                fr.dispose();}
                catch (SQLException ev) {
                    // TODO Auto-generated catch block
                    ev.printStackTrace();
                }
            }
        });
    }catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

        
    }
    
}
