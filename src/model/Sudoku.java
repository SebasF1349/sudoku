package model;

public class Sudoku {

  private int[][] board;
  private final int NODES = 9;

  public Sudoku() {
    board = new int[NODES][NODES];
  }

  public int getNumberNodes() {
    return NODES;
  }

  public void addEdge(int col, int row, int value) {
    board[col][row] = value;
  }

  public void removeEdge(int row, int col) {
    board[col][row] = 0;
  }

  public boolean hasEdge(int row, int col) {
    return board[col][row] != 0;
  }

  public int getValue(int row, int col) {
    return board[col][row];
  }

  public int[] getEdgesFrom(int row) {
    return board[row];
  }

  public int[] getEdgesTo(int col) {
    int[] ret = new int[NODES];
    for (int i = 0; i < NODES; i++) {
      ret[i] = board[i][col];
    }
    return ret;
  }
}
