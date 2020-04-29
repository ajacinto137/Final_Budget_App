package com.company;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controllers extends Budget {
    public HBox chooseWeek(String displayLabel){
        Label label = new Label(displayLabel);
        ComboBox<String> chosenWeek = new ComboBox<String>();
        chosenWeek.getItems().addAll(weeks);
        HBox chooseWeekLayout = new HBox();
        chooseWeekLayout.getChildren().addAll(label,chosenWeek);
        return chooseWeekLayout;
    }

    public VBox chooseWeek(String displayLabel, boolean printTrans){
        HBox chooseWeekLayout = new HBox();
        Label label = new Label(displayLabel);
        ComboBox<String> chosenWeek = new ComboBox<String>();
        chosenWeek.getItems().addAll(weeks);
        Label testLabel = new Label();

        if (printTrans){
            chosenWeek.setOnAction(e->{
                TransListView.getItems().clear();
                fileReadTrans();
                getTransactionByWeek(Integer.parseInt(chosenWeek.getValue()));
                for (int i = 0; i  < chosenTransactions.size(); i++){
                    TransListView.getItems().addAll(chosenTransactions.get(i));
                }
                testLabel.setText("Total: 0" + addTransactions(chosenTransactions));
            });

        }
        chooseWeekLayout.getChildren().addAll(label,chosenWeek,testLabel);
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(chooseWeekLayout,TransListView);
        return vBox;
    }

    public Button goToTransButon(){
        Button goToAddATrans = new Button("Go to Transactions Page");
        goToAddATrans.setOnAction(e ->{
            TransScene createTransScene = new TransScene();
            transScene = createTransScene.createTransScene();
            window.setScene(transScene);
        });
        return goToAddATrans;
    }

    public Button goToSetUpButton(){
        Button gotToSetUpPage = new Button("Go to Set Up Scene");
        gotToSetUpPage.setOnAction(e-> {
            SetUpScene createSetUpScene = new SetUpScene();
            setupScene = createSetUpScene.createSetUpScene();
            window.setScene(setupScene);
        });
        return gotToSetUpPage;
    }




}
