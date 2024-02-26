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

public class SearchUserForm extends JFrame {

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
    public SearchUserForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                usernameTxt.setText("");
            }
        });

        setTitle("Αναζήτηση Χρήστη");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 530, 451);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 255, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        usernameTxt = new JTextField();
        usernameTxt.setBounds(142, 89, 201, 37);

        contentPane.add(usernameTxt);
        usernameTxt.setColumns(10);

        JLabel usernameLbl = new JLabel("username");
        usernameLbl.setBounds(202, 58, 100, 20);
        usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        usernameLbl.setForeground(new Color(160, 82, 45));
        contentPane.add(usernameLbl);


        // Search Button
        JButton searchBtn = new JButton("Αναζήτηση");
        searchBtn.setBounds(87, 89, 100, 33);

        searchBtn.addActionListener(new ActionListener() {
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

                    if (users.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "User not found", "SEARCH",
                                JOptionPane.ERROR_MESSAGE );
                        return;
                    }

                    Main.getSearchUserForm().setVisible(false);
                    Main.getUpdateDeleteUsersForm().setVisible(true);
                } catch (UserDAOException | UserNotFoundException e1) {
                    // failure
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
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
                Main.getInsertUserForm().setVisible(true);
                Main.getSearchUserForm().setVisible(false);
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
                Main.getSearchUserForm().setVisible(false);
            }
        });
        closeBtn.setBounds(302, 345, 89, 37);
        closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        closeBtn.setForeground(new Color(0, 0, 255));
        contentPane.add(closeBtn);
    }

    public String getUsername() {
        return username;
    }
}
