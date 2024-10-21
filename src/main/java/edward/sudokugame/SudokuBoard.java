package edward.sudokugame;

/**
 * La clase SudokuBoard representa el tablero del juego de Sudoku 6x6.
 *
 * <p> Contiene métodos para inicializar el tablero, colocar números,
 * verificar su validez y comprobar si el tablero está completo.</p>
 */

public class SudokuBoard {

    private final int[][] board;
    private final int gridSize = 6;

    /**
     * Constructor que inicializa un nuevo tablero de Sudoku vacío.
     */

    public SudokuBoard() {
        board = new int[gridSize][gridSize];
        initializeBoard();
    }

    /**
     * Inicializa el tablero con ceros (celdas vacías).
     */

    private void initializeBoard() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                board[row][col] = 0;
            }
        }
    }

    /**
     * Coloca un número en una celda especificada si es válido.
     *
     * @param row la fila en la que se desea colocar el número.
     * @param col la columna en la que se desea colocar el número.
     * @param num el número que se desea colocar.
     * @return {@code true} si el número fue colocado exitosamente;
     *         {@code false} si no es válido.
     */

    public boolean placeNumber(int row, int col, int num) {
        if (isValid(row, col, num)) {
            board[row][col] = num;
            return true;
        }
        return false;
    }

    /**
     * Verifica si un número es válido en una celda dada.
     *
     * @param row la fila de la celda a verificar.
     * @param col la columna de la celda a verificar.
     * @param num el número que se desea validar.
     * @return {@code true} si el número puede ser colocado en la celda;
     *         {@code false} si no es válido.
     */

    public boolean isValid(int row, int col, int num) {

        for (int i = 0; i < gridSize; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        for (int i = 0; i < gridSize; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        int blockRowStart = (row / 2) * 2;
        int blockColStart = (col / 3) * 3;
        for (int i = blockRowStart; i < blockRowStart + 2; i++) {
            for (int j = blockColStart; j < blockColStart + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Obtiene el valor de una celda especificada.
     *
     * @param row la fila de la celda.
     * @param col la columna de la celda.
     * @return el valor en la celda (0 si está vacía).
     */

    public int getCell(int row, int col) {
        return board[row][col];
    }

    /**
     * Verifica si el tablero está completo (sin celdas vacías).
     *
     * @return {@code true} si el tablero está completo;
     *         {@code false} si hay celdas vacías.
     */

    public boolean isBoardComplete() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Reinicia el tablero, vaciando todas las celdas.
     */

    public void resetBoard() {
        initializeBoard();
    }

    /**
     * Obtiene el tamaño del tablero.
     *
     * @return el tamaño del tablero (6).
     */

    public int getGridSize() {
        return gridSize;
    }
}