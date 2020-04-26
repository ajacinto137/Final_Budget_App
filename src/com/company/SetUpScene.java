package com.company;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;

import static com.company.WriteToFile.saveRecord;

public class SetUpScene extends Budget{
    public Scene createSetUpScene(){
        //////////////////////////////////////////////////////////////////
        Label addACategory = new Label("Add A Category");
        TextField catField = new TextField();
        Label priceLabel = new Label("Price");
        TextField priceField = new TextField();
        Label transactionLabel = new Label("Transaction Page");
        fileRead();

        // Add Category to ListView
        Button button1 = new Button("Add");
        button1.setOnAction(e -> {
            if (catField.getText().isEmpty() || priceField.getText().isEmpty()){
                priceField.clear();
                catField.clear();
                JOptionPane.showMessageDialog(null, "You need to add more");
            }
            else {
                catPrice.add(priceField.getText());
                categories.add(catField.getText());
                listView.getItems().add(catField.getText() + ": " + priceField.getText());
                saveRecord(categories.get(categories.size()-1),catPrice.get(catPrice.size()-1),"Category_Data.txt");
                priceField.clear();
                catField.clear();
            }
        });

        //Button to go to next page
        Button goToDash = new Button("Go to Dash");
        goToDash.setOnAction(e ->{
            DashScene createDashScene = new DashScene();
            dashScene = createDashScene.createDashScene();
            window.setScene(dashScene);
        });

        //Lay Out for categoryLabel scene
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(addACategory, catField, priceLabel,priceField,button1,goToDash);
        //Container for layour
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(layout1,listView);
        layout1.setPadding(new Insets(20));
        //Assemble Layout into scene
        setupScene = new Scene(hBox);
        return setupScene;
    }
}
