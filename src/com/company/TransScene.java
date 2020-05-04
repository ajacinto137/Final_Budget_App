package com.company;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;

import static com.company.WriteToFile.saveRecord;

public class TransScene extends Budget {

    public Scene createTransScene(){
        fileRead();
        Label addAddTransaction = new Label("Add a transaction");
        Label weekLabel = new Label("Week");
        ComboBox<String> chosenWeek = new ComboBox<String>();
        chosenWeek.getItems().addAll(weeks);
        ListView listViewDash = new ListView();
        listViewDash.setPrefWidth(400);

        //Choose Category
        Label categoryLabel = new Label("Category");
        ComboBox<String> chosenCategory = new ComboBox<String>();
        chosenCategory.setItems(categories);


        // Submission Button
        Button button2 = new Button("Category Page");
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
                TransListView.getItems().clear();
                fileReadTrans();
                getTransactionByWeek(Integer.parseInt(chosenWeek.getValue()));
                for (int i = 0; i  < chosenTransactions.size(); i++){
                    listViewDash.getItems().addAll(chosenTransactions.get(i));
                }
                saveRecord(transWeek.get(transWeek.size()-1),transCat.get(transCat.size()-1), transPrice.get(transPrice.size()-1),"TransactionData.txt");
                transPriceField.clear();
                chosenCategory.setValue(null);
                chosenWeek.setValue(null);
                fileReadTrans();
//                for (int i = 0; i < transactionsArray.size(); i++){
//                    listViewDash.getItems().add(transactionsArray.get(i));
//                }
                System.out.println(transactionsArray.size());
            }

        });

        Button getTransactionByWeek = new Button("Get Transaction By Week");
        getTransactionByWeek.setOnAction(e->{
            getTransactionByWeek(2);
        });

        Controllers cntrlr = new Controllers();

        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(addAddTransaction,weekLabel,chosenWeek, categoryLabel,chosenCategory,transPriceLabel,
                transPriceField, submitTransaction,button2,cntrlr.getGotToDashButton());

        layout2.setPadding(new Insets(20));


        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(layout2, listViewDash);
//        hBox.setPrefWidth(500);
        transScene = new Scene(hBox);
        return transScene;
    }
}
