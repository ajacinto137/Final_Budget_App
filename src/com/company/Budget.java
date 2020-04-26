package com.company;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Budget extends Application {

    public static Stage window;
    Scene setupScene, dashScene;
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
//        DashScene createDashScene = new DashScene();
//        testScene = createDashScene.createDashScene();

        SetUpScene createSetUpScene = new SetUpScene();
        setupScene = createSetUpScene.createSetUpScene();

        window.setScene(setupScene);
        window.setTitle("Title");
        window.show();

    }
}
