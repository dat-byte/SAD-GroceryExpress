package edu.gatech.cs6310;

public class Item {
    private String name;

    private Integer weight;

    private Integer price;

    public Item(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
        this.price = 0;
    }

    @Override
    public String toString() {
        return name + "," + weight;
    }

    /* ======================= GETTERS AND SETTERS ======================= */

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getPrice() {
        return price;
    }
}