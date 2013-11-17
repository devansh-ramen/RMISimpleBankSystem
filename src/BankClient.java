import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class BankClient extends JFrame implements ActionListener {
	
	public static JButton btnLogin = new JButton("Login In");
	private JButton btnGetName = new JButton("Get Name");
	private JButton btnGetBalance = new JButton("Get Balance");
	private JButton btnDiposit = new JButton("Diposit");
	private JButton btnWithdraw = new JButton("Withdraw");
	private JButton btngetAccountDetails = new JButton("Get Account Details");
	
	private JPanel mainPanel = new JPanel();
	public static JPanel accountPanel = new JPanel();
	private JPanel loginPanel = new JPanel();
	public static int NUMBER;
	public static BankInterface ACCOUNT;
	
	public static void main (String [] args){

		new BankClient();
		//securitymanager
		try{
			
			ACCOUNT = (BankInterface) Naming.lookup("rmi://localhost:2008/BankService");
			System.out.println("Client ready");

		
		}catch(Exception e){
			System.out.println("BankClient exception: "+e);
		}
	}
	
	  public void actionPerformed(ActionEvent e) {
		  if (e.getSource() == btnLogin){
			  NUMBER = Integer.parseInt(JOptionPane.showInputDialog("Enter your account number : "));
			  System.out.println(NUMBER);
			  loginPanel.setVisible(false);
			  accountPanel.setVisible(true);
			//  doOperation("login");
		  }
		   if (e.getSource() == btnGetName){
			   try {
				   JOptionPane.showMessageDialog(  
				             null, "Your name is "+ ACCOUNT.getName(NUMBER) );
				
			   }catch (HeadlessException | RemoteException e1) {
						e1.printStackTrace();
			   }  
		   }
		   
		   if (e.getSource() == btnGetBalance){
			   try {
				   JOptionPane.showMessageDialog(  
				             null, "Your Balance is "+ ACCOUNT.getBalance(NUMBER) );
				
			   }catch (HeadlessException | RemoteException e1) {
						e1.printStackTrace();
			   }  
		   }
		   if (e.getSource() == btnDiposit){
			   int amount = Integer.parseInt(JOptionPane.showInputDialog("Enter amount to disposit : "));
			
			   try {
				   ACCOUNT.deposit(NUMBER,amount);
				   JOptionPane.showMessageDialog(  
				             null, "Your new Balance is "+ ACCOUNT.getBalance(NUMBER));
				
				} catch (RemoteException e2) {
					e2.printStackTrace();
				}
 
		   }
		   if (e.getSource() == btnWithdraw){
			   int amount = Integer.parseInt(JOptionPane.showInputDialog("Enter amount to withdraw : "));
			   try {
				   ACCOUNT.withdraw(NUMBER, amount);
				   JOptionPane.showMessageDialog(  
				             null, "Your new Balance is "+ ACCOUNT.getBalance(NUMBER));
				
				} catch (RemoteException e2) {
					e2.printStackTrace();
				}
		   }
		   if (e.getSource() == btngetAccountDetails){
			   try {
				   JOptionPane.showMessageDialog(  
				             null, "Name is "+ ACCOUNT.getName(NUMBER) +
				             "; Balance is "+ ACCOUNT.getBalance(NUMBER) +
				              "; Your Account Number is "+ ACCOUNT.getNumber(NUMBER));
				
				} catch (RemoteException e2) {
					e2.printStackTrace();
		
				}

		   }
	  }
	  
	  public BankClient(){
		  	accountPanel.add(btnGetName);
		  	accountPanel.add(btnGetBalance);
		  	accountPanel.add(btnDiposit);
		  	accountPanel.add(btnWithdraw);
		  	accountPanel.add(btngetAccountDetails);
		  	accountPanel.setVisible(false);
		  	//groupPanel.setVisible(false);
		  	
		  	loginPanel.add(btnLogin);
		  	
		  	btnLogin.addActionListener(this);
		  	btnGetName.addActionListener(this);
		  	btnGetBalance.addActionListener(this);
		  	btnDiposit.addActionListener(this);
		  	btnWithdraw.addActionListener(this);
		  	btngetAccountDetails.addActionListener(this);
		  	
		  	mainPanel.add(loginPanel); 
		    mainPanel.add(accountPanel); 
		    
		    add(mainPanel);
		 
		    setTitle("Welcome to our Banking System" );
		    setSize(700, 200);
		    setVisible(true);
	
	  }
}
