package org.example;

public class Board {
    private char[] cells;
    //creates the board
    public Board() {
        cells = new char[10];  // Index 1–9 used
         reset();
    }

    //sets cells 1-9
    public void reset() {
        for (int i = 1; i <= 9; i++) {
            cells[i] = (char) ('0' + i);
        }
    }

    //Prints the state of the current board
     public void print() {
        
        System.out.println("\n");
        System.out.println("    " + cells[1] + "  |  " + cells[2] + "  |  " + cells[3]);
        System.out.println("  -----+-----+-----");
        System.out.println("    " + cells[4] + "  |  " + cells[5] + "  |  " + cells[6]);
        System.out.println("  -----+-----+-----");
        System.out.println("    " + cells[7] + "  |  " + cells[8] + "  |  " + cells[9]);
        System.out.println("\n");
    }
    //checks to see if cell is used
    public boolean  isValidMove(int pos) {
        return pos >=  1 && pos <= 9 && cells[pos] != 'X' && cells[pos] != 'O';
    }

    public void makeMove(int pos, char player) {
        cells[pos] = player;
    }
    //ends game if board is full
    public boolean isFull() {
         for (int i = 1; i <= 9; i++) {
            if  (cells[i] != 'X' && cells[i] != 'O') return false;
        }
        return true;
    }
    //stores all the winning combos
    public boolean checkWinner(char player) {
        int[][] combos = {
            {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
            {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
            {1, 5, 9}, {3, 5, 7}
        };

         for (int[] c : combos) {
            if (cells[c[0]] == player && cells[c[1]] == player && cells[c[2]] == player) {
                 return true;
            }
        }
        return false;
    }
}
