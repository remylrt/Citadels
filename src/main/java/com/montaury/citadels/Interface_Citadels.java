package com.montaury.citadels;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Interface_Citadels extends Application {

     public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {
            ViewManager manager = new ViewManager();
            primaryStage = manager.getMainStage();
            primaryStage.setTitle("Citadelles");
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
