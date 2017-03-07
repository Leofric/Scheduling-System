import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

public class UserAccount {
private String firstname;
private String lastname;
private String username;
private String password; //encrypt?
private String emailAdress;
private Map<Calendar, Appointment> appointments;

public UserAccount(){
	firstname = null;
	lastname = null;
	username = null;
	password = null;
	emailAdress = null;
	appointments = null;
}

public UserAccount(String firstname, String lastname, String username, String password, String emailAdress){
	this.firstname = firstname;
	this.lastname = lastname;
	this.username = username;
	this.password = password;
	this.emailAdress = emailAdress;
	appointments = new TreeMap<Calendar, Appointment>();
}

public void changePassword(String newPassword){
	this.password = newPassword;
}

public void changeEmail(String newEmail){
	this.emailAdress = newEmail;
}

public void addAppointment(Appointment newAppointment){
	appointments.put(newAppointment.getDate(), newAppointment);
}

}
