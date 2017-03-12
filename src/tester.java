import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class tester {

	public static void main(String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		
		
		
		
//		try{
//			//jdbc:mysql://localhost:3306/Peoples?autoReconnect=true&useSSL=false
//			Connection myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=false&useSSL=false", "root", "punkulam");
//			Statement myStm = myconn.createStatement();
//			myStm.executeUpdate("INSERT INTO useraccount VALUES('1', '1', '2', '2', '2')");
//			ResultSet myRs = myStm.executeQuery("select userpassword from useraccount");
//			while(myRs.next()){
//				System.out.println(myRs.getString("userpassword"));
//			}
//			myconn.close();
//			myStm.close();
//			myRs.close();
//			
//			//myStm.
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
	}
}















