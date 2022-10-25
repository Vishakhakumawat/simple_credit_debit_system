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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

/**
 *
 * @author VISHAKHA1999
 */
public class AddBalance extends JFrame{
    final private Font mainFont1=new Font("Segoe print", Font.BOLD,16);
    public void initialise1(Connection c,Login l){
        JFrame f1=new JFrame();
        JLabel label=new JLabel("Enter the amount:");
        label.setFont(mainFont1);
        label.setBounds(80,80,200,50);
        JTextField t=new JTextField();
        t.setFont(mainFont1);
        t.setBounds(300,90,250,40);
        JLabel label1=new JLabel("Enter the account number:");
        label1.setFont(mainFont1);
        label1.setBounds(80,150,250,50);
        JTextField t1=new JTextField();
        t1.setFont(mainFont1);
        t1.setBounds(300,160,200,40);
        JButton credit=new JButton("ADD/CREDIT");
        credit.setBounds(200,220 , 200, 40);
        credit.setFont(mainFont1);
        credit.setBackground(new Color(135,206,235));
        credit.setForeground(Color.white);
        f1.add(label);
        f1.add(t);
        f1.add(label1);
        f1.add(t1);
        f1.add(credit);
        f1.setSize(600,600);
        f1.setLayout(null);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        credit.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
        //To change body of generated methods, choose Tools | Templates.
            if(c==null)
            { JOptionPane.showMessageDialog(f1,"Connection Out!!","Try again",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                String bal1=t.getText();
                String acc=t1.getText();
                int account;
                account = Integer.parseInt(acc);
                String email=l.t1.getText();
                double fbal=Double.parseDouble(bal1);
                double newbal=fbal+l.f;
                
                try
                {
                    PreparedStatement prepare=c.prepareStatement("update cus_info set balance=? where email=? AND account_no=?");
                    prepare.setDouble(1,newbal);
                    prepare.setString(2,email);
                    prepare.setInt(3,account);
                    int n=prepare.executeUpdate();
                    if(n==0)
                    {
                        JOptionPane.showMessageDialog(f1,"Balance is not updated","Try again",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        
                        String msg="Rs "+fbal+" is credited to your account successfully"+"\n"+"Your current balance is: "+newbal ;
                        JOptionPane.showMessageDialog(f1,msg);
                        c.close();
                    }
                }
                catch(SQLException ex)
                {
                    System.out.println(ex);
                }
            }
            }
         
        });
    }
    
}
