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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // scene 생성된 후에 작동
        // 이렇게 안하면 scene에 null 값이 들어감
        Platform.runLater(
            () -> moveScreen(car)
        );

        // 키보드 이벤트 핸들러
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
                            // moveScreen(car2);
                            // testPrint();
                            break;
            }
        });
    }

    public void moveScreen(Rectangle center) {
        Scene scene = App.getScene();
        
        // rootNode(=Scene) 의 layout 값을 변경해서 맵 이동 구현
        // car.translate : car 가 이동할 때
        // scene.width   : 창 사이즈가 바뀔 때 -> rootNode.layoutX를 재조정
        rootNode.layoutXProperty().bind(Bindings.createDoubleBinding(
            () -> -(getRealX(center)-(scene.getWidth() - car.getWidth())/2),
            center.translateXProperty(), scene.widthProperty()
        ));
        rootNode.layoutYProperty().bind(Bindings.createDoubleBinding(
            ()-> -(getRealY(center)-(scene.getHeight() - car.getHeight())/2),
            center.translateYProperty(), scene.heightProperty()
        ));
    }
    
    // 객체의 위치와 이동값을 합해서 반환
    public double getRealX(Rectangle car) {
        return car.getX() + car.getTranslateX();
    }
    public double getRealY(Rectangle car) {
        return car.getY() + car.getTranslateY();
    }
    
    // 테스트 코드
    public void testPrint() {
        System.out.println("ROOTNODE.layoutX : " + rootNode.getLayoutX() +"\tROOTNODE.width : " + rootNode.getWidth());
        System.out.println("CAR.x : " + car.getX() + "\tCAR.translateX : " + car.getTranslateX());
        // System.out.println("CLIP.translateX : " + clip.getTranslateX() + "\tCLIP.width : " + clip.getWidth());
        // System.out.println("SCENE.width :" + App.getScene().getWidth());
        System.out.println("setRootNodeX" + (getRealX(car)-(App.getScene().getWidth() - car.getWidth())/2));
        // moveMap(car2);
        // System.out.println("CAR2.x :" + car2.getX() +    "\tCAR2.translateX : "+ car.getTranslateX());
    }
}
