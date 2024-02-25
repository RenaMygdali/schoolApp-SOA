package gr.aueb.cf.schoolapp.controller_view;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.border.BevelBorder;

public class SearchForm extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField lastnameTxt;
	private String lastname = "";

	private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);
	private List<Teacher> teachers;

	public SearchForm() {
		setTitle("Εισαγωγή / Αναζήτηση Εκπαιδευτή");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				lastnameTxt.setText("");
			}
		});

		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 451);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setBounds(142, 89, 201, 37);
		contentPane.add(lastnameTxt);
		lastnameTxt.setColumns(10);
		
		JLabel lastnameLbl = new JLabel("Επώνυμο");
		lastnameLbl.setBounds(202, 58, 73, 20);
		lastnameLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		lastnameLbl.setForeground(new Color(160, 82, 45));
		contentPane.add(lastnameLbl);


		// Search Button
		JButton searchBtn = new JButton("Αναζήτηση");

		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Data Binding
				lastname = lastnameTxt.getText().trim();

				// Validation
				if (lastname.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Not valid input", "UPDATE ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {

					teachers = teacherService.getTeacherByLastname(lastname);

					if (teachers.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Teacher not found", "SEARCH",
								JOptionPane.ERROR_MESSAGE );
						return;
					}

					Main.getUpdateDeleteForm().setVisible(true);
					Main.getSearchForm().setVisible(false);
				} catch (TeacherDAOException | TeacherNotFoundException e1) {
					// failure
					String message = e1.getMessage();
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}


			}
		});
		searchBtn.setBounds(186, 137, 113, 37);
		searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchBtn.setForeground(new Color(0, 0, 255));
		contentPane.add(searchBtn);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(102, 40, 289, 161);
		mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(mainPanel);


		// Insert Button
		JButton insertBtn = new JButton("Εισαγωγή");

		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getInsertForm().setVisible(true);
				Main.getSearchForm().setVisible(false);
			}
		});

		insertBtn.setBounds(186, 238, 113, 37);
		insertBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		insertBtn.setForeground(new Color(0, 0, 255));
		contentPane.add(insertBtn);
		
		JPanel insertPanel = new JPanel();
		insertPanel.setBounds(102, 212, 289, 99);
		insertPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(insertPanel);

		// Close Button
		JButton closeBtn = new JButton("Close");

		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setVisible(true);
				Main.getSearchForm().setVisible(false);
			}
		});
		closeBtn.setBounds(302, 345, 89, 37);
		closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		closeBtn.setForeground(new Color(0, 0, 255));
		contentPane.add(closeBtn);
	}

	public String getLastname() {
		return lastname;
	}
}
