package com.company;

public class Category {
    String name;
    Double price;
    boolean weeklyBudget;

    public Category(String name, Double price, boolean weeklyBudget) {
        this.name = name;
        this.price = price;
        this.weeklyBudget = weeklyBudget;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", weeklyBudget=" + weeklyBudget +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isWeeklyBudget() {
        return weeklyBudget;
    }

    public void setWeeklyBudget(boolean weeklyBudget) {
        this.weeklyBudget = weeklyBudget;
    }
}
