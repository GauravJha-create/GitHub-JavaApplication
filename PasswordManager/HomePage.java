package PasswordManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class HomePage {

    //database info
    public static String JDBC_URL = //your values;
    public static String user = //your values;
    public static String password = //your values;
    public static String DBname = /your values;
    public static String tName = //your values;
    public static String username="", userpass="";

    public static PasswordManagerHome pHome=new PasswordManagerHome();
    public static ExistingUser eu=new ExistingUser();
    public static NewUser nu=new NewUser();
    public static addPassword aP=new addPassword();
    public static ViewSavedPass vp=new ViewSavedPass();
    public static OpenProfile op=new OpenProfile();

    public static void main(String[] args) {

        JFrame fr=new JFrame("PASS X");
        
        //creating new menubar and adding menu options
        JMenuBar menuBar=new JMenuBar();
        JMenu Profile=new JMenu("PROFILE");
        
        JMenuItem openProfile=new JMenuItem("Open Profile");
        JMenuItem logout=new JMenuItem("Logout");

        Profile.add(openProfile);
        Profile.add(logout);

        menuBar.add(Profile);
        fr.setJMenuBar(menuBar);

        //adding background image
        ImageIcon bg=new ImageIcon(ClassLoader.getSystemResource("PasswordManager\\HomePage.png"));
        Image i2=bg.getImage().getScaledInstance(800,500,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel img=new JLabel(i3);
        img.setLayout(new BorderLayout());
        fr.setContentPane(img);

        //frame properties
        fr.setBounds(400,200,800,500);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        fr.setLayout(null);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //defining texts on the frame
        JLabel wel=new JLabel("Welcome ");
        wel.setBounds(280, 20, 100, 50);
        wel.setFont(new Font("Serif",Font.PLAIN,20));
        wel.setForeground(Color.WHITE);

        JLabel uname=new JLabel(eu.username);
        uname.setBounds(365,17,200,50);
        uname.setFont(new Font("Times New Roman",Font.BOLD,35));
        uname.setForeground(Color.WHITE);

        JLabel text=new JLabel("Add new Passwords or view your saved Passwords.");
        text.setBounds(250,50,400,50);
        text.setFont(new Font("Serif",Font.ITALIC,15));
        text.setForeground(Color.WHITE);

        fr.add(wel);
        fr.add(uname);
        fr.add(text);

        //adding buttons
        JButton addPass=new JButton("Add Password");
        addPass.setBounds(20,120,150,30);
        fr.add(addPass);

        JButton viewPass=new JButton("View Password");
        viewPass.setBounds(20,160,150,30);
        fr.add(viewPass);
        

        //button functions
        addPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                fr.disable();
                addPassword();
                fr.enable();
            }
        });

        viewPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                vp.main(args);
            }
        });

        openProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                op.main(args);
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                int res=JOptionPane.showConfirmDialog(null, "Logout from '"+eu.userID+"'?", null, 0);
                if(res==JOptionPane.YES_OPTION)
                fr.dispose();
                pHome.main(args);
            }
        });

    }

    public static void addPassword(){
        try {
            Connection con=DriverManager.getConnection(JDBC_URL, user, password);
            Statement stat=con.createStatement();

            stat.executeUpdate("USE PASSXUSERS");
            String createTable="CREATE TABLE IF NOT EXISTS "+eu.userID+"(ID int auto_increment primary key, WEBSITE varchar(20), PASSWORD varchar(50),URL varchar(250));";
            stat.executeUpdate(createTable);
            aP.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
