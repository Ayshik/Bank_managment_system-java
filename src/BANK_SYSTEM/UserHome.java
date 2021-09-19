
package BANK_SYSTEM;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;


class UserHome extends JFrame implements ActionListener
{

    private  JLabel labelWelcome,labelName,labelNumber,labelBalance,labelWithdraw,labelDeposit;
    	private  JButton buttonLogout,buttonTransaction,buttonChangePassword;
       
	private  JPanel panel;
	
	public UserHome(String userId,String password, int accountNumber,String accountHolderName, double balance,int withdrawn,int deposited)
	{
		super("Bank Management System - User Home Window");
		
		this.setSize(600, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		labelWelcome = new JLabel("Welcome to Aiub Bank");
		labelWelcome.setBounds(200, 50, 200, 30);
		panel.add(labelWelcome);
		
		labelName = new JLabel("Accout Holder Name	:	"+accountHolderName);
		labelName.setBounds(200, 100, 200, 30);
		panel.add(labelName);
		
		labelNumber = new JLabel("Accout Number	:	"+accountNumber);
		labelNumber.setBounds(200, 150, 200, 30);
		panel.add(labelNumber);
		
		labelBalance = new JLabel("Current Balance	:	"+balance);
		labelBalance.setBounds(200, 200, 200, 30);
		panel.add(labelBalance);
                
                 labelWithdraw = new JLabel("Recently withdrawn	:	"+withdrawn);
		labelWithdraw.setBounds(200, 250, 200, 30);
		panel.add(labelWithdraw);
                
                labelDeposit = new JLabel("Recently deposited	:	"+deposited);
		labelDeposit.setBounds(200, 300, 200, 30);
		panel.add(labelDeposit);
		
		
		
		
		buttonLogout = new JButton("Logout");
		buttonLogout.setBounds(320, 400, 150, 30);
		buttonLogout.addActionListener(this);
		panel.add(buttonLogout);
                 
               
                
		
		
		this.add(panel);
               
		
	}
	public void actionPerformed(ActionEvent ae){
        String buttonClicked = ae.getActionCommand();
		
		
	
		if(buttonClicked.equals(buttonLogout.getText()))
		{
			Login l = new Login();
			l.setVisible(true);
			this.setVisible(false);
		}
               }
}