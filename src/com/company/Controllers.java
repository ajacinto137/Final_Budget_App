package com.company;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;


public class Controllers extends Budget {
    public HBox chooseWeek(String displayLabel){
        Label label = new Label(displayLabel);
        ComboBox<String> chosenWeek = new ComboBox<String>();
        chosenWeek.getItems().addAll(weeks);
        HBox chooseWeekLayout = new HBox();
        chooseWeekLayout.getChildren().addAll(label,chosenWeek);
        return chooseWeekLayout;
    }

    public VBox chooseWeek(String displayLabel, boolean printTrans){
        HBox chooseWeekLayout = new HBox(20);
        Label label = new Label(displayLabel);
        label.setAlignment(Pos.CENTER);
        ComboBox<String> chosenWeek = new ComboBox<String>();
        chosenWeek.getItems().addAll(weeks);
        Label testLabel = new Label();

        if (printTrans){
            chosenWeek.setOnAction(e->{
                TransListView.getItems().clear();
                fileReadTrans();
                getTransactionByWeek(Integer.parseInt(chosenWeek.getValue()));
                for (int i = 0; i  < chosenTransactions.size(); i++){
                    TransListView.getItems().addAll(chosenTransactions.get(i));
                }
                testLabel.setText("Total: 0" + addTransactions(chosenTransactions));
            });

        }
        chooseWeekLayout.getChildren().addAll(label,chosenWeek,testLabel);
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(chooseWeekLayout,TransListView);
        return vBox;
    }

    public Button goToTransButton(){
        Button goToAddATrans = new Button("Go to Transactions Page");
        goToAddATrans.setOnAction(e ->{
            TransScene createTransScene = new TransScene();
            transScene = createTransScene.createTransScene();
            window.setScene(transScene);
        });
        return goToAddATrans;
    }

    public Button goToSetUpButton(){
        Button gotToSetUpPage = new Button("Go to Set Up Scene");
        gotToSetUpPage.setOnAction(e-> {
            SetUpScene createSetUpScene = new SetUpScene();
            setupScene = createSetUpScene.createSetUpScene();
            window.setScene(setupScene);
        });
        return gotToSetUpPage;
    }

    public Button getGotToDashButton(){
        Button gotToDash = new Button("Dashboard");
        gotToDash.setOnAction(e-> {
            Dashboard dashboard = new Dashboard();
            dashScene = dashboard.createDashScene();
            window.setScene(dashScene);
        });
        return gotToDash;
    }


    public GridPane dashGrid(){
        GridPane gridPane = new GridPane();

        //Setting size for the pane

        //Setting the padding

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(10);
        gridPane.setHgap(25);

        //Setting the Grid alignment

        Label category = new Label("Category");
        Label utilized = new Label("Utilized");
        Label amountLeft = new Label("Amount Left");
        Label percentUtilized = new Label("Percent Used");
        //Arranging all the nodes in the grid
        gridPane.add(category, 0, 0);
        gridPane.add(utilized, 1, 0);
        gridPane.add(amountLeft, 2, 0);
        gridPane.add(percentUtilized, 3, 0);
        fileReadTrans();
        fileReadCat();
        for (int i = 0; i < categoriesObjs.size(); i++) {

            double total = keywordSumation(categoriesObjs.get(i).name,transactionsArray);
            double CategoryPrice = categoriesObjs.get(i).price;
            double percentUsed = (total/CategoryPrice)*100;
            String percentUsedFormatted = String.format("%.2f", percentUsed);
            String diference = String.valueOf(CategoryPrice-total);
            Label totalCategoryPrice = new Label(String.valueOf(CategoryPrice));
            Label categoryAndTotal = new Label(categoriesObjs.get(i).name + ": " + total);
            Label diferenceLabel = new Label(diference);
            Label percentage = new Label(percentUsedFormatted);

            gridPane.add(categoryAndTotal,0,i+1);
            gridPane.add(totalCategoryPrice,1,i+1);
            gridPane.add(diferenceLabel,2,i+1);
            gridPane.add(percentage,3,i+1);
        }
        String strDouble = String.format("%.2f", 2.00023);
        gridPane.setPadding(new Insets(20));
        return gridPane;
    }



    public VBox testWeeklyBudget(ArrayList<Category> categoriesObjs,ArrayList<Transaction>transactionsArray){
        VBox vBox = new VBox();
        int count = 0;
//        sortTransByWeek(weeks,transactionsArray);
        System.out.println("The size is" + week1Transaction.size());
        System.out.println("The size is" + week2Transaction.size());
        System.out.println("The size is" + week3Transaction.size());
        System.out.println("The size is" + week4Transaction.size());
        System.out.println(categoriesObjs.size());
        for (int i = 0; i < categoriesObjs.size(); i++) {
            if (categoriesObjs.get(i).weeklyBudget){
                count++;
                GridPane gridPane = new GridPane();
                gridPane.setVgap(10);
                gridPane.setHgap(25);
                System.out.println("IN if");

                for (int k = 0; k < count; k++) {


                    double total = keywordSumation(categoriesObjs.get(i).name,transactionsArray);
                    double CategoryPrice = categoriesObjs.get(i).price;
                    double percentUsed = (total/CategoryPrice)*100;
                    String percentUsedFormatted = String.format("%.2f", percentUsed);
                    String diference = String.valueOf(CategoryPrice-total);
                    Label totalCategoryPrice = new Label(String.valueOf(CategoryPrice));
                    Label categoryAndTotal = new Label(categoriesObjs.get(i).name + ": hello" + total);
                    Label diferenceLabel = new Label(diference);
                    Label percentage = new Label(percentUsedFormatted);

                    gridPane.add(categoryAndTotal,0,k);
                    gridPane.add(totalCategoryPrice,1,k);
                    gridPane.add(diferenceLabel,2,k);
                    gridPane.add(percentage,3,k);
                }
                vBox.getChildren().add(gridPane);
            }
            System.out.println(count);
        }
        return vBox;
    }


    public VBox addWeeklyBudgetTest(ArrayList<Transaction> transactionsArray, ArrayList<Category> categoriesObjs){
        GridPane gridPane = new GridPane();
//        gridPane.setVgap(1);
//        gridPane.setHgap(10);

        for (int i = 0; i < categoriesObjs.size(); i++) {
            System.out.println("IN for");

            if (categoriesObjs.get(i).weeklyBudget){

                System.out.println("IN if");
                double moneySpent = keywordSumation(categoriesObjs.get(i).name, transactionsArray);
                double limit = categoriesObjs.get(i).price;
                double difference = limit - moneySpent;
                double weeklyLimit = limit/4;
                double percentage = (moneySpent/limit) * 100;

                System.out.println(categoriesObjs.get(i).name + "Has a total of: " + moneySpent);

                Label moneySpentLabel = new Label("Money spent  = "+ moneySpent);
                Label test = new Label("Limit is " + limit);
                gridPane.add(moneySpentLabel,1,i+1);
                gridPane.add(test,2,i+1);


            }

        }

        String header = String.valueOf(transactionsArray.get(0).week);
        Label headerLabel = new Label("Week " + header + " Budget");
        headerLabel.setPadding(new Insets(20));
        VBox weeklyBudgetDisplay = new VBox();
//        weeklyBudgetDisplay.setAlignment(Pos.CENTER);
        weeklyBudgetDisplay.getChildren().addAll(headerLabel,gridPane);
        return weeklyBudgetDisplay;
    }


//    public Button test(){
//        Button goToAddATrans = new Button("testObjects");
//        goToAddATrans.setOnAction(e ->{
//            fileReadCat();
//            fileReadTrans();
//            System.out.println("Blahhhhhhh");
//            System.out.println(week1Transaction.size());
//            sortTransByWeek(weeks,transactionsArray);
//            System.out.println("Yoooooooooooooooooooooooooo");
//            System.out.println(week1Transaction.size());
//
////            addWeeklyBudget(week2Transaction,categoriesObjs);
//
//        });
//        return goToAddATrans;
//    }

}
