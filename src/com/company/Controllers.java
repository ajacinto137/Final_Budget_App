package com.company;

import javafx.scene.LightBase;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



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
        HBox chooseWeekLayout = new HBox();
        Label label = new Label(displayLabel);
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

    public Button goToTransButon(){
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
            gridPane.add(totalCategoryPrice,0,i+1);
            gridPane.add(categoryAndTotal,1,i+1);
            gridPane.add(diferenceLabel,2,i+1);
            gridPane.add(percentage,3,i+1);
        }
        String strDouble = String.format("%.2f", 2.00023);

        return gridPane;
    }

    public Button test(){
        Button goToAddATrans = new Button("testObjects");
        goToAddATrans.setOnAction(e ->{
            fileReadTrans();
            fileReadCat();
            keywordSumation("Rent",transactionsArray);
        });
        return goToAddATrans;
    }

}
