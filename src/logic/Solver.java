package logic;

import model.Sudoku;

public class Solver {
  private final Sudoku sudoku;

  public Solver(Sudoku sudoku) {
    this.sudoku = sudoku;
  }

  public boolean numberIsValid(int number, int row, int col) {
    return checkRow(number, row) && checkCol(number, col) && checkSquare(number, row, col);
  }

  public boolean checkRow(int number, int row) {
    return true;
  }

  public boolean checkCol(int number, int col) {
    return true;
  }

  public boolean checkSquare(int number, int row, int col) {
    return true;
  }

  public boolean solve() {
    for (int row = 0; row < sudoku.getNumberNodes(); row++) {
      for (int col = 0; col < sudoku.getNumberNodes(); col++) {
        if (!sudoku.hasEdge(row, col)) {
          for (int possibleNumber = 0;
              possibleNumber <= sudoku.getNumberNodes();
              possibleNumber++) {
            if (numberIsValid(possibleNumber, row, col)) {
              sudoku.addEdge(col, row, possibleNumber);
              if (solve()) {
                return true;
              }
              sudoku.removeEdge(row, col);
            }
          }
        }
      }
    }
    return false;
  }
}
