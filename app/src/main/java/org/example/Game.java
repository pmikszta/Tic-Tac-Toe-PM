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
    private int ties = 0;

    // tracks who starts the next game
    private Player startingPlayer;

    public Game() {
        scanner = new Scanner(System.in);
        board = new Board();
    }

    public void start() {
        System.out.println("Welcome to Tic-Tac-Toe!\n");

        int gameMode = selectGameMode(); // Menu for selecting game mode

        if (gameMode == 1) { // Human vs Human
            playerX = new Player('X', scanner);
            playerO = new Player('O', scanner);
            startingPlayer = playerX;
        } else { // Human vs Computer
            playerX = new Player('X', scanner);
            playerO = new ComputerPlayer('O', 'X');
            selectStartingPlayer(); // Ask if human or computer goes first
        }

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

                    if (currentPlayer == playerX) xWins++;
                    else oWins++;

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

            // If loser exists → they start next round
            if (loser != null) {
                startingPlayer = loser;
            } // else tie → startingPlayer stays same

            playAgain = askPlayAgain();
        } while (playAgain);

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

    private boolean askPlayAgain() {
        while (true) {
            System.out.print("\nDo you want to play again? (Y = Yes, N = Exit): ");
            String response = scanner.nextLine().trim();

            if (response.equalsIgnoreCase("Y")) {
                return true;
            } else if (response.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'Y' to play again or 'N' to exit.");
            }
        }
    }

    private int selectGameMode() {
        while (true) {
            System.out.println("Select game mode:");
            System.out.println("1. Human vs Human");
            System.out.println("2. Human vs Computer");
            System.out.print("Enter 1 or 2: ");
            String input = scanner.nextLine().trim();
            if (input.equals("1") || input.equals("2")) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void selectStartingPlayer() {
        while (true) {
            System.out.print("Do you want to go first? (Y = Yes / N = No): ");
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("Y")) {
                startingPlayer = playerX; // human goes first
                break;
            } else if (choice.equalsIgnoreCase("N")) {
                startingPlayer = playerO; // computer goes first
                break;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }
    }

    // Optional: Save game stats to disk (currently commented out)
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
}
