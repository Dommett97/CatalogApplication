/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CatalogApplication;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author callum
 */
public class newUser extends JPanel implements ActionListener {
    static int ID;
    static String TLE;
    static String FN;
    static String LN;
    static String L1;
    static String L2;
    static String TWN;
    static String CTY;
    static String POST;
    
    JFrame newUser = new JFrame("New User Login");
    JLabel title = new JLabel("Title: ");
    JTextField txtTitle = new JTextField(20);
    JLabel firstName = new JLabel("First Name: ");
    JTextField txtFN = new JTextField(15);
    JLabel lastName = new JLabel("Second Name: ");
    JTextField txtLN = new JTextField(15);
    JLabel line1 = new JLabel("Line 1: ");
    JTextField txtL1 = new JTextField(20);
    JLabel line2 = new JLabel("Line 2: ");
    JTextField txtL2 = new JTextField(20);
    JLabel town = new JLabel("Town: ");
    JTextField txtT = new JTextField(20);
    JLabel county = new JLabel("County: ");
    JTextField txtC = new JTextField(20);
    JLabel postcode = new JLabel("Postcode: ");
    JTextField txtP = new JTextField(18);
    JButton register = new JButton("Register");
    
    
    
    public newUser(){
        add(title);
        add(txtTitle);
        add(firstName);
        add(txtFN);
        add(lastName);
        add(txtLN);
        add(line1);
        add(txtL1);
        add(line2);
        add(txtL2);
        add(town);
        add(txtT);
        add(county);
        add(txtC);
        add(postcode);
        add(txtP);
        add(register);
        register.addActionListener(this);
        
        
    }
    
    public static int generateID(){
        UUID idOne = UUID.randomUUID();
        String str=""+idOne;        
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }
   

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == register){
         for (int i = 0; i < 1; i++) {
            System.out.println(generateID());
         }
        ID = generateID();
        TLE = txtTitle.getText();
        FN = txtFN.getText();
        LN = txtLN.getText();
        L1 = txtL1.getText();
        L2 = txtL2.getText();
        TWN = txtT.getText();
        CTY = txtC.getText();
        POST = txtP.getText();
        FurniturePanel.closeNewUser();
        TestFurnitureItems.getConnection();
        }
    }
}
        
    

