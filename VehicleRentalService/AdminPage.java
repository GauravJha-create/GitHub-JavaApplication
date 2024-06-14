package VehicleRentalService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;

public class AdminPage {

    // database info
    public static String JDBC_URL = "jdbc:mysql://localhost:3306/";
    public static String user = {YOUR USER NAME};
    public static String password = {YOUR USER PASSWORD};

    public static DefaultTableModel mod;
    public static JTable jt;
    public static JFrame f;

    public static JScrollPane scrl;

    public static TakeTaxiHomepage pHome = new TakeTaxiHomepage();

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            PreparedStatement db = con.prepareStatement("Use TAKETAXI");
            db.execute();
            Statement st = con.createStatement();

            String createTable1 = "CREATE TABLE IF NOT EXISTS TAKETAXIRENTALHISTORY (ID INT AUTO_INCREMENT UNIQUE, VEHICLE_NAME varchar(20), VEHICLE_TYPE varchar(10), COMPANY varchar(50), REG_NUMBER VARCHAR(10), CUSTOMERID VARCHAR(10), CUSTOMERNAME VARCHAR(30))";
            String createTable2 = "CREATE TABLE IF NOT EXISTS TAKETAXIAVAILABLEVEHICLES (ID INT AUTO_INCREMENT UNIQUE, VEHICLE_NAME varchar(20), VEHICLE_TYPE varchar(10), COMPANY varchar(50), REG_NUMBER VARCHAR(10) PRIMARY KEY)";
            String createTable3 = "CREATE TABLE IF NOT EXISTS TAKETAXIRENTEDVEHICLES (ID INT AUTO_INCREMENT UNIQUE, VEHICLE_NAME varchar(20), VEHICLE_TYPE varchar(10), COMPANY varchar(50), REG_NUMBER VARCHAR(10) PRIMARY KEY, CUSTOMERID VARCHAR(10), CUSTOMERNAME VARCHAR(30))";
            st.executeUpdate(createTable1);
            st.executeUpdate(createTable2);
            st.executeUpdate(createTable3);

            {
                JFrame fr = new JFrame("TakeTaxi Control Room");

                // creating new menubar and adding menu options
                JMenuBar menuBar = new JMenuBar();
                JMenu Profile = new JMenu("PROFILE");

                JMenuItem openProfile = new JMenuItem("Open Profile");
                JMenuItem logout = new JMenuItem("Logout");

                Profile.add(openProfile);
                Profile.add(logout);

                menuBar.add(Profile);
                fr.setJMenuBar(menuBar);

                // Create a custom JPanel for the background
                JPanel backgroundPanel = new JPanel() {
                    private Image backgroundImage;

                    // Load the background image
                    {
                        backgroundImage = new ImageIcon("BikeRentalService\\images\\control.jpg").getImage();
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

                // frame properties
                fr.setSize(800, 500);
                fr.setLocationRelativeTo(null);
                fr.setVisible(true);
                fr.setLayout(new BorderLayout());
                fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // defining texts on the frame
                JLabel open = new JLabel("See your owned vehicle data.", SwingConstants.CENTER);
                open.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
                open.setFont(new Font("Arial", Font.PLAIN, 25));
                open.setForeground(Color.WHITE);

                JPanel btnPanel = new JPanel();
                btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
                btnPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
                JButton allVeh = new JButton("All Vehicles");
                JButton rentVeh = new JButton("Rented");
                JButton mngVeh = new JButton("Manage Vehicle");
                btnPanel.add(allVeh);
                btnPanel.add(rentVeh);
                btnPanel.add(mngVeh);
                btnPanel.setOpaque(false);

                fr.add(open, BorderLayout.NORTH);
                fr.add(btnPanel, BorderLayout.CENTER);

                String createTable = "CREATE TABLE IF NOT EXISTS TAKETAXIVEHICLES (ID INT AUTO_INCREMENT UNIQUE, VEHICLE_NAME varchar(20), VEHICLE_TYPE varchar(10), COMPANY varchar(50), REG_NUMBER VARCHAR(10) PRIMARY KEY)";
                st.executeUpdate(createTable);

                // button functions

                allVeh.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        JFrame viewFrame = new JFrame("Vehicle Information");
                        viewFrame.setSize(500, 350);
                        viewFrame.setLocationRelativeTo(null);
                        viewFrame.setLayout(new BorderLayout());

                        drawTable("TAKETAXIVEHICLES");
                        viewFrame.add(scrl, BorderLayout.CENTER);

                        viewFrame.setVisible(true);
                    }
                });

                openProfile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        JFrame profile = new JFrame();
                        profile.setSize(400, 300);
                        profile.setLayout(new BorderLayout());
                        profile.setLocationRelativeTo(null);

                        ImageIcon ic = new ImageIcon("BikeRentalService\\images\\user.png");
                        Image img = ic.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                        ImageIcon scaledImg = new ImageIcon(img);
                        JLabel lbl = new JLabel(scaledImg);
                        lbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 120, 20));
                        lbl.setOpaque(false);
                        profile.add(lbl, BorderLayout.EAST);

                        JPanel pnl = new JPanel();
                        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));

                        JLabel name = new JLabel("Name: Gaurav Jha", SwingConstants.CENTER);
                        name.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                        name.setAlignmentX(Component.LEFT_ALIGNMENT);
                        JLabel licNo = new JLabel("License Number: XXXXXX", SwingConstants.CENTER);
                        licNo.setFont(new Font("Sans-Serif", Font.BOLD, 18));
                        licNo.setAlignmentX(Component.LEFT_ALIGNMENT);
                        JLabel id = new JLabel("ID Proof: Aadhar number XXXX-XXXX-XXXX-XXXX", SwingConstants.CENTER);
                        id.setAlignmentX(Component.LEFT_ALIGNMENT);
                        pnl.add(name);
                        pnl.add(licNo);
                        pnl.add(id);
                        pnl.setBorder(BorderFactory.createEmptyBorder(80, 10, 20, 0));
                        pnl.setOpaque(false);
                        profile.add(pnl);
                        profile.setVisible(true);
                    }
                });

                logout.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        int res = JOptionPane.showConfirmDialog(null, "Logout from Admin?", null, 0);
                        if (res == JOptionPane.YES_OPTION) {
                            fr.dispose();
                            pHome.main(args);
                        }
                    }
                });

                mngVeh.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        f = new JFrame();
                        f.setSize(600, 350);
                        f.setVisible(true);
                        f.setLocationRelativeTo(null);
                        f.setLayout(new BorderLayout());

                        JPanel btPnl = new JPanel();
                        btPnl.setLayout(new BoxLayout(btPnl, BoxLayout.Y_AXIS));
                        btPnl.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 0));
                        JButton addVeh = new JButton("ADD");
                        JButton delVeh = new JButton("DELETE");
                        JButton editVeh = new JButton("EDIT");
                        btPnl.add(addVeh);
                        btPnl.add(Box.createVerticalStrut(20));
                        btPnl.add(delVeh);
                        btPnl.add(Box.createVerticalStrut(20));
                        btPnl.add(editVeh);
                        btPnl.add(Box.createVerticalStrut(20));
                        btPnl.setOpaque(false);
                        f.add(btPnl, BorderLayout.WEST);

                        drawTable("TAKETAXIVEHICLES");
                        f.add(scrl, BorderLayout.EAST);

                        addVeh.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent ae) {
                                JFrame det = new JFrame("Add Vehicle");
                                det.setSize(350, 400);
                                det.setLocationRelativeTo(null);
                                det.setLayout(new BorderLayout());

                                String[] items = { "Bike", "Scooter", "Car" };

                                JComboBox<String> comboBox = new JComboBox<>(items);
                                comboBox.setSize(110, 40);
                                comboBox.setMaximumSize(new Dimension(70, 30));
                                comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);

                                JPanel addPnl = new JPanel();
                                addPnl.setLayout(new BoxLayout(addPnl, BoxLayout.Y_AXIS));
                                addPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                                addPnl.setOpaque(false);

                                JLabel vehName = new JLabel("Enter Vehicle Name", SwingConstants.CENTER);
                                JTextField txtvehName = new JTextField();
                                txtvehName.setAlignmentX(Component.LEFT_ALIGNMENT);
                                txtvehName.setMaximumSize(new Dimension(300, 30));

                                JLabel vehComp = new JLabel("Enter Manufacturer", SwingConstants.CENTER);
                                JTextField txtvehComp = new JTextField();
                                txtvehComp.setAlignmentX(Component.LEFT_ALIGNMENT);
                                txtvehComp.setMaximumSize(new Dimension(300, 30));

                                JLabel vehReg = new JLabel("Enter Registration Number", SwingConstants.CENTER);
                                JTextField txtvehReg = new JTextField();
                                txtvehReg.setAlignmentX(Component.LEFT_ALIGNMENT);
                                txtvehReg.setMaximumSize(new Dimension(300, 30));

                                JButton addvehButton = new JButton("CONFIRM");
                                JPanel buttonPanel = new JPanel();
                                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                                buttonPanel.setOpaque(false);
                                buttonPanel.add(addvehButton);
                                buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 0, 0));

                                addPnl.add(Box.createVerticalStrut(20));
                                addPnl.add(comboBox);
                                addPnl.add(Box.createVerticalStrut(10));
                                addPnl.add(vehName);
                                addPnl.add(txtvehName);
                                addPnl.add(Box.createVerticalStrut(10));
                                addPnl.add(vehComp);
                                addPnl.add(txtvehComp);
                                addPnl.add(Box.createVerticalStrut(10));
                                addPnl.add(vehReg);
                                addPnl.add(txtvehReg);
                                addPnl.add(Box.createVerticalStrut(10));

                                addPnl.add(buttonPanel);

                                det.add(addPnl, BorderLayout.CENTER);
                                det.setVisible(true);

                                addvehButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            String comboString = (String) comboBox.getSelectedItem();
                                            String vehString = txtvehName.getText();
                                            String compString = txtvehComp.getText();
                                            String regString = txtvehReg.getText();

                                            String ex = "INSERT INTO TAKETAXIVEHICLES(VEHICLE_NAME,VEHICLE_TYPE,COMPANY,REG_NUMBER) VALUES (?, ?, ?, ?)";
                                            PreparedStatement ps = con.prepareStatement(ex);
                                            ps.setString(1, vehString);
                                            ps.setString(2, comboString);
                                            ps.setString(3, compString);
                                            ps.setString(4, regString);

                                            String exe = "INSERT INTO TAKETAXIAVAILABLEVEHICLES(VEHICLE_NAME,VEHICLE_TYPE,COMPANY,REG_NUMBER) VALUES (?, ?, ?, ?)";
                                            PreparedStatement pstat = con.prepareStatement(exe);
                                            pstat.setString(1, vehString);
                                            pstat.setString(2, comboString);
                                            pstat.setString(3, compString);
                                            pstat.setString(4, regString);

                                            ps.executeUpdate();
                                            pstat.executeUpdate();
                                            JOptionPane.showMessageDialog(det, "Vehicle Added Successfully");
                                            det.dispose();
                                            mod.setRowCount(0);
                                            jt.revalidate();
                                            jt.repaint();
                                            refreshData();

                                        } catch (SQLException ex) {
                                            ex.printStackTrace();
                                            JOptionPane.showMessageDialog(det,
                                                    "An error occurred while adding data to the database.");
                                        }
                                    }
                                });
                            }
                        });

                        delVeh.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame del = new JFrame();
                                del.setSize(350, 400);
                                del.setLocationRelativeTo(null);
                                del.setLayout(new BorderLayout());

                                JPanel delPnl = new JPanel();
                                delPnl.setLayout(new BoxLayout(delPnl, BoxLayout.Y_AXIS));
                                delPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                                delPnl.setOpaque(false);

                                JLabel vehName = new JLabel("ENTER THE VEHICLE NAME YOU WANT TO DELETE",
                                        SwingConstants.CENTER);
                                JTextField txtvehName = new JTextField();
                                txtvehName.setAlignmentX(Component.LEFT_ALIGNMENT);
                                txtvehName.setMaximumSize(new Dimension(300, 30));

                                JLabel vehReg = new JLabel("ENTER THE REGISTRATION NUMBER", SwingConstants.CENTER);
                                JTextField txtvehReg = new JTextField();
                                txtvehReg.setAlignmentX(Component.LEFT_ALIGNMENT);
                                txtvehReg.setMaximumSize(new Dimension(300, 30));

                                JButton delvehButton = new JButton("CONFIRM");
                                JPanel buttonPanel = new JPanel();
                                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                                buttonPanel.setOpaque(false);
                                buttonPanel.add(delvehButton);
                                buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 0, 0));

                                delPnl.add(Box.createVerticalStrut(20));
                                delPnl.add(vehName);
                                delPnl.add(txtvehName);
                                delPnl.add(Box.createVerticalStrut(20));
                                delPnl.add(vehReg);
                                delPnl.add(txtvehReg);
                                delPnl.add(Box.createVerticalStrut(20));

                                delPnl.add(buttonPanel);
                                del.add(delPnl, BorderLayout.CENTER);
                                del.setVisible(true);

                                delvehButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            String vehString = txtvehName.getText();
                                            String regString = txtvehReg.getText();

                                            PreparedStatement preps = con.prepareStatement(
                                                    "Select COUNT(*) AS ROWCOUNT from TAKETAXIVEHICLES WHERE REG_NUMBER='"
                                                            + regString + "';");
                                            ResultSet rs = preps.executeQuery();
                                            int fl = 0;
                                            while (rs.next()) {
                                                int cnt = rs.getInt("ROWCOUNT");
                                                if (cnt == 0) {
                                                    fl = 1;
                                                }
                                            }

                                            if (fl == 0) {
                                                String ex = "DELETE FROM TAKETAXIVEHICLES WHERE VEHICLE_NAME LIKE'"
                                                        + vehString + "' AND REG_NUMBER LIKE'" + regString + "';";
                                                PreparedStatement ps = con.prepareStatement(ex);

                                                String exe = "DELETE FROM TAKETAXIAVAILABLEVEHICLES WHERE VEHICLE_NAME LIKE'"
                                                        + vehString + "' AND REG_NUMBER LIKE'" + regString + "';";
                                                PreparedStatement pstat = con.prepareStatement(exe);
                                                pstat.executeUpdate();

                                                String exec = "DELETE FROM TAKETAXIRENTEDVEHICLES WHERE VEHICLE_NAME LIKE'"
                                                        + vehString + "' AND REG_NUMBER LIKE'" + regString + "';";
                                                PreparedStatement pstate = con.prepareStatement(exec);
                                                pstate.executeUpdate();

                                                ps.executeUpdate();
                                                JOptionPane.showMessageDialog(del, "Vehicle Removed Successfully");

                                                mod.setRowCount(0);
                                                jt.revalidate();
                                                jt.repaint();
                                                refreshData();
                                                del.dispose();
                                            } else {
                                                JOptionPane.showMessageDialog(fr, "NO SUCH RECORD EXISTS");
                                                del.dispose();
                                            }
                                        } catch (SQLException ex) {
                                            ex.printStackTrace();
                                            JOptionPane.showMessageDialog(del,
                                                    "An error occurred while adding data to the database.");
                                        }
                                    }
                                });
                            }
                        });

                        editVeh.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame edit = new JFrame();
                                edit.setSize(350, 400);
                                edit.setLocationRelativeTo(null);
                                edit.setLayout(new BorderLayout());

                                JPanel editPnl = new JPanel();
                                editPnl.setLayout(new BoxLayout(editPnl, BoxLayout.Y_AXIS));
                                editPnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                                editPnl.setOpaque(false);

                                JLabel vehReg = new JLabel("ENTER THE REGISTRATION NUMBER YOU WANT TO EDIT",
                                        SwingConstants.CENTER);
                                JTextField txtvehReg = new JTextField();
                                txtvehReg.setAlignmentX(Component.LEFT_ALIGNMENT);
                                txtvehReg.setMaximumSize(new Dimension(300, 30));

                                String[] items1 = { "Bike", "Scooter", "Car" };
                                JComboBox<String> comboBox1 = new JComboBox<>(items1);
                                comboBox1.setSize(110, 40);
                                comboBox1.setMaximumSize(new Dimension(70, 30));
                                comboBox1.setAlignmentX(Component.LEFT_ALIGNMENT);

                                String[] items2 = { "VEHICLE NAME", "VEHICLE TYPE", "MANUFACTURER",
                                        "REGISTRATION NUMBER" };
                                JComboBox<String> comboBox2 = new JComboBox<>(items2);
                                comboBox2.setSize(200, 40);
                                comboBox2.setMaximumSize(new Dimension(200, 30));
                                comboBox2.setAlignmentX(Component.LEFT_ALIGNMENT);

                                JButton editvehButton = new JButton("CONFIRM");
                                JPanel buttonPanel = new JPanel();
                                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                                buttonPanel.setOpaque(false);
                                buttonPanel.add(editvehButton);
                                buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 0, 0));

                                editPnl.add(Box.createVerticalStrut(20));
                                editPnl.add(comboBox1);
                                editPnl.add(Box.createVerticalStrut(20));
                                editPnl.add(vehReg);
                                editPnl.add(txtvehReg);
                                editPnl.add(Box.createVerticalStrut(20));
                                editPnl.add(comboBox2);
                                editPnl.add(Box.createVerticalStrut(20));

                                editPnl.add(buttonPanel);
                                edit.add(editPnl, BorderLayout.CENTER);
                                edit.setVisible(true);

                                editvehButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            String type = (String) comboBox1.getSelectedItem();
                                            String edits = (String) comboBox2.getSelectedItem();
                                            String regString = txtvehReg.getText();

                                            PreparedStatement preps = con.prepareStatement(
                                                    "Select COUNT(*) AS ROWCOUNT from TAKETAXIVEHICLES WHERE VEHICLE_TYPE='"
                                                            + type + "' AND REG_NUMBER='" + regString + "';");
                                            ResultSet rs = preps.executeQuery();
                                            int fl = 0;
                                            while (rs.next()) {
                                                int cnt = rs.getInt("ROWCOUNT");
                                                if (cnt == 0) {
                                                    fl = 1;
                                                }
                                            }

                                            if (fl == 0) {
                                                if (edits == "VEHICLE NAME") {
                                                    edits = "VEHICLE_NAME";
                                                } else if (edits == "VEHICLE TYPE") {
                                                    edits = "VEHICLE_TYPE";
                                                } else if (edits == "MANUFACTURER") {
                                                    edits = "COMPANY";
                                                } else
                                                    edits = "REG_NUMBER";

                                                String input = JOptionPane.showInputDialog(edit, "Enter new " + edits);

                                                String ex = "UPDATE TAKETAXIVEHICLES  SET " + edits + "='" + input
                                                        + "' WHERE REG_NUMBER LIKE'" + regString
                                                        + "' AND VEHICLE_TYPE LIKE'" + type + "';";
                                                PreparedStatement ps = con.prepareStatement(ex);

                                                try {
                                                    String exe = "UPDATE TAKETAXIAVAILABLEVEHICLES  SET " + edits + "='"
                                                            + input + "' WHERE REG_NUMBER LIKE'" + regString
                                                            + "' AND VEHICLE_TYPE LIKE'" + type + "';";
                                                    PreparedStatement pstat = con.prepareStatement(exe);
                                                    pstat.executeUpdate();
                                                    ps.executeUpdate();
                                                    JOptionPane.showMessageDialog(edit, "EDITS MADE SUCCESSFULLY");
                                                    edit.dispose();
                                                    mod.setRowCount(0);
                                                    jt.revalidate();
                                                    jt.repaint();
                                                    refreshData();
                                                } catch (SQLException except) {
                                                    JOptionPane.showMessageDialog(edit,
                                                            "THIS VEHICLE IS NOT AVAILABLE CURRENTLY. PLEASE RETURN THE VEHICLE FIRST");
                                                }

                                            } else {
                                                JOptionPane.showMessageDialog(fr, "NO SUCH RECORD EXISTS");
                                                edit.dispose();
                                            }
                                        } catch (SQLException ex) {
                                            ex.printStackTrace();
                                            JOptionPane.showMessageDialog(edit,
                                                    "An error occurred while adding data to the database.");
                                        }
                                    }
                                });
                            }
                        });
                    }
                });

                rentVeh.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent a) {
                        JFrame viewFrame = new JFrame("Vehicle Information");
                        viewFrame.setSize(650, 350);
                        viewFrame.setLocationRelativeTo(null);
                        viewFrame.setLayout(new BorderLayout());

                        drawTable("TAKETAXIRENTEDVEHICLES", 1);
                        viewFrame.add(scrl, BorderLayout.CENTER);

                        viewFrame.setVisible(true);
                    }
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void refreshData() {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, user, password);
            Statement statement = connection.createStatement();
            PreparedStatement p = connection.prepareStatement("USE TAKETAXI");
            p.execute();
            String query = "SELECT * FROM TAKETAXIVEHICLES";
            ResultSet resultSet = statement.executeQuery(query);

            PreparedStatement info = connection.prepareStatement("Select*from TAKETAXIVEHICLES;");
            ResultSet rs = info.executeQuery();

            while (rs.next()) {
                Object[] rowData = {
                        rs.getString("VEHICLE_NAME"),
                        rs.getString("VEHICLE_TYPE"),
                        rs.getString("COMPANY"),
                        rs.getString("REG_NUMBER")
                };
                mod.addRow(rowData);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(f, "Error retrieving data from the database.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void drawTable(String tablename) {
        mod = new DefaultTableModel();
        jt = new JTable();
        jt.setModel(mod);

        mod.addColumn("VEHICLE NAME");
        mod.addColumn("VEHICLE TYPE");
        mod.addColumn("COMPANY");
        mod.addColumn("REGISTRATION NUMBER");

        try {
            Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            PreparedStatement info = con.prepareStatement("Select * FROM " + tablename);
            PreparedStatement db = con.prepareStatement("Use TAKETAXI");
            db.execute();
            ResultSet rs = info.executeQuery();

            while (rs.next()) {
                Object[] rowData = {
                        rs.getString("VEHICLE_NAME"),
                        rs.getString("VEHICLE_TYPE"),
                        rs.getString("COMPANY"),
                        rs.getString("REG_NUMBER"),
                };
                mod.addRow(rowData);
            }
            scrl = new JScrollPane(jt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void drawTable(String tablename, int i) {
        mod = new DefaultTableModel();
        jt = new JTable();
        jt.setModel(mod);

        mod.addColumn("VEHICLE NAME");
        mod.addColumn("VEHICLE TYPE");
        mod.addColumn("COMPANY");
        mod.addColumn("REGISTRATION NUMBER");
        mod.addColumn("CUSTOMER ID");
        mod.addColumn("CUSTOMER NAME");

        try {
            Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            PreparedStatement info = con.prepareStatement("Select * FROM " + tablename);
            PreparedStatement db = con.prepareStatement("Use TAKETAXI");
            db.execute();
            ResultSet rs = info.executeQuery();

            while (rs.next()) {
                Object[] rowData = {
                        rs.getString("VEHICLE_NAME"),
                        rs.getString("VEHICLE_TYPE"),
                        rs.getString("COMPANY"),
                        rs.getString("REG_NUMBER"),
                        rs.getString("CUSTOMERID"),
                        rs.getString("CUSTOMERNAME")
                };
                mod.addRow(rowData);
            }
            scrl = new JScrollPane(jt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void drawTable(String tablename, String vehicleType) {
        mod = new DefaultTableModel();
        jt = new JTable();
        jt.setModel(mod);

        mod.addColumn("VEHICLE NAME");
        mod.addColumn("VEHICLE TYPE");
        mod.addColumn("COMPANY");
        mod.addColumn("REGISTRATION NUMBER");

        try {
            Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            PreparedStatement info = con
                    .prepareStatement("Select * FROM " + tablename + " WHERE VEHICLE_TYPE LIKE '" + vehicleType + "'");
            PreparedStatement db = con.prepareStatement("Use TAKETAXI");
            db.execute();
            ResultSet rs = info.executeQuery();

            while (rs.next()) {
                Object[] rowData = {
                        rs.getString("VEHICLE_NAME"),
                        rs.getString("VEHICLE_TYPE"),
                        rs.getString("COMPANY"),
                        rs.getString("REG_NUMBER")
                };
                mod.addRow(rowData);
            }
            scrl = new JScrollPane(jt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void drawTable(String tablename, String userIDString, int i) {
        if (i == 1) {

            mod = new DefaultTableModel();
            jt = new JTable();
            jt.setModel(mod);

            mod.addColumn("VEHICLE NAME");
            mod.addColumn("VEHICLE TYPE");
            mod.addColumn("COMPANY");
            mod.addColumn("REGISTRATION NUMBER");

            try {
                Connection con = DriverManager.getConnection(JDBC_URL, user, password);
                PreparedStatement db = con.prepareStatement("Use TAKETAXI");
                PreparedStatement info = con.prepareStatement(
                        "Select * FROM " + tablename + " WHERE CUSTOMERID LIKE '" + userIDString + "'");
                db.execute();
                ResultSet rs = info.executeQuery();

                while (rs.next()) {
                    Object[] rowData = {
                            rs.getString("VEHICLE_NAME"),
                            rs.getString("VEHICLE_TYPE"),
                            rs.getString("COMPANY"),
                            rs.getString("REG_NUMBER"),
                    };
                    mod.addRow(rowData);
                }
                scrl = new JScrollPane(jt);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}