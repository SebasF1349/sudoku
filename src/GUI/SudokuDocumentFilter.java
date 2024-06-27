package GUI;

import javax.swing.text.*;

public class SudokuDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string.isEmpty() || (string.matches("[1-9]") && fb.getDocument().getLength() == 0)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text.isEmpty() || (text.matches("[1-9]") && fb.getDocument().getLength() - length == 0)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}