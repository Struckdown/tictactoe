package cs.ualberta.cmput402.tictactoe;

import cs.ualberta.cmput402.tictactoe.board.Board;
import cs.ualberta.cmput402.tictactoe.board.Board.Player;
import cs.ualberta.cmput402.tictactoe.board.exceptions.InvalidMoveException;

import java.util.Scanner;

/**
 * Created by snadi on 2018-07-18.
 * Edited by braedy on 2019-01-16.
 */
public class TicTacToeGame {

    private Board board;

    public TicTacToeGame(){
        board = new Board();
    }

    public void promptNextPlayer(){
        switch(board.getCurrentPlayer()){
            case X:
                System.out.println("It's player " + board.getSymbol(board.getCurrentPlayer()) + "'s turn. Please enter the coordinates of your next move as x,y: ");
                break;
            case O:
                System.out.println("It's player " + board.getSymbol(board.getCurrentPlayer()) + "'s turn. Please enter the coordinates of your next move as x,y: ");
                break;

        }
    }

    public void promptPlayAgain(){
      System.out.println("Do you want to play again? (y/n)");
    }

    public void playGame(){
        Scanner keyboardScanner = new Scanner(System.in);

        boolean playAgain = true;
        while (playAgain){
            while (board.getWinner() == null){
                board.printBoard();
                promptNextPlayer();
                String line = keyboardScanner.nextLine();
                String input[] = line.split(",");
                try {
                    board.playMove(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
                } catch (InvalidMoveException e) {
                    System.out.println("Invalid coordinates. Try again");
                    promptNextPlayer();
                }
            }

            board.printBoard();
            System.out.println("Player " + board.getWinner() + " has won the game!");

            promptPlayAgain();
            String line = keyboardScanner.nextLine();
            while (line.equals("y") && line.equals("n")){
                promptPlayAgain();
                line = keyboardScanner.nextLine();
            }

            if (line.equals("y")){
                board = new Board();
            } else {
                playAgain = false;
            }
        }
    }

    public static void main(String args[]){
        TicTacToeGame game = new TicTacToeGame();
        game.playGame();
    }
}
