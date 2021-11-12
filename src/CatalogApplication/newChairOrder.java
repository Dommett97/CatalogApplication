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
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author callu
 */
public class newChairOrder extends JPanel implements ActionListener{
    
    static int ID;
    static String TOW;
    static int QUANT;
    static boolean AR;
    static double chairPrice;
    
    static JFrame chairOrder = new JFrame("Chair Order Details");
    JLabel chairID = new JLabel("Please enter chair ID: ");
    JTextField txtID = new JTextField(10);
    JLabel typeOfWood = new JLabel("Please choose the type of wood: ");
    String tow [] = {"Oak", "Walnut"};
    JComboBox chooseWood = new JComboBox(tow);
    JLabel quant = new JLabel("Please enter the amount of chairs you want: ");
    JTextField txtQuant = new JTextField(10);
    JLabel armRest = new JLabel("Add arm rests to the chair?   ");
    Vector<Boolean> booleanVector = new Vector<Boolean>();
    JComboBox chooseARests = new JComboBox(booleanVector);
    static JButton enter = new JButton("Enter");
    
    public newChairOrder(){
        add(chairID);
        add(txtID);
        add(typeOfWood);
        add(chooseWood);
        add(quant);
        add(txtQuant);
        add(armRest);
        booleanVector.addElement(Boolean.TRUE);
        booleanVector.addElement(Boolean.FALSE);
        add(chooseARests);
        add(enter);
        enter.addActionListener(this);
        
    }
    
    public static void chairPrice(){
        if(AR == true){
            if(TOW.equals("Oak")){
                chairPrice = 1875 * 0.04 * QUANT;
            }
            else if(TOW.equals("Walnut")){
                chairPrice = 1875 * 0.03 * QUANT;

        }   
    }
        if(AR == false){
            if(TOW.equals("Oak")){
                chairPrice = 1625 * 0.04 * QUANT;

            }
            else if(TOW.equals("Walnut")){
                chairPrice = 1625 * 0.03 * QUANT;

            }
        }
    }
    public static void getConnection(){
        Connection con = null;
            
        try {        
            String host = "jdbc:sqlite:C://sqlite//GUI/SQLiteStudio/Customer Details";
            con = DriverManager.getConnection(host);
            System.out.println("Connection to SQLite has been established");
            PreparedStatement pst = con.prepareStatement("insert into[Chair Sales]([Chair ID], [Type of Wood], Quantity, [Arm Rests]) VALUES (?,?,?,?)");
            pst.setInt(1, ID);
            pst.setString(2, TOW);
            pst.setInt(3, QUANT);
            if(AR == true ){
                pst.setString(4, "True");
            }
            else{
                pst.setString(4, "False");
            }
            int i = pst.executeUpdate();
            if (i !=0){
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enter){
            String text = txtID.getText();
            ID = Integer.parseInt(text);
            TOW = (String) chooseWood.getSelectedItem();
            String quant = txtQuant.getText();
            QUANT = Integer.parseInt(quant);
            AR = (boolean) chooseARests.getSelectedItem();
            getConnection();
            chairPrice();
            CatalogScreen.closeChairOrder();
            System.out.println(chairPrice);
            
        }
    }
    
    
}
