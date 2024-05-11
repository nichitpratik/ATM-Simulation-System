package AtmSimulator;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class AccountBalance extends JFrame implements ActionListener, KeyListener{
    
    String CardNo, PinNo, Balance;
    
    JLabel AccName, AccNumber, text;
    
    JButton Close, Print;
    
    
    public AccountBalance(String CardNo, String PinNo) {
        
        setTitle("ATM Simulator");
        
        this.CardNo = CardNo;
        this.PinNo = PinNo;
        
        
        conn c = new conn();
        String q1 = "Select Uname FROM Accounts WHERE CardNumber="+CardNo+" AND PIN="+PinNo;
//        System.out.println(q1);
        String q2 = "Select AccNumber FROM Accounts WHERE CardNumber="+CardNo+" AND PIN="+PinNo;
//        System.out.println(q2);
        String q3 = "Select CurrBal FROM Accounts WHERE CardNumber="+CardNo+" AND PIN="+PinNo;
        
        String Name = null,AccNo = null, Balance=null;
        try
        {
            ResultSet rs1, rs2, rs3;
            rs1=c.s.executeQuery(q1);
            rs2=c.s1.executeQuery(q2);
            rs3=c.s2.executeQuery(q3);
            
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
            
            while (rs3.next())
            {
                Balance = rs3.getString(1);
                System.out.println(Balance);
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
        
        
        text = new JLabel("Avaliable Balance: "+ Balance + " Rs.");
        text.setFont(new Font("Ariel", Font.BOLD, 18));
        text.setBounds(150, 175, 350, 30);
        add(text);
        
        
        Close = new JButton("Close");
        Close.setBackground(new Color(46, 191, 95));
        Close.setForeground(Color.WHITE);
        Close.setBounds(400,250,100,30);
        Close.setFont(new Font("Arial", Font.BOLD, 16));
        add(Close);
        
        
        Print = new JButton("Print");
        Print.setBackground(new Color(46, 191, 95));
        Print.setForeground(Color.WHITE);
        Print.setBounds(275,250,100,30);
        Print.setFont(new Font("Arial", Font.BOLD, 16));
        add(Print);
        
        Close.addActionListener(this);
        Print.addActionListener(this);
        
        addKeyListener(this);
        setFocusable(true);
        
        setSize(800,500);
        getContentPane().setBackground(new Color(66, 141, 245));
        setLayout(null);
        setLocation(350,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == Close)
        {
            System.exit(0);
        }
        else if (ae.getSource() == Print)
        {
            setVisible(false);
            new Receipt(CardNo, PinNo).setVisible(true);
            
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
            setVisible(false);
            new Receipt(CardNo, PinNo).setVisible(true);
        }
        
    }
    
    public void keyTyped(KeyEvent ke)
      {
        System.out.println("keyTyped");
      }
    
    

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new AccountBalance("123456123456", "1234");
    }

}
