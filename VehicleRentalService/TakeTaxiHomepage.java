package VehicleRentalService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TakeTaxiHomepage {
    public static void main(String[] args) {
        ExistingUser eu = new ExistingUser();
        NewUserPage nup = new NewUserPage();

        JFrame fr = new JFrame("TakeTaxi");
        fr.setSize(600, 450);
        fr.setLayout(new BorderLayout());
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
        fr.setLocationRelativeTo(null);

        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("BikeRentalService\\images\\taxi.png"));
        Image i2 = backgroundIcon.getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setLayout(new BorderLayout());
        fr.setContentPane(img);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        ;
        JPanel textPanel1 = new JPanel();
        JPanel textPanel2 = new JPanel();
        textPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        textPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        JLabel a = new JLabel("Welcome to TakeTaxi", SwingConstants.CENTER);
        JLabel b = new JLabel("Book your next ride with just one click!!", SwingConstants.CENTER);
        a.setFont(new Font("Times New Roman", Font.BOLD, 20));
        b.setFont(new Font("Sans Serif", Font.BOLD, 15));
        textPanel1.add(a);
        textPanel2.add(b);
        mainPanel.add(textPanel1);
        mainPanel.add(textPanel2);
        fr.add(mainPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

        JButton exiUser = new JButton("Existing User");
        JButton newUser = new JButton("New User");
        buttonPanel.add(exiUser);
        buttonPanel.add(newUser);
        fr.add(buttonPanel, BorderLayout.SOUTH);

        exiUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fr.dispose();
                eu.main(args);
            }
        });

        newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr.dispose();
                nup.main(args);
            }
        });
    }
}
