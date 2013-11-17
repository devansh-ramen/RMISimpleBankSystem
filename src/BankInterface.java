import java.rmi.Remote;
import java.rmi.RemoteException;


public interface BankInterface extends Remote {
		public double getBalance(int accountNumber) throws RemoteException;
		public String getName(int accountNumber) throws RemoteException;
		public int getNumber(int accountNumber) throws RemoteException;
		public void deposit(int accountNumber, double amount) throws RemoteException;
		public void withdraw(int accountNumber, double amount) throws RemoteException;
		
}
