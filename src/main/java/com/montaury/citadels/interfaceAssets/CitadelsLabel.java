package com.montaury.citadels.interfaceAssets;

import javafx.scene.control.Label;

public class CitadelsLabel extends Label {

    public CitadelsLabel(String labelText, int positionY, int positionX) {
        setText(labelText);
        setLayoutX(positionX);
        setLayoutY(positionY);
    }
}
