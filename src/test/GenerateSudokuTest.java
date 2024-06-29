import logic.GenerateSudoku;
import logic.SudokuValidator;
import model.Difficulty;
import model.Sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerateSudokuTest {
    private SudokuValidator sudokuValidator;

    @BeforeEach
    void setup() {
        sudokuValidator = new SudokuValidator();
    }

    @Test
    void testGenerateValidSudoku() {
        GenerateSudoku generateSudoku = new GenerateSudoku(Difficulty.EASY);
        Sudoku sudoku = generateSudoku.getSudoku();

        assertTrue(sudokuValidator.isValid(sudoku));
    }

    @Test
    void testGeneratePartialSudoku() {
        GenerateSudoku generateSudoku = new GenerateSudoku(Difficulty.EASY);
        Sudoku sudoku = generateSudoku.getSudoku();

        assertFalse(sudokuValidator.isFull(sudoku));
        assertFalse(sudokuValidator.isEmpty(sudoku));
    }
}