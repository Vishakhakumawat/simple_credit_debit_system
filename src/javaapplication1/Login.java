/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VISHAKHA1999
 */
public class Login  {
 final private Font mainFont=new Font("Segoe print", Font.BOLD,28);
 final private Font mainFont1=new Font("Segoe print", Font.BOLD,16);
 /**
     * @param args the command line arguments
     */
     JLabel l1;
     JLabel l2;
     JLabel l3;
     JTextField t1;
     JPasswordField p1;
     JButton b;
     Connection con;
     double f;
    public Login()
    {
    
         JFrame frame=new JFrame("Login Form");
         l1=new JLabel("Member Login");
         l1.setBounds(270,100,300,80);
         l1.setFont(mainFont);
         l1.setForeground(new Color(135,206,235));
         l2=new JLabel("Enter user name/email:");
         l2.setBounds(80,200,200,60);
         l2.setFont(mainFont1);
         t1=new JTextField();
         t1.setBounds(310, 210, 400, 40);
         t1.setFont(mainFont1);
         l3=new JLabel("Enter password:");
         l3.setBounds(80,270,200,60);
         l3.setFont(mainFont1);
         p1=new JPasswordField();
         p1.setBounds(310, 280, 400, 40);
         p1.setFont(mainFont1);
         b=new JButton("Login");
         b.setBounds(270, 350, 100, 40);
         b.setFont(mainFont1);
         b.setBackground(new Color(135,206,235));
         b.setForeground(Color.white);
         frame.add(l1);
         frame.add(l2);
         frame.add(t1);
         frame.add(l3);
         frame.add(p1);
         frame.add(b);
         frame.setSize(800,800);
         frame.setLayout(null);
         frame.setVisible(true);
         frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
         b.addActionListener(new ActionListener()
        {
          @Override
          public void actionPerformed(ActionEvent ae)
         {
            String username=t1.getText();
            String pass=p1.getText();
             
            try{
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root", "vishakha");
            String sql="select balance from cus_info where email=? AND password=?";
            PreparedStatement preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,pass);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()==false){
               JOptionPane.showMessageDialog(frame,"Email or Password Invalid","Try again",JOptionPane.ERROR_MESSAGE);
                t1.setText("");
                p1.setText("");
                       }
            else{
                 f=resultSet.getDouble(1);
                 Home h=new Home();
                 h.initialize(con,f,Login.this);
                 h.setVisible(true);
            }
            }catch(SQLException e)
            { /*JOptionPane.showMessageDialog(frame,"Email or Password Invalid","Try again",JOptionPane.ERROR_MESSAGE);} catch (ClassNotFoundException ex) {
                  Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);*/
                System.out.println(e);
              }
         }
        });

    }
    
    public static void main(String[] args) {
        // TODO code application logic 
        Login l=new Login();
    }
    
}
