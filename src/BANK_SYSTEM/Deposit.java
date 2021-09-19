
package BANK_SYSTEM;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Deposit extends JFrame implements ActionListener
{
	private JLabel numberLabel, amountLabel;
	private JTextField numberTF, amountTF;
	private JButton buttonBack, buttonLogout, buttonSubmit;
	private JPanel panel;
	private String userId;
        private String password;
        private int accountNumber;
        private String accountHolderName;
        private double balance;
	public int deposited;
        
	public Deposit(String userId)
	{
		super("Bank Management System - Deposit Window");
		
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		numberLabel = new JLabel("Enter Account Number : ");
		numberLabel.setBounds(100,100,150,30);
		panel.add(numberLabel);
		
		numberTF = new JTextField();
		numberTF.setBounds(260,100,100,30);
		panel.add(numberTF);
		
		amountLabel = new JLabel("Enter Amount	: ");
		amountLabel.setBounds(100,150,150,30);
		panel.add(amountLabel);
		
		amountTF = new JTextField();
		amountTF.setBounds(260,150,100,30);
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
			updateInDB();
		}
		else if(buttonClicked.equals(buttonLogout.getText()))
		{
			Login l = new Login();
			l.setVisible(true);
			this.setVisible(false);
		}
	}
	public void updateInDB()
	{
		System.out.println(userId);
		double prevBalance=0,newBalance=0;
		String query = "SELECT `UserId`, `Password`,`Deposited`, `AccountNumber`, `AccountHolderName`, `Balance` FROM `users`;";     
                String query1 = "SELECT `UserId`, `Password`,`Deposited`, `AccountNumber`, `AccountHolderName`, `Balance` FROM `users`;";  
                String query2 = "SELECT  `AccountNumber`,`Deposited`,`Withdrawn` FROM `transaction`;"; 
        Connection con=null;
        Statement st = null;
		ResultSet rs = null;
		System.out.println(query);
                System.out.println(query1);
                System.out.println(query2);
        try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
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

				
				if(accountNumber==Integer.parseInt(numberTF.getText().toString()))
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
			query = "UPDATE users SET Balance="+newBalance+" where AccountNumber="+Integer.parseInt(numberTF.getText());
                        query1 = "UPDATE users SET Deposited ="+amountTF.getText()+" where AccountNumber="+Integer.parseInt(numberTF.getText());
                        query2=  "INSERT INTO transaction VALUES ('"+numberTF.getText()+"','"+amountTF.getText()+"','0')";
			st.executeUpdate(query);
                        st.executeUpdate(query1);
                        st.executeUpdate(query2);
			st.close();
			con.close();
			rs.close();
                        JOptionPane.showMessageDialog(this,"DIPOSITED SUCCESSFULLY!!"); 
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}
}


