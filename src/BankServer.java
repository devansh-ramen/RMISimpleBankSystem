import java.rmi.Naming;





public class BankServer {
	public static void main(String [] args){
		//security manager
		try{
			BankInterface h = new BankAccount();
			Naming.rebind("rmi://localhost:2008/BankService", h);
			
			System.out.println("Server is connected and ready for operation");
		}catch(Exception e){
			System.out.println("Error: "+ e);
			
		}
	}
}
