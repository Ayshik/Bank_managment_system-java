package BANK_SYSTEM;
import javax.swing.*;
import java.awt.event.*;

import java.sql.*;
public class Transfer extends JFrame implements ActionListener
{
	private JLabel numberLabel, amountLabel,NUMBERLABEL100;
	private JTextField numberTF, amountTF,NUMBERTF100;
	private JButton buttonBack, buttonLogout, buttonSubmit;
	private JPanel panel;
	
        private String userId1;
        private String userId;
         
        private String password1;
        private String password;
        
        private int accountNumber;
        private int accountNumber1;
        private String accountHolderName;
          private String accountHolderName1;
        private double balance;
           private double balance1;
	
	public Transfer (String userId)
	{
		super("Bank Management System - Transfer Window");
		
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		numberLabel = new JLabel("Transfer from Account Number : ");
		numberLabel.setBounds(100,100,150,30);
		panel.add(numberLabel);
		
		numberTF = new JTextField();
		numberTF.setBounds(260,100,100,30);
		panel.add(numberTF);
                
               NUMBERLABEL100 = new JLabel("Transfer to  Account Number : ");
	       NUMBERLABEL100.setBounds(100,150,150,30);
	       panel.add(NUMBERLABEL100);
		
		NUMBERTF100 = new JTextField();
		NUMBERTF100.setBounds(260,150,100,30);
		panel.add(NUMBERTF100);
		
		amountLabel = new JLabel("Enter Amount	: ");
		amountLabel.setBounds(100,200,150,30);
		panel.add(amountLabel);
		
		amountTF = new JTextField();
		amountTF.setBounds(260,200,100,30);
		panel.add(amountTF);
		
		buttonBack = new JButton("Back");
		buttonBack.setBounds(100, 300, 80, 30);
		buttonBack.addActionListener(this);
		panel.add(buttonBack);
		
		buttonSubmit = new JButton("Submit");
		buttonSubmit.setBounds(190, 300, 80, 30);
		buttonSubmit.addActionListener(this);
		panel.add(buttonSubmit);
		
		buttonLogout = new JButton("Logout");
		buttonLogout.setBounds(280, 300, 80, 30);
		buttonLogout.addActionListener(this);
		panel.add(buttonLogout);
		
		this.add(panel);
		this.userId=userId;
	}
        public void actionPerformed(ActionEvent ae)
	{
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(buttonBack.getText()))
		{
			AdminHome adh = new AdminHome(userId,password,accountNumber,accountHolderName,balance);
							this.setVisible(false);
							adh.setVisible(true);
		}
		else if(buttonClicked.equals(buttonSubmit.getText()))
		{
			deposit();
                        withdraw();
		}
		else if(buttonClicked.equals(buttonLogout.getText()))
		{
			Login l = new Login();
			l.setVisible(true);
			this.setVisible(false);
		}
	}
       
        public void deposit()
        {System.out.println(userId);
		double prevBalance=0, newBalance=0;
		String query = "SELECT `UserId`, `Password`,`Deposited`, `AccountNumber`, `AccountHolderName`, `Balance` FROM `users`;";     
                String query1 = "SELECT `UserId`, `Password`,`Deposited`, `AccountNumber`, `AccountHolderName`, `Balance` FROM `users`;";     
        Connection con=null;
        Statement st = null;
		ResultSet rs = null;
		System.out.println(query);
                System.out.println(query1);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clintinfo","root","");
			System.out.println("connection done");
			st = con.createStatement();
			System.out.println("statement created");
			rs = st.executeQuery(query);
			System.out.println("results received");
                        rs = st.executeQuery(query1);
                        System.out.println("results received");
                        
					
			while(rs.next())
			{
                String userId = rs.getString("UserID");
                String password = rs.getString("Password");
                int deposited = rs.getInt("Deposited");
				int accountNumber = rs.getInt("AccountNumber");
				String accountHolderName = rs.getString("AccountHolderName");
				double balance = rs.getDouble("Balance");

				
				if(accountNumber==Integer.parseInt(NUMBERTF100.getText().toString()))
				{
					prevBalance = balance;
					newBalance = prevBalance+Double.parseDouble(amountTF.getText());
					System.out.println(newBalance);
				}
			}
		}
		catch(Exception e){}
		try
		{
			System.out.println(".");
			query = "UPDATE users SET Balance="+newBalance+" where AccountNumber="+Integer.parseInt(NUMBERTF100.getText());
                         query1 = "UPDATE users SET Deposited="+amountTF.getText()+" where AccountNumber="+Integer.parseInt(NUMBERTF100.getText());
			st.executeUpdate(query);
                        st.executeUpdate(query1);
			st.close();
			con.close();
			rs.close();
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}
         public void withdraw()
        {
	System.out.println(userId);
		double prevBalance=0, newBalance=0;
		String query = "SELECT `UserId`, `Password`,`Withdrawn`, `AccountNumber`, `AccountHolderName`, `Balance` FROM `users`;";   
                String query1 = "SELECT `UserId`, `Password`,`Withdrawn`, `AccountNumber`, `AccountHolderName`, `Balance` FROM `users`;";     
        Connection con=null;
        Statement st = null;
		ResultSet rs = null;
		System.out.println(query);
                System.out.println(query1);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clintinfo","root","");
			System.out.println("connection done");
			st = con.createStatement();
			System.out.println("statement created");
			rs = st.executeQuery(query);
			System.out.println("results received");
                        rs = st.executeQuery(query1);
                        System.out.println("results received");
					
			while(rs.next())
			{
                String userId = rs.getString("UserID");
                String password = rs.getString("Password");
                int withdrawn = rs.getInt("Withdrawn");
				int accountNumber = rs.getInt("AccountNumber");
				String accountHolderName = rs.getString("AccountHolderName");
				double balance = rs.getDouble("Balance");
				
				if(accountNumber==Integer.parseInt(numberTF.getText().toString()))
				{
					prevBalance = balance;
					newBalance = prevBalance-Double.parseDouble(amountTF.getText());
					System.out.println(newBalance);
				}
			}
		}
		catch(Exception e){}
		try
		{
			System.out.println(".");
			query = "UPDATE users SET Balance="+newBalance+" where AccountNumber="+Integer.parseInt(numberTF.getText());
                        query1 = "UPDATE users SET Withdrawn="+amountTF.getText()+" where AccountNumber="+Integer.parseInt(numberTF.getText());
			st.executeUpdate(query);
                        st.executeUpdate(query1);
			st.close();
			con.close();
			rs.close();
		}
		catch(Exception e){System.out.println(e.getMessage());}
	
	}
}
