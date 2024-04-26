package PasswordManager;

import java.awt.BorderLayout;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

public class ViewSavedPass {
    //database info
    public static String JDBC_URL = //your values;
    public static String user = //your values;
    public static String password = //your values;
    public static String DBname = /your values;
    public static String username="", userpass="";

    public static ExistingUser eu=new ExistingUser();
    public static void main(String[] args) {
        try {
            Connection con=DriverManager.getConnection(JDBC_URL, user, password);
            PreparedStatement st=con.prepareStatement("Use passxusers");
            st.executeUpdate();

            JFrame fr=new JFrame("Saved Passwords");
            fr.setSize(500, 500);
            fr.setLocationRelativeTo(null);
            fr.setLayout(new BorderLayout());

            DefaultTableModel mod=new DefaultTableModel();
            JTable jt=new JTable();
            jt.setModel(mod);

            mod.addColumn("Website");
            mod.addColumn("Password");
            mod.addColumn("URL");

            PreparedStatement info=con.prepareStatement("Select*from "+eu.userID+";");
            ResultSet rs=info.executeQuery();

            while(rs.next()){
                Object[] rowData={
                    rs.getString("Website"),
                    rs.getString("Password"),
                    rs.getString("URL")
                };
                mod.addRow(rowData);
            }
            JScrollPane scroll=new JScrollPane(jt);
            fr.add(scroll,BorderLayout.CENTER);
            fr.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
    
}
