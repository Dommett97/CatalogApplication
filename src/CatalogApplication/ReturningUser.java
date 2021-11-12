/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CatalogApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;


/**
 *
 * @author callu
 */
public class ReturningUser extends JPanel implements ActionListener{
      
    static JFrame returnUser = new JFrame("Returning User Login");
    JLabel userID = new JLabel("Please enter your unique user ID: ");
    JTextField txtID = new JTextField(10);
    JButton enter = new JButton("Login");
    static String ID;
    JLabel success = new JLabel("Succsefully logged in, welcome!");
    JLabel failed = new JLabel("Failed to login, please check your User ID");
    
    public ReturningUser(){
        add(userID);
        add(txtID);
        add(enter);
        enter.addActionListener(this);
    }
   

    @Override
    public void actionPerformed(ActionEvent e) {
        Connection con = null;
            if(e.getSource() == enter){
                try {
                    CatalogScreen c = new CatalogScreen();
                    ID = txtID.getText();
                    String host = "jdbc:sqlite:C://sqlite//GUI/SQLiteStudio/Customer Details";
                    con = DriverManager.getConnection(host);
                    System.out.println("Connection to SQLite has been established");
                    PreparedStatement ps = con.prepareStatement("SELECT * from [Customer Details] WHERE [User ID] = ? ");
                    ps.setString(1, ID);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()){
                        removeAll();
                        revalidate();
                        repaint();
                        add(success);
                        con.close();
                        c.main(null);
                        
                        
                    }
                    
                }catch(Exception ex){
                    
                }
            }
    }
}



