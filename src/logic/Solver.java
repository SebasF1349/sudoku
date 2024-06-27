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
    for (int i = 0; i < 9; i++) {
      if (sudoku.getValue(row, i) == number) {
        return false;
      }
    }
    return true;
  }

  public boolean checkCol(int number, int col) {
    for (int j = 0; j < 9; j++) {
      if (sudoku.getValue(j, col) == number) {
        return false;
      }
    }
    return true;
  }

  public boolean checkSquare(int number, int row, int col) {
    int startRow = row - (row % 3);
    int startCol = col - (col % 3);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (sudoku.getValue(startRow + i, startCol + j) == number) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean solve() {
    for (int row = 0; row < sudoku.getNumberNodes(); row++) {
      for (int col = 0; col < sudoku.getNumberNodes(); col++) {
        if (!sudoku.hasEdge(row, col)) {
          for (int possibleNumber = 1;
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