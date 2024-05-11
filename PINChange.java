package AtmSimulator;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class PINChange extends JFrame implements ActionListener, KeyListener{
    String CardNo, PinNo;
    JPasswordField oldpin,newpin, confirmpin;
    JButton Confirm, Cancel;
    JLabel AccName, AccNumber, text1, text2, text3;

    public PINChange(String CardNo, String PinNo) {
        setTitle("ATM Simulator");
        
        this.CardNo = CardNo;
        this.PinNo = PinNo;
        
        
        conn c = new conn();
        String q1 = "Select Uname FROM Accounts WHERE CardNumber="+CardNo+" AND PIN="+PinNo;
//        System.out.println(q1);
        String q2 = "Select AccNumber FROM Accounts WHERE CardNumber="+CardNo+" AND PIN="+PinNo;
//        System.out.println(q2);
        
        
        String Name = null,AccNo = null;
        try
        {
            ResultSet rs1, rs2;
            rs1=c.s.executeQuery(q1);
            rs2=c.s1.executeQuery(q2);
            
            while (rs1.next())
            {
                Name = rs1.getString(1);
                System.out.println(Name);
            }
            
            
            while (rs2.next())
            {
                AccNo = rs2.getString(1);
                System.out.println(AccNo);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.exit(0);
        }
        
        AccName = new JLabel("Name: "+ Name);
        AccName.setFont(new Font("Times New Roman", Font.BOLD, 20));
        AccName.setBounds(250, 50, 500, 30);
        add(AccName);
        
        
        AccNumber = new JLabel("Account Number: "+ AccNo);
        AccNumber.setFont(new Font("Times New Roman", Font.BOLD, 20));
        AccNumber.setBounds(154, 100, 500, 30);
        add(AccNumber);
        
        
        text1 = new JLabel("Old Pin:");
        text1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        text1.setBounds(182, 150, 200, 30);
        add(text1);
        
        text2 = new JLabel("New Pin:");
        text2.setFont(new Font("Times New Roman", Font.BOLD, 18));
        text2.setBounds(175, 200, 200, 30);
        add(text2);
        
        text3 = new JLabel("Confirn Pin:");
        text3.setFont(new Font("Times New Roman", Font.BOLD, 18));
        text3.setBounds(150, 250, 200, 30);
        add(text3);
        
        oldpin = new JPasswordField();
        oldpin.setFont(new Font("Times New Roman", Font.BOLD, 16));
        oldpin.setBounds(275,150,200,25);
        add(oldpin);
        
        newpin = new JPasswordField();
        newpin.setFont(new Font("Times New Roman", Font.BOLD, 16));
        newpin.setBounds(275,200,200,25);
        add(newpin);
        
        confirmpin = new JPasswordField();
        confirmpin.setFont(new Font("Times New Roman", Font.BOLD, 16));
        confirmpin.setBounds(275,250,200,25);
        add(confirmpin);
        
        
        
        Confirm = new JButton("Confirm");
        Confirm.setBackground(new Color(46, 191, 95));
        Confirm.setForeground(Color.WHITE);
        Confirm.setFont(new Font("Arial", Font.BOLD, 16));
        Confirm.setBounds(300,300,150,30);
        add(Confirm);
        
        
        Cancel = new JButton("Cancel");
        Cancel.setBackground(new Color(46, 191, 95));
        Cancel.setForeground(Color.WHITE);
        Cancel.setFont(new Font("Arial", Font.BOLD, 16));
        Cancel.setBounds(300,340,150,30);
        add(Cancel);
        
        
        Confirm.addActionListener(this);
        Cancel.addActionListener(this);
        
        addKeyListener(this);
        confirmpin.addKeyListener(this);
        
        
        setSize(800,500);
        getContentPane().setBackground(new Color(66, 141, 245));
        setLayout(null);
        setLocation(350,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == Cancel)
        {
            System.exit(0);
        }
        else if (ae.getSource() == Confirm)
        {
            
            String NewPin, ConfirmPin, OldPin;
            
            OldPin = new String(oldpin.getPassword());
            NewPin = new String(newpin.getPassword());
            ConfirmPin = new String(confirmpin.getPassword());
            
            if (OldPin.equals(PinNo) && NewPin.equals(ConfirmPin))
            {
                conn c = new conn();
                String q1 = "UPDATE Accounts SET PIN="+NewPin+" WHERE CardNumber="+CardNo+" AND PIN="+PinNo;
                try {
                    c.s.executeUpdate(q1); 
                    JOptionPane.showMessageDialog(null, "Pin changed successfully");
                    System.exit(0);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    System.out.println(e);
                    System.exit(0);
                } 
            }
            else
            {
                
                
                JOptionPane.showMessageDialog(null, "Incorrect Pin");
                oldpin.setText("");
                newpin.setText("");
                confirmpin.setText("");
                
                
            }
            
        }
    }
    
    

    
    
    public void keyPressed(KeyEvent ke)
    {
        System.out.println("keyPressed");
    }
    
    public void keyReleased(KeyEvent ke)
    {
        if (ke.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
            System.exit(0);
        }
        else if(ke.getKeyCode() == KeyEvent.VK_ENTER)
        {
            String NewPin, ConfirmPin, OldPin;
            
            OldPin = new String(oldpin.getPassword());
            NewPin = new String(newpin.getPassword());
            ConfirmPin = new String(confirmpin.getPassword());
            
            if (OldPin.equals(PinNo) && NewPin.equals(ConfirmPin))
            {
                conn c = new conn();
                String q1 = "UPDATE Accounts SET PIN="+NewPin+" WHERE CardNumber="+CardNo+" AND PIN="+PinNo;
                try {
                    c.s.executeUpdate(q1); 
                    JOptionPane.showMessageDialog(null, "Pin changed successfully");
                    System.exit(0);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    System.out.println(e);
                    System.exit(0);
                } 
            }
            else
            {
                
                
                JOptionPane.showMessageDialog(null, "Incorrect Pin");
                oldpin.setText("");
                newpin.setText("");
                confirmpin.setText("");
                
                
            }
        }
        
    }
    
    public void keyTyped(KeyEvent ke)
      {
        System.out.println("keyTyped");
      }
    
    
    

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new PINChange("123456123456", "1234");
    }

}
