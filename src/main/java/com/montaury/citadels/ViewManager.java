package com.montaury.citadels;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewManager {

    private  Pane mainPane;
    private Scene mainScene;
    private Stage mainStage;


    public ViewManager(){

        loadAccueil();
    }

    private void loadAccueil(){
        mainPane = new Pane();
        mainScene = new Scene(mainPane, 500, 500);
        mainStage = new Stage();

        mainStage.setResizable(false);
        mainStage.setScene(mainScene);
    }

    public Stage getMainStage(){
        return mainStage;
    }
}
