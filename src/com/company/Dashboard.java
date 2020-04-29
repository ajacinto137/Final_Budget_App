package com.company;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Dashboard extends Budget {

    public Scene createDashScene(){

        Label dashLabel = new Label("This is you dashboad");
        Controllers cntrlr = new Controllers();
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(dashLabel, cntrlr.chooseWeek("Please choose a week",true), cntrlr.goToTransButon(), cntrlr.goToSetUpButton());
        transScene = new Scene(vBox,400,400);
        return transScene;
    }
}
