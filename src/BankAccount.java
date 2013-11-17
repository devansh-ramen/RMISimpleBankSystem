
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;





public class BankAccount  extends UnicastRemoteObject implements BankInterface{
	private String Name;
	private int Number;
	private double Balance;
	public static Connection con = DbConnection.getConnection();
	
	public BankAccount(String Name, int Number, double Balance) throws RemoteException{
		this.Name = Name;
		this.Number = Number;
		this.Balance = Balance;
	}
	public BankAccount() throws RemoteException{
		
	}

	public double getBalance(int accountNumber){
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT Balance FROM RMIBankSystem.Account WHERE (RMIBankSystem.Account.Number=" + accountNumber+")");
	        if(rs != null){	        	
	        	 while(rs.next()){
	        		 double Balance = rs.getDouble("Balance");
	        		 return Balance;
	        	 }
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exp"+ e);
		}
		 return Balance;
	}
	public String getName(int accountNumber){
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT Name FROM RMIBankSystem.Account WHERE (RMIBankSystem.Account.Number=" + accountNumber+")");
	        if(rs != null){	        	
	        	 while(rs.next()){
	        		 String name = rs.getString("Name");
	        		 return name;
	        	 }
	        }
		} catch (SQLException e) {
			System.out.println("exp"+ e);
		}
		 return null;
	}
	public int getNumber(int accountNumber){
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT Number FROM RMIBankSystem.Account WHERE (RMIBankSystem.Account.Number=" + accountNumber+")");
	        if(rs != null){	        	
	        	 while(rs.next()){
	        		 int number = rs.getInt("Number");
	        		 return number;
	        	 }
	        }
		} catch (SQLException e) {
			System.out.println("exp"+ e);
		}
		 return (Integer) null;
	}
	public void deposit(int accountNumber, double amount){
		try {
		    if(amount < 0){
		        throw new IllegalArgumentException("Deposit amount can not be less than zero");
		    }
		 
			double balance = getBalance(accountNumber);
			double newBalance = balance + amount;
			
			Statement st = con.createStatement();
			st.execute("UPDATE RMIBankSystem.Account SET Balance = "+ newBalance+" WHERE (RMIBankSystem.Account.Number=" + accountNumber+")");
		} catch (SQLException e) {
			System.out.println("exp"+ e);
		}
		

	}
	public void withdraw(int accountNumber, double amount){
		try {
			Statement st = con.createStatement();
			double balance = getBalance(accountNumber);
			double newBalance = balance - amount;
			
		    if(newBalance < 0){
		        throw new IllegalArgumentException("Deposit amount can not be less than zero");
		    }else{
		    	
		    	st.execute("UPDATE RMIBankSystem.Account SET Balance = "+ newBalance+" WHERE (RMIBankSystem.Account.Number=" + accountNumber+")");
		    }
		    
	    }catch (SQLException e) {
			System.out.println("exp"+ e);
		}
	}	
}
