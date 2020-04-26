package com.company;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static com.company.WriteToFile.saveRecord;


public class Budget extends Application {

    Stage window;
    Scene setupScene, dashScene, transactionScene;
    String[] weeks = {"1", "2", "3", "4"};
    ObservableList<String> categories = FXCollections.observableArrayList();
    ObservableList<String> catPrice = FXCollections.observableArrayList();
    ObservableList<String> transWeek = FXCollections.observableArrayList();
    ObservableList<String> transCat = FXCollections.observableArrayList();
    ObservableList<String> transPrice = FXCollections.observableArrayList();
    ObservableList<String> transaction = FXCollections.observableArrayList();
    ListView listView = new ListView();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage){

        //Stage
        window = primaryStage;

        //Set Up Catergory
        Label addACategory = new Label("Add A Category");
        TextField catField = new TextField();
        Label priceLabel = new Label("Price");
        TextField priceField = new TextField();
        Label transactionLabel = new Label("Transaction Page");
        fileRead();

        // Add Category to ListView
        Button button1 = new Button("Add");
        button1.setOnAction(e -> {
            catPrice.add(priceField.getText());
            categories.add(catField.getText());
            listView.getItems().add(catField.getText() + ": " + priceField.getText());
            saveRecord(categories.get(categories.size()-1),catPrice.get(catPrice.size()-1),"Category_Data.txt");
            priceField.clear();
            catField.clear();
        });


        //Button to go to next page
        Button testingAButton = new Button("Testing");
        testingAButton.setOnAction(e ->{
            window.setScene(dashScene);
        });



        //Lay Out for categoryLabel scene
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(addACategory, catField, priceLabel,priceField,button1,testingAButton);
        //Container for layour
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(layout1,listView);
        layout1.setPadding(new Insets(20));
        //Assemble Layout into scene
        setupScene = new Scene(hBox);
        // End Set Up Scene

//        Label transactionSetup = new Label("Transaction Setup");
//        HBox transactionHBox = new HBox(20);
//        transactionHBox.getChildren().addAll(transactionHBox);
//        transactionScene = new Scene(transactionHBox);




        // DashScene

        // Choose Week Controls
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
        button2.setOnAction(e -> window.setScene(setupScene));


        //Dash Scene Org and Assembly
        Label transPriceLabel = new Label("Price");
        TextField transPriceField = new TextField();


        Button submitTransaction = new Button("Submit Transaction");

        submitTransaction.setOnAction(e ->{
            transWeek.add(chosenWeek.getValue());
            transCat.add(chosenCategory.getValue());
            transPrice.add(transPriceField.getText());
            saveRecord(transWeek.get(transWeek.size()-1),transCat.get(transCat.size()-1), transPrice.get(transPrice.size()-1),"TransactionData.txt");
            transPriceField.clear();
            chosenCategory.setValue(null);
            chosenWeek.setValue(null);
        });

        Button readTransaction = new Button("Read Transaction");
        readTransaction.setOnAction(e->{
            fileReadTrans();
        });


        VBox layout2 = new VBox();
        layout2.getChildren().addAll(addAddTransaction,weekLabel,chosenWeek, categoryLabel,chosenCategory,transPriceLabel,transPriceField, submitTransaction,button2,readTransaction);
        dashScene = new Scene(layout2,400,400);


        //Dash Scene Org and Assembly
        window.setScene(setupScene);
        window.setTitle("Title");
        window.show();

    }

    public void fileRead(){
        String fileName = "Category_Data.txt";
        File file = new File(fileName);
        try{
            Scanner inputStream = new Scanner(file);
            while(inputStream.hasNext()){
                String data = inputStream.next();
                String[] values = data.split(",");
                listView.getItems().add(values[0] + ": " + values[1]);
                categories.add(values[0]);
            }
            inputStream.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void fileReadTrans(){
        String fileName = "TransactionData.txt";
        File file = new File(fileName);
        try{
            Scanner inputStream = new Scanner(file);
            while(inputStream.hasNext()){
                String data = inputStream.next();
                String[] values = data.split(",");
                transaction.add(values[0] + values[1] + values[2]);
            }
            for (int i = 0; i < transaction.size(); i++){
                System.out.println(transaction.get(i));
            }
            inputStream.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
//test