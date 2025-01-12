package edward.sudokugame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * La clase SudokuApp es la clase principal de la aplicación Sudoku.
 * Extiende la clase {@link Application} de JavaFX para iniciar la interfaz gráfica.
 *
 * <p> Esta clase carga el archivo FXML del menú principal y configura la ventana
 * de la aplicación al iniciarse.</p>
 *
 * <p> La aplicación muestra un tablero de Sudoku 6x6 que permite a los usuarios
 * jugar y resolver el rompecabezas.</p>
 */

public class SudokuApp extends Application {

    /**
     * El método {@code start} es el punto de entrada de la aplicación.
     *
     * <p> Este método se llama al iniciar la aplicación y se encarga de
     * cargar el archivo FXML que define la interfaz del menú, configurar el
     * escenario principal y mostrar la ventana de la aplicación.</p>
     *
     * @param primaryStage el escenario principal de la aplicación.
     */

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/edward/menu.fxml"));
            primaryStage.setTitle("Sudoku 6x6");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * El método {@code main} es el punto de entrada de la aplicación.
     *
     * <p> Este método llama al método {@link Application#launch(Class, String...)}
     * para iniciar la aplicación JavaFX.</p>
     *
     * @param args los argumentos de la línea de comandos pasados al programa.
     */

    public static void main(String[] args) {
        launch(args);
    }
}