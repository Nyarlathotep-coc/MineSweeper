//从我开始运行！只支持java8+！
package gui;

import javax.swing.*;

import entity.Session;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {

    public WelcomeScreen() {
    	final String username = Session.getLoggedInUsername(); 
    	
        setTitle("MineSweeper");

        setSize(600, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(null);

        JLabel titleLabel1 = new JLabel("MineSweeper", SwingConstants.CENTER);
        titleLabel1.setFont(new Font("Brush Script MT", Font.BOLD, 79));
        titleLabel1.setForeground(new Color(173, 216, 230));
        titleLabel1.setBounds(48, 87, 500, 79);

        JLabel titleLabel2 = new JLabel("MineSweeper", SwingConstants.CENTER);
        titleLabel2.setFont(new Font("Brush Script MT", Font.BOLD, 80));
        titleLabel2.setForeground(Color.BLACK);
        titleLabel2.setBounds(50, 90, 500, 80);

        titlePanel.add(titleLabel1);
        titlePanel.add(titleLabel2);

        JButton startButton = new JButton("开始游戏");
        startButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);  

        JButton loginButton = new JButton("登       录");
        loginButton.setFont(new Font("微软雅黑", Font.PLAIN, 20)); 
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton registerButton = new JButton("注       册");
        registerButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                new MineSweeperGame(username);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen();
                dispose(); 
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterScreen();
                dispose();  
            }
        });

        panel.add(Box.createVerticalStrut(50)); 
        panel.add(titlePanel); 
        panel.add(Box.createVerticalStrut(30));
        panel.add(startButton);
        panel.add(Box.createVerticalStrut(20)); 
        panel.add(loginButton);  
        panel.add(Box.createVerticalStrut(20));
        panel.add(registerButton);

        panel.add(Box.createVerticalGlue());
        
        add(panel, BorderLayout.CENTER);
       
        setVisible(true);
    }

    public static void main(String[] args) {
        new WelcomeScreen();
    }
}
