package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComputerPlayerTest {

    private Board board;
    private ComputerPlayer computer;

    @BeforeEach
    void setUp() {
        board = new Board();
        computer = new ComputerPlayer('O', 'X');
    }

    @Test
    void testFirstMovePicksCorner() {
        int move = computer.getMove(board);
        // corners are 1, 3, 7, 9
        assertTrue(move == 1 || move == 3 || move == 7 || move == 9,
                "First move should be a corner");
    }

    @Test
    void testSecondMovePicksCenterIfAvailable() {
        // Simulate first move has already been made
        computer.getMove(board); // first move
        // Clear board so center is available
        board.reset();
        int move = computer.getMove(board);
        assertEquals(5, move, "Second move should pick center if available");
    }

    @Test
    void testComputerTakesWinningMove() {
        // Set up a scenario where computer can win
        board.makeMove(1, 'O');
        board.makeMove(2, 'O');
        int move = computer.getMove(board);
        assertEquals(3, move, "Computer should take winning move at 3");
    }

    @Test
    void testComputerBlocksOpponentWin() {
        // Set up a scenario where opponent can win
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        int move = computer.getMove(board);
        assertEquals(3, move, "Computer should block opponent winning move at 3");
    }

    @Test
    void testComputerChoosesValidMove() {
        // Fill some cells to limit options
        board.makeMove(1, 'X');
        board.makeMove(2, 'O');
        board.makeMove(3, 'X');
        int move = computer.getMove(board);
        assertTrue(board.isValidMove(move), "Computer should always pick a valid move");
    }
}
