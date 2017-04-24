import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.*;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

public class Frame {

	private JFrame Frame;
	private JPasswordField passwordField;
	private JTextField usernameField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	static Connection myconn;
	private JTextField textField;
	private JTextField textField_6;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;

	private Integer ID = 0;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myconn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/test?autoReconnect=false&useSSL=false", "root", "punkulam");
					Frame window = new Frame();
					window.Frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Frame = new JFrame();
		Frame.setBounds(450, 150, 500, 350);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.getContentPane().setLayout(null);

		JPanel ParentFrame = new JPanel();
		ParentFrame.setBounds(0, 0, 494, 322);
		Frame.getContentPane().add(ParentFrame);
		ParentFrame.setLayout(new CardLayout(0, 0));
		CardLayout cardLayout = (CardLayout) ParentFrame.getLayout();
		cardLayout.show(ParentFrame, "login");

		JPanel login = new JPanel();
		ParentFrame.add(login, "login");
		GridBagLayout gbl_login = new GridBagLayout();
		gbl_login.columnWidths = new int[] {100, 100, 100, 150};
		gbl_login.rowHeights = new int[] { 50, 50, 50, 50, 50, 50, 0 };
		gbl_login.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_login.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		login.setLayout(gbl_login);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.fill = GridBagConstraints.VERTICAL;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 2;
		login.add(lblUsername, gbc_lblUsername);

		usernameField = new JTextField();
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameField.gridx = 2;
		gbc_usernameField.gridy = 2;
		login.add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 3;
		login.add(lblPassword, gbc_lblPassword);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBackground(SystemColor.window);
		textPane_1.setForeground(Color.RED);
		textPane_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM useraccount WHERE username = ? AND userpassword = ? "; // works
					PreparedStatement pst = myconn.prepareStatement(query);
					pst.setString(1, usernameField.getText());
					pst.setString(2, passwordField.getText());

					ResultSet rs = pst.executeQuery();
					int count = 0;
					while (rs.next()) {
						count++;
					}

					if (count == 1) {
						textPane_1.setVisible(false);
						try {
							String retrieveID = "SELECT userID FROM useraccount WHERE username = ?";
							PreparedStatement RID = myconn.prepareStatement(retrieveID);
							RID.setString(1, usernameField.getText());
							ResultSet result = RID.executeQuery();
							if (result.next()) {
								ID = result.getInt(1);
							}
							RID.close();
							result.close();
						} catch (Exception bad) {
							bad.printStackTrace();
						}
						cardLayout.show(ParentFrame, "homepage");
					} else {
						textPane_1.setVisible(true); // invalid login-info

					}
					rs.close();
					pst.close();
				}

				catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		}); // end event

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 3;
		login.add(passwordField, gbc_passwordField);
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 2;
		gbc_btnLogin.gridy = 4;
		login.add(btnLogin, gbc_btnLogin);

		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(ParentFrame, "newAccount");
				usernameField.setText(null);
				passwordField.setText(null);
				textPane_1.setVisible(false);
			}
		});

		GridBagConstraints gbc_textPane_1 = new GridBagConstraints();
		gbc_textPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_textPane_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_1.gridx = 3;
		gbc_textPane_1.gridy = 4;
		textPane_1.setEditable(false);
		textPane_1.setText("Invalid login information");
		textPane_1.setVisible(false);
		login.add(textPane_1, gbc_textPane_1);

		JLabel lblNewLabel_1 = new JLabel("New user? click here");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 5;
		login.add(lblNewLabel_1, gbc_lblNewLabel_1);

		btnCreateAccount.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		GridBagConstraints gbc_btnCreateAccount = new GridBagConstraints();
		gbc_btnCreateAccount.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCreateAccount.gridx = 3;
		gbc_btnCreateAccount.gridy = 5;
		login.add(btnCreateAccount, gbc_btnCreateAccount);

		// HomePage panel
		JPanel Homepage = new JPanel();
		ParentFrame.add(Homepage, "homepage");
		Homepage.setLayout(new BorderLayout(0, 0));

		JPanel HomeParentpanel = new JPanel();
		
		//creates calendar here, but adds it to the panel later, might be the error
		Calendar today = Calendar.getInstance();
		int currentYear = today.get(Calendar.YEAR);
		CalendarPane calendarPane = new CalendarPane(today.get(Calendar.MONTH), currentYear, ID);
		//***
		
		//HomeParentpanel.add(calendarPanel);
		
		JTabbedPane menuSelector = new JTabbedPane(JTabbedPane.TOP);
		Homepage.add(menuSelector, BorderLayout.NORTH);

		JPanel accountMenu = new JPanel();
		menuSelector.addTab("Account", null, accountMenu, null);
		accountMenu.setLayout(new CardLayout(0, 0));
		CardLayout AccMenuCardLayout = (CardLayout) accountMenu.getLayout();

		JPanel AccMain = new JPanel();
		accountMenu.add(AccMain, "AccMain");

		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBackground(SystemColor.window);
		textPane_2.setForeground(Color.RED);

		JButton btnChangeUsername = new JButton("Change Username");
		btnChangeUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccMenuCardLayout.show(accountMenu, "ChangeUN");
			}
		});
		btnChangeUsername.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		AccMain.add(btnChangeUsername);

		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccMenuCardLayout.show(accountMenu, "ChangePW");
			}
		});
		btnChangePassword.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		AccMain.add(btnChangePassword);

		JPanel ChangeUnPanel = new JPanel();
		accountMenu.add(ChangeUnPanel, "ChangeUN");

		JLabel lblNewLabel = new JLabel("Desired Username");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeUnPanel.add(lblNewLabel);

		textField = new JTextField();
		ChangeUnPanel.add(textField);
		textField.setColumns(10);

		JButton btnChange = new JButton("Update");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String check = "SELECT * FROM useraccount WHERE username = ?";
					PreparedStatement chk = myconn.prepareStatement(check);
					chk.setString(1, textField.getText());
					ResultSet rs = chk.executeQuery();
					int count = 0;
					while (rs.next()) {
						count++;
					}
					rs.close();
					chk.close();
					if (count == 0) {
						String update = "UPDATE useraccount SET username = ? WHERE username = ?";
						PreparedStatement updt = myconn.prepareStatement(update);
						updt.setString(1, textField.getText());
						updt.setString(2, usernameField.getText());
						updt.executeUpdate();
						updt.close();
					} else {
						textPane_2.setText("Error: that username already exists");
						textPane_2.setVisible(true);
						menuSelector.setSelectedIndex(3); // help tab
					}
				} catch (Exception er) {
					textPane_2.setText("Other error");
					textPane_2.setVisible(true);
					menuSelector.setSelectedIndex(3); // help tab
					er.printStackTrace();
				}
				AccMenuCardLayout.show(accountMenu, "AccMain");
			}
		});
		btnChange.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeUnPanel.add(btnChange);

		JPanel ChangePwPanel = new JPanel();
		accountMenu.add(ChangePwPanel, "ChangePW");

		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangePwPanel.add(lblNewPassword);

		textField_6 = new JTextField();
		textField_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangePwPanel.add(textField_6);
		textField_6.setColumns(8);

		JButton btnNewButton_3 = new JButton("Confirm");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String update = "UPDATE useraccount SET userpassword = ? WHERE userID = ?";
					PreparedStatement updt = myconn.prepareStatement(update);
					updt.setString(1, textField_6.getText());
					updt.setString(2, ID.toString());
					updt.executeUpdate();
					updt.close();
				} catch (Exception pwEr) {
					pwEr.printStackTrace();
					textPane_2.setText("Error: no change");
					textPane_2.setVisible(true);
					menuSelector.setSelectedIndex(3);
				}
				AccMenuCardLayout.show(accountMenu, "AccMain");
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangePwPanel.add(btnNewButton_3);

		JPanel AppointParentMenu = new JPanel();
		HomeParentpanel.add(AppointParentMenu);
		AppointParentMenu.setLayout(new CardLayout(0, 0));
		CardLayout AppointmentInfoLayout = (CardLayout) AppointParentMenu.getLayout();

		HomeParentpanel.add(calendarPane);
		
		JPanel appointmentMenu = new JPanel();
		menuSelector.addTab("Appointment", null, appointmentMenu, null);
		appointmentMenu.setLayout(new CardLayout(0, 0));
		CardLayout appSubMenu = (CardLayout) appointmentMenu.getLayout();

		JPanel AppMain = new JPanel();
		appointmentMenu.add(AppMain, "main");

		JButton btnNewAppointment = new JButton("New Appointment");
		AppMain.add(btnNewAppointment);
		btnNewAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppointmentInfoLayout.show(AppointParentMenu, "NewApp");
			}
		});
		btnNewAppointment.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		JButton btnDelAppointment = new JButton("Cancel Appointment");
		btnDelAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appSubMenu.show(appointmentMenu, "Delete");
			}
		});
		AppMain.add(btnDelAppointment);
		btnDelAppointment.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		JButton btnNewButton = new JButton("Edit Appointment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appSubMenu.show(appointmentMenu, "AppEdit");
			}
		});
		AppMain.add(btnNewButton);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		JPanel AppDlt = new JPanel();
		appointmentMenu.add(AppDlt, "Delete");
		AppDlt.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel lblAppointmentNumber = new JLabel("Appointment Number");
		lblAppointmentNumber.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		AppDlt.add(lblAppointmentNumber);

		textField_15 = new JTextField();
		textField_15.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		AppDlt.add(textField_15);
		textField_15.setColumns(4);

		JButton btnCancel = new JButton("Cancel Appointment");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM appointment WHERE appID = ?";
					PreparedStatement check = myconn.prepareStatement(query);
					check.setString(1, textField_15.getText());
					ResultSet appExist = check.executeQuery();
					int count = 0;
					while (appExist.next()) {
						count++;
					}
					check.close();
					appExist.close();
					if (count > 0) {
						String delete = "DELETE FROM appointment WHERE appID = ?";
						PreparedStatement deleteData = myconn.prepareStatement(delete);
						deleteData.setString(1, textField_15.getText());
						deleteData.executeUpdate();
						deleteData.close();
						appSubMenu.show(appointmentMenu, "main");

					} else {
						textPane_2.setText("Error deleting the appointment");
						textPane_2.setVisible(true);
						menuSelector.setSelectedIndex(3); // help tab
						appSubMenu.show(appointmentMenu, "main");
					}
					HomeParentpanel.remove(HomeParentpanel.getComponent(1));
					CalendarPane calendarPane = new CalendarPane(today.get(Calendar.MONTH), currentYear, ID);
					HomeParentpanel.add(calendarPane);

				} catch (Exception delerr) {
					delerr.printStackTrace();
				}
			}
		});
		btnCancel.setHorizontalAlignment(SwingConstants.RIGHT);
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		AppDlt.add(btnCancel);
		
		JTextArea textArea_2 = new JTextArea();
		
		JPanel AppEdit = new JPanel();
		appointmentMenu.add(AppEdit, "AppEdit");
		AppEdit.setLayout(new GridLayout(1, 3, 0, 0));
		
		JLabel lblAppointmentNumber_1 = new JLabel("Appointment Number");
		lblAppointmentNumber_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		AppEdit.add(lblAppointmentNumber_1);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get data from sql and assign it into the fields in the edit tab.
				try{
				String ps = "SELECT appdate, apptime, apptitle, appdescription FROM appointment WHERE appID = ?";
				PreparedStatement appointment = myconn.prepareStatement(ps);
				appointment.setString(1, textField_21.getText());
				ResultSet appInfo = appointment.executeQuery();
				
				String selection;
				String [] dateFields;
				while(appInfo.next()){ //setting sql data into editor fields
					selection = appInfo.getString("appdate");
					dateFields = selection.split("-");
					textField_18.setText(dateFields[0]);
					textField_17.setText(dateFields[1]);
					textField_16.setText(dateFields[2]);
					selection = appInfo.getString("apptime");
					textField_20.setText(selection);
					selection = appInfo.getString("apptitle");
					textField_19.setText(selection);
					selection = appInfo.getString("appdescription");
				}
				appointment.close();
				appInfo.close();
				appSubMenu.show(appointmentMenu, "main");
				AppointmentInfoLayout.show(AppointParentMenu, "Edit");
				}
				catch(Exception appexe){
					appexe.printStackTrace();
				}
			}
		});
		
		textField_21 = new JTextField();
		AppEdit.add(textField_21);
		textField_21.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_21.setColumns(10);
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		AppEdit.add(btnEdit);

		JPanel settingsMenu = new JPanel();
		menuSelector.addTab("Settings", null, settingsMenu, null);
		settingsMenu.setLayout(new CardLayout(0, 0));
		CardLayout SettingsMenuCardLayout = (CardLayout) settingsMenu.getLayout();

		JPanel SettingsMain = new JPanel();
		settingsMenu.add(SettingsMain, "SettingsMain");

		JButton btnEditCalendarRange = new JButton("Edit Calendar Range");
		btnEditCalendarRange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsMenuCardLayout.show(settingsMenu, "ChangeRange");
			}
		});
		btnEditCalendarRange.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		SettingsMain.add(btnEditCalendarRange);

		JButton btnNewButton_1 = new JButton("Edit Calendar Color");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsMenuCardLayout.show(settingsMenu, "ChangeColor");
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		SettingsMain.add(btnNewButton_1);

		JPanel ChangeRangePanel = new JPanel();
		settingsMenu.add(ChangeRangePanel, "ChangeRange");

		JLabel lblEnterDate = new JLabel("Enter date");
		lblEnterDate.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeRangePanel.add(lblEnterDate);

		JSpinner spinner = new JSpinner();
		JSpinner spinner_1 = new JSpinner();
		
		//Change Calendar Range code
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HomeParentpanel.remove(HomeParentpanel.getComponent(1));
					CalendarPane calendarPane = new CalendarPane((int) spinner.getValue() - 1,
							(int) spinner_1.getValue(), ID);
					HomeParentpanel.add(calendarPane);
				} catch (Exception inErr) {
					textPane_2.setText("Error: bad input");
					textPane_2.setVisible(true);
					menuSelector.setSelectedIndex(3);
					inErr.printStackTrace();
				}
				SettingsMenuCardLayout.show(settingsMenu, "SettingsMain");
			}
		});

		SpinnerModel modelY = new SpinnerNumberModel(currentYear, currentYear - 100, currentYear, 1);
		SpinnerModel modelM = new SpinnerNumberModel(1, 1, 12, 1);

		spinner.setModel(modelM);
		spinner.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeRangePanel.add(spinner);

		JLabel lblEnterYear = new JLabel("Enter Year");
		lblEnterYear.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeRangePanel.add(lblEnterYear);

		spinner_1.setModel(modelY);
		spinner_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeRangePanel.add(spinner_1);
		btnSelect.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeRangePanel.add(btnSelect);

		JPanel ChangeColorPanel = new JPanel();
		settingsMenu.add(ChangeColorPanel, "ChangeColor");

		JLabel lblDefineYourColor = new JLabel("Define your color  ");
		lblDefineYourColor.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeColorPanel.add(lblDefineYourColor);

		JLabel lblNewLabel_7 = new JLabel("R");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeColorPanel.add(lblNewLabel_7);

		textField_8 = new JTextField();
		textField_8.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeColorPanel.add(textField_8);
		textField_8.setColumns(3);

		JLabel lblNewLabel_8 = new JLabel("G");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeColorPanel.add(lblNewLabel_8);

		textField_9 = new JTextField();
		textField_9.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeColorPanel.add(textField_9);
		textField_9.setColumns(3);

		JLabel lblNewLabel_9 = new JLabel("B");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeColorPanel.add(lblNewLabel_9);

		textField_10 = new JTextField();
		textField_10.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeColorPanel.add(textField_10);
		textField_10.setColumns(3);

		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HomeParentpanel.getComponent(1).setBackground(new Color(Integer.parseInt(textField_8.getText()),
							Integer.parseInt(textField_9.getText()), Integer.parseInt(textField_10.getText())));
				} catch (Exception inErr) {
					textPane_2.setText("Error: bad input");
					textPane_2.setVisible(true);
					menuSelector.setSelectedIndex(3);
					inErr.printStackTrace();
				}
				SettingsMenuCardLayout.show(settingsMenu, "SettingsMain");
			}
		});
		btnEnter.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeColorPanel.add(btnEnter);

		JPanel helpMenu = new JPanel();
		menuSelector.addTab("Help", null, helpMenu, null);
		textPane_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textPane_2.setEditable(false);
		helpMenu.add(textPane_2);

		Homepage.add(HomeParentpanel, BorderLayout.CENTER);
		HomeParentpanel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel Details = new JPanel();
		AppointParentMenu.add(Details, "Details");
		Details.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		Details.add(panel, BorderLayout.NORTH);

		JLabel lblSelectDayFor = new JLabel("Select Day for details");
		lblSelectDayFor.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel.add(lblSelectDayFor);

		textField_11 = new JTextField();
		textField_11.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel.add(textField_11);
		textField_11.setColumns(2);

		JTextArea textArea = new JTextArea();
		JButton btnEnter_1 = new JButton("Update");
		btnEnter_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String check = "SELECT appID, apptitle, apptime, appdescription FROM useraccount NATURAL JOIN appointment WHERE userID = ? and appdate = ?";
					PreparedStatement chk = myconn.prepareStatement(check);
					String date = ((CalendarPane) HomeParentpanel.getComponent(1)).getYear() + "-" + (((CalendarPane) HomeParentpanel.getComponent(1)).getMonth() + 1) + "-" + textField_11.getText();
					chk.setInt(1, ID);

					// year month day
					chk.setString(2, date);
					ResultSet result = chk.executeQuery();
					int count = 0;
					while (result.next()) {
						count++;
					}
					if (count > 0) {
						textArea.setText(null);
						result.beforeFirst();

						String values;
						while (result.next()) {
							values = result.getString("apptitle");
							textArea.append("Appointment: " + values);
							values = result.getString("apptime");
							textArea.append("\nAt: " + values + " oclock");
							values = result.getString("appdescription");
							textArea.append("\nNotes: " + values);
							values = result.getString("appID");
							textArea.append("\nID Number = " + values);
							textArea.append("\n\n");
						}
					} else if (Integer.parseInt(textField_11.getText()) > 0
							&& Integer.parseInt(textField_11.getText()) < 31) {
						textArea.setText(null);
						textArea.append("There are no appointments \nfor the date \n" + date);
					} else {
						textPane_2.setText("Error: bad input");
						textPane_2.setVisible(true);
						menuSelector.setSelectedIndex(3);
					}

					HomeParentpanel.remove(HomeParentpanel.getComponent(1));
					CalendarPane calendarPane = new CalendarPane(today.get(Calendar.MONTH), currentYear, ID);
					HomeParentpanel.add(calendarPane);
					chk.close();
					result.close();

				} catch (Exception death) {
					death.printStackTrace();
				}

			}
		});
		btnEnter_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel.add(btnEnter_1);

		Details.add(textArea, BorderLayout.CENTER);
		
		//Edit Appointment Card
		JPanel EditAppointmentCard = new JPanel();
		AppointParentMenu.add(EditAppointmentCard, "Edit");
		
		JLabel lblNewLabel_16 = new JLabel("Day");
		lblNewLabel_16.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		EditAppointmentCard.add(lblNewLabel_16);
		
		textField_16 = new JTextField();
		textField_16.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		EditAppointmentCard.add(textField_16);
		textField_16.setColumns(2);
		
		JLabel lblNewLabel_17 = new JLabel("Month");
		lblNewLabel_17.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		EditAppointmentCard.add(lblNewLabel_17);
		
		textField_17 = new JTextField();
		textField_17.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		EditAppointmentCard.add(textField_17);
		textField_17.setColumns(2);
		
		JLabel lblNewLabel_18 = new JLabel("Year");
		lblNewLabel_18.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		EditAppointmentCard.add(lblNewLabel_18);
		
		textField_18 = new JTextField();
		textField_18.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		EditAppointmentCard.add(textField_18);
		textField_18.setColumns(4);
		
		JLabel lblNewLabel_19 = new JLabel("Title");
		lblNewLabel_19.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		EditAppointmentCard.add(lblNewLabel_19);
		
		textField_19 = new JTextField();
		textField_19.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		EditAppointmentCard.add(textField_19);
		textField_19.setColumns(10);
		
		JLabel lblNewLabel_20 = new JLabel("Time");
		lblNewLabel_20.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		EditAppointmentCard.add(lblNewLabel_20);

		textField_20 = new JTextField();
		textField_20.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		EditAppointmentCard.add(textField_20);
		textField_20.setColumns(4);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		EditAppointmentCard.add(lblDescription);
		
		textArea_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textArea_2.setColumns(20);
		textArea_2.setRows(4);
		EditAppointmentCard.add(textArea_2);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String update = "UPDATE appointment SET appdate = ?, apptime = ?, apptitle = ?, appdescription = ? WHERE appID = ?;";
					PreparedStatement newApp = myconn.prepareStatement(update);
					newApp.setString(1, textField_18.getText() + "-" + textField_17.getText() + "-" + textField_16.getText());
					newApp.setString(2, textField_20.getText());
					newApp.setString(3, textField_19.getText());
					newApp.setString(4, textArea_2.getText());
					newApp.setString(5, textField_21.getText());
										
					newApp.executeUpdate();
					
					AppointmentInfoLayout.show(AppointParentMenu, "Details");
					
				} catch (Exception newAppErr) {
					newAppErr.printStackTrace();
				}

			
				AppointmentInfoLayout.show(AppointParentMenu, "Details");
			}
		});
		
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		EditAppointmentCard.add(btnUpdate);
		
		//New Appointment Card
		JPanel NewAppointmentCard = new JPanel();
		AppointParentMenu.add(NewAppointmentCard, "NewApp");
		NewAppointmentCard.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel_10 = new JLabel("Day");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		NewAppointmentCard.add(lblNewLabel_10);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		NewAppointmentCard.add(textField_5);
		textField_5.setColumns(2);

		JLabel lblNewLabel_11 = new JLabel("Month");
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		NewAppointmentCard.add(lblNewLabel_11);

		textField_7 = new JTextField();
		textField_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		NewAppointmentCard.add(textField_7);
		textField_7.setColumns(2);

		JLabel lblNewLabel_12 = new JLabel("Year");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		NewAppointmentCard.add(lblNewLabel_12);

		textField_12 = new JTextField();
		textField_12.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		NewAppointmentCard.add(textField_12);
		textField_12.setColumns(4);

		JLabel lblNewLabel_13 = new JLabel("Title");
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		NewAppointmentCard.add(lblNewLabel_13);

		textField_13 = new JTextField();
		textField_13.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		NewAppointmentCard.add(textField_13);
		textField_13.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Time");
		lblNewLabel_14.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		NewAppointmentCard.add(lblNewLabel_14);

		textField_14 = new JTextField();
		textField_14.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		NewAppointmentCard.add(textField_14);
		textField_14.setColumns(5);

		JLabel lblNewLabel_15 = new JLabel("Description");
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		NewAppointmentCard.add(lblNewLabel_15);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setColumns(15);
		textArea_1.setRows(4);
		NewAppointmentCard.add(textArea_1);

		JButton btnNewButton_2 = new JButton("Create Appointment");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String update = "INSERT INTO appointment(userID, appdate, apptime, apptitle, appdescription) VALUES(?, ?, ?, ?, ?)";
					PreparedStatement newApp = myconn.prepareStatement(update);
					newApp.setString(1, ID.toString());
					newApp.setString(2, textField_12.getText() + "-" + textField_7.getText() + "-" + textField_5.getText());
					newApp.setString(3, textField_14.getText());
					newApp.setString(4, textField_13.getText());
					newApp.setString(5, textArea_1.getText());
										
					newApp.executeUpdate();
					
					AppointmentInfoLayout.show(AppointParentMenu, "Details");
					
					HomeParentpanel.remove(HomeParentpanel.getComponent(1));
					CalendarPane calendarPane = new CalendarPane(today.get(Calendar.MONTH), currentYear, ID);
					HomeParentpanel.add(calendarPane);
					
				} catch (Exception newAppErr) {
					newAppErr.printStackTrace();
				}

			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		NewAppointmentCard.add(btnNewButton_2);
				
		// new account tab
		JPanel NewAccount = new JPanel();
		ParentFrame.add(NewAccount, "newAccount");
		GridBagLayout gbl_NewAccount = new GridBagLayout();
		gbl_NewAccount.columnWidths = new int[] { 0, 62, 88, 150, 0 };
		gbl_NewAccount.rowHeights = new int[] { 26, 0, 0, 26, 26, 26, 26, 33, 0, 0 };
		gbl_NewAccount.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_NewAccount.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		NewAccount.setLayout(gbl_NewAccount);

		JTextPane textPane = new JTextPane();
		textPane.setBackground(SystemColor.window);
		textPane.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textPane.setForeground(Color.RED);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 3;
		gbc_textPane.gridy = 8;
		textPane.setEditable(false);
		textPane.setText("Sorry that username already exists");
		textPane.setVisible(false);

		JLabel lblUsername_1 = new JLabel("Firstname");
		lblUsername_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblUsername_1 = new GridBagConstraints();
		gbc_lblUsername_1.anchor = GridBagConstraints.EAST;
		gbc_lblUsername_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername_1.gridx = 2;
		gbc_lblUsername_1.gridy = 1;
		NewAccount.add(lblUsername_1, gbc_lblUsername_1);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 1;
		NewAccount.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Lastname");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 2;
		NewAccount.add(lblNewLabel_2, gbc_lblNewLabel_2);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 2;
		NewAccount.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField_1.gridx = 3;
		gbc_passwordField_1.gridy = 5;
		NewAccount.add(passwordField_1, gbc_passwordField_1);
		NewAccount.add(textPane, gbc_textPane);

		JLabel lblNewLabel_3 = new JLabel("Email Address");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 3;
		NewAccount.add(lblNewLabel_3, gbc_lblNewLabel_3);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.WEST;
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 3;
		NewAccount.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Desired Username");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 4;
		NewAccount.add(lblNewLabel_4, gbc_lblNewLabel_4);

		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.anchor = GridBagConstraints.WEST;
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 4;
		NewAccount.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Password");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 5;
		NewAccount.add(lblNewLabel_5, gbc_lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Retype password");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 2;
		gbc_lblNewLabel_6.gridy = 6;
		NewAccount.add(lblNewLabel_6, gbc_lblNewLabel_6);

		JButton btnCreateAccount_1 = new JButton("Create Account");
		btnCreateAccount_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textPane.setVisible(false);
					String update = "INSERT INTO useraccount(username, userpassword, firstname, lastname, emailaddress) VALUES(?, ?, ?, ?, ?)";
					String check = "SELECT * FROM useraccount WHERE username = ?";
					PreparedStatement chk = myconn.prepareStatement(check);
					chk.setString(1, textField_4.getText());
					ResultSet rs = chk.executeQuery();
					int count = 0;
					while (rs.next()) {
						count++;
					}
					if (count > 0) {
						textPane.setVisible(true);
					} else {
						PreparedStatement pst = myconn.prepareStatement(update);
						pst.setString(1, textField_4.getText());
						pst.setString(2, passwordField_1.getText());
						pst.setString(3, textField_1.getText());
						pst.setString(4, textField_2.getText());
						pst.setString(5, textField_3.getText());

						pst.executeUpdate();

						// reset fields
						textField_1.setText(null);
						textField_2.setText(null);
						textField_3.setText(null);
						textField_4.setText(null);
						passwordField_1.setText(null);
						passwordField_2.setText(null);

						cardLayout.show(ParentFrame, "login");
						pst.close();
					}

					rs.close();
					chk.close();

				} catch (Exception NewAccException) {
					NewAccException.printStackTrace();
					textPane.setVisible(true); // error message display
				}
			}
		});

		passwordField_2 = new JPasswordField();
		GridBagConstraints gbc_passwordField_2 = new GridBagConstraints();
		gbc_passwordField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_2.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField_2.gridx = 3;
		gbc_passwordField_2.gridy = 6;
		NewAccount.add(passwordField_2, gbc_passwordField_2);

		btnCreateAccount_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_btnCreateAccount_1 = new GridBagConstraints();
		gbc_btnCreateAccount_1.anchor = GridBagConstraints.WEST;
		gbc_btnCreateAccount_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreateAccount_1.fill = GridBagConstraints.VERTICAL;
		gbc_btnCreateAccount_1.gridx = 3;
		gbc_btnCreateAccount_1.gridy = 7;
		NewAccount.add(btnCreateAccount_1, gbc_btnCreateAccount_1);
	}
}
