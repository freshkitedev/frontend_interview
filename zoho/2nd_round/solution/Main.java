import DAL.InMemoryTicTacToeRepository;
import Model.Player;
import Service.TicTacToeService;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the size of the board
        System.out.println("Enter the size of the Tic-Tac-Toe board:");
        int boardSize = scanner.nextInt();

        // Initialize two players with their names and symbols
        Player player1 = new Player("Jaan", 'X');
        Player player2 = new Player("Jenni", 'O');

        // Initialize repository and service for the game
        InMemoryTicTacToeRepository repository = new InMemoryTicTacToeRepository();
        TicTacToeService service = new TicTacToeService(repository, player1, player2, boardSize);
        System.out.println("Welcome to Tic-Tac-Toe");

        // Game loop continues until someone wins or the game ends in a draw
        while (true) {
            service.displayBoard();
            System.out.println(service.getCurrentPlayer().getName() + ", enter your move (row and column):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            // Try to make the move, check if it's valid
            if (!service.playMove(row, col)) {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            // Check if the current player has won
            if (service.checkWinner()) {
                service.displayBoard();
                System.out.println("Congratulations! " + service.getCurrentPlayer().getName() + " wins!");
                break;
            }

            // Check if the game has ended in a draw
            if (service.isDraw()) {
                service.displayBoard();
                System.out.println("It's a draw!");
                break;
            }

            // Switch to the next player for their turn
            service.switchPlayer();
        }

        scanner.close();
    }
}
