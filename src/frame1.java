import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JToolBar;
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

import java.awt.Color;
import javax.swing.JScrollPane;

public class frame1 {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		ParentFrame.setLayout(new CardLayout(0,0));
		CardLayout cardLayout = (CardLayout) ParentFrame.getLayout();
		cardLayout.show(ParentFrame, "login");
		
		JPanel login = new JPanel();
		ParentFrame.add(login, "login");
		login.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblUsername.setBounds(110, 116, 64, 16);
		login.add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel.setBounds(110, 144, 64, 16);
		login.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(176, 138, 130, 26);
		login.add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(176, 110, 130, 26);
		login.add(textField);
		textField.setColumns(10);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(ParentFrame, "newAccount");
			}
		});
		
		btnCreateAccount.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnCreateAccount.setBounds(233, 230, 117, 29);
		login.add(btnCreateAccount);
		
		JLabel lblNewLabel_1 = new JLabel("New user? click here");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(130, 236, 102, 16);
		login.add(lblNewLabel_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//UNFINISHED DEMO
				cardLayout.show(ParentFrame, "homepage");
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnLogin.setBounds(176, 164, 130, 29);
		login.add(btnLogin);
		
		//new account tab
		JPanel NewAccount = new JPanel();
		ParentFrame.add(NewAccount, "newAccount");
		NewAccount.setLayout(null);
		
		JLabel lblUsername_1 = new JLabel("Firstname");
		lblUsername_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblUsername_1.setBounds(40, 70, 90, 16);
		NewAccount.add(lblUsername_1);
		
		JLabel lblNewLabel_2 = new JLabel("Lastname");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(40, 98, 90, 16);
		NewAccount.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email Address");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(40, 126, 90, 16);
		NewAccount.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Desired Username");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(40, 154, 90, 16);
		NewAccount.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Password");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(41, 182, 89, 16);
		NewAccount.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Retype password");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(41, 210, 89, 16);
		NewAccount.add(lblNewLabel_6);
		
		textField_1 = new JTextField();
		textField_1.setBounds(159, 64, 130, 26);
		NewAccount.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(159, 92, 130, 26);
		NewAccount.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(159, 120, 130, 26);
		NewAccount.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(159, 148, 130, 26);
		NewAccount.add(textField_4);
		textField_4.setColumns(10);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(159, 176, 130, 26);
		NewAccount.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(159, 204, 130, 26);
		NewAccount.add(passwordField_2);
		
		JButton btnCreateAccount_1 = new JButton("Create Account");
		btnCreateAccount_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if conditions are met
				cardLayout.show(ParentFrame, "login");
			}
		});
		btnCreateAccount_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnCreateAccount_1.setBounds(159, 231, 130, 29);
		NewAccount.add(btnCreateAccount_1);
		
		//HomePage panel
		JPanel Homepage = new JPanel();
		ParentFrame.add(Homepage, "homepage");
		Homepage.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane menuSelector = new JTabbedPane(JTabbedPane.TOP);
		Homepage.add(menuSelector, BorderLayout.NORTH);
		
		JPanel accountMenu = new JPanel();
		menuSelector.addTab("Account", null, accountMenu, null);
		
		JButton btnChangeUsername = new JButton("Change Username");
		btnChangeUsername.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		accountMenu.add(btnChangeUsername);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		accountMenu.add(btnChangePassword);
		
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
		
		JButton btnNewButton_1 = new JButton("Calendar range");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		settingsMenu.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Calendar Color");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		settingsMenu.add(btnNewButton_2);
		
		JPanel helpMenu = new JPanel();
		menuSelector.addTab("Help", null, helpMenu, null);
		
		JScrollPane scrollPane = new JScrollPane();
		Homepage.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(24,7);		
	    String [] columns = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
	    DefaultTableModel model = new DefaultTableModel(null,columns);
	    JTable table = new JTable(model);
		scrollPane.setViewportView(table);

		
		//the layout arguments will be variable based on the user date-range selection
		//if the range of dates is 7 days, 1 week, then 1 row, 7 columns 
		//if range is 14 days, 2 weeks, 2 rows 7 columns
		//basically rows = # of weeks (or #ofdays/7)
		//			columns = #of days selected up to 7, so if 1 day is selected, than 1 column if 7 or more days, then 7 columns
		
	}
}
