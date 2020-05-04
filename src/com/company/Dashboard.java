package com.company;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Dashboard extends Budget {

    public Scene createDashScene(){
        fileReadTrans();
        fileReadCat();
        sortTransByWeek(weeks,transactionsArray);
//        sortIntoWeekly();
        Label dashLabel = new Label("Dashboard");
        dashLabel.setAlignment(Pos.CENTER);
        Controllers cntrlr = new Controllers();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(cntrlr.chooseWeek("Display Transaction By Week",true),cntrlr.dashGrid());
        VBox vBox = new VBox(20);
        Label testLabel = new Label("Test");
        vBox.getChildren().addAll(dashLabel, hBox, cntrlr.goToTransButton(), cntrlr.goToSetUpButton());
        vBox.setPadding(new Insets(20));
        transScene = new Scene(vBox,775,400);
        return transScene;
    }
}
