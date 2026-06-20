package entity;

public class GridCell {
 private boolean hasMine; 
 private int surroundingMines; 
 private boolean revealed;
 private boolean flagged; 
 
 public GridCell() {
     this.hasMine = false;
     this.surroundingMines = 0;
     this.revealed = false;
     this.flagged = false;
 }

 public boolean hasMine() {
     return hasMine;
 }

 public void setHasMine(boolean hasMine) {
     this.hasMine = hasMine;
 }

 public int getSurroundingMines() {
     return surroundingMines;
 }

 public void setSurroundingMines(int surroundingMines) {
     this.surroundingMines = surroundingMines;
 }

 public boolean isRevealed() {
     return revealed;
 }

 public void setRevealed(boolean revealed) {
     this.revealed = revealed;
 }

 public boolean isFlagged() {
     return flagged;
 }

 public void setFlagged(boolean flagged) {
     this.flagged = flagged;
 }
}
