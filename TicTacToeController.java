package cpsc2150.hw5;

//Sam Fowler
//CPSC2150
//HW5

import javax.swing.*;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 *
 * This is where you will write code
 *
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and one of the IGameBoard implementations from Homework 4
 * You can choose which IGameBoard implementation to use
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class besides the package name
 */
public class TicTacToeController {
    //our current game that is being played
    private IGameBoard curGame;
    //to track who's turn it is
    private char curPlayer;
    //The screen that provides our view
    private TicTacToeView screen;
    // no other class variables are needed or should be declared


    // add the code and contracts for the constructor of our tic tac toe game

    /**
     * constructor for TicTacToeController
     * @param model GameBoard to be used in the game
     * @param view GUI representation of the GameBoard
     * @requires model to be of dynamic type GameBoardFast or GameBoardMem
     *              and view to be a GUI representation of a GameBoard
     * @ensures a TicTacToeController object will be created and its private data members will be initialized
     */
    TicTacToeController(IGameBoard model, TicTacToeView view)
    {
        curGame = model;
        screen = view;
        curPlayer = 'X';
    }

    //Add the code to respond to a user clicking on a button to try to claim a space
    //User will click on the button at row, col
    //User may click on a space that is not available, they do not get that space
    //We no longer have a main function that controls the flow of the game. Users will click buttons, anf
    //Will handle the events here
    // Make sure to make any changes to the screen needed through publicly available functions
    // When a player wins, display a message. You do not need to reset the game or close the window
    // The players can close the window and restart the game themselves
    // remember your javadoc comments and contracts

    /**
     * Method to handle button presses on the GUI
     * @param row row of the position that was clicked
     * @param col column of the position that was clicked
     * @requires 0 <= row < NUM_ROWS && 0 <= col < NUM_COLS
     * @ensures Both curGame and screen will be updated (or not updated) depending
     *          on the buttons that are pressed
     */
    public void processButtonClick(int row, int col)
    {
        BoardPosition pos = new BoardPosition(row, col, curPlayer);
        if (curGame.checkSpace(pos)) {
            curGame.placeMarker(pos);
            screen.setMarker(pos.getRow(), pos.getColumn(), pos.getPlayer());
            if (curGame.checkForWinner(pos)) {
                JOptionPane.showMessageDialog(screen, "Congratulations to player " + curPlayer + " on their victory!", "Winner!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        else {
            JOptionPane.showMessageDialog(screen, "That space is unavailable, please choose another","Error" ,JOptionPane.ERROR_MESSAGE);
            return; //ensure player isn't switched
        }


        if (curPlayer == 'X')
            curPlayer = 'O';
        else
            curPlayer = 'X';
        screen.setMessage("It is " + curPlayer + "'s turn.");

    }
}
