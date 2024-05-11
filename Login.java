package AtmSimulator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


//DONT FORGET TO ADD KEYLISTENER LATER
public class Login extends JFrame implements ActionListener, KeyListener
{
    JLabel welcome, card_no, pass;
    JTextField getCardNo;
    JPasswordField getPin;
    JButton signin_btn, clrbtn;
    
    
    
    Login()
    {
        setTitle("ATM Simulator");
        
        welcome = new JLabel("Welcome to ATM");
        welcome.setFont(new Font("Osward", Font.BOLD, 38));
        welcome.setBounds(250,25,500,40);
        add(welcome);
        
        
        
        card_no = new JLabel("Enter Card Number: ");
        card_no.setFont(new Font("Raleway", Font.PLAIN, 20));
        card_no.setBounds(110,100,200,40);
        add(card_no);
        
        
        pass = new JLabel("Enter PIN: ");
        pass.setFont(new Font("Raleway", Font.PLAIN, 20));
        pass.setBounds(200,150,200,40);
        add(pass);
        
        
        getCardNo = new JTextField(12);
        getCardNo.setBounds(300,110,250,25);
        getCardNo.setFont(new Font("Arial", Font.BOLD, 16));
        add(getCardNo);
        
        
        getPin = new JPasswordField(15);
        getPin.setBounds(300,160,250,25);
        getPin.setFont(new Font("Arial", Font.BOLD, 16));
        add(getPin);
        
        
        signin_btn = new JButton("SIGN IN");
        signin_btn.setBackground(new Color(46, 191, 95));
        signin_btn.setForeground(Color.WHITE);
        signin_btn.setFont(new Font("Arial", Font.BOLD, 16));
        signin_btn.setBounds(350,225,100,30);
        add(signin_btn);
        
        
        clrbtn = new JButton("CLEAR");
        clrbtn.setBackground(new Color(46, 191, 95));
        clrbtn.setForeground(Color.WHITE);
        clrbtn.setFont(new Font("Arial", Font.BOLD, 16));
        clrbtn.setBounds(350,275,100,30);
        add(clrbtn);
        
        
        signin_btn.addActionListener(this);
        clrbtn.addActionListener(this);
        
        getCardNo.addKeyListener(this);
        getPin.addKeyListener(this);
        clrbtn.addKeyListener(this);
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
        if (ae.getSource() == clrbtn)
        {
            getCardNo.setText("");
            getPin.setText("");
        }
        else if (ae.getSource() == signin_btn)
        {
            conn c = new conn();
            String CardNo = getCardNo.getText();
            String PinNo = new String(getPin.getPassword()); //getPassword returns a char array
            String q1 = "SELECT * FROM Accounts WHERE CardNumber='"+CardNo+"' and PIN='"+PinNo+"'";

            try
            {
                ResultSet rs = c.s.executeQuery(q1);
                if (rs.next())
                {
                    setVisible(false);
                    new Menu(CardNo, PinNo).setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or Password");
                    getCardNo.setText("");
                    getPin.setText("");
                }

            }
            catch (SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Enter Valid Card Number and PIN");
                System.out.println(e);
            }

        }
    }
    
    
    
//  DONT FORGET TO ADD KEYLISTENER LATER

  public void keyPressed(KeyEvent ke)
  {
      System.out.println("keyPressed");
  }
  
  public void keyReleased(KeyEvent ke)
  {
      if (ke.getKeyCode() == KeyEvent.VK_ENTER)
      {
          System.out.println("keyTyped");
          conn c = new conn();
            String CardNo = getCardNo.getText();
            String PinNo = new String(getPin.getPassword()); //getPassword returns a char array
            String q1 = "SELECT * FROM Accounts WHERE CardNumber='"+CardNo+"' and PIN='"+PinNo+"'";
            
          try
            {
                ResultSet rs = c.s.executeQuery(q1);
                if (rs.next())
                {
                    setVisible(false);
                    new Menu(CardNo, PinNo).setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or Password");
                }

            }
            catch (SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Enter Valid Card Number and PIN");
                System.out.println(e);
            }
      }
      else if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            System.exit(0);
        }
  }
  
  public void keyTyped(KeyEvent ke)
    {
      System.out.println("keyTyped");
    }
    
    

    public static void main(String[] args) {
        
        new Login().setVisible(true);
    }

}
