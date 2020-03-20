package com.montaury.citadels.interfaceAssets;

import com.montaury.citadels.interfaceAssets.buttons.CitadelsButton;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BoiteAOutils {

    public Button newButton(String text, int positionY, int positionX){
        CitadelsButton button = new CitadelsButton(text,positionY,positionX);
        button.getStyleClass().add("button");
        return button;
        //mainPane.getChildren().add(button);
    }

    public void createLabel(String text, int positionY, int positionX, Pane mainPane, String className){
        CitadelsLabel label = new CitadelsLabel(text, positionY, positionX);
        label.getStyleClass().add(className);
        mainPane.getChildren().add(label);
    }

    public CitadelsLabel createAndReturnLabel(String text, int positionY, int positionX, Pane mainPane, String className){
        CitadelsLabel label = new CitadelsLabel(text, positionY, positionX);
        label.getStyleClass().add(className);
        return label;
    }

}
