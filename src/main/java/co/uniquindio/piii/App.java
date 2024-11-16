package co.uniquindio.piii;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Cargamos la vista de registro como primera pantalla
        scene = new Scene(loadFXML("login"), 400, 300);
        stage.setScene(scene);
        stage.setTitle("Registro de Usuario");
        stage.show();
    }
    
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("registro.fxml"));
    
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
}
