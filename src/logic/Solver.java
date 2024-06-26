package logic;

import model.Sudoku;

public class Solver {
    private Sudoku sudoku;

    public Solver(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public boolean numberIsValid(int number, int row, int col) {
        return true;
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
        return true;
    }
}
