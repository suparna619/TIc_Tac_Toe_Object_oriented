package com.TTT;

import java.util.Scanner;

/**
 * Created by suparnad on 3/27/2015.
 */
public class GameRunner {
    private Board board;
    private GameState currentState;
    private Seed currentPlayer;
    private static Scanner in = new Scanner(System.in);

    public GameRunner(){
        board = new Board();
        initGame();
        System.out.println(runGame());
    }

    private String runGame() {
        do {
            playerMove(currentPlayer);
            board.paint();
            updateGame(currentPlayer);
            if (currentState == GameState.CROSS_WON)
                return System.lineSeparator()+"Congratulation Player 'X'..you won!";
            else if (currentState == GameState.NOUGHT_WON)
                return System.lineSeparator()+"Congratulation Player 'O'..you won!";
            else if (currentState == GameState.DRAW)
                return System.lineSeparator()+"It's Draw!";

            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
        } while (currentState == GameState.PLAYING);
        return null;
    }

    private void updateGame(Seed theSeed) {
        if (board.hasWon(theSeed))
            currentState = (theSeed == Seed.CROSS) ?
                    GameState.CROSS_WON : GameState.NOUGHT_WON;
        else if (board.isDraw())
            currentState = GameState.DRAW;
    }

    private void playerMove(Seed theSeed) {
        boolean validInput = false;
        do {
            if (theSeed == Seed.CROSS) {
                System.out.println("\nPlayer 'X', enter your move (row[1-3] column[1-3]): ");
            } else {
                System.out.println("\nPlayer 'O', enter your move (row[1-3] column[1-3]): ");
            }
            int row = in.nextInt() - 1;
            int col = in.nextInt() - 1;
            if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLUMNS
                    && board.cells[row][col].content == Seed.EMPTY) {
                board.cells[row][col].content = theSeed;
                board.currentRow = row;
                board.currentColumn = col;
                validInput = true;
            } else {
                System.out.println("This move at (" + (row + 1) + "," + (col + 1)
                        + ") is not valid. Try again...");
            }
        } while (!validInput);
    }

    private void initGame() {
        board.init();
        currentPlayer = Seed.CROSS;
        currentState = GameState.PLAYING;
    }
}
