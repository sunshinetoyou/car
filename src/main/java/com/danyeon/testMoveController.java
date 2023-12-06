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
        Platform.runLater(
            () -> moveMap(car)
        );

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
                            testPrint();
                            break;
            }
        });
    }

    public void moveMap(Rectangle center) {
        Scene scene = App.getScene();

        rootNode.layoutXProperty().bind(Bindings.createDoubleBinding(
            () -> -(getRealX(center)-(scene.getWidth() - car.getWidth())/2),
            center.translateXProperty(), scene.widthProperty()
        ));
        rootNode.layoutYProperty().bind(Bindings.createDoubleBinding(
            ()-> -(getRealY(center)-(scene.getHeight() - car.getHeight())/2),
            center.translateYProperty(), scene.heightProperty()
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
    
    public void testPrint() {
        System.out.println("ROOTNODE.layoutX : " + rootNode.getLayoutX() +"\tROOTNODE.width : " + rootNode.getWidth());
        System.out.println("CAR.x : " + car.getX() + "\tCAR.translateX : " + car.getTranslateX());
        // System.out.println("CLIP.translateX : " + clip.getTranslateX() + "\tCLIP.width : " + clip.getWidth());
        // System.out.println("SCENE.width :" + App.getScene().getWidth());
        System.out.println("setRootNodeX" + (getRealX(car)-(App.getScene().getWidth() - car.getWidth())/2));
        // moveMap(car2);
        // System.out.println("CAR2.x :" + car2.getX() + "\tCAR2.translateX : "+ car.getTranslateX());
    }
}
