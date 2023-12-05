package com.danyeon;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class testMoveController implements Initializable {
    @FXML private Pane map;
    @FXML private Rectangle car;    
    private Rectangle clip;
    // private Scene scene = App.scene;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // System.out.println();
        update();

        car.setOnKeyPressed((KeyEvent event) -> {
            // System.out.println(event.getCode());
            switch (event.getCode()) {
                case RIGHT: car.setTranslateX(car.getTranslateX() + 10);
                            // System.out.println(car.getRotate());
                            break;
                case LEFT:  car.setTranslateX(car.getTranslateX() -10);
                            // System.out.println(car.getRotate());
                            break;
                case UP:    car.setTranslateY(car.getTranslateY() -10);
                            break;
                case DOWN:  car.setTranslateY(car.getTranslateY() + 10);
                            break;
                // case SPACE: reset();
                //             break;
                default:    System.out.println("clip : " + clip);
                            System.out.println("Scene : " + App.getScene().xProperty());
                            System.out.println();
                            // System.out.println("Not Correct KeyCode");
                            break;
            }
        });
    }

    public void update() {
        AnimationTimer timer = new AnimationTimer() {
            
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 25_000_000) {   // 1ms = 1_000_000 ns
                    // update 할 것들
                    updateClip();
                    // System.out.println(car.getTranslateX());
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }
    
    public void updateClip() {
        clip = App.makeClip();

        clip.xProperty().bind(Bindings.createDoubleBinding(() -> 0.0, car.translateXProperty(), App.getScene().xProperty()));
        clip.yProperty().bind(car.translateYProperty());
    }
}
