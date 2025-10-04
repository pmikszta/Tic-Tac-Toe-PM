package org.example;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void testGetMoveValid() {
        String input = "5\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        Player player = new Player('X', scanner);
        Board board = new Board();

        int move = player.getMove(board);
        assertEquals(5, move, "Player should select position 5");
    }

    @Test
    void testGetMoveInvalidThenValid() {
        String input = "abc\n10\n3\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        Player player = new Player('O', scanner);
        Board board = new Board();

        int move = player.getMove(board);
        assertEquals(3, move, "Player should eventually select valid position 3");
    }
}
