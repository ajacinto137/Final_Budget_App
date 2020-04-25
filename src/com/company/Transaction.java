package com.company;

public class Transaction {
    int week;
    String category;
    double price;
    static int count;

    public Transaction(int week, String category, double price){
        this.week = week;
        this.category = category;
        this.price  = price;
        count++;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Transaction " + count + " { "+
                "week=" + week +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }

    public static void main(String[] args) {
        Transaction test_transaction = new Transaction(4,"food", 100.00);
        System.out.println(test_transaction.week + test_transaction.category + test_transaction.price);
        System.out.println(test_transaction.toString());
    }
}
