package gr.aueb.cf.schoolapp.controller_view;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.validator.TeacherValidator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class InsertForm extends JFrame {
	private static final long serialVersionUID = 123456;

	// Wiring
	private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

	private JPanel contentPane;
	private JTextField lastnameTxt;
	private JTextField firstnameTxt;

	public InsertForm() {
		setTitle("Εισαγωγή Εκπαιδευτή");

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				firstnameTxt.setText("");
				lastnameTxt.setText("");
			}
		});

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 405, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel firstnameLbl = new JLabel("Όνομα");
		firstnameLbl.setForeground(new Color(128, 0, 0));
		firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstnameLbl.setBounds(57, 57, 69, 25);
		contentPane.add(firstnameLbl);

		firstnameTxt = new JTextField();
		firstnameTxt.setBounds(131, 61, 143, 20);
		contentPane.add(firstnameTxt);
		firstnameTxt.setColumns(10);

		JLabel lastnameLbl = new JLabel("Επώνυμο");
		lastnameLbl.setForeground(new Color(128, 0, 0));
		lastnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lastnameLbl.setBounds(57, 90, 69, 25);
		contentPane.add(lastnameLbl);

		lastnameTxt = new JTextField();
		lastnameTxt.setColumns(10);
		lastnameTxt.setBounds(131, 94, 143, 20);
		contentPane.add(lastnameTxt);

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
				String inputLastname = lastnameTxt.getText().trim();
				String inputFirstname = firstnameTxt.getText().trim();


				// Validation
				if (inputLastname.isEmpty() || inputFirstname.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Not valid input", "INSERT ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}

			    try {
					// Data binding - create DTO
					TeacherInsertDTO insertDTO = new TeacherInsertDTO();
					insertDTO.setFirstname(inputFirstname);
					insertDTO.setLastname(inputLastname);

					// Validation
					errors = TeacherValidator.validate(insertDTO);

					if (!errors.isEmpty()) {
						String firstnameMessage = (errors.get("firstname") != null) ? "Firstname" +
								": " + errors.get("firstname") : "";
						String lastnameMessage = (errors.get("lastname") != null) ? "Lastname" +
								": " + errors.get("lastname") : "";
						JOptionPane.showMessageDialog(null,
								firstnameMessage + " " + lastnameMessage, "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Καλώ το API του service layer
					Teacher teacher = teacherService.insertTeacher(insertDTO);

					if (teacher == null) {
						JOptionPane.showMessageDialog(null, "Teacher not inserted", "INSERT",
								JOptionPane.ERROR_MESSAGE );
						return;
					}

					JOptionPane.showMessageDialog(null, "Teacher " + teacher.getLastname() + " " +
							"was" +
							" inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);
			    } catch (TeacherDAOException e1) {
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
				Main.getSearchForm().setVisible(true);
				Main.getInsertForm().setVisible(false);
			}
		});

		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(226, 191, 129, 41);
		contentPane.add(closeBtn);
	}
}
