import logic.SudokuValidator;
import model.Sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SudokuValidatorTest {
    SudokuValidator sudokuValidator;
    Sudoku sudoku;

    @BeforeEach
    void setUp() {
        sudokuValidator = new SudokuValidator();
        sudoku = new Sudoku();
    }

    @Test
    void testIsEmptyFail() {
        sudoku.addEdge(1, 1, 1);
        assertFalse(sudokuValidator.isEmpty(sudoku));
    }

    @Test
    void testIsEmptySuccess() {
        assertTrue(sudokuValidator.isEmpty(sudoku));
    }

    @Test
    void testIsFullFail() {
        assertFalse(sudokuValidator.isFull(sudoku));
        sudoku.addEdge(1, 1, 1);
        assertFalse(sudokuValidator.isFull(sudoku));
    }

    @Test
    void testIsFullSuccess() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                sudoku.addEdge(row, col, 1);
            }
        }
        assertTrue(sudokuValidator.isFull(sudoku));
    }

    @Test
    void testIsValidFailRow() {
        sudoku.addEdge(0, 0, 1);
        sudoku.addEdge(0, 1, 1);

        assertFalse(sudokuValidator.isValid(sudoku));
    }

    @Test
    void testIsValidFailCol() {
        sudoku.addEdge(0, 0, 1);
        sudoku.addEdge(1, 0, 1);

        assertFalse(sudokuValidator.isValid(sudoku));
    }

    @Test
    void testIsValidFailSquare() {
        sudoku.addEdge(0, 0, 1);
        sudoku.addEdge(1, 1, 1);

        assertFalse(sudokuValidator.isValid(sudoku));
    }

    @Test
    void testIsValidSuccess() {
        sudoku.addEdge(0, 0, 1);
        sudoku.addEdge(1, 1, 2);

        assertTrue(sudokuValidator.isValid(sudoku));
    }
}