package logic;

import model.Sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {

    public boolean isEmpty(Sudoku sudoku) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (sudoku.hasEdge(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isFull(Sudoku sudoku) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!sudoku.hasEdge(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(Sudoku sudoku) {
        for (int i = 0; i < sudoku.getNumberNodes(); i++) {
            Set<Integer> rowValues = new HashSet<>();
            Set<Integer> colValues = new HashSet<>();
            Set<Integer> squareValues = new HashSet<>();
            for (int j = 0; j < sudoku.getNumberNodes(); j++) {
                if (sudoku.getValue(i, j) != 0 && !rowValues.add(sudoku.getValue(i, j))) {
                    return false;
                }
                if (sudoku.getValue(j, i) != 0 && !colValues.add(sudoku.getValue(j, i))) {
                    return false;
                }
            }
            int startRow = i - (i % 3);
            int startCol = i % 3 * 3;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    int value = sudoku.getValue(row + startRow, col + startCol);
                    if (value != 0 && !squareValues.add(value)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
