package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {
    private Random random = new Random();
    private boolean isFirstMove = true;
    private char opponentSymbol;

    public ComputerPlayer(char symbol, char opponentSymbol) {
        super(symbol, null); // scanner not needed for computer
        this.opponentSymbol = opponentSymbol;
    }

    @Override
    public int getMove(Board board) {
        // Opportunistic rules
        // 1. First move → pick a corner
        if (isFirstMove) {
            isFirstMove = false;
            int[] corners = {1, 3, 7, 9};
            List<Integer> availableCorners = new ArrayList<>();
            for (int c : corners) {
                if (board.isValidMove(c)) availableCorners.add(c);
            }
            if (!availableCorners.isEmpty()) {
                return availableCorners.get(random.nextInt(availableCorners.size()));
            }
        }

        // 2. Second move → pick center if available
        if (board.isValidMove(5)) {
            return 5;
        }

        // 3. Win if possible
        for (int i = 1; i <= 9; i++) {
            if (board.isValidMove(i)) {
                board.makeMove(i, getSymbol());
                if (board.checkWinner(getSymbol())) {
                    board.makeMove(i, (char) ('0' + i)); // undo temporary move
                    return i;
                }
                board.makeMove(i, (char) ('0' + i)); // undo temporary move
            }
        }

        // 4. Block opponent from winning
        for (int i = 1; i <= 9; i++) {
            if (board.isValidMove(i)) {
                board.makeMove(i, opponentSymbol);
                if (board.checkWinner(opponentSymbol)) {
                    board.makeMove(i, (char) ('0' + i)); // undo temporary move
                    return i;
                }
                board.makeMove(i, (char) ('0' + i)); // undo temporary move
            }
        }

        // 5. Otherwise, pick random available spot
        List<Integer> available = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (board.isValidMove(i)) available.add(i);
        }
        return available.get(random.nextInt(available.size()));
    }
}
