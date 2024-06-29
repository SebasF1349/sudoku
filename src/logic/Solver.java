package logic;

import model.Sudoku;

public class Solver {
    private Sudoku sudoku;

    public Solver(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public boolean numberIsValid(int row, int col, int number) {
        return checkRow(row, number) && checkCol(col, number) && checkSquare(row, col, number);
    }

    public boolean checkRow(int row, int number) {
        for (int i = 0; i < 9; i++) {
            if (sudoku.getValue(row, i) == number) {
                return false;
            }
        }
        return true;
    }

    public boolean checkCol(int col, int number) {
        for (int j = 0; j < 9; j++) {
            if (sudoku.getValue(j, col) == number) {
                return false;
            }
        }
        return true;
    }

    public boolean checkSquare(int row, int col, int number) {
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
                    for (int possibleNumber = 1; possibleNumber <= sudoku.getNumberNodes(); possibleNumber++) {
                        if (numberIsValid(row, col, possibleNumber)) {
                            sudoku.addEdge(row, col, possibleNumber);
                            if (solve()) {
                                return true;
                            }
                            sudoku.removeEdge(row, col);
                        }
                    }
                    return false; // No número válido encontrado, retrocede
                }
            }
        }
        return true; // Sudoku resuelto
    }
}
