import java.util.Calendar;

public class Appointment {

	private Calendar date;
	private String startTime;
	private String endTime;
	private String title;
	private String description;
	
	public Appointment(){
		date = null;
		startTime = null;
		endTime = null;
		title = null;
		description = null;
	}
	
	public Appointment(Calendar date, String startTime, String endTime, String title){
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title;
	}
	
	public Appointment(Calendar date, String startTime, String endTime, String title, String description){
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title;
		this.description = description;
	}
	
	public void editDate(Calendar newDate){
		date = newDate;
	}
	
	public void editTitle(String newTitle){
		title = newTitle;
	}
	
	public void editStart(String newStart){
		startTime = newStart;
	}
	
	public void editEnd(String newEnd){
		endTime = newEnd;
	}
	
	public void editDescription(String newDescription){
		description = newDescription;
	}
	
	public Calendar getDate(){
		return this.date;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getStart(){
		return this.startTime;
	}
	
	public String getEnd(){
		return this.endTime;
	}
	
	public String getDescription(){
		return this.description;
	}
	
}
