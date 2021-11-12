/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CatalogApplication;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 *
 * @author callum
 */
public class CatalogScreen implements ActionListener{
    
    private JComponent ui = null;
    int selectionCounter = 0;
    double total = 0;
    
    static JFrame chairOrderScreen = new JFrame("New Chair Order");
    static JFrame tableOrderScreen = new JFrame("New Table Order");
    static JFrame deskOrderScreen = new JFrame("New Desk Order");
    CardLayout selectedItems = new CardLayout();
    JPanel items = new JPanel(selectedItems);
    JPanel chosenItems = new JPanel(new GridLayout(4, 2, 10, 10));
    JButton addChair = new JButton("Add Chair");
    JButton addTable = new JButton("Add Table");
    JButton addDesk = new JButton("Add Desk");
    JButton clearAll = new JButton("Clear All");
    JButton totalPrice = new JButton("Total Price");
    JButton saveOrder = new JButton("Save Order");
    JButton displayOrder = new JButton("Display Orders");
    ImageIcon chair = new ImageIcon(new ImageIcon("images/chair.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
    ImageIcon table = new ImageIcon(new ImageIcon("images/table.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
    ImageIcon desk = new ImageIcon(new ImageIcon("images/desk.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));;
    
    CatalogScreen(){
        initGUI();
    }

    
    
    public void initGUI(){
        if (ui!=null) return;
        
        ui = new JPanel(new BorderLayout(4, 4));
        ui.setBorder(new TitledBorder(""));
        items.setBorder(new TitledBorder("Selected Items"));
        ui.add(items);
        items.add(chosenItems);
        JPanel lineStart = new JPanel(new GridBagLayout());
        lineStart.setBorder(new TitledBorder(""));
        ui.add(lineStart, BorderLayout.LINE_START);
        
        JPanel buttonsCentered = new JPanel(new GridLayout(10, 1, 10, 10));
        buttonsCentered.setBorder(new TitledBorder(""));
        lineStart.add(buttonsCentered);
        
        buttonsCentered.add(addChair);
        buttonsCentered.add(addTable);
        buttonsCentered.add(addDesk);
        buttonsCentered.add(clearAll);
        buttonsCentered.add(totalPrice);
        buttonsCentered.add(saveOrder);
        buttonsCentered.add(displayOrder);
        addChair.addActionListener(this);
        addTable.addActionListener(this);
        addDesk.addActionListener(this);
        clearAll.addActionListener(this);
        totalPrice.addActionListener(this);
        
        }
        
    
    
    
    public JComponent getUI(){
        return ui;
    }
    
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception useDefault) {
                }
                CatalogScreen o = new CatalogScreen();

                JFrame f = new JFrame(o.getClass().getSimpleName());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                f.setContentPane(o.getUI());
                f.pack();
                f.setSize(800,400);

                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
    
    public static void newChairOrder(){
        newChairOrder chairOrder = new newChairOrder();
        chairOrderScreen.setSize(350,180);
        chairOrderScreen.add(chairOrder);
        chairOrderScreen.setVisible(true);
        
    }
    
    public static void newTableOrder(){
        newTableOrder tableOrder = new newTableOrder();
        tableOrderScreen.setSize(350,220);
        tableOrderScreen.add(tableOrder);
        tableOrderScreen.setVisible(true);
    }
    
    public static void newDeskOrder(){
        newDeskOrder deskOrder = new newDeskOrder();
        deskOrderScreen.setSize(350,200);
        deskOrderScreen.add(deskOrder);
        deskOrderScreen.setVisible(true);
        deskOrderScreen.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.out.println("e");
            }
        }
        );
    }
    
    public static void closeChairOrder(){
        chairOrderScreen.setVisible(false);
    }
    
    public static void closeTableOrder(){
        tableOrderScreen.setVisible(false);
    }
    
    public static void closeDeskOrder(){
        deskOrderScreen.setVisible(false);
    }
    
    public void totalPrice(){
        if(selectionCounter <= 0){
            total = 0;
            System.out.println(total);
        }
        else if(selectionCounter > 0){   
            total = newChairOrder.chairPrice + newTableOrder.tablePrice + newDeskOrder.deskPrice;
            System.out.println(total);
        }
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {  
        if(selectionCounter <= 7){
            if(e.getSource() == addChair && selectionCounter <= 7){
            selectionCounter++;
            JLabel c = new JLabel();
            c.setIcon(chair);
            chosenItems.add(c);
            ui.revalidate();  
            newChairOrder();  
        }
            if(e.getSource() == addTable && selectionCounter <= 7){
            selectionCounter++;
            JLabel t = new JLabel();
            t.setIcon(table);
            chosenItems.add(t);
            ui.revalidate();
            newTableOrder();
            
        }
            if(e.getSource() == addDesk && selectionCounter <= 7){
            selectionCounter++;
            JLabel d = new JLabel();
            d.setIcon(desk);
            chosenItems.add(d);
            ui.revalidate();
            newDeskOrder();
        }
            if(selectionCounter == 8){
                System.out.println("You have reached the maximum amount of orders");
            }
            
            if(e.getSource() == clearAll){
                selectionCounter = 0;
                chosenItems.removeAll();
                chosenItems.updateUI();              
            }
            
            if(e.getSource() == totalPrice){
                totalPrice();
            }
        }
    }
}


