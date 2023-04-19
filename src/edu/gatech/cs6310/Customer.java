package edu.gatech.cs6310;

import java.util.TreeMap;

public class Customer extends User{

    private String accountName;
    private Integer rating;
    private Integer credit;
    private Integer remainingCredit;
    private final TreeMap<String, Order> orderHistory = new TreeMap<String, Order>();

    public Customer(String fName, String lName, String ssn, String phoneNumber, Integer rating, Integer credit) {
        super(fName, lName, ssn, phoneNumber);
        this.rating = rating;
        this.credit = credit;
        this.remainingCredit = credit;
    }

    @Override
    public String toString() {
        return "name:" + getFirstName() + "_" + getLastName() + "," +
                "phone:" + getPhoneNumber() + "," +
                "rating:" + rating + "," +
                "credit:" + credit;
    }

    public Boolean doesOrderExit(String orderID) {
        return orderHistory.containsKey(orderID);
    }

    public void addOrder(Order order, String storeName) {
        orderHistory.put(order.getOrderID() + "_" + storeName, order);
    }

    public void removeOrder(String orderID) {
        orderHistory.remove(orderID);
    }

    /* ======================= GETTERS AND SETTERS ======================= */

    public Integer getCredit() { return credit; }

    public void decreaseCreditLimit(Integer credit) {
        this.credit -= credit;
        this.remainingCredit = credit;
    }

    public Integer getRemainingCredit() { return remainingCredit; }

    public void decreaseRemainingCreditLimit(Integer credit) { this.remainingCredit -= credit; }

    public void increaseRemainingCreditLimit(Integer amount) { remainingCredit += amount; }

    public String getAccountName() { return accountName; }

}
