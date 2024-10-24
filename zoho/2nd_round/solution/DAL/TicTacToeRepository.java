package DAL;

import Model.Board;

//repository interface for saving and retrieving the board state
public interface TicTacToeRepository {
    void save(Board board);
    Board load();
}
