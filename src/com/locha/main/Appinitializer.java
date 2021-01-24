package com.locha.main;

import com.locha.stages.StageList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Appinitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StageList.mainMenuFormStage = primaryStage;
        try {
            primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/MainMenuForm.fxml"))));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }
}
