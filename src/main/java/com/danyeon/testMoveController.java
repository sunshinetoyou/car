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
    @FXML private Rectangle car2;
    @FXML private Pane map;
    @FXML private Rectangle clip;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // Platform.runLater(() ->
        //     moveClip()
        // );
        moveMap(car);

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
                default:    // System.out.println("Not Correct Value");
                            moveMap(car2);
                            // System.out.println("CAR.x : " + car.getX() + "\nCAR.translateX : " + car.getTranslateX());
                            // System.out.println("CLIP.translateX : " + clip.getTranslateX() + "\nCLIP.width : " + clip.getWidth());
                            // System.out.println("CLAMPRANGE : " +clampRange(getRealX()-(clip.getWidth() - car.getWidth())/2, 0, map.getWidth()-clip.getWidth()));
                            // System.out.println("MAP : " + map.getLayoutX() + " " + map.getLayoutY());
                            // System.out.println("SCENE.width :" + App.getScene().getWidth());
                            // System.out.println("ROOTNODE : " + rootNode.getLayoutX());
                            break;
            }
        });
    }

    // public void moveClip() {
    //     Scene scene = App.getScene();

    //     clip.widthProperty().bind(scene.widthProperty());
    //     clip.heightProperty().bind(scene.heightProperty());

    //     clip.xProperty().bind(Bindings.createDoubleBinding(
    //         () -> clampRange(getRealX()-(clip.getWidth() - car.getWidth())/2, 0, map.getWidth()-clip.getWidth()),
    //         car.translateXProperty(), scene.widthProperty()
    //     ));
    //     clip.yProperty().bind(Bindings.createDoubleBinding(
    //         ()-> clampRange(getRealY()-(clip.getHeight() - car.getHeight())/2, 0, map.getHeight()-clip.getHeight()),
    //         car.translateYProperty(), scene.heightProperty()
    //     ));
    // }

    public void moveMap(Rectangle car) {
        // System.out.println("layoutX" + (getRealX(car)-(clip.getWidth() - car.getWidth())/2));
        // System.out.println("layoutY" + (getRealY(car)-(clip.getHeight() - car.getHeight())/2));
        rootNode.setLayoutX(getRealX(car)-(clip.getWidth() - car.getWidth())/2);
        rootNode.setLayoutY(getRealY(car)-(clip.getHeight() - car.getHeight())/2);

        rootNode.layoutXProperty().bind(Bindings.createDoubleBinding(
            () -> -car.getTranslateX(),
            car.translateXProperty()
        ));
        rootNode.layoutYProperty().bind(Bindings.createDoubleBinding(
            ()-> -car.getTranslateY(),
            car.translateYProperty()
        ));
    }

    // private double clampRange(double value, double min, double max) {
    //     // System.out.println("VALUE "+value+"MIN "+min+"MAX "+max);
    //     if (max < min ) return value; // map.width 가 0이 되어서 max 값이 -200 되는 경우 예외처리
    //     if (value < min) return min;
    //     if (value > max) return max;
    //     return value;
    // }
    
    public double getRealX(Rectangle car) {
        return car.getX() + car.getTranslateX();
    }

    public double getRealY(Rectangle car) {
        return car.getY() + car.getTranslateY();
    }
}
