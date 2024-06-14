package VehicleRentalService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.MouseEvent;

public class UserHomePage {

    // database info
    public static String JDBC_URL = "jdbc:mysql://localhost:3306/";
    public static String user = {YOUR USER NAME};
    public static String password = {YOUR USER PASSWORD};
    public static String DBname = "TAKETAXI";

    public static TakeTaxiHomepage bikeHome = new TakeTaxiHomepage();
    public static ExistingUser eu = new ExistingUser();
    public static NewUserPage nu = new NewUserPage();
    public static BookNew bn = new BookNew();
    public static AdminPage apg = new AdminPage();
    public static ReturnVehicle rveh = new ReturnVehicle();

    public static DefaultTableModel tbl;
    public static JTable jtbl;
    public static JScrollPane sc;

    public static void main(String[] args) {

        JFrame fr = new JFrame("Welcome " + eu.username);

        try {
            Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            PreparedStatement db = con.prepareStatement("Use TAKETAXI");
            db.execute();

            tbl = new DefaultTableModel();
            jtbl = new JTable();
            jtbl.setModel(tbl);

            tbl.addColumn("VEHICLE NAME");
            tbl.addColumn("VEHICLE TYPE");
            tbl.addColumn("COMPANY");
            tbl.addColumn("REGISTRATION NO.");
            sc = new JScrollPane(jtbl);

            // creating new menubar and adding menu options
            JMenuBar menuBar = new JMenuBar();
            JMenu Home = new JMenu("HOME");
            JMenu Options = new JMenu("OPTIONS");
            JMenu About = new JMenu("ABOUT");

            JMenuItem openProfile = new JMenuItem("Open Profile");
            JMenuItem logout = new JMenuItem("Logout");
            JMenuItem rentHist = new JMenuItem("Rental History");
            JMenuItem contact = new JMenuItem("Contact Us");
            JMenuItem about = new JMenuItem("About Us");

            Home.add(openProfile);
            Home.add(logout);
            Options.add(rentHist);
            About.add(contact);
            About.add(about);

            menuBar.add(Home);
            menuBar.add(Options);
            menuBar.add(About);
            fr.setJMenuBar(menuBar);

            // adding background image
            ImageIcon bg = new ImageIcon(
                    ClassLoader.getSystemResource("BikeRentalService\\images\\indian monuments.png"));
            Image i2 = bg.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel img = new JLabel(i3);
            img.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
            img.setLayout(new BorderLayout());
            fr.setContentPane(img);

            // frame properties
            fr.setSize(700, 500);
            fr.setLayout(new BorderLayout());
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            ;
            JPanel textPanel12 = new JPanel();
            JPanel textPanel3 = new JPanel();
            textPanel12.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            textPanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

            JLabel a = new JLabel("Welcome ", SwingConstants.CENTER);
            JLabel b = new JLabel(eu.username, SwingConstants.CENTER);
            JLabel c = new JLabel("How can we plan your next ride?", SwingConstants.CENTER);
            a.setFont(new Font("Times New Roman", Font.BOLD, 28));
            a.setForeground(Color.BLACK);
            b.setFont(new Font("Times New Roman", Font.BOLD, 32));
            b.setForeground(Color.RED);
            c.setFont(new Font("Sans Serif", Font.ITALIC, 22));
            c.setForeground(Color.BLACK);
            textPanel12.add(a);
            textPanel12.add(b);
            textPanel12.setOpaque(false);
            textPanel3.add(c);
            textPanel3.setOpaque(false);
            mainPanel.add(textPanel12);
            mainPanel.add(textPanel3);
            mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            mainPanel.setOpaque(false);
            fr.add(mainPanel, BorderLayout.NORTH);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

            JButton bookNew = new JButton("Book New Vehicle");
            JButton retVeh = new JButton("Return Vehicle");
            buttonPanel.add(bookNew);
            buttonPanel.add(retVeh);
            buttonPanel.setOpaque(false);
            fr.add(buttonPanel, BorderLayout.SOUTH);

            bookNew.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    bn.main(args);
                }
            });

            retVeh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    rveh.main(args);
                }
            });

            openProfile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    JFrame proFrame = new JFrame("USER PROFILE");
                    proFrame.setSize(350, 300);
                    proFrame.setLayout(new BorderLayout());
                    proFrame.setLocationRelativeTo(null);
                    proFrame.setVisible(true);

                    JPanel mPnl = new JPanel();
                    mPnl.setLayout(new BoxLayout(mPnl, BoxLayout.Y_AXIS));
                    ;
                    JPanel txtPnl1 = new JPanel();
                    txtPnl1.setLayout(new BoxLayout(txtPnl1, BoxLayout.Y_AXIS));

                    JLabel nameLbl = new JLabel("Name: " + eu.username, SwingConstants.CENTER);
                    nameLbl.setFont(new Font("SANS SERIF", Font.BOLD, 20));
                    JLabel adhrLbl = new JLabel("Aadhar Number: " + eu.aadharText, SwingConstants.CENTER);
                    adhrLbl.setFont(new Font("SANS SERIF", Font.PLAIN, 20));
                    JLabel licLbl = new JLabel("License: " + eu.licNo, SwingConstants.CENTER);
                    licLbl.setFont(new Font("SANS SERIF", Font.PLAIN, 20));
                    JLabel addLbl = new JLabel("Address: " + eu.addressDet, SwingConstants.CENTER);
                    addLbl.setFont(new Font("SANS SERIF", Font.PLAIN, 20));
                    JLabel phLbl = new JLabel("Phone Number: " + eu.phoneNo, SwingConstants.CENTER);
                    phLbl.setFont(new Font("SANS SERIF", Font.PLAIN, 20));

                    txtPnl1.add(nameLbl);
                    txtPnl1.add(adhrLbl);
                    txtPnl1.add(licLbl);
                    txtPnl1.add(addLbl);
                    txtPnl1.add(phLbl);
                    txtPnl1.setOpaque(false);
                    mPnl.add(txtPnl1);
                    mPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
                    mPnl.setOpaque(false);
                    proFrame.add(mPnl, BorderLayout.NORTH);

                }
            });

            logout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    int res = JOptionPane.showConfirmDialog(null, "Logout from '" + eu.userID + "'?", null, 0);
                    if (res == JOptionPane.YES_OPTION) {
                        fr.dispose();
                        bikeHome.main(args);
                    }
                }
            });

            rentHist.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    JFrame f = new JFrame();
                    f.setSize(500, 350);
                    f.setLayout(new BorderLayout());
                    f.setLocationRelativeTo(null);
                    f.setVisible(true);
                    apg.drawTable("TAKETAXIRENTALHISTORY", eu.userID, 1);
                    f.add(apg.scrl, BorderLayout.CENTER);
                }
            });

            contact.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    JFrame f = new JFrame();
                    f.setSize(300, 150);
                    f.setLayout(new BorderLayout());
                    f.setLocationRelativeTo(null);
                    f.setVisible(true);

                    JPanel abc = new JPanel();
                    abc.setLayout(new GridLayout(2, 1));

                    JLabel phone = new JLabel("Phone number- xxxxx xxxxx", SwingConstants.CENTER);
                    JLabel email = new JLabel("Email- xxxxx@gmail.com", SwingConstants.CENTER);
                    phone.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    email.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    abc.add(phone);
                    abc.add(email);
                    f.add(abc, BorderLayout.CENTER);
                }
            });

            about.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    JFrame profile = new JFrame("About Developer");
                    profile.setSize(400, 350);
                    profile.setLayout(new BorderLayout());
                    profile.setLocationRelativeTo(null);
                    profile.setVisible(true);

                    ImageIcon ic = new ImageIcon("BikeRentalService\\images\\developer.jpg");
                    Image img = ic.getImage().getScaledInstance(150, 190, Image.SCALE_SMOOTH);
                    ImageIcon scaledImg = new ImageIcon(img);
                    JLabel lbl = new JLabel(scaledImg);
                    lbl.setBorder(BorderFactory.createEmptyBorder(50, 0, 120, 20));
                    lbl.setOpaque(false);
                    profile.add(lbl, BorderLayout.EAST);

                    JPanel pnl = new JPanel();
                    pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment
                    pnl.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); // Add padding
                    pnl.setOpaque(false);

                    JLabel db = new JLabel("Developed by", SwingConstants.CENTER);
                    db.setFont(new Font("Times New Roman", Font.BOLD, 28));
                    db.setAlignmentX(Component.CENTER_ALIGNMENT);

                    JLabel name = new JLabel("Gaurav Jha", SwingConstants.CENTER);
                    name.setFont(new Font("Times New Roman", Font.BOLD, 28));
                    name.setForeground(Color.RED);
                    name.setAlignmentX(Component.CENTER_ALIGNMENT);

                    JLabel desc = new JLabel(
                            "<html>This GUI is developed by Gaurav Jha, a BTech Student from DIT University.</html>",
                            SwingConstants.CENTER);
                    desc.setFont(new Font("Sans-Serif", Font.BOLD, 16));
                    desc.setAlignmentX(Component.CENTER_ALIGNMENT);

                    JLabel id = new JLabel(
                            "<html>He has a keen interest in software development and has worked on multiple projects.</html>",
                            SwingConstants.CENTER);
                    id.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
                    id.setAlignmentX(Component.CENTER_ALIGNMENT);

                    pnl.add(db);
                    pnl.add(name);
                    pnl.add(Box.createVerticalStrut(20));
                    pnl.add(desc);
                    pnl.add(Box.createVerticalStrut(20));
                    pnl.add(id);

                    profile.add(pnl, BorderLayout.CENTER);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
