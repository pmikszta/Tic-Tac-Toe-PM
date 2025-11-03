package org.example;

import java.util.Scanner;

//class for the player in the game
public class Player {
    private char symbol;
    private Scanner scanner;

    public Player(char symbol, Scanner scanner) {
        this.symbol = symbol;
        this.scanner = scanner;
    }

    public char getSymbol() {
        return symbol;
    }
// checks if move is legal
    public int getMove(Board board) {
        while (true) {
            System.out.print("\nPlayer " + symbol + ", enter a number (1-9): ");
            String input = scanner.nextLine();
            try {
                int move = Integer.parseInt(input);
                if (board.isValidMove(move)) {
                    return move;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
