package edward.sudokugame;

public class SudokuBoard {

    private final int[][] board; // Matriz que representa el tablero
    private final int gridSize = 6; // Tamaño del tablero (6x6)

    public SudokuBoard() {
        board = new int[gridSize][gridSize];
        initializeBoard();
    }

    // Inicializa el tablero con ceros (vacío)
    private void initializeBoard() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                board[row][col] = 0;
            }
        }
    }

    // Coloca un número en una celda, si es válido
    public boolean placeNumber(int row, int col, int num) {
        if (isValid(row, col, num)) {
            board[row][col] = num;
            return true;
        }
        return false;
    }

    // Verifica si es válido colocar el número en la celda dada
    public boolean isValid(int row, int col, int num) {
        // Verificar fila
        for (int i = 0; i < gridSize; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        // Verificar columna
        for (int i = 0; i < gridSize; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        // Verificar bloque 2x3
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

    // Obtiene el valor de una celda
    public int getCell(int row, int col) {
        return board[row][col];
    }

    // Verifica si el tablero está completo
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

    // Reinicia el tablero
    public void resetBoard() {
        initializeBoard();
    }

    public int getGridSize() {
        return gridSize;
    }
}