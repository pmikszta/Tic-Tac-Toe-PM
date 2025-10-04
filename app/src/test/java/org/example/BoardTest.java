package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testResetBoard() {
        board.makeMove(1, 'X');
        board.reset();
        assertTrue(board.isValidMove(1), "Cell 1 should be reset and valid after reset()");
    }

    @Test
    void testValidMove() {
        assertTrue(board.isValidMove(5), "Position 5 should be valid initially");
    }

    @Test
    void testInvalidMoveAfterTaken() {
        board.makeMove(5, 'X');
        assertFalse(board.isValidMove(5), "Position 5 should be invalid after move");
    }

    @Test
    void testMakeMoveUpdatesBoard() {
        board.makeMove(3, 'O');
        assertFalse(board.isValidMove(3), "Position 3 should not be valid after O moves there");
    }

    @Test
    void testCheckWinnerRow() {
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        board.makeMove(3, 'X');
        assertTrue(board.checkWinner('X'), "X should win with top row");
    }

    @Test
    void testCheckWinnerDiagonal() {
        board.makeMove(1, 'O');
        board.makeMove(5, 'O');
        board.makeMove(9, 'O');
        assertTrue(board.checkWinner('O'), "O should win with diagonal 1-5-9");
    }

    @Test
    void testBoardIsFull() {
        for (int i = 1; i <= 9; i++) {
            board.makeMove(i, (i % 2 == 0) ? 'X' : 'O');
        }
        assertTrue(board.isFull(), "Board should be full after all cells are filled");
    }
}