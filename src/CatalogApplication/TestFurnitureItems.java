/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CatalogApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFrame;


/**
 *
 * @author callum
 */
public class TestFurnitureItems  {
    
    
    public static void main(String[] args) {
        
        JFrame application = new JFrame("ROFC Furniture Application");
        application.setSize(300, 100);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FurniturePanel panel = new FurniturePanel();
        application.add(panel);
        application.setVisible(true);       
        
    }
    
        public static void getConnection(){
        Connection con = null;
            
        try {        
            String host = "jdbc:sqlite:C://sqlite//GUI/SQLiteStudio/Customer Details";
            con = DriverManager.getConnection(host);
            System.out.println("Connection to SQLite has been established");
            PreparedStatement pst = con.prepareStatement("insert into[Customer Details]([User ID], Title, [First Name], [Last Name], [Line 1],[Line 2], Town, County, Postcode) VALUES (?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, newUser.ID);
            pst.setString(2, newUser.TLE);
            pst.setString(3, newUser.FN);
            pst.setString(4, newUser.LN);
            pst.setString(5, newUser.L1);
            pst.setString(6, newUser.L2);
            pst.setString(7, newUser.TWN);
            pst.setString(8, newUser.CTY);
            pst.setString(9, newUser.POST);
            int i = pst.executeUpdate();
            if (i !=0){
                System.out.println("added");
                con.close();
            }
            else{
                System.out.println("Failed to add");
            }
  
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        }
}
