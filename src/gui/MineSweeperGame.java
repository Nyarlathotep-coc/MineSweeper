package gui;

import dao.GameStatsDAO;
import dao.UserDAO;
import entity.GameStats;
import entity.GridCell;
import entity.GridPosition;
import service.GameService;
import util.DBUtil;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class MineSweeperGame extends JFrame {
    private String username; 
    private int userId;
    private static final int GRID_SIZE = 10; 
    private JButton[][] gridButtons; 
    private JPanel gridPanel;
    private GameService gameService;

    private int totalCells;
    private int totalMines;
    private int remainingCells;
    private int remainingMines;

    private GameStatsDAO gameStatsDAO;
    private GameStats gameStats;

    public MineSweeperGame(String username) {
        this.username = username;

        try {
            Connection connection = DBUtil.getConnection();
            UserDAO userDAO = new UserDAO(connection);
            userId = userDAO.getUserId(username); 

            gameStatsDAO = new GameStatsDAO(connection);
            gameStats = gameStatsDAO.getGameStats(userId);

            if (gameStats == null) {
                gameStats = new GameStats();
                gameStats.setWins(0);
                gameStats.setLosses(0);
            }

            gameService = new GameService();
            gameService.startNewGame(userId, GRID_SIZE, 10); 

            totalCells = GRID_SIZE * GRID_SIZE;
            totalMines = 10;
            remainingCells = totalCells - totalMines;
            remainingMines = totalMines;

            setTitle("扫雷游戏");

            setSize(600, 600);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setLayout(new BorderLayout());

            gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
            gridButtons = new JButton[GRID_SIZE][GRID_SIZE];

            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    final GridPosition gridPosition = new GridPosition(row, col);

                    final JButton button = new JButton();
                    button.setPreferredSize(new Dimension(40, 40));
                    button.setText("");
                    button.addActionListener(new ButtonClickListener(gridPosition));

                    button.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            if (e.getButton() == MouseEvent.BUTTON3) {
                                markCell(button, gridPosition);
                            }
                        }
                    });

                    gridButtons[row][col] = button;
                    gridPanel.add(button);
                }
            }

            add(gridPanel, BorderLayout.CENTER);

            setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace(); 
            JOptionPane.showMessageDialog(this, "数据库连接失败，请检查配置！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ButtonClickListener implements ActionListener {
        private GridPosition gridPosition;

        public ButtonClickListener(GridPosition gridPosition) {
            this.gridPosition = gridPosition;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int row = gridPosition.getRow();
            int col = gridPosition.getCol();
            GridCell cell = gameService.getGame().getGrid()[row][col];
            JButton button = (JButton) e.getSource();

            if (cell.hasMine()) {
                button.setText("BOOM!");
                button.setBackground(Color.RED); 
                button.setForeground(Color.WHITE); 
                button.setEnabled(false);

                gameService.getGame().endGame();

                gameStats.incrementLosses();

                showGameOverPanel();

            } else {
                button.setText(String.valueOf(cell.getSurroundingMines()));
                button.setEnabled(false); 

                gameService.getGame().cellRevealed();
                remainingCells--; 
            }

            cell.setRevealed(true);

            if (gameService.getGame().isGameOver()) {
                showGameOverPanel();
                System.out.println("Wins: " + gameStats.getWins());
                System.out.println("Losses: " + gameStats.getLosses());
            }
        }

        private void showGameOverPanel() {
            int remainingCells = MineSweeperGame.this.remainingCells;
            int remainingMines = MineSweeperGame.this.remainingMines;

            gameStatsDAO.updateGameStats(userId, gameStats);

            GameOverPanel.showGameOverScreen(totalCells, totalMines, remainingCells, remainingMines,
                    gameStats.getWins(), gameStats.getLosses());
        }
    }

    private void markCell(JButton button, GridPosition gridPosition) {
        GridCell cell = gameService.getGame().getGrid()[gridPosition.getRow()][gridPosition.getCol()];


        if (button.getText().equals("")) {
            button.setText("🚩");
            button.setForeground(Color.RED); 
        } else if (button.getText().equals("🚩")) {
            button.setText("?"); 
            button.setForeground(Color.BLUE); 
        } else {
            button.setText("");
        }
    }
}
