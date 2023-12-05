package com.danyeon;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class testMoveController implements Initializable{
    
    @FXML private Pane rootNode;
    @FXML private Rectangle car;
    @FXML private Pane map;
    @FXML private Rectangle clip;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        Platform.runLater(() ->
            moveClip()
        );
        moveMap();
        car.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case RIGHT: car.setTranslateX(car.getTranslateX() + 30);
                            break;
                case LEFT:  car.setTranslateX(car.getTranslateX() - 30);
                            break;
                case UP:    car.setTranslateY(car.getTranslateY() - 30);
                            break;
                case DOWN:  car.setTranslateY(car.getTranslateY() + 30);
                            break;
                default:    System.out.println("Not Correct Value");
                            // System.out.println("CAR : " + car.getX() + " " + car.getTranslateX());
                            // System.out.println("CLIP : " + clip.getTranslateX());
                            // System.out.println("CLAMPRANGE : " +clampRange(getRealX()-(clip.getWidth() - car.getWidth())/2, 0, map.getWidth()-clip.getWidth()));
                            // System.out.println("MAP : " + map.getLayoutX() + " " + map.getLayoutY());
                            System.out.println("ROOTNODE : " + rootNode.getLayoutX());
                            break;
            }
        });
    }

    public Scene getScene() {
        return (Scene) car.getScene();
    }

    public void moveClip() {
        Scene scene = App.getScene();

        clip.widthProperty().bind(scene.widthProperty());
        clip.heightProperty().bind(scene.heightProperty());

        clip.xProperty().bind(Bindings.createDoubleBinding(
            () -> clampRange(getRealX()-(clip.getWidth() - car.getWidth())/2, 0, map.getWidth()-clip.getWidth()),
            car.translateXProperty(), scene.widthProperty()
        ));
        clip.yProperty().bind(Bindings.createDoubleBinding(
            ()-> clampRange(getRealY()-(clip.getHeight() - car.getHeight())/2, 0, map.getHeight()-clip.getHeight()),
            car.translateYProperty(), scene.heightProperty()
        ));
    }

    public void moveMap() {
        map.layoutXProperty().bind(Bindings.createDoubleBinding(
            ()-> -car.getTranslateX(),
            car.translateXProperty()
        ));
        map.layoutYProperty().bind(Bindings.createDoubleBinding(
            ()-> -car.getTranslateY(),
            car.translateYProperty()
        ));

        rootNode.layoutXProperty().bind(Bindings.createDoubleBinding(
            () -> -car.getTranslateX(),
            car.translateXProperty()
        ));
        rootNode.layoutYProperty().bind(Bindings.createDoubleBinding(
            ()-> -car.getTranslateY(),
            car.translateYProperty()
        ));
    }

    private double clampRange(double value, double min, double max) {
        // System.out.println("VALUE "+value+"MIN "+min+"MAX "+max);
        if (max < min ) return value; // map.width 가 0이 되어서 max 값이 -200 되는 경우 예외처리
        if (value < min) return min;
        if (value > max) return max;
        return value;
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
    
    public double getRealX() {
        return car.getX() + car.getTranslateX();
    }

    public double getRealY() {
        return car.getY() + car.getTranslateY();
    }
}
