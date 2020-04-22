package com.company;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import static com.company.WriteToFile.saveRecord;


public class Budget extends Application {

    Stage window;
    Scene setupScene, dashScene;
    String[] weeks = {"Week 1", "Week 2", "Week 3", "Week 4"};
    ObservableList<String> categories = FXCollections.observableArrayList();
    ObservableList<String> catPrice = FXCollections.observableArrayList();


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

        Button add = new Button("Add");

        add.setOnAction(e -> {
            categories.add(catField.getText());
            System.out.println(categories);
        });
        Button button1 = new Button("Submit");
        button1.setOnAction(e -> {
            window.setScene(dashScene);
            catPrice.add(priceField.getText());
            categories.add(catField.getText());
            System.out.println(categories);
            saveRecord(categories.get(categories.size()-1),catPrice.get(catPrice.size()-1),"cake.txt");
        });

        //Organize Layout
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, catField,add,label2,priceField,button1);
        layout1.setPadding(new Insets(20));

        //Assemble Layout into scene
        setupScene = new Scene(layout1);

        // End Set Up Scene



        // DashScene

        // Choose Week Controls
        Label week = new Label("Week");
        ComboBox<String> chosenWeek = new ComboBox<String>();
        chosenWeek.getItems().addAll(weeks);

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
}
