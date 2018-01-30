package cpsc2150.hw5;

/**
 * @invariant 0 < NUM_ROWS <= MAX_SIZE
 * @invariant 0 < NUM_COLS <= MAX_SIZE
 * Correspondence NUM_ROWS = NUM_ROWS
 * Correspondence NUM_COLS = NUM_COLS
 * Correspondence this = board[NUM_ROWS][NUM_COLS]
 */

import java.util.Arrays;

public class GameBoardFast implements IGameBoard {

    private char[][] board;
    private int NUM_ROWS;
    private int NUM_COLS;
    private int MARKERS_TO_WIN;

    /**
     * Creates a character grid, with every member initialized to ' '
     * @requires 0 < rows < MAX_SIZE, 0 < cols < MAX_SIZE, and numToWin <= rows and cols
     * @ensures a GameBoardFast will be created
     */
    public GameBoardFast(int rows, int cols, int numToWin) {
        NUM_ROWS = rows;
        NUM_COLS = cols;
        board = new char[NUM_ROWS][NUM_COLS];
        for (char[] row : board)
            Arrays.fill(row, ' ');
        MARKERS_TO_WIN = numToWin;
    }

    public boolean checkSpace(BoardPosition pos) {
        if (pos.getRow() > NUM_ROWS || pos.getColumn() > NUM_COLS || pos.getRow() < 0 || pos.getColumn() < 0)
            return false;
        return board[pos.getRow()][pos.getColumn()] == ' ';
    }

    public void placeMarker(BoardPosition marker) {
        board[marker.getRow()][marker.getColumn()] = marker.getPlayer();
    }

    public boolean checkForWinner(BoardPosition lastPos) {
        return checkHorizontalWin(lastPos) || checkVerticalWin(lastPos) || checkDiagonalWin(lastPos);
    }

    /**
     * checks for a win by checking for horizontal markers in a row
     * @param lastPos last marker that was placed
     * @return true if there were MARKERS_TO_WIN markers in a horizontal row
     * @requires lastPos to be a BoardPosition with 0 < row < NUM_ROWS, 0 < col < NUM_COLS,
     *              and either 'X' or 'O' as the marker
     * @ensures board = #board
     */
    private boolean checkHorizontalWin(BoardPosition lastPos) {
        int checkCol = lastPos.getColumn();
        int count = 1;

        //loop to check above lastPos
        while(checkCol > 0) {
            if (board[lastPos.getRow()][checkCol-1] == lastPos.getPlayer()) {
                count++;
                checkCol--;
            }
            else
                break;
        }

        //reset checkCol to make sure left hand markers aren't double-counted
        checkCol = lastPos.getColumn();

        //loop to check below lastPos
        while (checkCol < NUM_COLS-1) {
            if (board[lastPos.getRow()][checkCol+1] == lastPos.getPlayer()) {
                count++;
                checkCol++;
            }
            else
                break;
        }
        return count >= MARKERS_TO_WIN;
    }


    /**
     * checks for a win by checking for vertical markers in a row
     * @param lastPos last marker that was placed
     * @return true if there were MAKRERS_TO_WIN markers in a vertical row
     * @requires lastPos to be a BoardPosition with 0 < row < NUM_ROWS, 0 < col < NUM_COLS,
     *              and either 'X' or 'O' as the marker
     * @ensures board = #board
     */
    private boolean checkVerticalWin(BoardPosition lastPos) {
        int checkRow = lastPos.getRow();
        int count = 1;

        //loop to check to the left of lastPos
        while (checkRow > 0) {
            if (board[checkRow-1][lastPos.getColumn()] == lastPos.getPlayer()) {
                count++;
                checkRow--;
            }
            else
                break;
        }

        //reset checkRow to make sure upper markers aren't double counted
        checkRow = lastPos.getRow();

        //loop to check to the right of lastPos
        while (checkRow < NUM_ROWS-1) {
            if (board[checkRow+1][lastPos.getColumn()] == lastPos.getPlayer()) {
                count++;
                checkRow++;
            }
            else
                break;
        }
        return count >= MARKERS_TO_WIN;
    }

    /**
     * checks for a win by checking for diagonal markers in a row
     * @param lastPos last marker to be placed
     * @return true if there were MAKRERS_TO_WIN markers in a diagonal row
     * @requires lastPos to be a BoardPosition with 0 < row < NUM_ROWS, 0 < col < NUM_COLS,
     *              and either 'X' or 'O' as the marker
     * @ensures board = #board
     */
    private boolean checkDiagonalWin(BoardPosition lastPos) {
        int checkRow = lastPos.getRow();
        int checkCol = lastPos.getColumn();
        int count = 1;

        //section to check for a diagonal going down from left ro right ( \ )
        //loop to check above and to the left of lastPos
        while (checkRow > 0 && checkCol > 0) {
            if (board[checkRow-1][checkCol-1] == lastPos.getPlayer()) {
                count++;
                checkCol--;
                checkRow--;
            }
            else
                break;
        }

        //reset checkCol and checkRow so upper-left markers aren't double-counted
        checkCol = lastPos.getColumn();
        checkRow = lastPos.getRow();

        //loop to check below and to the right of lastPos
        while (checkRow < NUM_ROWS-1 && checkCol < NUM_COLS-1) {
            if (board[checkRow+1][checkCol+1] == lastPos.getPlayer()) {
                count++;
                checkCol++;
                checkRow++;
            }
            else
                break;
        }
        if (count >= MARKERS_TO_WIN)
            return true;

        //section to check for a diagonal going up from left to right ( / )
        //loop to check below and to the left of lastPos
        checkCol = lastPos.getColumn();
        checkRow = lastPos.getRow();
        count = 1;

        while (checkRow > 0 && checkCol < NUM_COLS-1) {
            if (board[checkRow-1][checkCol+1] == lastPos.getPlayer()) {
                count++;
                checkRow--;
                checkCol++;
            }
            else
                break;
        }

        //reset checkRow and checkCol to make sure lower-left markers aren't double counted
        checkCol = lastPos.getColumn();
        checkRow = lastPos.getRow();

        //loop to check above and to the right of lastPos
        while (checkRow < NUM_ROWS-1 && checkCol > 0) {
            if (board[checkRow+1][checkCol-1] == lastPos.getPlayer()) {
                count++;
                checkRow++;
                checkCol--;
            }
            else
                break;
        }
        return count >= MARKERS_TO_WIN;
    }

    /**
     * converts the GameBoardFast into a graphical representation of
     * blank spaces, Xs and Os with the GameBoard grid
     * @return graphical representation of the GameBoardFast
     * @requires nothing
     * @ensures a graphical representation of a GameBoardFast will be returned
     */
    @Override
    public String toString() {
        if (NUM_COLS <= 10) {
            String graphicalBoard = "   ";
            for (int j = 0; j < NUM_COLS; j++) {
                graphicalBoard += j + "|";
            }
            graphicalBoard += "\n";
            for (int i = 0; i < NUM_ROWS; i++) {
                if (i < 10)
                    graphicalBoard += " ";
                graphicalBoard += i + "|";
                for (int j = 0; j < NUM_COLS; j++) {
                    graphicalBoard += board[i][j] + "|";
                }
                graphicalBoard += "\n";
            }
            return graphicalBoard;
        }
        else {
            String graphicalBoard = "   ";
            for (int j = 0; j < NUM_COLS; j++) {
                if (j < 10)
                    graphicalBoard += " " + j + "|";
                else
                    graphicalBoard += j + "|";
            }
            graphicalBoard += "\n";
            for (int i = 0; i < NUM_ROWS; i++) {
                if (i < 10)
                    graphicalBoard += " ";
                graphicalBoard += i + "|";
                for (int j = 0; j < NUM_COLS; j++) {
                    graphicalBoard += " " + board[i][j] + "|";
                }
                graphicalBoard += "\n";
            }
            return graphicalBoard;
        }
    }

}