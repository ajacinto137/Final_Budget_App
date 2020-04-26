package com.company;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    ArrayList<Transaction> transactionsArray = new ArrayList<>();
    ListView listView = new ListView();

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
                Transaction newTransAction = new Transaction(Integer.parseInt(values[0]), values[1],Double.parseDouble(values[2]));
                transactionsArray.add(newTransAction);
                transaction.add(values[0] + values[1] + values[2]);
            }
            for (int i = 0; i < transactionsArray.size(); i++){
                System.out.println(transactionsArray.get(i));
            }
            inputStream.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void getTransactionByWeek(int x){
        int count = 0;
        for (int i = 0; i < transactionsArray.size(); i++)
            if (transactionsArray.get(i).week == x) {
                System.out.println(transactionsArray.get(i));
                count++;
            }
        System.out.println("There are " + count + " transactions in week " + x );
    }

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

//        Button readTransaction = new Button("Read Transaction");
//        readTransaction.setOnAction(e->{
//            fileReadTrans();
//            System.out.println(transactionsArray.size());
//        });

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


        //Dash Scene Org and Assembly
        fileReadTrans();
        window.setScene(setupScene);
        window.setTitle("Title");
        window.show();

    }

}
