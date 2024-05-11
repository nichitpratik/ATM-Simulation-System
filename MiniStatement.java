package AtmSimulator;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class MiniStatement extends JFrame implements ActionListener, KeyListener{
    
    String CardNo, PinNo;
    JButton Close, Print;
    JLabel AccName, AccNumber, text, trans;
    JPanel panel;
   
    
    
    
    
    MiniStatement(String CardNo,String PinNo)
    {
        setTitle("Atm Simulator");
        
        this.CardNo = CardNo;
        this.PinNo = PinNo;
        
        conn c = new conn();
        String q1 = "Select Uname FROM Accounts WHERE CardNumber="+CardNo+" AND PIN="+PinNo;
//        System.out.println(q1);
        String q2 = "Select AccNumber FROM Accounts WHERE CardNumber="+CardNo+" AND PIN="+PinNo;
        
//        System.out.println(q2);
        
        String Name = null,AccNo = null, Transactionid,Operation, Amount, DateTime;
        
        panel = new JPanel();
        
        
        
        
        try
        {
            ResultSet rs1, rs2, rs3;
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
            String q3 = "Select * FROM Transactions WHERE AccNumber="+AccNo+" ORDER BY Transactions_Time";
            
            rs3 = c.s2.executeQuery(q3);
            int i;
            int j = 0;
            
            
            String arr1[] = {"    Account No", "\tTransactionid", "\tOperation", "\tAmount", "\tTime"};
            
            for (i=0;i<5;i++) {
                
                trans = new JLabel(arr1[i]);
                trans.setFont(new Font("Ariel", Font.BOLD, 15));
                
                
//            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            panel.add(trans);
            }
            
            rs3.afterLast();
            while (rs3.previous())
            {
                if (j==10)
                {break;}
                j++;
                trans = new JLabel("    AccountNo        Transactionid        Operation        Amount        Time");
                trans.setFont(new Font("Ariel", Font.BOLD, 15));
                
                
                Transactionid = rs3.getString("TransactionsId");
                Operation = rs3.getString("Operation");
                Amount = rs3.getString("Amount");
                DateTime = rs3.getString("Transactions_Time");
                DateTime = DateTime.substring(0,19);
                
                String arr[] = {"    "+AccNo, Transactionid, Operation, Amount, DateTime};
                
//                trans = new JLabel("    "+AccNo+"        "+Transactionid+"        "+Operation+"        "+Amount+"        "+DateTime);
                for (i=0;i<5;i++) {
                    
                    trans = new JLabel(arr[i]);
                    trans.setFont(new Font("Ariel", Font.BOLD, 15));
                    
                    
//                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//                panel.setAlignmentX(Component.LEFT_ALIGNMENT);
                
                panel.add(trans);
                }
                
                
            }
            
            System.out.println(j);
            
            for (int k = 0; k < 10-j;k++)
            {
                System.out.println(j+k);
                for (i=0;i<5;i++) {
                    
                    panel.add(new JLabel(" "));
                }
            }
           
            panel.setLayout(new GridLayout(11,5,-25,15));
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.exit(0);
        }
        
        
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        scrollPane.setBounds(0, 125, 800, 300);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        setContentPane(contentPane);
        pack();
    
        
        AccName = new JLabel("Name: "+ Name);
        AccName.setFont(new Font("Times New Roman", Font.BOLD, 20));
        AccName.setBounds(250, 30, 500, 30);
        add(AccName);
        
        
        AccNumber = new JLabel("Account Number: "+ AccNo);
        AccNumber.setFont(new Font("Times New Roman", Font.BOLD, 20));
        AccNumber.setBounds(154, 60, 500, 30);
        add(AccNumber);
        
        
        Close = new JButton("Close");
        Close.setBackground(new Color(46, 191, 95));
        Close.setForeground(Color.WHITE);
        Close.setBounds(400,430,100,30);
        Close.setFont(new Font("Arial", Font.BOLD, 16));
        add(Close);
        
        
        Print = new JButton("Print");
        Print.setBackground(new Color(46, 191, 95));
        Print.setForeground(Color.WHITE);
        Print.setBounds(275,430,100,30);
        Print.setFont(new Font("Arial", Font.BOLD, 16));
        add(Print);
        
        
        
        addKeyListener(this);
        
        Close.addActionListener(this);
        Print.addActionListener(this);
        
        text = new JLabel("Recent Transactions");
        text.setFont(new Font("Times New Roman", Font.BOLD, 20));
        text.setBounds(100, 90, 350, 30);
        add(text);
        
        
        
        setSize(800,500);
        setLayout(null);
        getContentPane().setBackground(new Color(66, 141, 245));
        setLocation(350,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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
            new Receipt(CardNo, PinNo);
        }
        
    }
    

    
    
    public void keyPressed(KeyEvent ke)
    {
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
        new MiniStatement("123456123456","1234");
    }

}
