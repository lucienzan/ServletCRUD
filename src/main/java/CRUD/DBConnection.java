package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	static Connection con;
	
	public static Connection GetDBConnection() {
		 try{
		        Class.forName("com.mysql.cj.jdbc.Driver");
				final String url = "jdbc:mysql://localhost:3306/user?useSSL=false";
			    final String uname = "root";
			    final String pass = "root";   
			    con=DriverManager.getConnection(url,uname,pass);
		    }catch (Exception e){
		        e.printStackTrace();
		    }
		 
	        return con;
	}
}
