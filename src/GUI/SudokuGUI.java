package GUI;

import logic.GenerateSudoku;
import logic.Solver;
import model.Difficulty;
import model.Sudoku;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class SudokuGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField[][] cells;
    private JButton solveButton, clearButton, generateButton;

    public SudokuGUI() {
        setTitle("Sudoku solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 500);

        JPanel contentPanel = new JPanel(new BorderLayout());

        mainPanel = new JPanel(new GridLayout(3, 3, 2, 2));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        cells = new JTextField[9][9];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JPanel subPanel = new JPanel(new GridLayout(3, 3, 1, 1));
                subPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

                for (int m = 0; m < 3; m++) {
                    for (int n = 0; n < 3; n++) {
                        int row = j * 3 + n;
                        int col = i * 3 + m;
                        cells[row][col] = new JTextField(1);
                        cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                        cells[row][col].setFont(new Font("Arial", Font.BOLD, 20));
                        cells[row][col].setDisabledTextColor(new Color(100, 100, 100));
                        ((PlainDocument) cells[row][col].getDocument()).setDocumentFilter(new SudokuDocumentFilter());
                        subPanel.add(cells[row][col]);
                    }
                }
                mainPanel.add(subPanel);
            }
        }

        JPanel buttonPanel = new JPanel(new FlowLayout());
        solveButton = new JButton("Resolver");
        clearButton = new JButton("Limpiar");
        generateButton = new JButton("Generar");

        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(generateButton);

        solveButton.addActionListener(e -> solveSudoku());
        clearButton.addActionListener(e -> clearSudoku());
        generateButton.addActionListener(e -> generateSudoku());

        contentPanel.add(mainPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel);
        setVisible(true);
    }


    private int[][] StringToInteger(JTextField[][] textFields) {
        int[][] board = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String text = textFields[row][col].getText().trim();
                if (!text.isEmpty()) {
                    board[row][col] = Integer.parseInt(text);
                } else {
                    board[row][col] = 0;
                }
            }
        }
        return board;
    }

    private void solveSudoku() {

        Sudoku sudoku = new Sudoku();
        sudoku.addBoard(StringToInteger(cells));

        if (sudoku.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Necesita ingresar al menos un número", "Tablero vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!sudoku.isValid()) {
            JOptionPane.showMessageDialog(this, "El Sudoku es inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Solver solver = new Solver(sudoku);

        if (!solver.solve()) {
            JOptionPane.showMessageDialog(this, "El Sudoku no tiene solución", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    if (sudoku.getValue(row, col) != 0) {
                        cells[row][col].setText(String.valueOf(sudoku.getValue(row, col)));
                    } else {
                        cells[row][col].setText("");
                    }
                }
            }
        }
    }

    private void clearSudoku() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col].setText("");
                cells[row][col].setEnabled(true);
            }
        }
    }

    private void generateSudoku() {
        Difficulty[] difficultyOptions = {Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD};
        int difficulty = JOptionPane.showOptionDialog(this, "Elija la dificultad", "Dificultad", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, difficultyOptions, difficultyOptions[0]);
        if (difficulty == -1) {
            return;
        }
        GenerateSudoku nuevoSudokuGenerator = new GenerateSudoku(difficultyOptions[difficulty]);
        Sudoku nuevoSudoku = nuevoSudokuGenerator.getSudoku();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (nuevoSudoku.getValue(row, col) != 0) {
                    cells[row][col].setText(String.valueOf(nuevoSudoku.getValue(row, col)));
                    cells[row][col].setEnabled(false);
                } else {
                    cells[row][col].setText("");
                    cells[row][col].setEnabled(true);
                }
            }
        }
    }

}