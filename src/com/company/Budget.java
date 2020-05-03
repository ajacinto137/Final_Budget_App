package com.company;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
    ArrayList<Transaction> transactionsArray = new ArrayList<>();
    ArrayList<Transaction> chosenTransactions = new ArrayList<>();
    ListView CatlistView = new ListView();
    ListView TransListView = new ListView();
    ArrayList<ArrayList<Transaction>> weeklyTransactions = new ArrayList<ArrayList<Transaction>>();
    ArrayList<Transaction> week1Transaction = new ArrayList<>();
    ArrayList<Transaction> week2Transaction = new ArrayList<>();
    ArrayList<Transaction> week3Transaction = new ArrayList<>();
    ArrayList<Transaction> week4Transaction = new ArrayList<>();



    //Put transactions into list view
    public void addWeeklyBudget(ArrayList<Transaction> transactionsArray, ArrayList<Category> categoriesObjs){
        VBox vBox = new VBox();
        for (int i = 0; i < categoriesObjs.size(); i++) {
            System.out.println("IN for");
            if (categoriesObjs.get(i).weeklyBudget == true){
                System.out.println("IN if");
                double moneySpent = keywordSumation(categoriesObjs.get(i).name, transactionsArray);
                double limit = categoriesObjs.get(i).price;
                double difference = limit - moneySpent;
                double weeklyLimit = limit/4;
                double percentage = (moneySpent/limit) * 100;
                System.out.println(categoriesObjs.get(i).name + "Has a total of: " + moneySpent);
                Label header = new Label("Week " + transactionsArray.get(i).week + "Weekly Budgeted Totals");


            }
        }
    }



    public void display(ArrayList<Transaction> transactionsArray){
        for (int i = 0; i < transactionsArray.size(); i++) {
            System.out.println(transactionsArray.get(i));
        }
    }
    public void sortTransByWeek (String[] weeks, ArrayList<Transaction>transactionsArray){

        System.out.println("Sorting Now");
        week1Transaction.clear();
        week2Transaction.clear();
        week3Transaction.clear();
        week4Transaction.clear();
        weeklyTransactions.add(week1Transaction);
        weeklyTransactions.add(week2Transaction);
        weeklyTransactions.add(week3Transaction);
        weeklyTransactions.add(week4Transaction);
        for (int i = 0; i < weeks.length; i++) {
            for (int k = 0; k < transactionsArray.size(); k++){
                if(Integer.parseInt(weeks[i]) == transactionsArray.get(k).week){
                    weeklyTransactions.get(i).add(transactionsArray.get(k));
                }
            }

        }


        display(week1Transaction);
        System.out.println("----------------------End 1----------------------------");
        display(week2Transaction);
        System.out.println("----------------------End 2----------------------------");
        display(week3Transaction);
        System.out.println("----------------------End 3----------------------------");
        display(week4Transaction);
        System.out.println("----------------------End 4----------------------------");



    }



    //Add the price of all transacitions in an array
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
//            for (int i = 0; i < catPrice.size(); i++) {
//                System.out.println(catPrice.get(i) + " " + categories.get(i));
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void fileReadCat() {
        System.out.println("Reading Cat");
        String fileName = "ObjCatFile.txt";
        File file = new File(fileName);
        categoriesObjs.clear();
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
//            for (int i = 0; i < categoriesObjs.size(); i++) {
//                System.out.println(categoriesObjs.get(i));
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("End File Read Cat");
    }

    public void fileReadTrans() {
        System.out.println("File Read Trans");
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
//            for (int i = 0; i < transactionsArray.size(); i++) {
//                System.out.println(transactionsArray.get(i));
//            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("File Read Trans End");
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
