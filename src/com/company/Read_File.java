package com.company;

import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Read_File {
    public static void main(String[] args) {
        fileRead();
    }
    public static String fileRead(){
        String fileName = "Category_Data.txt";
        File file = new File(fileName);
        try{
            Scanner inputStream = new Scanner(file);
            while(inputStream.hasNext()){
                String data = inputStream.next();
                String[] values = data.split(",");
                System.out.println(values[1]);
                return (values[0] + ":" + values[1]);
            }
            inputStream.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }


    public static String fileRead(ObservableList transacation){
        String fileName = "TransactionData.txt";
        String[] transaction_from_file;
        File file = new File(fileName);
        try{
            Scanner inputStream = new Scanner(file);
            while(inputStream.hasNext()){
                String data = inputStream.next();
                String[] values = data.split(",");
                System.out.println(values[1]);
                transacation.add(values[1]);
                return (values[0] + ":" + values[1]);
            }
            inputStream.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

}
