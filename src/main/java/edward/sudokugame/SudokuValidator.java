package edward.sudokugame;

/**
 * La clase SudokuValidator proporciona métodos para validar la colocación
 * de números en un tablero de Sudoku.
 *
 * <p> Incluye métodos para verificar si un número es válido en una fila,
 * columna o bloque de 2x3, asegurando que las reglas del Sudoku se respeten.</p>
 */

public class SudokuValidator {

    /**
     * Verifica si un número es válido en una fila del tablero.
     *
     * @param board el tablero de Sudoku representado como una matriz bidimensional.
     * @param row la fila en la que se desea colocar el número.
     * @param num el número que se desea validar.
     * @return {@code true} si el número puede ser colocado en la fila;
     *         {@code false} si ya está presente.
     */

    public static boolean isValidInRow(int[][] board, int row, int num) {
        for (int col = 0; col < board.length; col++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica si un número es válido en una columna del tablero.
     *
     * @param board el tablero de Sudoku representado como una matriz bidimensional.
     * @param col la columna en la que se desea colocar el número.
     * @param num el número que se desea validar.
     * @return {@code true} si el número puede ser colocado en la columna;
     *         {@code false} si ya está presente.
     */


    public static boolean isValidInCol(int[][] board, int col, int num) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica si un número es válido en un bloque de 2x3 del tablero.
     *
     * @param board el tablero de Sudoku representado como una matriz bidimensional.
     * @param startRow la fila inicial del bloque.
     * @param startCol la columna inicial del bloque.
     * @param num el número que se desea validar.
     * @return {@code true} si el número puede ser colocado en el bloque;
     *         {@code false} si ya está presente.
     */

    public static boolean isValidInBlock(int[][] board, int startRow, int startCol, int num) {
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[startRow + row][startCol + col] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Verifica si un número es válido en una posición específica del tablero.
     *
     * @param board el tablero de Sudoku representado como una matriz bidimensional.
     * @param row la fila de la posición a validar.
     * @param col la columna de la posición a validar.
     * @param num el número que se desea validar.
     * @return {@code true} si el número puede ser colocado en la posición;
     *         {@code false} si no es válido.
     */

    public static boolean isValid(int[][] board, int row, int col, int num) {
        return isValidInRow(board, row, num) &&
                isValidInCol(board, col, num) &&
                isValidInBlock(board, row - row % 2, col - col % 3, num);
    }
}