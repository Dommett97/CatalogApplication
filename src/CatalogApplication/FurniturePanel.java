/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CatalogApplication;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author callum
 */
public class FurniturePanel extends JPanel implements ActionListener {
    
    JButton button1;
    JButton button2;
    JLabel label1 = new JLabel();    
    static JFrame application = new JFrame("New User Login");
    static JFrame application2 = new JFrame("Returning User Login");
    
    public FurniturePanel(){ 
        
        Dimension size = new Dimension(100,100);
        button1 = new JButton("New User");
        button2 = new JButton("Returning User");
        button1.addActionListener(this);
        button2.addActionListener(this);
        label1 = new JLabel("Are you a new or returning user?");
        label1.setSize(size);
        add(label1);
        add(button1);
        add(button2);
        
    }
    
    public void newUser(){
        newUser newUser = new newUser();
        application.setSize(300, 280);
        application.add(newUser);
        application.setVisible(true);
    }
    
    public static void closeNewUser(){
        application.setVisible(false);
    }
    
    public static void closeReturningUser() throws InterruptedException{
        TimeUnit.SECONDS.sleep(2);
        application2.setVisible(false);
    }
    
    public static void returningUser(){
        ReturningUser returning = new ReturningUser();
        application2.setSize(300,100);
        application2.add(returning);
        application2.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == button1){         
            newUser();
            
        }
        if(e.getSource() == button2){
            returningUser();
        }
    }
    
}
