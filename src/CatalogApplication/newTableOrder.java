/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CatalogApplication;

import static CatalogApplication.newChairOrder.ID;
import static CatalogApplication.newChairOrder.TOW;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author callum
 */
public class newTableOrder extends JPanel implements ActionListener{
    
    static int ID;
    static String TOW;
    static int QUANT;
    static int DIAM;
    static int UNITS;
    static String BASE;
    static double tablePrice;
    
    JFrame tableOrder = new JFrame("Desk Order Details");
    JLabel tableID = new JLabel("Please enter Table ID: ");
    JTextField txtID = new JTextField(10);
    JLabel typeOfWood = new JLabel("Please choose the type of wood: ");
    String tow [] = {"Oak", "Walnut"};
    JComboBox chooseWood = new JComboBox(tow);
    JLabel quant = new JLabel("Please enter the amount of tables you want: ");
    JTextField txtQuant = new JTextField(10);
    JLabel diameter = new JLabel("Please enter the diameter of the table(50cm or more):  ");
    JTextField txtDiameter = new JTextField(10);
    JLabel base = new JLabel("Please choose what table base you want: ");
    String cob [] = {"Wood", "Chrome"};
    JComboBox chooseBase = new JComboBox(cob);
    static JButton enter = new JButton("Enter");
    
    public newTableOrder(){
        add(tableID);
        add(txtID);
        add(typeOfWood);
        add(chooseWood);
        add(quant);
        add(txtQuant);
        add(diameter);
        add(txtDiameter);
        add(base);
        add(chooseBase);
        add(enter);
        enter.addActionListener(this);
    }
    
    public static void tablePrice(){
        if(TOW.equals("Oak")){
            if(BASE.equals("Wood")){
                tablePrice = UNITS * 0.04 + 45 * QUANT;
            }
            else if (BASE.equals("Chrome")){
                tablePrice = UNITS * 0.04 + 35 * QUANT;
            }
        }
        
         if(TOW.equals("Walnut")){
            if(BASE.equals("Wood")){
                tablePrice = UNITS * 0.03 + 45 * QUANT;
            }
            else if (BASE.equals("Chrome")){
                tablePrice = UNITS * 0.03 + 35 * QUANT;
            }
        }
    }
    
    public static void getConnection(){
        Connection con = null;
        try {        
            String host = "jdbc:sqlite:C://sqlite//GUI/SQLiteStudio/Customer Details";
            con = DriverManager.getConnection(host);
            System.out.println("Connection to SQLite has been established");
            PreparedStatement pst = con.prepareStatement("insert into[Table Sales]([Table ID], [Type of Wood], Quantity, Base) VALUES (?,?,?,?)");
            pst.setInt(1, ID);
            pst.setString(2, TOW);
            pst.setInt(3, QUANT);
            pst.setString(4,BASE);  
            
            int i = pst.executeUpdate();
            if (i !=0){
                con.close();
            }
            else{
                System.out.println("Failed to add");
            }
            
        }   catch (SQLException ex) {
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enter){
            String i = txtID.getText();
            ID = Integer.parseInt(i);
            TOW = (String) chooseWood.getSelectedItem();
            String q = txtQuant.getText();
            QUANT = Integer.parseInt(q);
            String d = txtDiameter.getText();
            DIAM = Integer.parseInt(d);
            UNITS = DIAM * DIAM;
            BASE = (String) chooseBase.getSelectedItem();
            getConnection();
            tablePrice();
            CatalogScreen.closeTableOrder();
            System.out.println(tablePrice);
            
        }       
    }
    
}
