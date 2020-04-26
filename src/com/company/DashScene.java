package com.company;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.swing.*;

import static com.company.WriteToFile.saveRecord;

public class DashScene extends Budget {

    public Scene createDashScene(){
        fileRead();
        Label addAddTransaction = new Label("Add a transaction");
        Label weekLabel = new Label("Week");
        ComboBox<String> chosenWeek = new ComboBox<String>();
        chosenWeek.getItems().addAll(weeks);

        //Choose Category
        Label categoryLabel = new Label("Category");
        ComboBox<String> chosenCategory = new ComboBox<String>();
        chosenCategory.setItems(categories);


        // Submission Button
        Button button2 = new Button("Go back");
        button2.setOnAction(e -> {

            SetUpScene createSetUpScene = new SetUpScene();
            setupScene = createSetUpScene.createSetUpScene();
            window.setScene(setupScene);
        });


        //Dash Scene Org and Assembly
        Label transPriceLabel = new Label("Price");
        TextField transPriceField = new TextField();


        Button submitTransaction = new Button("Submit Transaction");

        submitTransaction.setOnAction(e ->{
            if (transPriceField.getText().isEmpty() || chosenCategory.getValue().isEmpty() || chosenWeek.getValue().isEmpty()){
                transPriceField.clear();
                chosenCategory.setValue(null);
                chosenWeek.setValue(null);
                JOptionPane.showMessageDialog(null, "You need to add more");
            }
            else{
                transactionsArray.clear();
                transWeek.add(chosenWeek.getValue());
                transCat.add(chosenCategory.getValue());
                transPrice.add(transPriceField.getText());
                saveRecord(transWeek.get(transWeek.size()-1),transCat.get(transCat.size()-1), transPrice.get(transPrice.size()-1),"TransactionData.txt");
                transPriceField.clear();
                chosenCategory.setValue(null);
                chosenWeek.setValue(null);
                fileReadTrans();
                System.out.println(transactionsArray.size());
            }

        });

        Button getTransactionByWeek = new Button("Get Transaction By Week");
        getTransactionByWeek.setOnAction(e->{
            getTransactionByWeek(2);
        });


        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(addAddTransaction,weekLabel,chosenWeek, categoryLabel,chosenCategory,transPriceLabel,
                transPriceField, submitTransaction,getTransactionByWeek,button2);
        layout2.setPadding(new Insets(20));
        layout2.setAlignment(Pos.CENTER);
        dashScene = new Scene(layout2,400,400);
        return dashScene;
    }
}
