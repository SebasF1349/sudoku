import logic.Solver;
import model.Sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SolverTest {
    private static Sudoku sudoku;

    @BeforeEach
    void setUp() {
        sudoku = new Sudoku();
    }

    @Test
    void testNumberIsValidFail() {
        sudoku.addEdge(0, 0, 1);
        sudoku.addEdge(1, 1, 2);
        sudoku.addEdge(2, 2, 3);

        Solver solver = new Solver(sudoku);

        assertFalse(solver.numberIsValid(2, 0, 1));
    }

    @Test
    void testNumberIsValidSuccess() {
        sudoku.addEdge(0, 0, 1);
        sudoku.addEdge(1, 1, 2);
        sudoku.addEdge(2, 2, 3);

        Solver solver = new Solver(sudoku);

        assertTrue(solver.numberIsValid(4, 0, 1));
    }

    @Test
    void testCheckRowFail() {
        sudoku.addEdge(0, 0, 1);

        Solver solver = new Solver(sudoku);

        assertFalse(solver.checkRow(1, 0));
    }

    @Test
    void testCheckRowSuccess() {
        sudoku.addEdge(0, 0, 1);

        Solver solver = new Solver(sudoku);

        assertTrue(solver.checkRow(2, 0));
    }

    @Test
    void testCheckColFail() {
        sudoku.addEdge(0, 0, 1);

        Solver solver = new Solver(sudoku);

        assertFalse(solver.checkCol(1, 0));
    }

    @Test
    void testCheckColSuccess() {
        sudoku.addEdge(0, 0, 1);

        Solver solver = new Solver(sudoku);

        assertTrue(solver.checkCol(2, 0));
    }

    @Test
    void testCheckSquareFail() {
        sudoku.addEdge(0, 0, 1);

        Solver solver = new Solver(sudoku);

        assertFalse(solver.checkSquare(1, 1, 1));
    }

    @Test
    void testCheckSquareSuccess() {
        sudoku.addEdge(0, 0, 1);

        Solver solver = new Solver(sudoku);

        assertTrue(solver.checkSquare(2, 1, 1));
    }

    @Test
    void testSolveFail() {
        sudoku.addEdge(0, 0, 1);
        sudoku.addEdge(0, 1, 2);
        sudoku.addEdge(0, 2, 3);
        sudoku.addEdge(0, 3, 4);
        sudoku.addEdge(0, 4, 5);
        sudoku.addEdge(0, 5, 6);
        sudoku.addEdge(0, 6, 7);
        sudoku.addEdge(0, 7, 8);

        sudoku.addEdge(1, 8, 1);
        sudoku.addEdge(2, 8, 2);
        sudoku.addEdge(3, 8, 3);
        sudoku.addEdge(4, 8, 4);
        sudoku.addEdge(5, 8, 5);
        sudoku.addEdge(6, 8, 6);
        sudoku.addEdge(7, 8, 7);
        sudoku.addEdge(8, 8, 9);

        Solver solver = new Solver(sudoku);

        assertFalse(solver.solve());
    }

    @Test
    void testSolveSucess() {
        sudoku.addEdge(0, 0, 1);
        sudoku.addEdge(0, 1, 2);
        sudoku.addEdge(0, 2, 3);
        sudoku.addEdge(0, 3, 4);
        sudoku.addEdge(0, 4, 5);
        sudoku.addEdge(0, 5, 6);
        sudoku.addEdge(0, 6, 7);
        sudoku.addEdge(0, 7, 8);

        sudoku.addEdge(1, 8, 1);
        sudoku.addEdge(2, 8, 2);
        sudoku.addEdge(3, 8, 3);
        sudoku.addEdge(4, 8, 4);
        sudoku.addEdge(5, 8, 5);
        sudoku.addEdge(6, 8, 6);
        sudoku.addEdge(7, 8, 7);
        sudoku.addEdge(8, 8, 8);

        Solver solver = new Solver(sudoku);

        assertTrue(solver.solve());
    }
}