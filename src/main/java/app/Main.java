package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String... args) {
        Config.initCache(args);
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent main = FXMLLoader.load(getClass().getResource("main.fxml"));
        var scene = new Scene(main);
        scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
        primaryStage.setTitle("Color wheel");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png"), 30, 3, false, true));
        primaryStage.show();
    }
}
