package com.danyeon;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class testMoveController implements Initializable{
    
    @FXML private Rectangle car;
    @FXML private Pane map;
    private Rectangle clip = new Rectangle();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        update();
        
        car.setOnKeyPressed((KeyEvent event) -> {
            // System.out.println(event.getCode());
            switch (event.getCode()) {
                case RIGHT: car.setTranslateX(car.getTranslateX() + 30);
                            // System.out.println(clip.getX());
                            break;
                case LEFT:  car.setTranslateX(car.getTranslateX() - 30);
                            break;
                case UP:    car.setTranslateY(car.getTranslateY() - 30);
                            break;
                case DOWN:  car.setTranslateY(car.getTranslateY() + 30);
                            break;
                // case SPACE: reset();
                //             break;
                default:    // System.out.println("car : " + car.translateXProperty().getValue());
                            // System.out.println("Scene : " + getScene().xProperty().getValue() + "    " + getScene().yProperty().getValue());
                            // System.out.println("clip : " + clip.xProperty().getValue() + "    " + clip.yProperty().getValue());
                            // System.out.println("Not Correct KeyCode");
                            System.out.println(map.getWidth());
                            break;
            }
        });
    }

    private double clampRange(double value, double min, double max) {
        // System.out.println("value : " + value);
        // System.out.println("min : " + min);
        // System.out.println("max : " + max);

        if (value < min) {
            return min;
        }
        if (value > max) {
            return max ;
        }
        return value ;
    }

    public Rectangle makeClip() {
        Scene scene = getScene();

        // clip.widthProperty().bind(scene.widthProperty());
        // clip.heightProperty().bind(scene.heightProperty());

        // System.out.println("car : RealX / " + getRealX());
        // System.out.println("a : " + (scene.getWidth()-car.getWidth())/2);
        // System.out.println(map.getWidth());
        // System.out.println("value : " + (getRealX() - (scene.getWidth()-car.getWidth())/2));
        System.out.println(clampRange(25, 0, 100));
        clip.xProperty().bind(Bindings.createDoubleBinding(
            () -> clampRange(getRealX() - (scene.getWidth()-car.getWidth())/2, 0, map.getWidth() - scene.getWidth()),
            car.xProperty(), scene.widthProperty()
        ));
        // clip.yProperty().bind(car.translateYProperty());

        clip.widthProperty().setValue(400);
        clip.heightProperty().setValue(400);

        return clip;
    }

    public Scene getScene() {
        return (Scene) car.getScene();
    }

    public double getRealX() {
        return car.getLayoutX() + car.getTranslateX();
    }

    public void update() {
        AnimationTimer timer = new AnimationTimer() {
            
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 2_000_000_000) {   // 1ms = 1_000_000 ns
                    // update 할 것들
                    // map.setClip(makeClip());
                    // System.out.println(getScene().widthProperty());
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }
}
