package com.company;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        RadioButton isWeeklyExpenses = new RadioButton("Yes");
        RadioButton isNotAweeklyExpense = new RadioButton("No");
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
                CatlistView.getItems().add(catField.getText() + ": " + priceField.getText());
                saveRecord(categories.get(categories.size()-1),catPrice.get(catPrice.size()-1),"Category_Data.txt");

                if (isNotAweeklyExpense.isSelected()){
                    Category newCatagory = new Category(catField.getText(),Double.parseDouble(priceField.getText()),false);
                    saveRecord(newCatagory.name,newCatagory.price,newCatagory.weeklyBudget,"ObjCatFile.txt");
                    categoriesObjs.add(newCatagory);

                }
                if (isWeeklyExpenses.isSelected()){
                    Category newCatagory = new Category(catField.getText(),Double.parseDouble(priceField.getText()),true);
                    saveRecord(newCatagory.name,newCatagory.price,newCatagory.weeklyBudget,"ObjCatFile.txt");
                    categoriesObjs.add(newCatagory);
                }

                priceField.clear();
                catField.clear();
            }
        });

        //Button to go to next page
        Button goToAddATrans = new Button("Add Transactions");
        goToAddATrans.setOnAction(e ->{
            TransScene createTransScene = new TransScene();
            transScene = createTransScene.createTransScene();
            window.setScene(transScene);
        });

        Button gotToDash = new Button("Go to Dash Scene");
        gotToDash.setOnAction(e-> {
            Dashboard dashboard = new Dashboard();
            dashScene = dashboard.createDashScene();
            window.setScene(dashScene);
        });

        //Lay Out for categoryLabel scene
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(addACategory, catField, priceLabel,priceField,isWeeklyExpenses,isNotAweeklyExpense,button1, goToAddATrans,gotToDash);
        //Container for layour
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(layout1, CatlistView);
        layout1.setPadding(new Insets(20));
        //Assemble Layout into scene
        setupScene = new Scene(hBox);
        return setupScene;
    }
}
