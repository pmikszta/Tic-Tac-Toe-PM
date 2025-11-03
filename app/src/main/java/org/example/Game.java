package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    private Board board;
    private Player playerX;
    private Player playerO;
    private Scanner scanner;

    private int xWins = 0;
    private int oWins = 0;
    private int ties  = 0;

    // this tracks who starts NEXT game
    private Player startingPlayer;

    public Game() {
        scanner = new Scanner(System.in);
        board = new Board();
        playerX = new Player('X', scanner);
        playerO = new Player('O', scanner);

        // X starts the very first game
        startingPlayer = playerX;
    }

    public void start() {
        System.out.println("Welcome to Tic-Tac-Toe!\n");

        boolean playAgain;
        do {
            board.reset();
            board.print();

            Player currentPlayer = startingPlayer;
            boolean gameEnded = false;
            Player loser = null;

            while (!gameEnded) {
                int move = currentPlayer.getMove(board);
                board.makeMove(move, currentPlayer.getSymbol());
                board.print();

                if (board.checkWinner(currentPlayer.getSymbol())) {
                    System.out.println("\n Player " + currentPlayer.getSymbol() + " wins! \n");

                    if (currentPlayer == playerX) xWins++; else oWins++;
                    loser = (currentPlayer == playerX) ? playerO : playerX;
                    gameEnded = true;
                } else if (board.isFull()) {
                    System.out.println("\nIt's a draw!\n");
                    ties++;
                    loser = null; // tie = no loser
                    gameEnded = true;
                } else {
                    currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
                }
            }

            printScoreboard();

            // if loser exists → they start next round
            if (loser != null) {
                startingPlayer = loser;
            } // else tie → startingPlayer stays same

            playAgain = askPlayAgain();
        } while (playAgain);
        // commented out save results to disk
        // saveLogToDisk();
        System.out.println("\nThanks for playing!");
        scanner.close();
    }

    private void printScoreboard() {
        System.out.println("========== SCOREBOARD ==========");
        System.out.println("X wins : " + xWins);
        System.out.println("O wins : " + oWins);
        System.out.println("Ties   : " + ties);
        System.out.println("================================\n");
    }
//save results to file if needed
    private void saveLogToDisk() {
        try (FileWriter fw = new FileWriter("game_log.txt")) {
            fw.write("Final Tic-Tac-Toe Game Statistics\n");
            fw.write("---------------------------------\n");
            fw.write("X wins : " + xWins + "\n");
            fw.write("O wins : " + oWins + "\n");
            fw.write("Ties   : " + ties + "\n");
        } catch (IOException e) {
            System.out.println("Error writing game log to disk: " + e.getMessage());
        }
    }

    private boolean askPlayAgain() {
        while (true) {
            System.out.print("\nDo you want to play again? (Y = Yes, N = Exit): ");
            String response = scanner.nextLine().trim();

            if (response.equalsIgnoreCase("Y")) {
                return true;
            } else if (response.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println( "Invalid input. Please enter 'Y' to play again or 'N' to exit.");
            }
        }
    }
}
