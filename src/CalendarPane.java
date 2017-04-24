import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarPane extends JPanel {

	private static final long serialVersionUID = 1L;
	static Connection myconn;
	protected int month;
	protected int year;
	protected int ID; 				
	protected String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };

	protected String[] dayNames = { "S", "M", "T", "W", "T", "F", "S" };

	public CalendarPane(int month, int year, Integer ID) {
		try{
		myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=false&useSSL=false", "root", "punkulam");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		this.month = month;
		this.year = year;
		this.ID = ID; 								
		this.add(createGUI());
	}
	
	public int getMonth(){
		return this.month;
	}
	
	public int getYear(){
		return this.year;
	}

	protected JPanel createGUI() {
		JPanel monthPanel = new JPanel(true);
		monthPanel.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption));
		monthPanel.setLayout(new BorderLayout());
		monthPanel.setBackground(Color.WHITE);
		monthPanel.setForeground(Color.BLACK);
		monthPanel.add(createTitleGUI(), BorderLayout.NORTH);
		monthPanel.add(createDaysGUI(ID), BorderLayout.SOUTH); // change here

		return monthPanel;
	}

	protected JPanel createTitleGUI() {
		JPanel titlePanel = new JPanel(true);
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setBackground(Color.WHITE);

		JLabel label = new JLabel(monthNames[month] + " " + year);
		label.setForeground(SystemColor.black);

		titlePanel.add(label, BorderLayout.CENTER);

		return titlePanel;
	}
	
	protected JPanel createDaysGUI(int ID) {
		JPanel dayPanel = new JPanel(true);
		dayPanel.setLayout(new GridLayout(0, dayNames.length));

		Calendar today = Calendar.getInstance();
		int tMonth = today.get(Calendar.MONTH);
		int tYear = today.get(Calendar.YEAR);
		int tDay = today.get(Calendar.DAY_OF_MONTH);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		Calendar iterator = (Calendar) calendar.clone();
		iterator.add(Calendar.DAY_OF_MONTH, -(iterator.get(Calendar.DAY_OF_WEEK) - 1));

		Calendar maximum = (Calendar) calendar.clone();
		maximum.add(Calendar.MONTH, +1);
		
		try{
			String getAppDates = "SELECT AppDate FROM appointment WHERE userID = ?";
			PreparedStatement GAD = myconn.prepareStatement(getAppDates);
			String Identification = ""+ID;
			GAD.setString(1, Identification);
			ResultSet AppDate = GAD.executeQuery();
			
		for (int i = 0; i < dayNames.length; i++) {
			JPanel dPanel = new JPanel(true);
			dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JLabel dLabel = new JLabel(dayNames[i]);
			dPanel.add(dLabel);
			dayPanel.add(dPanel);
		}

		int count = 0;
		int limit = dayNames.length * 6;

		while (iterator.getTimeInMillis() < maximum.getTimeInMillis()) {
			int lMonth = iterator.get(Calendar.MONTH);
			int lYear = iterator.get(Calendar.YEAR);

			JPanel dPanel = new JPanel(true);
			dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JLabel dayLabel = new JLabel();

			boolean test = false;
			while(AppDate.next()){
				String App = AppDate.getString(1);
				String AppSplit[] = App.split("-");
				String format[] = AppSplit[1].split("0");
				AppSplit[1] = format[1];
				if(Integer.parseInt(AppSplit[0]) == year && (Integer.parseInt(AppSplit[1]))-1 == month && Integer.parseInt(AppSplit[2]) == iterator.get(Calendar.DAY_OF_MONTH)){
					dPanel.setBackground(Color.CYAN);
					test = true;
				}
			}
			AppDate.beforeFirst();
			
			if ((lMonth == month) && (lYear == year)) {
				int lDay = iterator.get(Calendar.DAY_OF_MONTH);
				dayLabel.setText(Integer.toString(lDay));
				if ((tMonth == month) && (tYear == year) && (tDay == lDay)) {
					dPanel.setBackground(Color.YELLOW);
				} 
				else if(test == false){
					dPanel.setBackground(Color.WHITE);
				}
			} else {
				dayLabel.setText(" ");
				dPanel.setBackground(Color.WHITE);
			}
			dPanel.add(dayLabel);
			dayPanel.add(dPanel);
			iterator.add(Calendar.DAY_OF_YEAR, +1);
			count++;
		}

		for (int i = count; i < limit; i++) {
			JPanel dPanel = new JPanel(true);
			dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JLabel dayLabel = new JLabel();
			dayLabel.setText(" ");
			dPanel.setBackground(Color.WHITE);
			dPanel.add(dayLabel);
			dayPanel.add(dPanel);
		}
		
		AppDate.close();
		GAD.close();
		
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return dayPanel;
	}
}
