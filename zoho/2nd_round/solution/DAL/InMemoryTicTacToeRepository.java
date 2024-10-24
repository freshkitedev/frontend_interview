package DAL;

import Model.Board;

//In-memory implementation for TicTacToeRepository
public class InMemoryTicTacToeRepository implements TicTacToeRepository{
    private Board board;

    //save the current board state in memory
    @Override
    public void save(Board board) {
        this.board = board;
    }

    //load the current board state from memory
    @Override
    public Board load() {
        return board;
    }
}

