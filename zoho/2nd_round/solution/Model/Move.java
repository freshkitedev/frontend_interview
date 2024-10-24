package Model;

//class representing a move made by a player
public class Move {
    private int row;
    private int col;

    //constructor initializes the move with row and column
    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    //getter for row and col
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
