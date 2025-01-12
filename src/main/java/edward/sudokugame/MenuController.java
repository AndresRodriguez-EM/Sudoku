package edward.sudokugame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.util.HashSet;
import java.util.Random;

/**
 * La clase MenuController controla la lógica de la interfaz de usuario
 * para el menú principal del juego Sudoku.
 *
 * <p> Se encarga de gestionar las acciones del usuario, como iniciar
 * el juego y mostrar la ayuda, así como la creación y validación del
 * tablero de Sudoku.</p>
 */

public class MenuController {

    @FXML
    private Button btnStart;

    @FXML
    private Button btnHelp;

    private SudokuBoard sudokuBoard = new SudokuBoard();
    private Random random = new Random();

    /**
     * Maneja la acción del botón "Iniciar Juego".
     *
     * <p> Este método se llama cuando el usuario hace clic en el botón
     * "Iniciar Juego". Se encarga de crear y mostrar el tablero de Sudoku.</p>
     *
     * @param event el evento de acción que se dispara al hacer clic en el botón.
     */

    @FXML
    private void handleStartAction(ActionEvent event) {
        crearTableroSudoku();
    }

    @FXML
    private void handleHelpAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ayuda");
        alert.setHeaderText("Instrucciones para jugar");
        alert.setContentText("Este es un juego de Sudoku 6x6. "
                + "\n\nLas reglas son las siguientes:"
                + "\n1. Completa el tablero con los números del 1 al 6."
                + "\n2. Cada fila, columna y bloque de 2x3 debe contener todos los números sin repetir."
                + "\n3. Si cometes un error, el cuadro se resaltará en rojo."
                + "\n4. Para comenzar un nuevo juego, haz clic en el botón 'Iniciar Juego'."
                + "\n\n¡Buena suerte!");
        alert.showAndWait();
    }

    /**
     * Crea y configura el tablero de Sudoku.
     *
     * <p> Este método inicializa la interfaz del tablero de Sudoku, creando un
     * {@link GridPane} que contiene campos de texto para que el usuario ingrese
     * los números. También se encarga de la lógica de validación para los números
     * ingresados.</p>
     */

    @FXML
    private void crearTableroSudoku() {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-padding: 0; -fx-hgap: 0; -fx-vgap: 0;");

        for (int row = 0; row < sudokuBoard.getGridSize(); row++) {
            for (int col = 0; col < sudokuBoard.getGridSize(); col++) {
                StackPane cellPane = new StackPane();
                TextField textField = new TextField();
                textField.setPrefHeight(60);
                textField.setPrefWidth(60);
                textField.setAlignment(Pos.CENTER);
                textField.setFont(Font.font(18));
                textField.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

                if ((row / 2 + col / 3) % 2 == 0) {
                    cellPane.setStyle("-fx-background-color: lightblue;");
                } else {
                    cellPane.setStyle("-fx-background-color: white;");
                }

                String borderStyle = "-fx-border-color: black; -fx-border-width: 1;";
                if (col % 3 == 0) {
                    borderStyle += "-fx-border-left-width: 2;";
                }
                if (row % 2 == 0) {
                    borderStyle += "-fx-border-top-width: 2;";
                }
                cellPane.setStyle(cellPane.getStyle() + borderStyle);

                final int currentRow = row;
                final int currentCol = col;

                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        textField.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                    if (!newValue.isEmpty()) {
                        int num = Integer.parseInt(newValue);
                        if (num < 1 || num > 6 || !sudokuBoard.placeNumber(currentRow, currentCol, num)) {
                            textField.setStyle("-fx-background-color: lightcoral;");
                        } else {
                            textField.setStyle("-fx-background-color: transparent;");
                        }
                    } else {
                        textField.setStyle("-fx-background-color: transparent;");
                    }
                });

                cellPane.getChildren().add(textField);
                gridPane.add(cellPane, col, row);
            }
        }

        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 2; blockCol++) {
                HashSet<Integer> numbers = new HashSet<>();
                while (numbers.size() < 2) {
                    numbers.add(random.nextInt(6) + 1);
                }
                int[] numsToPlace = numbers.stream().mapToInt(Number::intValue).toArray();
                int index = 0;

                for (int row = blockRow * 2; row < blockRow * 2 + 2; row++) {
                    for (int col = blockCol * 3; col < blockCol * 3 + 3; col++) {
                        if (index < numsToPlace.length) {
                            if (isPlacementValid(row, col, numsToPlace[index])) {
                                TextField textField = (TextField) ((StackPane) gridPane.getChildren().get(row * 6 + col)).getChildren().get(0);
                                textField.setText(String.valueOf(numsToPlace[index]));
                                sudokuBoard.placeNumber(row, col, numsToPlace[index]);
                                index++;
                            }
                        }
                    }
                }
            }
        }

        gridPane.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(gridPane);

        Button btnHelp = new Button("Sugerencia (5 restantes)");
        vbox.getChildren().add(btnHelp);

        final int[] remainingHelps = {5};

        btnHelp.setOnAction(event -> {
            if (remainingHelps[0] > 0) {
                sugerirNumero(sudokuBoard, gridPane);
                remainingHelps[0]--;
                btnHelp.setText("Sugerencia (" + remainingHelps[0] + " restantes)");

                if (remainingHelps[0] == 0) {
                    btnHelp.setDisable(true);
                    btnHelp.setText("No quedan sugerencias");
                }
            }
        });

        Scene scene = new Scene(vbox, 600, 650);
        Stage stage = (Stage) btnStart.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Sudoku 6x6 - Juego");
    }

    /**
     * Verifica si un número se puede colocar en una celda sin violar las reglas
     * del Sudoku.
     *
     * @param row la fila en la que se desea colocar el número.
     * @param col la columna en la que se desea colocar el número.
     * @param num el número a colocar.
     * @return {@code true} si la colocación es válida; {@code false} de lo contrario.
     */

    private boolean isPlacementValid(int row, int col, int num) {
        // Verificar fila
        for (int c = 0; c < sudokuBoard.getGridSize(); c++) {
            if (sudokuBoard.getCell(row, c) == num) return false;
        }
        // Verificar columna
        for (int r = 0; r < sudokuBoard.getGridSize(); r++) {
            if (sudokuBoard.getCell(r, col) == num) return false;
        }
        return true;
    }

    /**
     * Sugiere un número para una celda vacía en el tablero de Sudoku.
     *
     * @param sudokuBoard el tablero de Sudoku actual.
     * @param gridPane el {@link GridPane} que contiene las celdas del tablero.
     */

    private void sugerirNumero(SudokuBoard sudokuBoard, GridPane gridPane) {
        for (int row = 0; row < sudokuBoard.getGridSize(); row++) {
            for (int col = 0; col < sudokuBoard.getGridSize(); col++) {
                if (sudokuBoard.getCell(row, col) == 0) {
                    for (int num = 1; num <= 6; num++) {
                        if (sudokuBoard.isValid(row, col, num)) {
                            StackPane cellPane = (StackPane) gridPane.getChildren().get(row * 6 + col);
                            TextField suggestedField = (TextField) cellPane.getChildren().get(0);
                            suggestedField.setStyle("-fx-background-color: lightblue;");
                            suggestedField.setText(String.valueOf(num));
                            return;
                        }
                    }
                }
            }
        }
    }
}