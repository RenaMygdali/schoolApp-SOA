package gr.aueb.cf.schoolapp.controller_view;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserInsertDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.security.SecUtil;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.validator.UserValidator;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class InsertUserForm extends JFrame {
	private static final long serialVersionUID = 123456;

	// Wiring
	private final IUserDAO userDAO = new UserDAOImpl();
	private final IUserService userService = new UserServiceImpl(userDAO);

	private JPanel contentPane;
	private JTextField usernameTxt;
	private JTextField passwordTxt;

	public InsertUserForm() {
		setTitle("Εισαγωγή Χρήστη");

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				usernameTxt.setText("");
				passwordTxt.setText("");
			}
		});

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 405, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel usernameLbl = new JLabel("username");
		usernameLbl.setForeground(new Color(128, 0, 0));
		usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernameLbl.setBounds(57, 57, 69, 25);
		contentPane.add(usernameLbl);

		usernameTxt = new JTextField();
		usernameTxt.setBounds(131, 61, 143, 20);
		contentPane.add(usernameTxt);
		usernameTxt.setColumns(10);

		JLabel passwordLbl = new JLabel("password");
		passwordLbl.setForeground(new Color(128, 0, 0));
		passwordLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordLbl.setBounds(57, 90, 69, 25);
		contentPane.add(passwordLbl);

		passwordTxt = new JPasswordField();
		passwordTxt.setColumns(10);
		passwordTxt.setBounds(131, 94, 143, 20);
		contentPane.add(passwordTxt);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(240, 240, 240));
		mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mainPanel.setBounds(24, 11, 325, 156);
		contentPane.add(mainPanel);


		// Insert Button
		JButton insertBtn = new JButton("Εισαγωγή");


		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String, String> errors = new HashMap<>();

				// Data Binding
				// Παίρνω τα δεδομένα από τη φόρμα.
				String inputUsername = usernameTxt.getText().trim();
				String inputPassword = passwordTxt.getText().trim();


				// Validation
				if (inputPassword.isEmpty() || inputUsername.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Not valid input", "INSERT ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}

			    try {
					// Data binding - create DTO
					UserInsertDTO insertDTO = new UserInsertDTO();
					insertDTO.setUsername(inputUsername);
					insertDTO.setPassword(inputPassword);

					// Validation
					errors = UserValidator.validate(insertDTO);

					if (!errors.isEmpty()) {
						String usernameMessage = (errors.get("username") != null) ? "Username" +
								": " + errors.get("username") : "";
						String passwordMessage = (errors.get("password") != null) ? "Password" +
								": " + errors.get("password") : "";
						JOptionPane.showMessageDialog(null,
								usernameMessage + " " + passwordMessage, "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

//					String hashPassword = SecUtil.hashPassword(inputPassword);
//					insertDTO.setPassword(hashPassword);

					// Καλώ το API του service layer
					User user = userService.insertUser(insertDTO);

					if (user == null) {
						JOptionPane.showMessageDialog(null, "User not inserted", "INSERT",
								JOptionPane.ERROR_MESSAGE );
						return;
					}

					JOptionPane.showMessageDialog(null, "User " + user.getUsername() + " " +
							"was inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);
			    } catch (UserDAOException e1) {
			    	String message = e1.getMessage();
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			    }
			}
		});
		insertBtn.setForeground(new Color(0, 0, 255));
		insertBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		insertBtn.setBounds(99, 191, 129, 41);
		contentPane.add(insertBtn);


		// Close Button
		JButton closeBtn = new JButton("Κλείσιμο");

		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchUserForm().setVisible(true);
				Main.getInsertUserForm().setVisible(false);
			}
		});

		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(226, 191, 129, 41);
		contentPane.add(closeBtn);
	}
}
