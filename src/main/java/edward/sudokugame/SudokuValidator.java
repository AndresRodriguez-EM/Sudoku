package edward.sudokugame;

public class SudokuValidator {

    // Método para verificar si un número es válido en una fila
    public static boolean isValidInRow(int[][] board, int row, int num) {
        for (int col = 0; col < board.length; col++) {
            if (board[row][col] == num) {
                return false; // Número ya está en la fila
            }
        }
        return true;
    }

    // Método para verificar si un número es válido en una columna
    public static boolean isValidInCol(int[][] board, int col, int num) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][col] == num) {
                return false; // Número ya está en la columna
            }
        }
        return true;
    }

    // Método para verificar si un número es válido en un bloque 2x3
    public static boolean isValidInBlock(int[][] board, int startRow, int startCol, int num) {
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[startRow + row][startCol + col] == num) {
                    return false; // Número ya está en el bloque 2x3
                }
            }
        }
        return true;
    }

    // Validar si un número es válido en una posición (fila, columna y bloque)
    public static boolean isValid(int[][] board, int row, int col, int num) {
        return isValidInRow(board, row, num) &&
                isValidInCol(board, col, num) &&
                isValidInBlock(board, row - row % 2, col - col % 3, num);
    }
}