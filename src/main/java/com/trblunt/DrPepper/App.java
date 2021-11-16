package com.trblunt.DrPepper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadScene("FrontPage"), 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    static <T> T setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("scenes/" + fxml + ".fxml"));
        Parent content = fxmlLoader.load();
        if (content instanceof Region) {
            double width = ((Region) content).getPrefWidth();
            double height = ((Region) content).getPrefWidth();
            scene.getWindow().setWidth(width);
            scene.getWindow().setHeight(height);
        }
        
        scene.setRoot(content);
        return fxmlLoader.getController();
    }

    private static Parent loadScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("scenes/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static FXMLLoader loadComponent(String fxml) {
        return new FXMLLoader(App.class.getResource("components/" + fxml + ".fxml"));
    }

    public static void main(String[] args) {
        launch();
    }

}