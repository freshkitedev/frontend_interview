import java.util.Scanner;

/**
 * tic_tak_toe
 */

public class tic_tak_toe {
    private static Scanner scan;

    static void play_game(char [][] game) {
        for (int r = 0; r < game.length; r++ ) {
            for (int c = 0; c < game[r].length; c++ ) {
                game[r][c] = ' ';
            }
        }
        
        char player = 'x';
        int play_count = 0;
        while (play_count < game.length * game[0].length) {
            print_board(game);
            System.out.println("Enter position row col:");
            int row = scan.nextInt();
            int col = scan.nextInt();
            System.out.println(row + " " + col);
            if (row >= game.length || col >= game[0].length) {
                System.out.println("Position out of range enter alternate position");
                continue;
            }
            if ( game[row][col] == ' ') {
                game[row][col] = player;
                if (have_won(game, player, row, col)) {
                    System.out.println("Player " + player + " won the game");
                    print_board(game);
                    return;
                }
                player = (player == 'x') ? 'o': 'x';
                play_count++;
            } else {
                System.out.println("Position already filled enter alternate position");
            }
        }
        System.out.println("Game draw");
        scan.close();
    }

    static void print_board(char [][] game) {
        for (int r = 0; r < game.length; r++ ) {
            for (int c = 0; c < game[r].length; c++ ) {
                System.out.print(game[r][c]);
                System.out.print('|');
            }
            System.out.println();
        }
    }

    static boolean have_won(char [][] game, char p, int row, int col ) {
        boolean hw = true;
        for (int c = 0; c < game[row].length; c++) {
            if (game[row][c] != p) {
                hw = false;
                break;
            }
        }
        if (hw == true) {
            return true;
        }
        hw = true;

        for (int r = 0; r < game.length; r++) {
            if (game[r][col] != p) {
                hw = false;
                break;
            }
        }
        if (hw == true) {
            return true;
        }
        hw = true;
        
        int drow = 0;
        int dcol = 0;
        while (drow < game.length && dcol < game[drow].length) {
            if (game[drow][dcol] != p) {
                hw = false;
                break;
            }
            drow++;
            dcol++;
        }
        if (hw == true) {
            return true;
        }
        hw = true;

        drow = 0;
        dcol = game[drow].length - 1;
        while (drow < game.length && dcol >= 0) {
            if (game[drow][dcol] != p) {
                hw = false;
                break;
            }
            drow++;
            dcol--;
        }
        return hw;
    }

    public static void main(String[] args) {
        System.out.println("Enter board size of square matrix(one value):");
        scan = new Scanner(System.in);
        int size = scan.nextInt();
                
        char [][] board = new char[size][size];
        play_game(board);
        scan.close();
    }
}
