package cz.vse.adventure.main;


import cz.vse.adventure.logic.Game;
import cz.vse.adventure.logic.IGame;
import cz.vse.adventure.uiText.TextInterface;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Start extends Application
{
    public static void main(String[] args)
    {
        IGame game = new Game();
        if(args.length > 0 && args[0].equals("text") ){
            TextInterface ui = new TextInterface(game);
            ui.play();
            Platform.exit();
        }else {
            launch(args);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/cz.vse.adventure.main/home.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Adventure");
        primaryStage.show();
    }
}
