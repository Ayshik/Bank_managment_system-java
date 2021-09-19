package BANK_SYSTEM;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class AdminHome extends JFrame implements ActionListener
{
	private JLabel labelWelcome;
	private JButton createNewButton, deleteButton, DepositButton, withdrawButton, transferButton, buttonLogout, buttonTransaction,buttonChangePassword;
	private JPanel panel;
	private String userId;
        
	
	public AdminHome(String userId,String password, int accountNumber,String accountHolderName, double balance)
	{
		super("Sample Management System - Admin Home Window");
		
		this.setSize(600, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		labelWelcome = new JLabel("Logged In: "+userId);
		labelWelcome.setBounds(230, 50, 200, 30);
		panel.add(labelWelcome);
		
		createNewButton = new JButton("Create New Account");
		createNewButton.setBounds(120, 100, 150, 30);
		createNewButton.addActionListener(this);
		panel.add(createNewButton);
		
		deleteButton = new JButton("Delete Account");
		deleteButton.setBounds(300, 100, 150, 30);
		deleteButton.addActionListener(this);
		panel.add(deleteButton);
		
		DepositButton = new JButton("Deposit");
		DepositButton.setBounds(120, 150, 150, 30);
		DepositButton.addActionListener(this);
		panel.add(DepositButton);
		
		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(300, 150, 150, 30);
		withdrawButton.addActionListener(this);
		panel.add(withdrawButton);
		
		transferButton = new JButton("Transfer");
		transferButton.setBounds(120, 200, 150, 30);
		transferButton.addActionListener(this);
		panel.add(transferButton);
                
                buttonChangePassword = new JButton("Change Password");
		buttonChangePassword.setBounds(300, 200, 150, 30);
		buttonChangePassword.addActionListener(this);
		panel.add(buttonChangePassword);
		
		buttonLogout = new JButton("Logout");
		buttonLogout.setBounds(300, 250, 150, 30);
		buttonLogout.addActionListener(this);
                panel.add(buttonLogout); 
                
                
                
		
		
		this.add(panel);
		this.userId=userId;
		
	}

    AdminHome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	public void actionPerformed(ActionEvent ae)
	{
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(createNewButton.getText()))
		{
			CreateNewAccount cna = new CreateNewAccount(userId);
			cna.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(deleteButton.getText()))
		{
			DeleteAccount del = new DeleteAccount(userId);
			del.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(DepositButton.getText()))
		{
			Deposit dep = new Deposit(userId);
			dep.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(withdrawButton.getText()))
		{
			Withdraw wi = new Withdraw(userId);
			wi.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(transferButton.getText()))
		{
			Transfer tra = new Transfer(userId);
                        tra.setVisible(true);
			this.setVisible(false);
                        
		}
		else if(buttonClicked.equals(buttonLogout.getText()))
		{
			Login l = new Login();
			l.setVisible(true);
			this.setVisible(false);
		}
                else if(buttonClicked.equals(buttonChangePassword.getText()))
		{
			ChangePassword l = new ChangePassword();
			l.setVisible(true);
			this.setVisible(false);
		}
               

	}
}