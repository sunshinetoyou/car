package com.danyeon;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class testMoveController implements Initializable{
    
    @FXML private Circle circle;
    @FXML private Pane map;
    private Rectangle clip = new Rectangle();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        update();
        
        circle.setOnKeyPressed((KeyEvent event) -> {
            // System.out.println(event.getCode());
            switch (event.getCode()) {
                case RIGHT: circle.setTranslateX(circle.getTranslateX() + 30);
                            System.out.println(clip.getX());
                            break;
                case LEFT:  circle.setTranslateX(circle.getTranslateX() - 30);
                            break;
                case UP:    circle.setTranslateY(circle.getTranslateY() - 30);
                            break;
                case DOWN:  circle.setTranslateY(circle.getTranslateY() + 30);
                            break;
                // case SPACE: reset();
                //             break;
                default:    // System.out.println("Scene : " + getScene().widthProperty() + "    " + getScene().heightProperty());
                            // System.out.println("clip : " +clip.widthProperty() + "    " + clip.heightProperty());
                            System.out.println("Not Correct KeyCode");
                            break;
            }
        });
    }
    
    public Rectangle makeClip() {
        Scene scene = getScene();

        clip.widthProperty().bind(scene.widthProperty());
        clip.heightProperty().bind(scene.heightProperty());
        
        clip.xProperty().setValue(200);
        clip.yProperty().setValue(0);

        return clip;
    }

    public Scene getScene() {
        return (Scene) circle.getScene();
    }

    public void update() {
        AnimationTimer timer = new AnimationTimer() {
            
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 25_000_000) {   // 1ms = 1_000_000 ns
                    // update 할 것들
                    map.setClip(makeClip());
                    // System.out.println(getScene().widthProperty());
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }
}
