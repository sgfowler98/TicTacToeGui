package cpsc2150.hw5;

/**
 * Sam Fowler
 * Homework 2 - Extended Tic Tac Toe
 */

public class BoardPosition {

    private int row;
    private int col;
    private char player;

    /**
     * constructor for a BoardPosition
     * @param rowInput row of the specific board position
     * @param colInput column of the specific board position
     * @param playerInput player identity (i.e. 'X' or 'O')
     * @requires
     * 0 <= rowInput < NUM_ROWS, 0 <= colInput < NUM_COLS, and playerInput == 'X' || 'O'
     * @ensures a BoardPosition object will be created
     */
    public BoardPosition(int rowInput, int colInput, char playerInput) {
        row = rowInput;
        col = colInput;
        player = playerInput;
    }

    /**
     * accessor for the row of a BoardPosition
     * @return row value for a board position
     * @ensures the row of the BoardPosition will be returned
     */
    public int getRow() {
        return row;
    }

    /**
     * accessor for the column of a BoardPosition
     * @return column value for a board position
     * @ensures the column of the BoardPosition will be returned
     */
    public int getColumn() {
        return col;
    }

    /**
     * accessor for the player identity of a BoardPosition
     * @return player identity for a board position
     * @ensures the player marker of the BoardPosition will be returned
     */
    public char getPlayer() {
        return player;
    }

    /**
     * Equals method for BoardPosition
     * @param obj object to be compared
     * @requires obj to be of type BoardPosition
     * @return true if BoardPositions are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BoardPosition))
            return false;
        BoardPosition b = (BoardPosition) obj;
        return (this.getRow() == b.getRow()) && (this.getColumn() == b.getColumn()) && (this.getPlayer() == b.getPlayer());
    }
}
