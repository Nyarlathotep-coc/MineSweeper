package gui;

import dao.UserDAO;
import entity.User;
import util.DBUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class RegisterScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RegisterScreen() {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton registerButton = new JButton("注册");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));  // GridLayout 布局
        inputPanel.add(new JLabel("用户名："));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("密码："));
        inputPanel.add(passwordField);
        inputPanel.add(registerButton);

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.CENTER);

        setTitle("用户注册");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名或密码不能为空！", "输入错误", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Connection connection = DBUtil.getConnection();
            UserDAO userDAO = new UserDAO(connection);

            if (userDAO.getUser(username) != null) {
                JOptionPane.showMessageDialog(this, "用户名已存在！", "注册失败", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User user = new User(username, password);
            userDAO.saveUser(user);

            JOptionPane.showMessageDialog(this, "注册成功！", "注册成功", JOptionPane.INFORMATION_MESSAGE);
            dispose(); 
            new LoginScreen();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据库连接失败，请稍后再试！", "注册失败", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new RegisterScreen();
    }
}
