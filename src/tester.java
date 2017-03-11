import java.sql.*;

public class tester {

	public static void main(String[] args) {
		try{
			//jdbc:mysql://localhost:3306/Peoples?autoReconnect=true&useSSL=false
			Connection myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=false&useSSL=false", "root", "punkulam");
			Statement myStm = myconn.createStatement();
			myStm.executeUpdate("INSERT INTO useraccount VALUES('1', '1', '2', '2', '2')");
			ResultSet myRs = myStm.executeQuery("select userpassword from useraccount");
			while(myRs.next()){
				System.out.println(myRs.getString("userpassword"));
			}
			myconn.close();
			myStm.close();
			myRs.close();
			
			//myStm.
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}















