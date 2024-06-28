package logic;

import model.Sudoku;
import java.util.Random;

public class GenerateSudoku {
    private final Sudoku sudoku;
    private final Random random = new Random();

    public GenerateSudoku() {
        this.sudoku = new Sudoku();
        generateFullBoard();
        removeNumbers();
    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    private void generateFullBoard() {
        fillDiagonal();
        fillRemaining(0, 3);
    }

    private void fillDiagonal() {
        for (int i = 0; i < 9; i += 3) {
            fillBox(i, i);
        }
    }

    private boolean fillRemaining(int i, int j) {
        if (j >= 9 && i < 8) {
            i++;
            j = 0;
        }
        if (i >= 9 && j >= 9) {
            return true;
        }

        if (i < 3) {
            if (j < 3) {
                j = 3;
            }
        } else if (i < 6) {
            if (j == (i / 3) * 3) {
                j += 3;
            }
        } else {
            if (j == 6) {
                i++;
                j = 0;
                if (i >= 9) {
                    return true;
                }
            }
        }

        for (int num = 1; num <= 9; num++) {
            if (isSafe(i, j, num)) {
                sudoku.addEdge(i, j, num);
                if (fillRemaining(i, j + 1)) {
                    return true;
                }
                sudoku.removeEdge(i, j);
            }
        }
        return false;
    }

    private void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    num = random.nextInt(9) + 1;
                } while (!isUnusedInBox(row, col, num));
                sudoku.addEdge(row + i, col + j, num);
            }
        }
    }

    private boolean isUnusedInBox(int rowStart, int colStart, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudoku.getValue(rowStart + i, colStart + j) == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSafe(int i, int j, int num) {
        Solver solver = new Solver(sudoku);
        return solver.numberIsValid(num, i, j);
    }

    private void removeNumbers() {
        int count = 40; // Number of cells to remove, adjust as needed
        while (count != 0) {
            int cellId = random.nextInt(81);
            int i = (cellId / 9);
            int j = cellId % 9;
            if (sudoku.getValue(i, j) != 0) {
                sudoku.removeEdge(i, j);
                count--;
            }
        }
    }
}
