package com.montaury.citadels;

import com.montaury.citadels.interfaceAssets.BoiteAOutils;
import com.montaury.citadels.interfaceAssets.ResolutionFenetre;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewManager {

    private  Pane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private BoiteAOutils outils = new BoiteAOutils();

    public ViewManager(){

        loadAccueil();
    }

    private void loadAccueil(){
        mainPane = new Pane();
        mainScene = new Scene(mainPane, ResolutionFenetre.accueil.getWidth(), ResolutionFenetre.accueil.getHeight());
        mainScene.getStylesheets().add("main.css");


        outils.createLabel("Citadelles",28, 280, mainPane, "title");

        Button buttonNouvellePartie = outils.newButton("Nouvelle partie",206, 305);
        buttonNouvellePartie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        mainPane.getChildren().add(buttonNouvellePartie);

        Button buttonQuitter = outils.newButton("Quitter",270, 305);
        buttonQuitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });

        mainPane.getChildren().add(buttonQuitter);
        mainStage = new Stage();


        mainStage.setResizable(false);
        mainStage.setScene(mainScene);
    }

    public Stage getMainStage(){
        return mainStage;
    }
}
