/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queuedemon;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class QueueDemonController implements Initializable {
    @FXML
    private Button btnPush;
    @FXML
    private Button btnPop;
    @FXML
    private Button btnExit;
    @FXML
    private GridPane gridBoxBall;
    @FXML
    private GridPane gridBoxNum;
    @FXML
    private Label description;    
    private int curColumn = 0; 
    private int count = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void actionPush(ActionEvent event) {       
        System.out.println("Button Push Clicked!");
        if (curColumn >= 8) {
            description.setText("Queue full already, you can not push more!");        
        } else {
            addCircle(curColumn, 0);
            addLabel(curColumn, 0);
            count++;
            description.setText("Push successfully!");
            curColumn++;
            System.out.println(curColumn);
        }
    }
    
    public void actionPop(ActionEvent event) {        
        System.out.println("Button Pop Clicked!");
        if (curColumn == 0) {
            description.setText("Queue Empty! Nothing to pop!");
            System.out.println(curColumn);
        } else if (curColumn == 1) {
            gridBoxBall.getChildren().remove(0); 
            gridBoxNum.getChildren().remove(0);
            curColumn = 0;
            description.setText("Pop successfully!");
            System.out.println(curColumn);
        
        } else {
            gridBoxBall.getChildren().remove(0);
            gridBoxNum.getChildren().remove(0);
            curColumn = curColumn - 1;
            System.out.println(curColumn);

            for (int i = 0; i < curColumn; i++) {
                Translate translate = new Translate();
                translate.setZ(0);
                translate.setY(0);
                translate.setX(-40);
                gridBoxBall.getChildren().get(i).getTransforms().addAll(translate);
                gridBoxNum.getChildren().get(i).getTransforms().addAll(translate);
               
            }
             description.setText("Pop successfully!");
        }

    }

    public void actionExit(ActionEvent event) {
        System.exit(0);
    }

    public void addCircle(int col, int row){
        Circle circle = new Circle(20);
        circle.setFill(Color.ORANGE);            
        gridBoxBall.add(circle, col, row);
    
        
    }
    public void addLabel(int col, int row){
        Label label = new Label(count + "");     
        label.setPrefSize(40, 40);
        label.setAlignment(Pos.CENTER);
        gridBoxNum.add(label, col, row);
    }
}
