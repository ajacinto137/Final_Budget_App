package com.company;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Dashboard extends Budget {

    public Scene createDashScene(){
        fileReadTrans();
        fileReadCat();
        Label dashLabel = new Label("This is you dashboad");
        Controllers cntrlr = new Controllers();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(cntrlr.chooseWeek("Please choose a week",true),cntrlr.dashGrid(),cntrlr.testWeeklyBudget(categoriesObjs));
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(dashLabel, hBox, cntrlr.goToTransButon(), cntrlr.goToSetUpButton(),cntrlr.test());
        transScene = new Scene(vBox,400,400);
        return transScene;
    }
}
