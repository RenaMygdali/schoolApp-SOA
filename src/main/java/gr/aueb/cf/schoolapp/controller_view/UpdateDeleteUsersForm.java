package gr.aueb.cf.schoolapp.controller_view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.dto.UserUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.security.SecUtil;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Objects;
import javax.swing.JSeparator;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JPasswordField;

public class UpdateDeleteUsersForm extends JFrame {
    private static final long serialVersionUID = 123456;
    private JPanel contentPane;
    private JTextField usernameTxt;
//    private JLabel roleLbl;
    Connection conn;
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private JTextField idTxt;
    private JPasswordField newPassTxt;
    private JPasswordField confirmPassTxt;
    private String inputNewPass;
    private String inputConfirmPass;
    private List<User> users;
    private IUserDAO userDAO = new UserDAOImpl();
    private IUserService userService = new UserServiceImpl(userDAO);

    private int listPosition;
    private int listSize;
//    private JTextField roleTxt;

    public UpdateDeleteUsersForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                String sql = "SELECT * FROM USERS WHERE USERNAME LIKE ?";

                try {
                    users =
                            userService.getUserByUsername(Main.getUsersSearchForm().getUsername());

                    if (users == null) {
                        JOptionPane.showMessageDialog(null, "User not found", "SEARCH",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                    listPosition = 0;
                    listSize = users.size();

                    if (listSize == 0) {
                        idTxt.setText("");
                        usernameTxt.setText("");
                        return;
                    }

                    idTxt.setText(Integer.toString(users.get(listPosition).getId()));
                    usernameTxt.setText(users.get(listPosition).getUsername());
                } catch (UserDAOException | UserNotFoundException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error in getting users",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        setTitle("Ενημέρωση / Διαγραφή Χρήστη");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 538, 468);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        newPassTxt = new JPasswordField();
        newPassTxt.setBounds(153, 203, 207, 20);
        newPassTxt.setColumns(10);
        contentPane.add(newPassTxt);

        idTxt = new JTextField();
        idTxt.setBounds(153, 40, 59, 20);
        idTxt.setEditable(false);
        idTxt.setColumns(10);
        idTxt.setBackground(new Color(252, 252, 205));
        contentPane.add(idTxt);

        JLabel usernameLbl = new JLabel("Username");
        usernameLbl.setBounds(61, 75, 73, 17);
        usernameLbl.setForeground(new Color(128, 0, 0));
        usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        contentPane.add(usernameLbl);

        usernameTxt = new JTextField();
        usernameTxt.setBounds(153, 73, 207, 20);
        contentPane.add(usernameTxt);
        usernameTxt.setColumns(10);

//        roleLbl = new JLabel("Role");
//        roleLbl.setBounds(96, 106, 38, 17);
//        roleLbl.setForeground(new Color(128, 0, 0));
//        roleLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
//        contentPane.add(roleLbl);

//        roleTxt = new JTextField();
//        roleTxt.setColumns(10);
//        roleTxt.setBounds(153, 104, 207, 20);
//        contentPane.add(roleTxt);

        JLabel idLbl = new JLabel("ID");
        idLbl.setBounds(117, 42, 17, 17);
        idLbl.setForeground(new Color(128, 0, 0));
        idLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        contentPane.add(idLbl);

        JSeparator separator = new JSeparator();
        separator.setBounds(45, 273, 450, 2);
        contentPane.add(separator);


        // Results are displayed based on the order they were stored in the DB and not based on the ascending order of the id

        // First Record Button
        JButton firstBtn = new JButton("");
        firstBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listSize > 0) {
                    listPosition = 0;
                    idTxt.setText(String.format("%s", users.get(listPosition).getId()));
                    usernameTxt.setText(users.get(listPosition).getUsername());
                }
            }
        });
        firstBtn.setBounds(108, 300, 49, 23);
        firstBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("First record.png"))));
//

        contentPane.add(firstBtn);


        // Previous Record Button

        JButton prevBtn = new JButton("");
        prevBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listPosition > 0) {
                    listPosition--;
                    idTxt.setText(String.format("%s", users.get(listPosition).getId()));
                    usernameTxt.setText(users.get(listPosition).getUsername());
                }
            }
        });
        prevBtn.setBounds(155, 300, 49, 23);
        prevBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Previous_record.png"))));
        contentPane.add(prevBtn);


        // Next Record Button

        JButton nextBtn = new JButton("");
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listPosition <= listSize - 2) {
                    listPosition++;
                    idTxt.setText(String.format("%s", users.get(listPosition).getId()));
                    usernameTxt.setText(users.get(listPosition).getPassword());
                }
            }
        });
        nextBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Next_track.png"))));
        nextBtn.setBounds(200, 300, 49, 23);
        contentPane.add(nextBtn);


        // Last Record Button

        JButton lastBtn = new JButton("");
        lastBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listSize > 0) {
                    listPosition = listSize - 1;
                    idTxt.setText(String.format("%s", users.get(listPosition).getId()));
                    usernameTxt.setText(users.get(listPosition).getUsername());
                }
            }
        });
        lastBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Last_Record.png"))));
        lastBtn.setBounds(248, 300, 49, 23);
        contentPane.add(lastBtn);


        // Update Button

        JButton udpateBtn = new JButton("Update");
        udpateBtn.setBounds(112, 360, 90, 45);

        udpateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Data Binding
                String id = idTxt.getText().trim();
                String username = usernameTxt.getText().trim();
                String inputPass = String.valueOf(newPassTxt.getPassword()).trim();
                String confirmPass = String.valueOf(confirmPassTxt.getPassword()).trim();

                // Validation
                if (inputPass.isEmpty() || confirmPass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please type password", "PASSWORD ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!inputPass.equals(confirmPass)) {
                    JOptionPane.showMessageDialog(null, "Confirmation password not the same. " +
                                    "Please try again",
                            "PASSWORD UPDATE", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    UserUpdateDTO updateDTO = new UserUpdateDTO();
                    updateDTO.setId(Integer.parseInt(id));
                    updateDTO.setUsername(username);
                    updateDTO.setPassword(inputPass);

                    User users = userService.updateUser(updateDTO);

                    // success
                    JOptionPane.showMessageDialog(null, "User was updated", "UPDATE",
                            JOptionPane.PLAIN_MESSAGE);

                } catch (UserDAOException | UserNotFoundException e1) {
                    // failure
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        udpateBtn.setForeground(new Color(26, 37, 179));
        udpateBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        udpateBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        contentPane.add(udpateBtn);


        // Delete Button

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(207, 360, 90, 45);

        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int response;
                    String username;

                    // Data Binding
                    username = usernameTxt.getText().trim();

                    if (username.isEmpty()) return;

                    response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος;", "Warning",
                            JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        userService.deleteUser(username);
                        JOptionPane.showMessageDialog(null, "User was deleted successfully",
                                "DELETE", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (UserDAOException | UserNotFoundException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog (null, message, "DELETE", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteBtn.setForeground(new Color(26, 37, 179));
        deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        deleteBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        contentPane.add(deleteBtn);

        // Close Button

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(398, 360, 90, 45);

        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getUpdateDeleteUsersForm().setVisible(false);
                Main.getUsersSearchForm().setVisible(true);
            }
        });

        closeBtn.setForeground(new Color(26, 37, 179));
        closeBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        closeBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        contentPane.add(closeBtn);

        JLabel changePassLbl = new JLabel("Change Password");
        changePassLbl.setBounds(143, 175, 136, 17);
        changePassLbl.setForeground(new Color(128, 0, 0));
        changePassLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        contentPane.add(changePassLbl);

        JLabel newPassLbl = new JLabel("new Password");
        newPassLbl.setBounds(44, 205, 90, 17);
        newPassLbl.setForeground(new Color(226, 3, 26));
        newPassLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(newPassLbl);

        confirmPassTxt = new JPasswordField();
        confirmPassTxt.setBounds(153, 234, 207, 20);
        confirmPassTxt.setColumns(10);
        contentPane.add(confirmPassTxt);

        JLabel confirmPassLbl = new JLabel("confirm Password");
        confirmPassLbl.setBounds(26, 236, 110, 17);
        confirmPassLbl.setForeground(new Color(226, 3, 26));
        confirmPassLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(confirmPassLbl);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(45, 158, 450, 2);
        contentPane.add(separator_1);
    }
}