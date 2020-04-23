package com.company;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Scanner;

import static com.company.Read_File.fileRead;
import static com.company.WriteToFile.saveRecord;


public class Budget extends Application {

    Stage window;
    Scene setupScene, dashScene;
    String[] weeks = {"Week 1", "Week 2", "Week 3", "Week 4"};
    ObservableList<String> categories = FXCollections.observableArrayList();
    ObservableList<String> catPrice = FXCollections.observableArrayList();
    ListView listView = new ListView();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage){

        window = primaryStage;


        //SetUpScene
        Label label1 = new Label("Add A Category");
        TextField catField = new TextField();
        Label label2 = new Label("Price");
        TextField priceField = new TextField();
        fileRead();
        Button button1 = new Button("Add");
        Button nextPage = new Button("Submit");

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, catField,label2,priceField,button1,nextPage);

        nextPage.setOnAction(e ->{
            window.setScene(dashScene);
        });

        button1.setOnAction(e -> {
//          window.setScene(dashScene);
            catPrice.add(priceField.getText());
            categories.add(catField.getText());
            System.out.println(categories);
            listView.getItems().add(catField.getText() + ": " + priceField.getText());
            saveRecord(categories.get(categories.size()-1),catPrice.get(catPrice.size()-1),"Category_Data.txt");
            priceField.clear();
            catField.clear();
        });

        //Organize Layout

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(layout1,listView);
        layout1.setPadding(new Insets(20));

        //Assemble Layout into scene
        setupScene = new Scene(hBox);

        // End Set Up Scene



        // DashScene

        // Choose Week Controls
        Label week = new Label("Week");
        ComboBox<String> chosenWeek = new ComboBox<String>();
        chosenWeek.getItems().addAll(weeks);

        Label day = new Label("Katrina is anooying");

        //Choose Category
        Label category = new Label("Category");
        ComboBox<String> chosenCategory = new ComboBox<String>();
//        chosenCategory.getItems().addAll(categories);
//        ComboBox<String> chosenCategory = new ComboBox<>(FXCollections.observableArrayList(categories));
        chosenCategory.setItems(categories);




        // Submission Button
        Button button2 = new Button("button");
        button2.setOnAction(e -> window.setScene(setupScene));

        //test button
        Button test = new Button("testing");
        test.setOnAction(e ->{
            System.out.println(categories);
        });



        //Dash Scene Org and Assembly
        VBox layout2 = new VBox();
        layout2.getChildren().addAll(week,chosenWeek,category,chosenCategory,button2,test);
        dashScene = new Scene(layout2,400,400);

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
                System.out.println(values[1]);
                listView.getItems().add(values[0] + ":" + values[1]);
                categories.add(values[0]);
            }
            inputStream.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
