package AtmSimulator;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;
import javax.swing.*;
import java.time.*;
import java.time.format.*;



public class Deposit extends JFrame implements ActionListener, KeyListener {
    
    String CardNo, PinNo, AccNo;
    JTextField amount;
    JButton deposit, Cancel;
    JLabel AccName, AccNumber, text;
    
    Deposit(String CardNo, String PinNo)
    {
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
        this.AccNo = AccNo;
        
        AccName = new JLabel("Name: "+ Name);
        AccName.setFont(new Font("Times New Roman", Font.BOLD, 20));
        AccName.setBounds(250, 50, 500, 30);
        add(AccName);
        
        
        AccNumber = new JLabel("Account Number: "+ AccNo);
        AccNumber.setFont(new Font("Times New Roman", Font.BOLD, 20));
        AccNumber.setBounds(154, 100, 500, 30);
        add(AccNumber);
        
        
        text = new JLabel("Enter Amount:");
        text.setFont(new Font("Times New Roman", Font.BOLD, 18));
        text.setBounds(150, 175, 200, 30);
        add(text);
        
        
        amount = new JTextField();
        amount.setFont(new Font("Times New Roman", Font.BOLD, 16));
        amount.setBounds(275,181,200,20);
        add(amount);
        
        
        deposit = new JButton("Deposit");
        deposit.setBackground(new Color(46, 191, 95));
        deposit.setForeground(Color.WHITE);
        deposit.setFont(new Font("Arial", Font.BOLD, 16));
        deposit.setBounds(300,240,100,30);
        add(deposit);
        
        
        Cancel = new JButton("Cancel");
        Cancel.setBackground(new Color(46, 191, 95));
        Cancel.setForeground(Color.WHITE);
        Cancel.setFont(new Font("Arial", Font.BOLD, 16));
        Cancel.setBounds(300,290,100,30);
        add(Cancel);
        
        
        deposit.addActionListener(this);
        Cancel.addActionListener(this);
        
        amount.addKeyListener(this);
        addKeyListener(this);
        setFocusable(true);
        
        setSize(800,500);
        getContentPane().setBackground(new Color(66, 141, 245));
        setLayout(null);
        setLocation(350,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    protected String getTransactionId() {
        String AlowedChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder RandomId = new StringBuilder();
        Random rnd = new Random();
        while (RandomId.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * AlowedChar.length());
            RandomId.append(AlowedChar.charAt(index));
        }
        String Tid = RandomId.toString();
        return Tid;

    }
    
    
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == Cancel)
        {
            System.exit(0);
        }
        
        else if(ae.getSource() == deposit)
        {
            conn c = new conn();
            String q1, Amount, q2, RandomId;
            RandomId = getTransactionId();
            
            Amount = amount.getText();
            
            
            LocalDateTime timestamp = LocalDateTime.now();
            DateTimeFormatter FormatedTSimeStamp= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String FormattedDate = timestamp.format(FormatedTSimeStamp);
//            System.out.println(formattedDate);
            
            
            
            q1 = "UPDATE Accounts SET CurrBal = CurrBal+"+ Amount+" WHERE CardNumber = "+CardNo+" AND PIN="+PinNo;
            q2 = "INSERT INTO Transactions VALUES("+AccNo+",'"+RandomId+"','Credit',"+Amount+",'"+FormattedDate+"')";
            
            System.out.println(q2);
            try {
                c.s.executeUpdate(q1);
                c.s1.executeUpdate(q2); 
                setVisible(false);
                new Receipt(CardNo, PinNo).setVisible(true);
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Enter Valid Amount");
                System.out.println(e);
                amount.setText("");
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
            conn c = new conn();
            String q1, Amount, q2, RandomId;
            RandomId = getTransactionId();
            
            Amount = amount.getText();
            
            
            LocalDateTime timestamp = LocalDateTime.now();
            DateTimeFormatter FormatedTSimeStamp= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String FormattedDate = timestamp.format(FormatedTSimeStamp);
//            System.out.println(formattedDate);
            
            
            
            q1 = "UPDATE Accounts SET CurrBal = CurrBal+"+ Amount+" WHERE CardNumber = "+CardNo+" AND PIN="+PinNo;
            q2 = "INSERT INTO Transactions VALUES("+AccNo+",'"+RandomId+"','Credit',"+Amount+",'"+FormattedDate+"')";
            
            System.out.println(q2);
            try {
                c.s.executeUpdate(q1);
                c.s1.executeUpdate(q2); 
                setVisible(false);
                new Receipt(CardNo, PinNo).setVisible(true);
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Enter Valid Amount");
                amount.setText("");
            }
        }
        
    }
    
    public void keyTyped(KeyEvent ke)
      {
        System.out.println("keyTyped");
      }
    
    
    
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Deposit("123456123456", "1234");
    }

}
