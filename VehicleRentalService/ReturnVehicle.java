package VehicleRentalService;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;
import java.sql.*;

public class ReturnVehicle {
    public static String JDBC_URL = "jdbc:mysql://localhost:3306/";
    public static String user = {YOUR USER NAME};
    public static String password = {YOUR USER PASSWORD};

    public static AdminPage apg = new AdminPage();
    public static ExistingUser eu = new ExistingUser();
    public static UserHomePage uhp = new UserHomePage();

    private static int row = 0, col = 0;
    private static JFrame f;

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            PreparedStatement db = con.prepareStatement("Use TAKETAXI");
            db.execute();
            Statement st = con.createStatement();
            {
                f = new JFrame();
                f.setSize(450, 500);
                f.setVisible(true);
                f.setLocationRelativeTo(null);
                f.setLayout(new BorderLayout());

                JLabel retLbl = new JLabel("SELECT THE VEHICLE YOU WANT TO RETURN:", SwingConstants.CENTER);
                retLbl.setFont(new Font("SANS SERIF", Font.BOLD, 18));
                retLbl.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

                JButton rentVeh = new JButton("RETURN");
                f.add(retLbl, BorderLayout.NORTH);
                f.add(rentVeh, BorderLayout.SOUTH);

                try {
                    apg.drawTable("TAKETAXIRENTEDVEHICLES", eu.userID, 1);
                    f.add(apg.scrl);

                    apg.jt.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            row = apg.jt.rowAtPoint(e.getPoint());
                            col = apg.jt.columnAtPoint(e.getPoint());

                            rentVeh.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    if (row >= 0 && col >= 0) {
                                        returnRentedVehicle();
                                        f.dispose();
                                    }
                                }
                            });
                        }
                    });

                } catch (Exception e) {
                    // TODO: handle exception
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void returnRentedVehicle() {
        try {
            Connection con = DriverManager.getConnection(JDBC_URL, user, password);
            PreparedStatement db = con.prepareStatement("Use TAKETAXI");
            db.execute();

            String vehName = (String) apg.mod.getValueAt(row, 0);
            String vehType = (String) apg.mod.getValueAt(row, 1);
            String vehComp = (String) apg.mod.getValueAt(row, 2);
            String vehReg = (String) apg.mod.getValueAt(row, 3);

            int cnf = JOptionPane.showConfirmDialog(null, "CONFIRM RETURN " + vehName + "?", "CONFIRM RETURN",
                    JOptionPane.YES_NO_OPTION);
            if (cnf == JOptionPane.YES_OPTION) {
                String del = "DELETE FROM TAKETAXIRENTEDVEHICLES WHERE VEHICLE_NAME LIKE '" + vehName + "';";
                PreparedStatement ps = con.prepareStatement(del);
                ps.executeUpdate();

                String ins = "INSERT INTO TAKETAXIAVAILABLEVEHICLES(VEHICLE_NAME, VEHICLE_TYPE,COMPANY,REG_NUMBER) VALUES(?, ?, ?, ?)";
                ps = con.prepareStatement(ins);
                ps.setString(1, vehName);
                ps.setString(2, vehType);
                ps.setString(3, vehComp);
                ps.setString(4, vehReg);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(f, "RETURNED SUCCESSFULLY. THANKS FOR USING OUR SERVICE.");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
