package BANK_SYSTEM;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class ChangePassword extends JFrame implements ActionListener
{
	private JLabel numberLabel,passwordLabel;
	private JTextField numberTF;
	private JButton buttonBack, buttonLogout, buttonUpdate;
        private JPasswordField passwordPF;
	private JPanel panel;
        private String userId;
        private String password;
        private int accountNumber,withdrawn,deposited;
        private String accountHolderName;
        private double balance;

	public ChangePassword()
	{
		super("Bank Management System - change password");
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
                
                passwordLabel = new JLabel("Enter New Password : ");
		passwordLabel.setBounds(100, 250, 150, 30);
		panel.add(passwordLabel);
		
		passwordPF =new JPasswordField();
		passwordPF.setBounds(260, 250, 100, 30);
		panel.add(passwordPF);
		
		buttonBack = new JButton("Back");
		buttonBack.setBounds(100, 300, 80, 30);
		buttonBack.addActionListener(this);
		panel.add(buttonBack);
		
		buttonUpdate = new JButton("Delete");
		buttonUpdate.setBounds(190, 300, 80, 30);
		buttonUpdate.addActionListener(this);
		panel.add(buttonUpdate);
		
		buttonLogout = new JButton("Logout");
		buttonLogout.setBounds(280, 300, 80, 30);
		buttonLogout.addActionListener(this);
		panel.add(buttonLogout);
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(buttonBack.getText()))
		{
			UserHome ush = new UserHome(userId,password,accountNumber,accountHolderName,balance,withdrawn,deposited);
							this.setVisible(false);
							ush.setVisible(true);
		}
		else if(buttonClicked.equals(buttonUpdate.getText()))
		{
			insertIntoDB();
		}
		else if(buttonClicked.equals(buttonLogout.getText()))
		{
			Login l = new Login();
			l.setVisible(true);
			this.setVisible(false);
		}
	}
	
	public void insertIntoDB()
	{
		String query = "UPDATE users set Password="+passwordPF.getText()+" where AccountNumber="+numberTF.getText()+";";
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clintinfo", "root", "");
			Statement stm = con.createStatement();
			stm.execute(query);
			stm.close();
			con.close();
                         JOptionPane.showMessageDialog(this,"PASSWORD CHANGED SUCCESSFULLY!!");
					
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
    }
}
