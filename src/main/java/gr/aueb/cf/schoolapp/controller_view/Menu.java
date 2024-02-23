package gr.aueb.cf.schoolapp.controller_view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;

import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {
	private static final long serialVersionUID = 123456;

	public Menu() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = classLoader.getResource("eduv2.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(url));

		setTitle("Διαχείριση Εκπαιδευτικού Συστήματος");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(520, 400);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Ποιότητα στην Εκπαίδευση");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitle.setBounds(12, 26, 486, 65);
		contentPane.add(lblTitle);

		JLabel lblTitle2 = new JLabel("Ποιότητα στην Εκπαίδευση");
		lblTitle2.setForeground(new Color(0, 128, 0));
		lblTitle2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitle2.setBounds(14, 28, 486, 65);
		contentPane.add(lblTitle2);


		// Εκπαιδευτές Button
		JButton btnTeachers = new JButton("");
		btnTeachers.setBounds(12, 132, 40, 30);
		btnTeachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setVisible(false);
				Main.getSearchForm().setVisible(true);
			}
		});
		contentPane.add(btnTeachers);


		// Εκπαιδευόμενοι Button
		JButton btnStudents = new JButton("");
		btnStudents.setBounds(12, 183, 40, 30);
		contentPane.add(btnStudents);

		// Users Button
		JButton btnUsers = new JButton("");
		btnUsers.setBounds(12, 234, 40, 30);
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setVisible(false);
				Main.getUsersSearchForm().setVisible(true);
			}
		});
		contentPane.add(btnUsers);


		JSeparator separator = new JSeparator();
		separator.setBounds(22, 102, 450, 1);
		contentPane.add(separator);

		JLabel lblTeachers = new JLabel("Εκπαιδευτές");
		lblTeachers.setForeground(new Color(128, 0, 0));
		lblTeachers.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTeachers.setBounds(67, 127, 123, 40);
		contentPane.add(lblTeachers);

		JLabel lblStudents = new JLabel("Εκπαιδευόμενοι");
		lblStudents.setForeground(new Color(128, 0, 0));
		lblStudents.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStudents.setBounds(67, 178, 167, 40);
		contentPane.add(lblStudents);

		JLabel lblUsers = new JLabel("Χρήστες");
		lblUsers.setForeground(new Color(128, 0, 0));
		lblUsers.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsers.setBounds(67, 229, 167, 40);
		contentPane.add(lblUsers);
	}
}
