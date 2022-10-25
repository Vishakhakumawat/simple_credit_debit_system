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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author VISHAKHA1999
 */
class Home extends JFrame {
    final private Font mainFont=new Font("Segoe print", Font.BOLD,28);
    final private Font mainFont1=new Font("Segoe print", Font.BOLD,16);

  
    public void initialize(Connection conn,double bal,Login l)
    {
        JFrame f=new JFrame("Home Page");
        JLabel l1=new JLabel("Welcome!!");
        l1.setBounds(100,100,200,100);
        l1.setForeground(new Color(135,206,235));
        l1.setFont(mainFont);
        JLabel l2=new JLabel("Your current balance is :"+bal);
        l2.setBounds(100,210,350,40);
        l2.setFont(mainFont1);
        JButton add=new JButton("Add Money");
        add.setFont(mainFont1);
        add.setBackground(new Color(135,206,235));
        add.setForeground(Color.white);
        add.setBounds(100,270,200,40);
        JButton withdraw=new JButton("Withdraw Money");
        withdraw.setFont(mainFont1);
        withdraw.setBackground(new Color(135,206,235));
        withdraw.setForeground(Color.white);
        withdraw.setBounds(310,270,200,40);
        f.add(l1);
        f.add(l2);
        f.add(add);
        f.add(withdraw);
        f.setSize(600,600);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
          //To change body of generated methods, choose Tools | Templates.
            if(conn==null)
            {
                JOptionPane.showMessageDialog(f,"Connection Out!!","Try again",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
               AddBalance ab=new AddBalance();
               ab.initialise1(conn, l);
               ab.setVisible(true);
            }
            }
        });
        withdraw.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
          //To change body of generated methods, choose Tools | Templates.
            if(conn==null)
            {
                JOptionPane.showMessageDialog(f,"Connection Out!!","Try again",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
               WithdrawBalance wb=new WithdrawBalance();
               wb.initialise1(conn, l);
               wb.setVisible(true);
            }
            }
        });
    }
    
}
