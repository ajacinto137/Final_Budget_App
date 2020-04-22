package com.company;

import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class WriteToFile {

    public static void saveRecord(String Category, String Price,String filepath){

        try{

            FileWriter fw = new FileWriter(filepath,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(Category + "," + Price);
            pw.flush();
            pw.close();
            System.out.println("Record Saved");
        }

        catch(Exception e)
        {
            System.out.println("Catch");
        }

    }
}
