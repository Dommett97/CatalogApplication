/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CatalogApplication;

import static CatalogApplication.newTableOrder.ID;
import static CatalogApplication.newTableOrder.QUANT;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class newDeskOrder extends JPanel implements ActionListener {
    
    static int ID;
    static String TOW;
    static int QUANT;
    static int UNITS;
    static int DEPTH;
    static int WIDTH;
    static int DRAWS;
    static int deskPrice;
    
    static JFrame deskOrder = new JFrame("Desk Order Details");
    JLabel deskID = new JLabel("Please enter desk ID: ");
    JTextField txtID = new JTextField(10);
    JLabel typeOfWood = new JLabel("Please choose the type of wood: ");
    String tow [] = {"Oak", "Walnut"};
    JComboBox chooseWood = new JComboBox(tow);
    JLabel quant = new JLabel("Please enter the amount of desks you would like: ");
    JTextField txtQuant = new JTextField(10);
    JLabel depth = new JLabel("Please enter the depth of the desk: ");
    JTextField txtDepth = new JTextField(10);
    JLabel width = new JLabel("Please enter the width of the desk:");
    JTextField txtWidth = new JTextField(10);
    JLabel draws = new JLabel("Amount of draws (Maximum of 4):");
    JTextField txtDraws = new JTextField(10);
    static JButton enter = new JButton("Enter");
    
    
    public newDeskOrder(){
        add(deskID);
        add(txtID);
        add(typeOfWood);
        add(chooseWood);
        add(quant);
        add(txtQuant);
        add(depth);
        add(txtDepth);
        add(width);
        add(txtWidth);
        add(draws);
        add(txtDraws);
        add(enter);
        enter.addActionListener(this);
    }

    public static void deskPrice(){
        if (TOW.equals("Oak")){
            deskPrice = (int) ((int) (DEPTH * WIDTH * 0.04) + (DRAWS * 8.50));
        }
        if (TOW.equals("Walnut")){
            deskPrice = (int) ((int) (DEPTH * WIDTH * 0.03) + (DRAWS * 8.50));
        }
    }
    
    public static void getConnection(){
        Connection con = null;
        try {        
            String host = "jdbc:sqlite:C://sqlite//GUI/SQLiteStudio/Customer Details";
            con = DriverManager.getConnection(host);
            System.out.println("Connection to SQLite has been established");
            PreparedStatement pst = con.prepareStatement("insert into[Table Sales]([Desk ID], [Type of Wood], Quantity, Draws) VALUES (?,?,?,?)");
            pst.setInt(1, ID);
            pst.setString(2, TOW);
            pst.setInt(3, QUANT);
            pst.setInt(4, DRAWS);  
            
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
            String d = txtDepth.getText();
            DEPTH = Integer.parseInt(d);
            String w = txtWidth.getText();
            WIDTH = Integer.parseInt(w);
            String dr = txtDraws.getText();
            DRAWS = Integer.parseInt(dr);
            getConnection();
            deskPrice();
            CatalogScreen.closeDeskOrder();
            System.out.println(deskPrice);
            
            
        }
    }
    
}
