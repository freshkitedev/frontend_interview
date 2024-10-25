package Model;

// Class representing the game board
public class Board {
    private char[][] board;
    private int size;

    // Constructor that initializes the board based on the size provided by the user
    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        initializeBoard();
    }

    // Initialize the board with '-' indicating empty cells
    public void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the current state of the board
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Check if a move is valid (empty cell)
    public boolean isValidMove(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }
        return board[row][col] == '-';
    }

    // Make a move, placing the player's symbol at the specified location
    public void makeMove(int row, int col, char symbol) {
        if (isValidMove(row, col)) {
            board[row][col] = symbol;
        }
    }

    // Check if the board is full (for detecting a draw)
    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if a player has won (same symbol in a row, column, or diagonal)
    public boolean checkWin(char symbol) {
        return (checkRows(symbol) || checkColumns(symbol) || checkDiagonals(symbol));
    }

    // Check if the player has won by filling any row
    private boolean checkRows(char symbol) {
        for (int i = 0; i < size; i++) {
            boolean win = true;
            for (int j = 0; j < size; j++) {
                if (board[i][j] != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }
        return false;
    }

    // Check if the player has won by filling any column
    private boolean checkColumns(char symbol) {
        for (int i = 0; i < size; i++) {
            boolean win = true;
            for (int j = 0; j < size; j++) {
                if (board[j][i] != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }
        return false;
    }

    // Check if the player has won by filling either diagonal
    private boolean checkDiagonals(char symbol) {
        boolean win1 = true, win2 = true;
        for (int i = 0; i < size; i++) {
            if (board[i][i] != symbol) win1 = false;
            if (board[i][size - 1 - i] != symbol) win2 = false;
        }
        return win1 || win2;
    }
}

