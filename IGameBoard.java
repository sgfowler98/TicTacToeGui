package cpsc2150.hw5;

//Sam Fowler
//CPSC2150
//HW4

/**
 * IGameBoard represents a 2-dimensional gameboard that has characters
 * on it as markers (X, O). No space on the board can have multiple
 * players, and there can be a clear winner. Board is NUM_ROWS x
 NUM_COLS in size
 *
 * Initialization ensures: the Board does not have any markers on it
 * Defines: NUM_ROWS: Z
 * Defines: NUM_COLS: Z
 * Constraints: 0< NUM_ROWS <= MAX_SIZE
 * 0< NUM_COLS <= MAX_SIZE
 */
public interface IGameBoard {
    int MAX_SIZE = 100;
    // add your contracts

    /**
     * Checks if a BoardPosition is occupied
     * @param pos the BoardPosition to be checked
     * @return true if unoccupied, false if occupied
     * @requires pos to be a BoardPosition with 0 < row < NUM_ROWS, 0 < col < NUM_ROWS,
     * and marker either 'X' or 'O'
     * @ensures pos = #pos
     */
    boolean checkSpace(BoardPosition pos);
    // add your contracts

    /**
     * Places a marker from a BoardPosition into the GameBoard
     * @param lastPos BoardPosition to be placed
     * @requires lastPos to be unoccupied, be within the bounds of the GameBoard,
     * and have marker 'X' or 'O'
     * @ensures lastPos = #lastPos
     */
    void placeMarker(BoardPosition lastPos);
    // add your contracts

    /**
     * Checks if the last placement resulted in a game win
     * @param lastPos BoardPosition to be checked for a win
     * @return true if there was a winner, false if not
     * @requires lastPos to be a recently-placed BoardPosition
     * @ensures lastPos = #lastPos
     */
    boolean checkForWinner(BoardPosition lastPos);
}