package gr.aueb.cf.schoolapp.controller_view;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.border.BevelBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class UsersSearchForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameTxt;
    private String username= "";
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);
    private List<User> users;

    /**
     * Create the frame.
     */
    public UsersSearchForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                usernameTxt.setText("");
            }
        });

        setTitle("Αναζήτηση Χρήστη");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 414, 290);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(234, 234, 234));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel searchPanel = new JPanel();
        searchPanel.setBounds(72, 30, 268, 133);
        searchPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        contentPane.add(searchPanel);
        searchPanel.setLayout(null);

        usernameTxt = new JTextField();
        usernameTxt.setBounds(60, 45, 155, 23);
        searchPanel.add(usernameTxt);
        usernameTxt.setColumns(10);

        JLabel userNameLbl = new JLabel("Username");
        userNameLbl.setBounds(95, 11, 84, 23);
        userNameLbl.setForeground(new Color(205, 48, 20));
        userNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 19));
        searchPanel.add(userNameLbl);


        // Search Button
        JButton searchUserBtn = new JButton("Αναζήτηση");
        searchUserBtn.setBounds(87, 89, 100, 33);

        searchUserBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Data Binding
                username = usernameTxt.getText().trim();

                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill username field", "UPDATE " +
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {

                    users = userService.getUserByUsername(username);

                    Main.getUsersSearchForm().setVisible(false);
                    Main.getUpdateDeleteUsersForm().setVisible(true);
                } catch (UserDAOException | UserNotFoundException e1) {
                    // failure
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        searchUserBtn.setForeground(Color.BLUE);
        searchUserBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        searchPanel.add(searchUserBtn);


        // Close Button
        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(240, 192, 100, 33);
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getMenu().setVisible(true);
                Main.getUsersSearchForm().setVisible(false);
            }
        });
        closeBtn.setForeground(Color.BLUE);
        closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        contentPane.add(closeBtn);
    }

    public String getUsername() {
        return username;
    }
}
