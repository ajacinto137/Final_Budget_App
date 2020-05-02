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
    Scene setupScene, transScene, dashScene;
    String[] weeks = {"1", "2", "3", "4"};
    ArrayList<Category> categoriesObjs = new ArrayList<>();
    ObservableList<String> categories = FXCollections.observableArrayList();
    ObservableList<String> catPrice = FXCollections.observableArrayList();
    ObservableList<String> transWeek = FXCollections.observableArrayList();
    ObservableList<String> transCat = FXCollections.observableArrayList();
    ObservableList<String> transPrice = FXCollections.observableArrayList();
    ObservableList<String> transaction = FXCollections.observableArrayList();
    static ArrayList<Transaction> transactionsArray = new ArrayList<>();
    ArrayList<Transaction> chosenTransactions = new ArrayList<>();
    ListView CatlistView = new ListView();
    ListView TransListView = new ListView();


    //Put transactions into list view

    public double addTransactions(ArrayList<Transaction> chosenTransactions){
        double total = 0;
        for (int i = 0; i < chosenTransactions.size(); i++){
            total += chosenTransactions.get(i).price;
        }
        return total;
    }


    public double keywordSumation(String keyword,ArrayList<Transaction>transactionsArray){
        double total = 0;
        for (int i = 0; i < transactionsArray.size() ; i++) {
            if(keyword.equals(transactionsArray.get(i).category)){
                total += transactionsArray.get(i).price;
            }
        }
        System.out.println(total);
        return total;
    }

    public void fileRead() {
        String fileName = "Category_Data.txt";
        File file = new File(fileName);
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                String[] values = data.split(",");
                CatlistView.getItems().add(values[0] + ": " + values[1]);
                categories.add(values[0]);
                catPrice.add(values[1]);
            }
            inputStream.close();
            for (int i = 0; i < catPrice.size(); i++) {
                System.out.println(catPrice.get(i) + " " + categories.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void fileReadCat() {
        String fileName = "ObjCatFile.txt";
        File file = new File(fileName);
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                String[] values = data.split(",");
//                CatlistView.getItems().add(values[0] + ": " + values[1]);
//                categories.add(values[0]);
//                catPrice.add(values[1]);
                Category newCategory = new Category(values[0], Double.parseDouble(values[1]), Boolean.parseBoolean(values[2]));
                categoriesObjs.add(newCategory);
            }
            inputStream.close();
            for (int i = 0; i < categoriesObjs.size(); i++) {
                System.out.println(categoriesObjs.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void fileReadTrans() {
        String fileName = "TransactionData.txt";
        File file = new File(fileName);
        transactionsArray.clear();
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                String[] values = data.split(",");
                Transaction newTransAction = new Transaction(Integer.parseInt(values[0]), values[1], Double.parseDouble(values[2]));
                transactionsArray.add(newTransAction);
            }
            for (int i = 0; i < transactionsArray.size(); i++) {
                System.out.println(transactionsArray.get(i));
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getTransactionByWeek(int x){
        chosenTransactions.clear();
        int count = 0;
        for (int i = 0; i < transactionsArray.size(); i++)
            if (transactionsArray.get(i).week == x) {
                System.out.println(transactionsArray.get(i));
                chosenTransactions.add(transactionsArray.get(i));
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
//        DashScene createTransScene = new DashScene();
//        testScene = createTransScene.createTransScene();

//        SetUpScene createSetUpScene = new SetUpScene();
//        setupScene = createSetUpScene.createSetUpScene();

        Dashboard dashboard = new Dashboard();
        dashScene = dashboard.createDashScene();
        window.setScene(dashScene);
        window.setTitle("Budget App");
        window.show();

    }
}
