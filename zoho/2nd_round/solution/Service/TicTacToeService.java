package Service;

import DAL.TicTacToeRepository;
import Model.Board;
import Model.Player;

public class TicTacToeService {
    private TicTacToeRepository repository;
    private Player currentPlayer;
    private Player player1, player2;

    // Constructor to initialize the game with the repository and two players
    public TicTacToeService(TicTacToeRepository repository, Player player1, Player player2, int boardSize) {
        this.repository = repository;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        startNewGame(boardSize);
    }

    // Method to start a new game by creating a fresh board
    public void startNewGame(int boardSize) {
        Board board = new Board(boardSize);
        repository.save(board);
    }

    // Method to make a move and update the board if the move is valid
    public boolean playMove(int row, int col) {
        Board board = repository.load();
        if (board.isValidMove(row, col)) {
            board.makeMove(row, col, currentPlayer.getSymbol());
            repository.save(board);
            return true;
        }
        return false;
    }

    // Method to switch to the next player
    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    // Method to get the current player
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    // Method to check if the current player has won
    public boolean checkWinner() {
        Board board = repository.load();
        return board.checkWin(currentPlayer.getSymbol());
    }

    // Method to check if the game has ended in a draw
    public boolean isDraw() {
        Board board = repository.load();
        return board.isFull() && !checkWinner();
    }

    // Method to display the current state of the board
    public void displayBoard() {
        Board board = repository.load();
        board.printBoard();
    }
}
