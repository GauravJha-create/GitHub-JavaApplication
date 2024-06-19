package MovieBooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserHomePageMovie {

    public static String JDBC_URL = "jdbc:mysql://localhost:3306/";
    public static String user = "root";
    public static String password = "jhagaurav7500";
    public static String DBname = "SHOWTIX";

    public static void main(String[] args) {
        JFrame fr = new JFrame("Welcome " + new ExistingUserMovie().username);
        fr.setSize(700, 500);
        fr.setLayout(new BorderLayout());
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);

        try {
            Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            PreparedStatement db = con.prepareStatement("Use SHOWTIX");
            db.execute();

            JPanel backgroundPanel = new JPanel() {
                private Image backgroundImage;

                // Load the background image
                {
                    backgroundImage = new ImageIcon("MovieBooking\\images\\moviehall.jpg").getImage();
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
            fr.setContentPane(backgroundPanel);
            fr.setVisible(true);

            String ins1 = "CREATE TABLE IF NOT EXISTS BOOKINGHISTORY (ID INT AUTO_INCREMENT UNIQUE, MOVIE_NAME VARCHAR(20), SEAT_ROW VARCHAR(2), SEAT_COLUMN  VARCHAR(2), CUSTOMER_ID VARCHAR(10), CUSTOMER_NAME VARCHAR(20))";
            PreparedStatement ps1 = con.prepareStatement(ins1);
            ps1.executeUpdate();

            JLabel wel = new JLabel("WELCOME " + new ExistingUserMovie().username, SwingConstants.CENTER);
            wel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
            wel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
            wel.setForeground(Color.WHITE);
            fr.add(wel, BorderLayout.NORTH);

            // creating new menubar and adding menu options
            JMenuBar menuBar = new JMenuBar();
            JMenu Home = new JMenu("HOME");
            JMenu Options = new JMenu("OPTIONS");
            JMenu About = new JMenu("ABOUT");

            JMenuItem logout = new JMenuItem("Logout");
            JMenuItem bookHist = new JMenuItem("Booking History");
            JMenuItem contact = new JMenuItem("Contact Us");
            JMenuItem about = new JMenuItem("About Us");

            Home.add(logout);
            Options.add(bookHist);
            About.add(contact);
            About.add(about);

            menuBar.add(Home);
            menuBar.add(Options);
            menuBar.add(About);
            fr.setJMenuBar(menuBar);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

            JButton bookTix = new JButton("Book Tickets");
            JButton newRel = new JButton("See new releases");
            buttonPanel.add(bookTix);
            buttonPanel.add(newRel);
            buttonPanel.setOpaque(false);
            fr.add(buttonPanel, BorderLayout.SOUTH);

            logout.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    int res = JOptionPane.showConfirmDialog(null,
                            "Logout from '" + new ExistingUserMovie().userID + "'?",
                            null, 0);
                    if (res == JOptionPane.YES_OPTION) {
                        fr.dispose();
                        new ShowTixHomepage().main(args);
                    }
                }
            });

            newRel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    JFrame mov = new JFrame();
                    mov.setSize(500, 600);
                    mov.setLayout(new BorderLayout());
                    mov.setLocationRelativeTo(null);
                    mov.setVisible(true);

                    JPanel moviesMain = new JPanel();
                    moviesMain.setLayout(new BoxLayout(moviesMain, BoxLayout.Y_AXIS));
                    moviesMain.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                    JPanel row1 = new JPanel();
                    row1.setLayout(new BorderLayout());
                    JPanel row2 = new JPanel();
                    row2.setLayout(new BorderLayout());
                    JPanel row3 = new JPanel();
                    row3.setLayout(new BorderLayout());
                    JPanel row4 = new JPanel();
                    row4.setLayout(new BorderLayout());

                    row1.setOpaque(true);
                    row2.setOpaque(true);
                    row3.setOpaque(true);
                    row4.setOpaque(true);

                    row1.setBackground(new Color(0, 0, 0, 130));
                    row2.setBackground(new Color(0, 0, 0, 160));
                    row3.setBackground(new Color(0, 0, 0, 130));
                    row4.setBackground(new Color(0, 0, 0, 160));

                    JPanel txr1 = new JPanel(new BorderLayout());
                    txr1.setOpaque(false);
                    JPanel txr2 = new JPanel(new BorderLayout());
                    txr2.setOpaque(false);
                    JPanel txr3 = new JPanel(new BorderLayout());
                    txr3.setOpaque(false);
                    JPanel txr4 = new JPanel(new BorderLayout());
                    txr4.setOpaque(false);

                    JLabel tx1 = new JLabel("GODFATHER", SwingConstants.CENTER);
                    JLabel desc1 = new JLabel("<html>DESC OF THE MOVIE</html>", SwingConstants.CENTER);
                    JLabel tx2 = new JLabel("BADLA", SwingConstants.CENTER);
                    JLabel desc2 = new JLabel("<html>DESC OF THE MOVIE</html>", SwingConstants.CENTER);
                    JLabel tx3 = new JLabel("AJNABEE", SwingConstants.CENTER);
                    JLabel desc3 = new JLabel("<html>DESC OF THE MOVIE</html>", SwingConstants.CENTER);
                    JLabel tx4 = new JLabel("3 IDIOTS", SwingConstants.CENTER);
                    JLabel desc4 = new JLabel("<html>DESC OF THE MOVIE</html>", SwingConstants.CENTER);

                    txr1.add(tx1, BorderLayout.NORTH);
                    txr1.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
                    txr1.add(desc1, BorderLayout.CENTER);
                    txr2.add(tx2, BorderLayout.NORTH);
                    txr2.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
                    txr2.add(desc2, BorderLayout.CENTER);
                    txr3.add(tx3, BorderLayout.NORTH);
                    txr3.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
                    txr3.add(desc3, BorderLayout.CENTER);
                    txr4.add(tx4, BorderLayout.NORTH);
                    txr4.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
                    txr4.add(desc4, BorderLayout.CENTER);

                    int posterWidth = 75; // Desired width
                    int posterHeight = 100; // Desired height

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
                    posterLabel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                    JLabel posterLabel2 = new JLabel(poster2);
                    posterLabel2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                    JLabel posterLabel3 = new JLabel(poster3);
                    posterLabel3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                    JLabel posterLabel4 = new JLabel(poster4);
                    posterLabel4.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

                    row1.add(posterLabel1, BorderLayout.WEST);
                    row1.add(txr1, BorderLayout.CENTER);
                    row2.add(posterLabel2, BorderLayout.WEST);
                    row2.add(txr2, BorderLayout.CENTER);
                    row3.add(posterLabel3, BorderLayout.WEST);
                    row3.add(txr3, BorderLayout.CENTER);
                    row4.add(posterLabel4, BorderLayout.WEST);
                    row4.add(txr4, BorderLayout.CENTER);

                    moviesMain.add(row1);
                    moviesMain.add(row2);
                    moviesMain.add(row3);
                    moviesMain.add(row4);

                    mov.add(moviesMain, BorderLayout.CENTER);
                }
            });

            bookTix.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    JFrame f = new JFrame();
                    f.setSize(550, 600);
                    f.setLayout(new BorderLayout()); // Changed to BorderLayout for better control
                    f.setLocationRelativeTo(null);
                    f.setVisible(true);

                    // Top label
                    JLabel txt = new JLabel("CHOOSE YOUR SEAT", SwingConstants.CENTER);
                    txt.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
                    f.add(txt, BorderLayout.NORTH);

                    // Panel for movie selection
                    JPanel movieSelectionPanel = new JPanel();
                    movieSelectionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

                    JLabel movieLabel = new JLabel("Select Movie:");
                    String[] movies = { "GODFATHER", "BADLA", "AJNABEE", "3 IDIOTS" };
                    JComboBox<String> movieBox = new JComboBox<>(movies);
                    movieBox.setPreferredSize(new Dimension(200, 30));

                    movieSelectionPanel.add(movieLabel);
                    movieSelectionPanel.add(movieBox);

                    f.add(movieSelectionPanel, BorderLayout.CENTER);

                    // Main seat panel
                    JPanel seatPanel = new JPanel();
                    seatPanel.setLayout(new BoxLayout(seatPanel, BoxLayout.Y_AXIS));
                    seatPanel.setOpaque(false);

                    ImageIcon seatimg = new ImageIcon(new ImageIcon("MovieBooking\\images\\seats.png").getImage()
                            .getScaledInstance(310, 310, Image.SCALE_SMOOTH)); // Reduced size of the image

                    JLabel seats = new JLabel(seatimg);
                    seats.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
                    seatPanel.add(seats);

                    // Options panel for row and column selection
                    JPanel options = new JPanel();
                    options.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

                    String[] rows = { "1", "2", "3", "4", "5" };
                    String[] columns = { "A", "B", "C", "D", "E", "F", "G", "H" };

                    JLabel rowLabel = new JLabel("Row:");
                    JComboBox<String> rowBox = new JComboBox<>(rows);
                    rowBox.setPreferredSize(new Dimension(100, 30));

                    JLabel colLabel = new JLabel("Column:");
                    JComboBox<String> colBox = new JComboBox<>(columns);
                    colBox.setPreferredSize(new Dimension(100, 30));

                    options.add(rowLabel);
                    options.add(rowBox);
                    options.add(colLabel);
                    options.add(colBox);

                    seatPanel.add(options);

                    // Confirm button
                    JButton confirmButton = new JButton("Confirm Selection");
                    confirmButton.setPreferredSize(new Dimension(200, 30));
                    confirmButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {

                                String ex = "CREATE TABLE IF NOT EXISTS BOOKEDSEATS (ID INT AUTO_INCREMENT UNIQUE, MOVIE_NAME varchar(20), SEAT_ROW varchar(2), SEAT_COLUMN varchar(2))";
                                db.executeUpdate(ex);

                                String selectedMovie = (String) movieBox.getSelectedItem();
                                String selectedRow = (String) rowBox.getSelectedItem();
                                String selectedColumn = (String) colBox.getSelectedItem();

                                if (isSeatAvailable(selectedMovie, selectedRow, selectedColumn) == 1) {
                                    String ins2 = "INSERT INTO BOOKINGHISTORY(MOVIE_NAME, SEAT_ROW, SEAT_COLUMN, CUSTOMER_ID,CUSTOMER_NAME) VALUES(?, ?, ?, ?, ?)";
                                    PreparedStatement ps2 = con.prepareStatement(ins2);
                                    ps2.setString(1, selectedMovie);
                                    ps2.setString(2, selectedRow);
                                    ps2.setString(3, selectedColumn);
                                    ps2.setString(4, new ExistingUserMovie().userID);
                                    ps2.setString(5, new ExistingUserMovie().username);
                                    ps2.executeUpdate();
                                    JOptionPane.showMessageDialog(f,
                                            "You have selected:\nMovie: " + selectedMovie + "\nRow: "
                                                    + selectedRow + "\nColumn: " + selectedColumn);
                                }

                                else {
                                    JOptionPane.showMessageDialog(f,
                                            "PLEASE SELECT ANOTHER SEAT. THIS IS ALREADY OCCUPIED");
                                }

                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });

                    JPanel confirmPanel = new JPanel();
                    confirmPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
                    confirmPanel.add(confirmButton);

                    seatPanel.add(confirmPanel);

                    f.add(seatPanel, BorderLayout.SOUTH);
                }
            });

            bookHist.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    JFrame f = new JFrame();
                    f.setSize(500, 350);
                    f.setLayout(new BorderLayout());
                    f.setLocationRelativeTo(null);
                    f.setVisible(true);

                    DefaultTableModel mod = new DefaultTableModel();
                    JTable jt = new JTable();
                    jt.setModel(mod);

                    mod.addColumn("MOVIE NAME");
                    mod.addColumn("SEAT");
                    try {
                        PreparedStatement ps3 = con
                                .prepareStatement("SELECT * FROM BOOKINGHISTORY WHERE CUSTOMER_ID LIKE '"
                                        + new ExistingUserMovie().userID + "'");
                        ResultSet rs = ps3.executeQuery();
                        while (rs.next()) {
                            Object[] rowData = {
                                    rs.getString("MOVIE_NAME"),
                                    rs.getString("SEAT_ROW") +
                                            rs.getString("SEAT_COLUMN")
                            };
                            mod.addRow(rowData);
                        }
                        JScrollPane scrl = new JScrollPane(jt);
                        f.add(scrl);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
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
                            "<html>SHOWTIX allows you to book tickets for movie releases at an affordable price.</html>",
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

    static int isSeatAvailable(String moviename, String selrow, String selcol) {
        int no = 1;
        Connection con = null;
        PreparedStatement db = null;
        PreparedStatement ex = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(JDBC_URL, user, password);
            db = con.prepareStatement("USE SHOWTIX");
            db.execute();

            ex = con.prepareStatement(
                    "SELECT * FROM BOOKEDSEATS WHERE MOVIE_NAME = ? AND SEAT_ROW = ? AND SEAT_COLUMN = ?");
            ex.setString(1, moviename);
            ex.setString(2, selrow);
            ex.setString(3, selcol);
            rs = ex.executeQuery();

            if (rs.next()) {
                no = 0; // Seat is already booked
            } else {
                // Book the seat
                String insertQuery = "INSERT INTO BOOKEDSEATS (MOVIE_NAME, SEAT_ROW, SEAT_COLUMN) VALUES (?, ?, ?)";
                ps = con.prepareStatement(insertQuery);
                ps.setString(1, moviename);
                ps.setString(2, selrow);
                ps.setString(3, selcol);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return no;
    }
}