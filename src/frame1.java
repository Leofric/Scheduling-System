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
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;
import java.awt.ItemSelectable;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import javax.swing.JSpinner;

public class frame1 {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField usernameField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	// private JTable table;

	static Connection myconn;
	private JTextField textField;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTable table_1;
	private JTextField textField_11;

	private Integer ID = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myconn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/test?autoReconnect=false&useSSL=false", "root", "punkulam");
					frame1 window = new frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel ParentFrame = new JPanel();
		ParentFrame.setBounds(0, 0, 450, 278);
		frame.getContentPane().add(ParentFrame);
		ParentFrame.setLayout(new CardLayout(0, 0));
		CardLayout cardLayout = (CardLayout) ParentFrame.getLayout();
		cardLayout.show(ParentFrame, "login");

		JPanel login = new JPanel();
		ParentFrame.add(login, "login");
		GridBagLayout gbl_login = new GridBagLayout();
		gbl_login.columnWidths = new int[] { 150, 150, 150, 0 };
		gbl_login.rowHeights = new int[] { 45, 45, 45, 45, 45, 45, 0 };
		gbl_login.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_login.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		login.setLayout(gbl_login);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.fill = GridBagConstraints.VERTICAL;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 2;
		login.add(lblUsername, gbc_lblUsername);

		usernameField = new JTextField();
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameField.gridx = 1;
		gbc_usernameField.gridy = 2;
		login.add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
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
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 3;
		login.add(passwordField, gbc_passwordField);
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 1;
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
		gbc_textPane_1.gridx = 2;
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
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 5;
		login.add(lblNewLabel_1, gbc_lblNewLabel_1);

		btnCreateAccount.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		GridBagConstraints gbc_btnCreateAccount = new GridBagConstraints();
		gbc_btnCreateAccount.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCreateAccount.gridx = 2;
		gbc_btnCreateAccount.gridy = 5;
		login.add(btnCreateAccount, gbc_btnCreateAccount);

		// HomePage panel
		JPanel Homepage = new JPanel();
		ParentFrame.add(Homepage, "homepage");
		Homepage.setLayout(new BorderLayout(0, 0));

		JTabbedPane menuSelector = new JTabbedPane(JTabbedPane.TOP);
		Homepage.add(menuSelector, BorderLayout.NORTH);

		JPanel accountMenu = new JPanel();
		menuSelector.addTab("Account", null, accountMenu, null);
		accountMenu.setLayout(new CardLayout(0, 0));
		CardLayout AccMenuCardLayout = (CardLayout) accountMenu.getLayout();

		JPanel AccMain = new JPanel();
		accountMenu.add(AccMain, "AccMain");

		JTextPane textPane_2 = new JTextPane();
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

		JPanel appointmentMenu = new JPanel();
		menuSelector.addTab("Appointment", null, appointmentMenu, null);

		JButton btnNewAppointment = new JButton("New Appointment");
		btnNewAppointment.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		appointmentMenu.add(btnNewAppointment);

		JButton btnEditAppointment = new JButton("Cancel Appointment");
		btnEditAppointment.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		appointmentMenu.add(btnEditAppointment);

		JButton btnNewButton = new JButton("Edit Appointment");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		appointmentMenu.add(btnNewButton);

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

		textField_7 = new JTextField();
		textField_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeRangePanel.add(textField_7);
		textField_7.setColumns(10);

		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// date range could be tricky, but ideally, they select a date
				// then you update the calendar below to show a week starting
				// with that date?
				SettingsMenuCardLayout.show(settingsMenu, "SettingsMain");
			}
		});
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
				// change the color of the panels showing an appointment
				// using rgb values from the 3 text fields
				SettingsMenuCardLayout.show(settingsMenu, "SettingsMain");
			}
		});
		btnEnter.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ChangeColorPanel.add(btnEnter);

		JPanel helpMenu = new JPanel();
		menuSelector.addTab("Help", null, helpMenu, null);

		textPane_2.setEnabled(false);
		textPane_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textPane_2.setEditable(false);
		helpMenu.add(textPane_2);

		JPanel HomeParentpanel = new JPanel();
		Homepage.add(HomeParentpanel, BorderLayout.CENTER);
		HomeParentpanel.setLayout(new GridLayout(0, 2, 0, 0));

		CalendarPanel calendarPanel = new CalendarPanel(2, 2017);
		HomeParentpanel.add(calendarPanel);

		JPanel panel_1 = new JPanel();
		HomeParentpanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JTextPane txtpnLdkfjadslfajsdkfad = new JTextPane();
		panel_1.add(txtpnLdkfjadslfajsdkfad, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.NORTH);

		JLabel lblSelectDayFor = new JLabel("Select Day for details");
		lblSelectDayFor.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel.add(lblSelectDayFor);

		textField_11 = new JTextField();
		textField_11.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel.add(textField_11);
		textField_11.setColumns(2);

		JButton btnEnter_1 = new JButton("Update");
		btnEnter_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel.add(btnEnter_1);

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
