package com.danyeon;

import java.net.URL;
import java.util.ResourceBundle;

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
    @FXML private Rectangle clip;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // moveClip();
        System.out.println("CAR : " + car.getX()+ " width : " + car.getWidth());
        System.out.println("clip : " + clip.getWidth());
        System.out.println("clampRange : " + (clip.getWidth() - car.getWidth())/2);
        // clip.layoutXProperty().bind(Bindings.createDoubleBinding(
        //     () -> clampRange(getRealX()-((clip.getWidth() - car.getWidth())/2), 0, map.getWidth()-clip.getWidth()),
        //     car.translateXProperty()
        // ));
        
        car.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case RIGHT: car.setX(car.getX() + 30);
                            break;
                case LEFT:  car.setX(car.getX() - 30);
                            break;
                case UP:    car.setY(car.getY() - 30);
                            break;
                case DOWN:  car.setY(car.getY() + 30);
                            break;
                default:    // System.out.println("Not Correct Value");
                            System.out.println(car.getX());
                            break;
            }
        });
    }

    public Scene getScene() {
        return (Scene) car.getScene();
    }

    public void moveClip() {
        // Scene scene = getScene();

        clip.xProperty().bind(Bindings.createDoubleBinding(
            () -> clampRange(car.getX()-((clip.getWidth() - car.getWidth())/2), 0, map.getWidth()-clip.getWidth()),
            car.xProperty()
        ));
        clip.yProperty();
    }

    private double clampRange(double value, double min, double max) {
        if (value < min) return min;
        if (value > max) return max ;
        return value ;
    }

    // public Rectangle makeClip() {
    //     Scene scene = getScene();

    //     // clip.widthProperty().bind(scene.widthProperty());
    //     // clip.heightProperty().bind(scene.heightProperty());

    //     // System.out.println("car : RealX / " + getRealX());
    //     // System.out.println("a : " + (scene.getWidth()-car.getWidth())/2);
    //     // System.out.println(map.getWidth());
    //     // System.out.println("value : " + (getRealX() - (scene.getWidth()-car.getWidth())/2));
    //     System.out.println(clampRange(25, 0, 100));
    //     clip.xProperty().bind(Bindings.createDoubleBinding(
    //         () -> clampRange(getRealX() - (scene.getWidth()-car.getWidth())/2, 0, map.getWidth() - scene.getWidth()),
    //         car.xProperty(), scene.widthProperty()
    //     ));
    //     // clip.yProperty().bind(car.translateYProperty());

    //     clip.widthProperty().setValue(400);
    //     clip.heightProperty().setValue(200);

    //     return clip;
    // }
    
    // public double getRealX() {
    //     return car.getLayoutX() + car.getTranslateX();
    // }
}
