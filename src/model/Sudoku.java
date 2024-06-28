package model;

public class Sudoku {

  private int[][] board;
  private final int NODES = 9;

  public Sudoku() {
    board = new int[NODES][NODES];
  }

  public void addBoard(int[][] board) {
    this.board = board;
  }

  public int[][] getBoard() {
    return board;
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
}