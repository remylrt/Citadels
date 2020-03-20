package com.montaury.citadels.interfaceAssets.buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;

public class CitadelsButton extends Button {

    private final String FONT_PATH = "src/main/java/com/montaury/citadels/interfaceAssets/fonts/Enchanted_LAND.otf";


    public CitadelsButton(String buttonText, int positionY, int positionX) {
        setText(buttonText);
        setButtonFont();
        setPrefHeight(49);
        setPrefWidth(190);
        setLayoutX(positionX);
        setLayoutY(positionY);
        initializeButtonListeners();
    }

    private void setButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (Exception e) {
            setFont(Font.font("Verdana",23));
        }
    }

    private void setButtonPressedStyle(){
        getStyleClass().remove("button");
        getStyleClass().add("button-selected");
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonDefaultStyle(){
        getStyleClass().remove("button-selected");
        getStyleClass().add("button");
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners(){
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    setButtonPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    setButtonDefaultStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
    }
}
