package PasswordManager;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class PasswordManagerHome {
    public static void main(String[] args) {
        ExistingUser eu=new ExistingUser();
        NewUser nu=new NewUser();
        JFrame fr=new JFrame("PassX");

        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("PasswordManager\\gradIMG.png"));
        Image i2=backgroundIcon.getImage().getScaledInstance(800, 500, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setLayout(new BorderLayout());
        fr.setContentPane(img);

        fr.setBounds(400,200,800,500);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        fr.setLayout(null);

        

        //to end the program execution upon closing the window
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel wel=new JLabel("Welcome to PassX");
        JLabel inf=new JLabel("One Place to store all your passwords");
        JLabel abc=new JLabel("Java Application Project created by Gaurav");
        wel.setFont(new Font("Times New Roman",Font.BOLD|Font.ITALIC,30));
        inf.setFont(new Font("Serif",Font.PLAIN,16));
        abc.setFont(new Font("Serif",Font.PLAIN,13));
        wel.setForeground(Color.WHITE);
        inf.setForeground(Color.WHITE);
        abc.setForeground(Color.WHITE);
        wel.setBounds(280,20,300,50);
        inf.setBounds(275,40,300,50);
        abc.setBounds(275,400,300,50);
        fr.add(wel);
        fr.add(inf);
        fr.add(abc);
        
        JButton exiUser=new JButton("Existing User");
        JButton newUser=new JButton("New User");
        exiUser.setBounds(330,150,120,40);
        fr.add(exiUser);
        newUser.setBounds(330,250,120,40);
        fr.add(newUser);

        exiUser.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                fr.dispose();
                eu.main(args);
            }
        });

        newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr.dispose();
                nu.main(args);
            }
        });

    }
    
}
