package VehicleRentalService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class BookNew {

    public static String JDBC_URL = "jdbc:mysql://localhost:3306/";
    public static String user = {YOUR USER NAME};
    public static String password = {YOUR USER PASSWORD};

    public static AdminPage apg = new AdminPage();
    public static ExistingUser eu = new ExistingUser();

    private static int row = 0, col = 0;
    private static JFrame fr;

    public static UserHomePage uhp = new UserHomePage();

    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            PreparedStatement db = con.prepareStatement("Use TAKETAXI");
            db.execute();
            Statement st = con.createStatement();
            {
                String createTable = "CREATE TABLE IF NOT EXISTS TAKETAXIRENTEDVEHICLES (ID INT AUTO_INCREMENT UNIQUE, VEHICLE_NAME varchar(20), VEHICLE_TYPE varchar(10), COMPANY varchar(50), REG_NUMBER VARCHAR(10) PRIMARY KEY, CUSTOMERID varchar(10), CUSTOMERNAME varchar(30))";
                st.executeUpdate(createTable);

                fr = new JFrame("BOOK NEW VEHICLE");
                fr.setSize(600, 450);
                fr.setLayout(new BorderLayout());
                fr.setLocationRelativeTo(null);
                fr.setVisible(true);

                JLabel wel = new JLabel("SELECT THE VEHICLE YOU WANT TO RENT", SwingConstants.CENTER);
                wel.setFont(new Font("SANS SERIF", Font.BOLD, 18));
                wel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
                ImageIcon sctr = new ImageIcon("BikeRentalService\\scooter.png");
                ImageIcon car = new ImageIcon("BikeRentalService\\car.png");
                ImageIcon bike = new ImageIcon("BikeRentalService\\bike.png");
                Image imgsctr = sctr.getImage();
                Image imgcar = car.getImage();
                Image imgbike = bike.getImage();
                Image resizedsctr = imgsctr.getScaledInstance(120, 80, Image.SCALE_SMOOTH);
                Image resizedcar = imgcar.getScaledInstance(120, 70, Image.SCALE_SMOOTH);
                Image resizedbike = imgbike.getScaledInstance(160, 100, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon1 = new ImageIcon(resizedsctr);
                ImageIcon resizedIcon2 = new ImageIcon(resizedcar);
                ImageIcon resizedIcon3 = new ImageIcon(resizedbike);

                JButton btnSctr = new JButton(resizedIcon1);
                btnSctr.setPreferredSize(new Dimension(130, 110));
                JButton btnCar = new JButton(resizedIcon2);
                btnCar.setPreferredSize(new Dimension(140, 110));
                JButton btnBike = new JButton(resizedIcon3);
                btnBike.setPreferredSize(new Dimension(150, 110));

                panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
                panel.setOpaque(false);

                panel.add(btnSctr);
                panel.add(btnCar);
                panel.add(btnBike);
                fr.add(panel);
                fr.add(wel, BorderLayout.NORTH);

                btnSctr.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        JFrame f = new JFrame();
                        f.setSize(600, 350);
                        f.setVisible(true);
                        f.setLocationRelativeTo(null);
                        f.setLayout(new BorderLayout());

                        JButton rentVeh = new JButton("RENT");
                        f.add(rentVeh, BorderLayout.SOUTH);

                        try {
                            apg.drawTable("TAKETAXIAVAILABLEVEHICLES", "SCOOTER");
                            f.add(apg.scrl);

                            apg.jt.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent e) {
                                    row = apg.jt.rowAtPoint(e.getPoint());
                                    col = apg.jt.columnAtPoint(e.getPoint());

                                    rentVeh.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            if (row >= 0 && col >= 0) {
                                                rentConfirm();
                                                f.dispose();
                                            }
                                        }
                                    });
                                }
                            });

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            // TODO: handle exception
                        }

                        // new BookNew().main(args);
                    }
                });

                btnCar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        JFrame f = new JFrame();
                        f.setSize(600, 350);
                        f.setVisible(true);
                        f.setLocationRelativeTo(null);
                        f.setLayout(new BorderLayout());

                        JButton rentVeh = new JButton("RENT");
                        f.add(rentVeh, BorderLayout.SOUTH);

                        try {
                            apg.drawTable("TAKETAXIAVAILABLEVEHICLES", "CAR");
                            f.add(apg.scrl);

                            apg.jt.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent e) {
                                    row = apg.jt.rowAtPoint(e.getPoint());
                                    col = apg.jt.columnAtPoint(e.getPoint());

                                    rentVeh.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            if (row >= 0 && col >= 0) {
                                                rentConfirm();
                                                f.dispose();
                                            }
                                        }
                                    });
                                }
                            });

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            // TODO: handle exception
                        }
                    }
                });

                btnBike.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        JFrame f = new JFrame();
                        f.setSize(600, 350);
                        f.setVisible(true);
                        f.setLocationRelativeTo(null);
                        f.setLayout(new BorderLayout());

                        JButton rentVeh = new JButton("RENT");
                        f.add(rentVeh, BorderLayout.SOUTH);

                        try {
                            apg.drawTable("TAKETAXIAVAILABLEVEHICLES", "BIKE");
                            f.add(apg.scrl);

                            apg.jt.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent e) {
                                    row = apg.jt.rowAtPoint(e.getPoint());
                                    col = apg.jt.columnAtPoint(e.getPoint());

                                    rentVeh.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            if (row >= 0 && col >= 0) {
                                                rentConfirm();
                                                f.dispose();
                                            }
                                        }
                                    });
                                }
                            });

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            // TODO: handle exception
                        }
                    }
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void rentConfirm() {
        try {
            Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            PreparedStatement db = con.prepareStatement("Use TAKETAXI");
            db.execute();

            String vehName = (String) apg.mod.getValueAt(row, 0);
            String vehType = (String) apg.mod.getValueAt(row, 1);
            String vehComp = (String) apg.mod.getValueAt(row, 2);
            String vehReg = (String) apg.mod.getValueAt(row, 3);

            int cnf = JOptionPane.showConfirmDialog(null, "CONFIRM RENT " + vehName + "?", "CONFIRM RENTAL",
                    JOptionPane.YES_NO_OPTION);
            if (cnf == JOptionPane.YES_OPTION) {
                String ins = "INSERT INTO TAKETAXIRENTEDVEHICLES(VEHICLE_NAME, VEHICLE_TYPE,COMPANY,REG_NUMBER,CUSTOMERID,CUSTOMERNAME) VALUES(?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(ins);
                ps.setString(1, vehName);
                ps.setString(2, vehType);
                ps.setString(3, vehComp);
                ps.setString(4, vehReg);
                ps.setString(5, eu.userID);
                ps.setString(6, eu.username);
                ps.executeUpdate();

                String ins1 = "INSERT INTO TAKETAXIRENTALHISTORY(VEHICLE_NAME, VEHICLE_TYPE,COMPANY,REG_NUMBER,CUSTOMERID,CUSTOMERNAME) VALUES(?, ?, ?, ?, ?, ?)";
                PreparedStatement ps1 = con.prepareStatement(ins1);
                ps1.setString(1, vehName);
                ps1.setString(2, vehType);
                ps1.setString(3, vehComp);
                ps1.setString(4, vehReg);
                ps1.setString(5, eu.userID);
                ps1.setString(6, eu.username);
                ps1.executeUpdate();

                String del = "DELETE FROM TAKETAXIAVAILABLEVEHICLES WHERE VEHICLE_NAME LIKE '" + vehName + "'";
                PreparedStatement pstat = con.prepareStatement(del);
                pstat.executeUpdate();

                uhp.tbl.addRow(new Object[] { vehName, vehType, vehComp, vehReg });

                JOptionPane.showMessageDialog(fr, "RENTED SUCCESSFULLY");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
