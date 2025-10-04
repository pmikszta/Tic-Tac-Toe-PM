package org.example;

import java.util.Scanner;

import java.util.Scanner;
//main class for the gamm that App.java calls
public class Game {
    private Board board;
    private Player playerX;
    private Player playerO;
    private Scanner scanner;

     public Game() {
        scanner = new Scanner(System.in);
        board = new Board();
        playerX = new Player('X', scanner);
        playerO = new Player('O', scanner);
    }
    //Starts the game
    public void start() {
        System.out.println("Welcome to Tic-Tac-Toe!\n");

        boolean playAgain;
        do {
            board.reset();
            board.print();
             Player currentPlayer = playerX;
            boolean gameEnded = false;

            while (!gameEnded) {
                 int move = currentPlayer.getMove(board);
                board.makeMove(move, currentPlayer.getSymbol());
                board.print();

                if (board.checkWinner(currentPlayer.getSymbol())) {
                    System.out.println("\n Player " + currentPlayer.getSymbol() + " wins! \n");
                    gameEnded = true;
                } else if (board.isFull()) {
                    System.out.println("\nIt's a draw!");
                    gameEnded = true;
                } else {
                    currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
                }
            }
            //game loop
            playAgain = askPlayAgain();
        } while (playAgain);

         System.out.println("\nThanks for playing!");
        scanner.close();
    }
    //error checking loop
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
