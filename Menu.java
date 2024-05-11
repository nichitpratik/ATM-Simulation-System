package AtmSimulator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import AtmSimulator.Menu;

import java.sql.*;

public class Menu extends JFrame implements ActionListener, KeyListener {
    
    JLabel AccName, AccNumber, Operation;
    protected JButton deposit, withdraw, miniStatement, AccBalance, PinChange, Cancel;
    String CardNo, PinNo;
    
    public Menu(String CardNo, String PinNo) 
    {
        setTitle("ATM Simulator");
        
        this.CardNo = CardNo;
        this.PinNo = PinNo;
        
        
        
        
//        System.out.println(CardNo);
//        System.out.println(PinNo);
        

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
        
        
        
        
        deposit = new JButton("Deposit");
        deposit.setFont(new Font("Ariel", Font.BOLD, 20));
        deposit.setBounds(50,200,300,50);
        add(deposit);
        
        
        withdraw = new JButton("Withdraw");
        withdraw.setFont(new Font("Ariel", Font.BOLD, 20));
        withdraw.setBounds(450,200,300,50);
        add(withdraw);
        
        
        AccBalance = new JButton("Check Balance");
        AccBalance.setFont(new Font("Ariel", Font.BOLD, 20));
        AccBalance.setBounds(50,275,300,50);
        add(AccBalance);
        
        
        miniStatement = new JButton("Mini Statement");
        miniStatement.setFont(new Font("Ariel", Font.BOLD, 20));
        miniStatement.setBounds(450,275,300,50);
        add(miniStatement);
        
        
        PinChange = new JButton("Change Pin");
        PinChange.setFont(new Font("Ariel", Font.BOLD, 20));
        PinChange.setBounds(50,350,300,50);
        add(PinChange);
        
        
        Cancel = new JButton("Cancel");
        Cancel.setFont(new Font("Ariel", Font.BOLD, 20));
        Cancel.setBounds(450,350,300,50);
        add(Cancel);
        
        
        
        
        deposit.addActionListener(this);
        withdraw.addActionListener(this);
        AccBalance.addActionListener(this);
        miniStatement.addActionListener(this);
        PinChange.addActionListener(this);
        Cancel.addActionListener(this);
        
        
        
        
        addKeyListener(this);
        setFocusable(true);
        
        setSize(800,500);
        getContentPane().setBackground(new Color(66, 141, 245));
        setLocation(350,200);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == Cancel)
        {
            System.exit(0);
        }
        else if (ae.getSource() == deposit)
        {
            setVisible(false);
            new Deposit(CardNo,PinNo).setVisible(true);
        }
        else if (ae.getSource() == withdraw)
        {
            setVisible(false);
            new Withdraw(CardNo,PinNo).setVisible(true);
        }
        else if (ae.getSource() == PinChange)
        {
            setVisible(false);
            new PINChange(CardNo,PinNo).setVisible(true);
        }
        else if (ae.getSource() == miniStatement)
        {
            setVisible(false);
            new MiniStatement(CardNo,PinNo).setVisible(true);
        }
        else if (ae.getSource() == AccBalance)
        {
            setVisible(false);
            new AccountBalance(CardNo,PinNo).setVisible(true);
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
    }
    
    public void keyTyped(KeyEvent ke)
      {
        System.out.println("keyTyped");
      }
    
    
    
    

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Menu("123456123456","1234");
    }

}
