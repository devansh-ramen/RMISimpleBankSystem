//import java.sql.Connection;



//SQL
import java.sql.Connection;
import java.sql.DriverManager;


public class DbConnection { 
	
	private static Connection con;

	public static Connection getConnection() {
		try{	//try the connection
			Class.forName("com.mysql.jdbc.Driver"); //for Devansh
			
			try{	//for devansh
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=1765");
			}catch (Exception ex){ 	
				System.out.println("Connection localhost problem: " + ex.getMessage());
			}
	
			
		}catch (Exception ex){ //Could not connect exception
	      System.out.println(ex.getMessage() );
		}//END of try
		
		return con;		
	}
}
	