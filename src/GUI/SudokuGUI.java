package GUI;

import logic.Solver;
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
                        int row = i * 3 + m;
                        int col = j * 3 + n;
                        cells[row][col] = new JTextField(1);
                        cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                        cells[row][col].setFont(new Font("Arial", Font.BOLD, 20));
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
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                String text = textFields[row][col].getText().trim();
                if (!text.isEmpty()) {
                    board[col][row] = Integer.parseInt(text);
                } else {
                    board[col][row] = 0;
                }
            }
        }
        return board;
    }

    private void solveSudoku() {
        Sudoku sudoku = new Sudoku();
        sudoku.addBoard(StringToInteger(cells));

        Solver solver = new Solver(sudoku);

        //resolvemos el sudoku y devolvemos un error si no se pudo resolver, sino mostramos el tablero resuelto
//        if (no se puede resolver) {
//            JOptionPane.showMessageDialog(this, "El Sudoku no tiene solución", "Error", JOptionPane.ERROR_MESSAGE);
//        } else {
//            int[][] solvedBoard = tablero resuelto;
//            for (int col = 0; col < 9; col++) {
//                for (int row = 0; row < 9; row++) {
//                    if (solvedBoard[col][row] != 0) {
//                        cells[col][row].setText(String.valueOf(solvedBoard[col][row]));
//                    } else {
//                        cells[col][row].setText("");
//                    }
//                }
//            }
//        }
    }

    private void clearSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setText("");
            }
        }
    }

    private void generateSudoku() {

        System.out.println("Generar Sudoku");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuGUI());
    }
}