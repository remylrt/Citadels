package com.montaury.citadels;

import com.montaury.citadels.interfaceAssets.BoiteAOutils;
import com.montaury.citadels.interfaceAssets.CitadelsLabel;
import com.montaury.citadels.interfaceAssets.ResolutionFenetre;
import com.montaury.citadels.player.HumanController;
import com.montaury.citadels.player.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
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
                mainStage.close();
                loadSettings();
                mainStage.show();
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

    private void loadSettings(){
        mainPane = new Pane();
        mainScene = new Scene(mainPane, ResolutionFenetre.parametragePartie.getWidth(), ResolutionFenetre.parametragePartie.getHeight());
        mainScene.getStylesheets().add("mainSettings.css");

        GridPane grid = new GridPane();
        grid.getColumnConstraints().add(new ColumnConstraints(300));
        grid.getColumnConstraints().add(new ColumnConstraints(300));
        grid.getColumnConstraints().add(new ColumnConstraints(300));
        grid.getRowConstraints().add(new RowConstraints(50));
        grid.getRowConstraints().add(new RowConstraints(50));
        grid.getRowConstraints().add(new RowConstraints(50));
        grid.setLayoutX(100);
        grid.setLayoutY(100);


        CitadelsLabel labelPseudo = outils.createAndReturnLabel("Citadelles",0, 0, mainPane, "inputText");
        grid.setConstraints(labelPseudo, 0, 0);

        TextField textFieldPseudo = new TextField();
        grid.setConstraints(textFieldPseudo, 1, 0);

        CitadelsLabel labelAge = outils.createAndReturnLabel("Age",0, 0, mainPane, "inputText");
        grid.setConstraints(labelAge, 0, 1);

        TextField textFieldAge = new TextField();
        grid.setConstraints(textFieldAge, 1, 1);

        CitadelsLabel labelNbPlayer = outils.createAndReturnLabel("Nombre de joueur (2 à 8 joueurs)",0, 0, mainPane, "inputText");
        grid.setConstraints(labelNbPlayer, 0, 2);

        Slider sliderNbJoueur = new Slider(2, 8, 1);
        sliderNbJoueur.setShowTickMarks(true);
        sliderNbJoueur.setShowTickLabels(true);
        sliderNbJoueur.setMajorTickUnit(1f);
        grid.setConstraints(sliderNbJoueur, 1, 2);

        grid.getChildren().addAll(labelPseudo, labelAge, labelNbPlayer,textFieldPseudo, textFieldAge, sliderNbJoueur);
        mainPane.getChildren().add(grid);


        mainPane.getChildren().add(outils.newButton("Jouer",400, 400));
        Button buttonJouer = outils.newButton("Jouer",400, 400);
        buttonJouer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        mainPane.getChildren().add(buttonJouer);

        mainStage = new Stage();
        mainStage.setResizable(false);
        mainStage.setScene(mainScene);
    }


    public Stage getMainStage(){
        return mainStage;
    }
}
