package model;

import java.util.HashSet;
import java.util.Set;

public class Sudoku {

    private int[][] board;
    private final int NODES = 9;

    public Sudoku() {
        board = new int[NODES][NODES];
    }

    public void addBoard(int[][] board) {
        this.board = board;
    }

    public int getNumberNodes() {
        return NODES;
    }

    public void addEdge(int row, int col, int value) {
        board[row][col] = value;
    }

    public void removeEdge(int row, int col) {
        board[row][col] = 0;
    }

    public boolean hasEdge(int row, int col) {
        return board[row][col] != 0;
    }

    public int getValue(int row, int col) {
        return board[row][col];
    }

    public boolean isEmpty() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (hasEdge(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isFull() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!hasEdge(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid() {
        for (int i = 0; i < NODES; i++) {
            Set<Integer> rowValues = new HashSet<>();
            Set<Integer> colValues = new HashSet<>();
            Set<Integer> squareValues = new HashSet<>();
            for (int j = 0; j < NODES; j++) {
                if (board[i][j] != 0 && !rowValues.add(board[i][j])) {
                    return false;
                }
                if (board[j][i] != 0 && !colValues.add(board[j][i])) {
                    return false;
                }
            }
            int startRow = i - (i % 3);
            int startCol = i % 3 * 3;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    int value = board[row + startRow][col + startCol];
                    if (value != 0 && !squareValues.add(value)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}