package entity;

import java.util.Random;

public class Game {
    private boolean gameOver;
    private boolean gameWon;
    private GridCell[][] grid;
    private int gridSize;
    private int mineCount;
    private String status;
    private int userId;
    private int remainingCellsToOpen; 

    public Game(int userId, int gridSize, int mineCount) {
        this.userId = userId;
        this.gridSize = gridSize;
        this.mineCount = mineCount;
        this.status = "In Progress";
        this.grid = new GridCell[gridSize][gridSize];
        this.remainingCellsToOpen = gridSize * gridSize - mineCount;
        initialize();
    }

    public void initialize() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = new GridCell();
            }
        }

        Random rand = new Random();
        int minesPlaced = 0;
        while (minesPlaced < mineCount) {
            int row = rand.nextInt(gridSize);
            int col = rand.nextInt(gridSize);

            if (!grid[row][col].hasMine()) {
                grid[row][col].setHasMine(true);
                minesPlaced++;
            }
        }

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j].hasMine()) {
                    continue;
                }

                int surroundingMines = 0;
                for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
                    for (int colOffset = -1; colOffset <= 1; colOffset++) {
                        int row = i + rowOffset;
                        int col = j + colOffset;
                        if (row >= 0 && col >= 0 && row < gridSize && col < gridSize && grid[row][col].hasMine()) {
                            surroundingMines++;
                        }
                    }
                }
                grid[i][j].setSurroundingMines(surroundingMines);
            }
        }
    }

    public void endGame() {
        this.gameOver = true;
        setStatus("Game Over");
    }

    public void winGame() {
        this.gameWon = true;
        setStatus("You Win!");
    }

    public void cellRevealed() {
        remainingCellsToOpen--;
        checkWinCondition();
    }

    private void checkWinCondition() {
        if (remainingCellsToOpen == 0) {
            winGame();
        }
    }

    public GridCell[][] getGrid() {
        return grid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }
}
