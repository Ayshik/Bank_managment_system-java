
package BANK_SYSTEM;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class CreateNewAccount extends JFrame implements ActionListener
{
	private JLabel nameLabel, numberLabel, initialBalanceLabel, idLabel, passwordLabel;
	private JTextField nameTF, numberTF, initialBalanceTF, idTF;
	private JPasswordField passwordPF;
	private JButton buttonBack, buttonLogout, buttonInsert;
	private JPanel panel;
         private String userId;
        private String password;
        private int accountNumber;
        private String accountHolderName;
        private double balance;
	public CreateNewAccount(String msg)
	{
		super("BANK Management System - Create New Account Window");
		
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		nameLabel = new JLabel("Enter Name : ");
		nameLabel.setBounds(100,50,150,30);
		panel.add(nameLabel);
		
		nameTF = new JTextField();
		nameTF.setBounds(260,50,100,30);
		panel.add(nameTF);
		
		numberLabel = new JLabel("Enter Account Number : ");
		numberLabel.setBounds(100,100,150,30);
		panel.add(numberLabel);
		
		numberTF = new JTextField();
		numberTF.setBounds(260,100,100,30);
		panel.add(numberTF);
		
		initialBalanceLabel = new JLabel("Enter Initial Balance : ");
		initialBalanceLabel.setBounds(100,150,150,30);
		panel.add(initialBalanceLabel);
		
		initialBalanceTF = new JTextField();
		initialBalanceTF.setBounds(260,150,100,30);
		panel.add(initialBalanceTF);
		
		idLabel = new JLabel("Enter User ID : ");
		idLabel.setBounds(100,200,150,30);
		panel.add(idLabel);
		
		idTF = new JTextField();
		idTF.setBounds(260,200,100,30);
		panel.add(idTF);
		
		passwordLabel = new JLabel("Enter Password : ");
		passwordLabel.setBounds(100, 250, 150, 30);
		panel.add(passwordLabel);
		
		passwordPF =new JPasswordField();
		passwordPF.setBounds(260, 250, 100, 30);
		panel.add(passwordPF);
		
		buttonBack = new JButton("Back");
		buttonBack.setBounds(100, 300, 80, 30);
		buttonBack.addActionListener(this);
		panel.add(buttonBack);
		
		buttonInsert = new JButton("Insert");
		buttonInsert.setBounds(190, 300, 80, 30);
		buttonInsert.addActionListener(this);
		panel.add(buttonInsert);
		
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
		AdminHome adh = new AdminHome(userId,password,accountNumber,accountHolderName,balance);
							this.setVisible(false);
							adh.setVisible(true);
		}
		else if(buttonClicked.equals(buttonInsert.getText()))
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
		String query = "INSERT INTO users VALUES ('"+nameTF.getText()+"','"+idTF.getText()+"','"+numberTF.getText()+"','"+passwordPF.getText()+"','"+initialBalanceTF.getText()+"','0','0')";
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clintinfo", "root", "");
			Statement stm = con.createStatement();
			stm.execute(query);
			stm.close();
			con.close();
                        JOptionPane.showMessageDialog(this,"CONGATULATIONS YOUR ACCOUNT HAS BEEN CREATED!!"); 
					
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
    }
}



