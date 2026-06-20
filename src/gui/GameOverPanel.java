package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOverPanel extends JPanel {

    public GameOverPanel(int totalCells, int totalMines, int remainingCells, int remainingMines, int wins, int losses) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("游戏结束", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        add(titleLabel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(6, 1));

        statsPanel.add(new JLabel("成功游戏次数: " + wins));
        statsPanel.add(new JLabel("失败游戏次数: " + losses));
        statsPanel.add(new JLabel("总格子数: " + totalCells));
        statsPanel.add(new JLabel("总雷数: " + totalMines));
        statsPanel.add(new JLabel("剩余格子数: " + remainingCells));
        statsPanel.add(new JLabel("剩余雷数: " + remainingMines));

        add(statsPanel, BorderLayout.CENTER);

        JButton newGameButton = new JButton("开始新游戏");
        newGameButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(GameOverPanel.this);
                topFrame.dispose();
                
                new WelcomeScreen();
            }
        });
        add(newGameButton, BorderLayout.SOUTH);
    }

    public static void showGameOverScreen(int totalCells, int totalMines, int remainingCells, int remainingMines, int wins, int losses) {
        JFrame gameOverFrame = new JFrame("游戏结束");
        GameOverPanel panel = new GameOverPanel(totalCells, totalMines, remainingCells, remainingMines, wins, losses);
        gameOverFrame.add(panel);
        gameOverFrame.setSize(600, 600);
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setVisible(true);
    }
}
