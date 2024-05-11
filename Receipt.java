package AtmSimulator;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.io.File;
import java.io.*;


public class Receipt extends JFrame implements ActionListener{

    String CardNo, PinNo, Name, AccNo, currbal, arr1[];
    int rows;
    JPanel panel;
    JLabel thanks, name, accno, bal, text;
    JButton Close, Print;
    
    
    public Receipt(String CardNo, String PinNo) {
        setTitle("Atm Simulator");
        this.CardNo = CardNo;
        this.PinNo = PinNo;
        
        
        
        
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0,100,800,300);
        panel.setLayout(new GridLayout(9,5,-100,0));
        
        String Name=null, AccNo = null,currbal=null,q1, q2, q3, q4;
        JLabel trans;
        ResultSet rs4 = null;
        try
        {
            conn c = new conn();
            
            ResultSet rs1, rs2, rs3;
            
            
            q1 = "Select Uname FROM Accounts WHERE CardNumber = "+CardNo+" And PIN="+PinNo;
            q2 = "Select AccNumber FROM Accounts WHERE CardNumber = "+CardNo+" And PIN="+PinNo;
            q3 = "Select CurrBal FROM Accounts WHERE CardNumber = "+CardNo+" And PIN="+PinNo;
            
            rs1 = c.s.executeQuery(q1);
            rs2 = c.s1.executeQuery(q2);
            rs3 = c.s2.executeQuery(q3);
            
            while (rs1.next())
            {
                Name = rs1.getString(1);
            }
            this.Name = Name;
            
            while (rs2.next())
            {
                AccNo = rs2.getString(1);
            }
            this.AccNo = AccNo;
            while (rs3.next())
            {
                currbal = rs3.getString(1);
            }
            this.currbal = currbal;
            
            q4 = "Select * FROM Transactions Where AccNumber = "+AccNo+" ORDER BY Transactions_Time";
            
            rs4 = c.s3.executeQuery(q4);
            rs4.last();
            this.rows = rs4.getRow();
            rs4.first();
            if (rows>5)
            {
                rows = 5;
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.exit(0);
        }
        int i;
        
        name = new JLabel("    Name: "+Name);
        name.setFont(new Font("Ariel", Font.BOLD, 16));
        panel.add(name);
        for (i=0;i<4;i++) {
            
            panel.add(new JLabel(" "));
        }
        
        accno = new JLabel("    Account No: "+AccNo);
        accno.setFont(new Font("Ariel", Font.BOLD, 16));
        panel.add(accno);
            for (i=0;i<4;i++) {
            
                panel.add(new JLabel(" "));
            }
        
        bal = new JLabel("    Account Balance: "+currbal);
        bal.setFont(new Font("Ariel", Font.BOLD, 16));
        panel.add(bal);
        for (i=0;i<4;i++) {
            
            panel.add(new JLabel(" "));
            }
        
        text = new JLabel("    Last 5 transactions: ");
        text.setFont(new Font("Ariel", Font.BOLD, 16));
        panel.add(text);
        for (i=0;i<4;i++) {
            
            panel.add(new JLabel(" "));
            }
        
        
        String tid,time,amount, op;
        
        arr1 = new String[rows];
        try {
            rs4.afterLast();
            int j = 0;
            while (rs4.previous())
            {
                if (j ==5)
                {
                    break;
                }
                tid = rs4.getString(2);
                op = rs4.getString(3);
                amount = rs4.getString(4);
                time = rs4.getString(5).substring(0, 19);
                String arr[] = {"    "+AccNo,tid,op,amount,time};
                
                if (j<rows)
                {this.arr1[j] = AccNo+"\t"+tid+"\t"+op+"\t"+amount+"\t"+time;
                } 
                for (i=0;i<5;i++)
                {   
                    trans = new JLabel(arr[i]);
                    trans.setFont(new Font("Ariel",Font.BOLD,16));
                    panel.add(trans);
                }
                j++;
                
            }
            
            for (i=0;i<5-j;i++) {
                
                for (int k = 0;k<5;k++)
                panel.add(new JLabel(" "));
                }
            
            
            
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.exit(0);
        }
        
        
        
        
        
        
        
        
        
        
//        add(panel);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(panel);
        setContentPane(contentPane);
        pack();
        
        
        thanks = new JLabel("Thank You for using ATM");
        thanks.setFont(new Font("Ariel", Font.BOLD, 30));
        thanks.setBounds(200,20,400,35);
        add(thanks);
        
        Close = new JButton("Close");
        Close.setBackground(new Color(46, 191, 95));
        Close.setForeground(Color.WHITE);
        Close.setBounds(400,415,100,30);
        Close.setFont(new Font("Arial", Font.BOLD, 16));
        add(Close);
        
        
        Print = new JButton("Print");
        Print.setBackground(new Color(46, 191, 95));
        Print.setForeground(Color.WHITE);
        Print.setBounds(275,415,100,30);
        Print.setFont(new Font("Arial", Font.BOLD, 16));
        add(Print);
        
        Close.addActionListener(this);
        Print.addActionListener(this);
        
        
        
        setSize(800,500);
        setLayout(null);
        getContentPane().setBackground(new Color(66, 141, 245));
        setLocation(350,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        System.out.println(rows);
    }
    
    
    public void PrintReceipt()
    {
        
        try{
            File receipt = new File("Receipt.txt");

            // if file doesnt exists, then create it
            if (!receipt.exists()) {
                
                receipt.createNewFile();
                System.out.println("File Created Successfully");
            }else
            {
//                System.out.println("File Exixst");   
            }
            
            FileWriter fw = new FileWriter(receipt.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Name: "+Name+"\n");
            bw.write("AccountNo: "+AccNo+"\n");
            bw.write("Current Balance: "+currbal+"\n");
            bw.write("Last 5 Transactions: \n");
            
            for (int i = 0;i<rows;i++)
            {
                bw.write(arr1[i]+"\n");
            }
            
            bw.close();
            System.out.println("File Created Successfully");
            JOptionPane.showMessageDialog(null, "Receipt Created successfully");
            System.exit(0);
            
        }catch(IOException e){
            e.printStackTrace();
        }
    
    
    }

    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == Close)
        {
            System.exit(0);
        }
        else if (ae.getSource() == Print)
        {
            PrintReceipt();
        }
        
    }
    
    
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Receipt("123456123456", "1234");
    }

}
