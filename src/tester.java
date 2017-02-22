import java.util.GregorianCalendar;

public class tester {

	public static void main(String[] args) {
		UserAccount test1 = new UserAccount("Fubcity","password","FubCity@fub.com"); //username, password, email
		Appointment school = new Appointment(new GregorianCalendar(2001, 1, 1), "6:00", "8:00", "School"); //date, start, end, title

	test1.addAppointment(school);
	
	}

}
