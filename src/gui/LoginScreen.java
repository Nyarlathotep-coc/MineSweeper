package gui;

import dao.UserDAO;
import entity.Session;
import entity.User;
import util.DBUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginScreen() {
        setTitle("登录");

        setSize(400, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        JLabel usernameLabel = new JLabel("用户名:");
        JLabel passwordLabel = new JLabel("密码:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("登录");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser(e);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void loginUser(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Connection connection = DBUtil.getConnection();
            UserDAO userDAO = new UserDAO(connection);

            if (userDAO.validateUser(username, password)) {
                JOptionPane.showMessageDialog(this, "登录成功！");
                Session.setLoggedInUsername(username);
                
                dispose();
                
                new WelcomeScreen();
            } else {
                JOptionPane.showMessageDialog(this, "用户名或密码错误！");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据库连接失败！");
        }
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}
