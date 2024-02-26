package gr.aueb.cf.schoolapp.controller_view;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.util.List;
import java.util.Objects;

public class UpdateDeleteForm extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField idTxt;
	private JTextField firstnameTxt;
	private JTextField lastnameTxt;

	private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

	private int listPosition;
	private int listSize;
	private List<Teacher> teachers;

	public UpdateDeleteForm() {

		setTitle("Ενημέρωση / Διαγραφή Εκπαιδευτή");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					teachers =
							teacherService.getTeacherByLastname(Main.getSearchForm().getLastname());

					listPosition = 0;
					listSize = teachers.size();

					if (listSize == 0) {
						idTxt.setText("");
						firstnameTxt.setText("");
						lastnameTxt.setText("");
						return;
					}

					idTxt.setText(Integer.toString(teachers.get(listPosition).getId()));
					firstnameTxt.setText(teachers.get(listPosition).getFirstname());
					lastnameTxt.setText(teachers.get(listPosition).getLastname());
				} catch (TeacherDAOException e1) {
					String message = e1.getMessage();
					JOptionPane.showMessageDialog(null, message, "Error in getting teachers",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 452, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);


		setContentPane(contentPane);
		contentPane.setLayout(null);


		// id Label
		JLabel idLbl = new JLabel("ID");
		idLbl.setForeground(new Color(128, 0, 0));
		idLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		idLbl.setBounds(85, 22, 17, 14);
		contentPane.add(idLbl);

		// id text
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setBackground(new Color(255, 255, 183));
		idTxt.setBounds(109, 19, 44, 20);
		contentPane.add(idTxt);
		idTxt.setColumns(10);


		// firstname label
		JLabel firstnameLbl = new JLabel("Όνομα");
		firstnameLbl.setForeground(new Color(128, 0, 0));
		firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstnameLbl.setBounds(53, 61, 49, 14);
		contentPane.add(firstnameLbl);

		// firstname text
		firstnameTxt = new JTextField();
		firstnameTxt.setBounds(109, 58, 151, 20);
		contentPane.add(firstnameTxt);
		firstnameTxt.setColumns(10);


		// lastname label
		JLabel lastnameLbl = new JLabel("Επώνυμο");
		lastnameLbl.setForeground(new Color(128, 0, 0));
		lastnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lastnameLbl.setBounds(38, 100, 64, 14);
		contentPane.add(lastnameLbl);

		// lastname text
		lastnameTxt = new JTextField();
		lastnameTxt.setBounds(109, 97, 151, 20);
		contentPane.add(lastnameTxt);
		lastnameTxt.setColumns(10);


		// update panel
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 317, 125);
		contentPane.add(panel);


		// First Record Button
		JButton firstBtn = new JButton("");

		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listSize > 0) {
					listPosition = 0;
					idTxt.setText(String.format("%s", teachers.get(listPosition).getId()));
					lastnameTxt.setText(teachers.get(listPosition).getLastname());
					firstnameTxt.setText(teachers.get(listPosition).getFirstname());
				}
			}
		});

		firstBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("First record.png"))));
		firstBtn.setBounds(69, 151, 49, 36);
		contentPane.add(firstBtn);


		// Previous Record Button
		JButton prevBtn = new JButton("");
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listPosition > 0) {
					listPosition--;
					idTxt.setText(String.format("%s", teachers.get(listPosition).getId()));
					lastnameTxt.setText(teachers.get(listPosition).getLastname());
					firstnameTxt.setText(teachers.get(listPosition).getFirstname());
				}
			}
		});

		prevBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Previous_record.png"))));
		prevBtn.setBounds(119, 151, 49, 36);
		contentPane.add(prevBtn);


		// Next Record Button
		JButton nextBtn = new JButton("");

		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listPosition <= listSize - 2) {
					listPosition++;
					idTxt.setText(String.format("%s", teachers.get(listPosition).getId()));
					lastnameTxt.setText(teachers.get(listPosition).getLastname());
					firstnameTxt.setText(teachers.get(listPosition).getFirstname());
				}
			}
		});
		nextBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Next_track.png"))));
		nextBtn.setBounds(169, 151, 49, 36);
		contentPane.add(nextBtn);


		// Last Record Button
		JButton lastBtn = new JButton("");

		lastBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listSize > 0) {
					listPosition = listSize - 1;
					idTxt.setText(String.format("%s", teachers.get(listPosition).getId()));
					firstnameTxt.setText(teachers.get(listPosition).getLastname());
					lastnameTxt.setText(teachers.get(listPosition).getLastname());
				}
			}
		});

		lastBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Last_Record.png"))));
		lastBtn.setBounds(219, 151, 49, 36);
		contentPane.add(lastBtn);


		// Update Button
		JButton updateBtn = new JButton("Update");

		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Data Binding
				String id = idTxt.getText().trim();
				String inputFirstname = firstnameTxt.getText().trim();
				String inputLastname = lastnameTxt.getText().trim();

				// Validation
				if (inputFirstname.isEmpty() || inputLastname.isEmpty() || id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Not valid input", "UPDATE ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					TeacherUpdateDTO updateDTO = new TeacherUpdateDTO();
					updateDTO.setId(Integer.parseInt(id));
					updateDTO.setFirstname(inputFirstname);
					updateDTO.setLastname(inputLastname);

					Teacher teacher = teacherService.updateTeacher(updateDTO);

					// success
					JOptionPane.showMessageDialog(null, "Teacher was updated", "UPDATE",
							JOptionPane.PLAIN_MESSAGE);

				} catch (TeacherDAOException | TeacherNotFoundException e1) {
					// failure
					String message = e1.getMessage();
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				} 
			}
		});

		updateBtn.setForeground(new Color(0, 0, 255));
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		updateBtn.setBounds(99, 225, 100, 46);
		contentPane.add(updateBtn);


		// Delete Button
		JButton deleteBtn = new JButton("Delete");

		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int response;
					int id;

					// Data Binding
					String idStr = idTxt.getText();

					if (idStr.isEmpty()) return;

					id = Integer.parseInt(idStr);

					response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος;", "Warning",
							JOptionPane.YES_NO_OPTION);

					if (response == JOptionPane.YES_OPTION) {
						teacherService.deleteTeacher(id);
						JOptionPane.showMessageDialog(null, "Teacher was deleted successfully",
								"DELETE", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (TeacherDAOException | TeacherNotFoundException e1) {
					String message = e1.getMessage();
					JOptionPane.showMessageDialog (null, message, "DELETE", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		deleteBtn.setForeground(Color.BLUE);
		deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		deleteBtn.setBounds(202, 225, 100, 46);
		contentPane.add(deleteBtn);


		// Close Button
		JButton closeBtn = new JButton("Close");

		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchForm().setVisible(true);
				Main.getUpdateDeleteForm().setVisible(false);
			}
		});

		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(305, 225, 100, 46);
		contentPane.add(closeBtn);


		// Separator
		JSeparator separator = new JSeparator();
		separator.setBounds(23, 202, 390, 1);
		contentPane.add(separator);
	}
}
