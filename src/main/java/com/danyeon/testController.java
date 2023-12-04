package com.danyeon;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class testController implements Initializable {
    @FXML private Rectangle car;
    private Rectangle clip;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        new Car(car);
        update();
    }

    public void update() {
        AnimationTimer timer = new AnimationTimer() {
            
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 25_000_000) {   // 1ms = 1_000_000 ns
                    // update 할 것들
                    moveCamera();
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }

    public void moveCamera() {
        // if (player.getTranslateX() > 640 && player.getTranslateX() < level.levelWidth-640 ) {
		// 	level.blockContainer.setLayoutX(-(player.getTranslateX()-640));
		// }
        
    }

    public void makeClip() {
        Scene scene = App.getScene();
        clip.widthProperty().bind(scene.widthProperty());
        clip.heightProperty().bind(scene.heightProperty());

        clip.xProperty().setValue(800);
        clip.yProperty().setValue(800);
    }
}


class Car {
    
    @FXML private Rectangle car;

    private double length = 4;
    private double theta = 30;

    public Car(Rectangle rec) {
        this.car = rec;

        car.setOnMouseClicked(e -> {
            System.out.println(getBound());
        });

        car.setOnKeyPressed((KeyEvent event) -> {
            // System.out.println(event.getCode());
            switch (event.getCode()) {
                case RIGHT: handle(-theta);
                            // System.out.println(car.getRotate());
                            break;
                case LEFT:  handle(theta);
                            // System.out.println(car.getRotate());
                            break;
                case UP:    axel(-length);
                            break;
                case DOWN:  axel(length);
                            break;
                case SPACE: reset();
                            break;
                default:    camera();
                            System.out.println("Not Correct KeyCode");
                            break;
            }
        });
    }

    // x, y 값 움직이기

    // 앞으로 움직이기
    public void axel(double length) {
        // System.out.println("각도 : " + car.getRotate());
        // System.out.println("ang " + (360 + car.getRotate()) % 360 + 90 );
        // System.out.println("cos " + Math.cos(Math.toRadians((360 + car.getRotate()) % 360 + 90)));
        // System.out.println("sin " + Math.sin(Math.toRadians((360 + car.getRotate()) % 360 + 90)));
        // System.out.println();

        car.setTranslateX(car.getTranslateX() + length * Math.cos(Math.toRadians((360 + car.getRotate()) % 360 + 90)));
        car.setTranslateY(car.getTranslateY() + length * Math.sin(Math.toRadians((360 + car.getRotate()) % 360 + 90)));
    }

    // 옆으로 움직이기
    public void handle(double angle) {
        car.setRotate(car.getRotate() - angle);;
    }

    public void reset() {
        car.setTranslateX(0);
        car.setTranslateY(0);
        car.setRotate(0);
    }

    public Bounds getBound() {
        return car.getBoundsInLocal();
    }

    public void camera() {
        // car.setClip();
        System.out.println(car.yProperty());
        // car.getClip();
    }
}