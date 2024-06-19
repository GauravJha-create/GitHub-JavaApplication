package MovieBooking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowTixHomepage {

    public static void main(String[] args) {
        JFrame fr = new JFrame("SHOWTIX- Movie Ticket Booking System");
        fr.setSize(800, 600); // Increased size for better display
        fr.setLayout(new BorderLayout());
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage;

            // Load the background image
            {
                backgroundImage = new ImageIcon("MovieBooking\\images\\mainhomepage.jpg").getImage();
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
        fr.setContentPane(backgroundPanel);

        JPanel labelPanel = new JPanel();
        labelPanel.setOpaque(true);
        labelPanel.setBackground(new Color(0, 0, 0, 200)); // Semi-transparent black
        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside the panel

        JLabel open = new JLabel("BOOK YOUR NEXT MOVIE WITH SHOWTIX", SwingConstants.CENTER);
        open.setFont(new Font("Arial", Font.PLAIN, 25));
        open.setForeground(Color.WHITE);
        labelPanel.add(open); // Add the label to the panel

        fr.add(labelPanel, BorderLayout.NORTH);

        JPanel postersPanel = new JPanel();
        postersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Adjust spacing here
        postersPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        // Load and resize movie poster images
        int posterWidth = 150; // Desired width
        int posterHeight = 200; // Desired height

        ImageIcon poster1 = new ImageIcon(new ImageIcon("MovieBooking\\images\\movie1.png").getImage()
                .getScaledInstance(posterWidth, posterHeight, Image.SCALE_SMOOTH));
        ImageIcon poster2 = new ImageIcon(new ImageIcon("MovieBooking\\images\\movie2.jpg").getImage()
                .getScaledInstance(posterWidth, posterHeight, Image.SCALE_SMOOTH));
        ImageIcon poster3 = new ImageIcon(new ImageIcon("MovieBooking\\images\\movie3.jpg").getImage()
                .getScaledInstance(posterWidth, posterHeight, Image.SCALE_SMOOTH));
        ImageIcon poster4 = new ImageIcon(new ImageIcon("MovieBooking\\images\\movie4.jpg").getImage()
                .getScaledInstance(posterWidth, posterHeight, Image.SCALE_SMOOTH));

        // Create JLabels with movie posters
        JLabel posterLabel1 = new JLabel(poster1);
        JLabel posterLabel2 = new JLabel(poster2);
        JLabel posterLabel3 = new JLabel(poster3);
        JLabel posterLabel4 = new JLabel(poster4);

        // Add labels to panel
        postersPanel.add(posterLabel1);
        postersPanel.add(posterLabel2);
        postersPanel.add(posterLabel3);
        postersPanel.add(posterLabel4);

        postersPanel.setOpaque(false);

        // Add panel to frame
        fr.add(postersPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 10, 0)); // Adjusted padding
        buttonPanel.setOpaque(false);

        JButton exiUser = new JButton("Existing User");
        JButton newUser = new JButton("New User");
        buttonPanel.add(exiUser);
        buttonPanel.add(newUser);
        fr.add(buttonPanel, BorderLayout.SOUTH);

        exiUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fr.dispose();
                new ExistingUserMovie().main(args);
            }
        });

        newUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fr.dispose();
                new NewUser().main(args);
            }
        });

        fr.setVisible(true);
    }
}
