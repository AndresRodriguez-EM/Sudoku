package edward.sudokugame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SudokuApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar el archivo FXML del menú
            Parent root = FXMLLoader.load(getClass().getResource("/edward/menu.fxml"));
            primaryStage.setTitle("Sudoku 6x6");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}