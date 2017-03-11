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
import java.sql.*;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
		login.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblUsername.setBounds(110, 116, 64, 16);
		login.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblPassword.setBounds(110, 144, 64, 16);
		login.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(176, 138, 130, 26);
		login.add(passwordField);

		usernameField = new JTextField();
		usernameField.setBounds(176, 110, 130, 26);
		login.add(usernameField);
		usernameField.setColumns(10);

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
				try {
					String query = "SELECT * FROM useraccount WHERE username = ? AND userpassword = ? ";
					PreparedStatement pst = myconn.prepareStatement(query);
					pst.setString(1, usernameField.getText());
					pst.setString(2, passwordField.getText());

					ResultSet rs = pst.executeQuery();
					int count = 0;
					while (rs.next()) {
						count++;
					}

					if (count == 1) {
						cardLayout.show(ParentFrame, "homepage");
					} else {
						// display error message

					}
					rs.close();
					pst.close();
				}

				catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		}); // end event
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnLogin.setBounds(176, 164, 130, 29);
		login.add(btnLogin);

		// new account tab
		JPanel NewAccount = new JPanel();
		ParentFrame.add(NewAccount, "newAccount");
		GridBagLayout gbl_NewAccount = new GridBagLayout();
		gbl_NewAccount.columnWidths = new int[] { 0, 62, 88, 150, 0 };
		gbl_NewAccount.rowHeights = new int[] { 26, 26, 0, 26, 26, 26, 26, 33, 0, 0 };
		gbl_NewAccount.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_NewAccount.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		NewAccount.setLayout(gbl_NewAccount);

		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textPane.setForeground(Color.RED);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 3;
		gbc_textPane.gridy = 8;
		textPane.setEditable(false);
		textPane.setText("Sorry that username already exists");
		textPane.setVisible(false);

		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField_1.gridx = 3;
		gbc_passwordField_1.gridy = 5;
		NewAccount.add(passwordField_1, gbc_passwordField_1);
		NewAccount.add(textPane, gbc_textPane);

		JLabel lblUsername_1 = new JLabel("Firstname");
		lblUsername_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblUsername_1 = new GridBagConstraints();
		gbc_lblUsername_1.anchor = GridBagConstraints.EAST;
		gbc_lblUsername_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername_1.gridx = 2;
		gbc_lblUsername_1.gridy = 0;
		NewAccount.add(lblUsername_1, gbc_lblUsername_1);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 0;
		NewAccount.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Lastname");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 1;
		NewAccount.add(lblNewLabel_2, gbc_lblNewLabel_2);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 1;
		NewAccount.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

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
					String query = "INSERT INTO useraccount VALUES(?, ?, ?, ?, ?)";
					PreparedStatement pst = myconn.prepareStatement(query);
					pst.setString(1, textField_1.getText());
					pst.setString(2, textField_2.getText());
					pst.setString(3, textField_3.getText());
					pst.setString(4, textField_4.getText());
					pst.setString(5, passwordField_1.getText());

					// reset fields
					textField_1.setText(null);
					textField_2.setText(null);
					textField_3.setText(null);
					textField_4.setText(null);
					passwordField_1.setText(null);
					passwordField_2.setText(null);

					cardLayout.show(ParentFrame, "login");
					pst.close();
				} catch (Exception NewAccException) {
					textPane.setVisible(true); //error message display
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

		// HomePage panel
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

		// using j table format
		// table = new JTable(24,7);
		// String [] columns = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
		// DefaultTableModel model = new DefaultTableModel(null,columns);

	}
}
