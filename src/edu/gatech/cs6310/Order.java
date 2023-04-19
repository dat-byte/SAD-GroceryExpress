package edu.gatech.cs6310;

import javax.sound.sampled.Line;
import java.sql.SQLOutput;
import java.util.TreeMap;

public class Order {
    private String orderID;
    private String customerID;
    private String storeID;
    private String droneID;
    private Boolean isComplete;
    private Integer totalCost;
    private Integer totalWeight;
    private TreeMap<String, LineItem> lineItems = new TreeMap<String, LineItem>();

    public Order(String orderID, String storeID, String customerID, String droneID) {
        this.orderID = orderID;
        this.storeID = storeID;
        this.customerID = customerID;
        this.droneID = droneID;
        this.totalCost = 0;
        this.totalWeight = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("orderID:").append(orderID);

        if (lineItems.isEmpty())
            return sb.toString();
        sb.append("\n");
        for (String key : lineItems.keySet()) {
            sb.append(lineItems.get(key).displayLineItemInfo());
            sb.append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public Boolean doesItemExistInOrder(String itemName) {
        return lineItems.containsKey(itemName);
    }

    public LineItem findLineItem(String itemName) {
        if (lineItems.containsKey(itemName))
            return lineItems.get(itemName);
        return new LineItem(true);
    }

    public void addLineItem(LineItem line) {
        lineItems.put(line.getItem().getName(), line);
        totalWeight += line.getWeight();
        totalCost += line.getCost();
    }

    public void removeLineItemFromOrder(String itemName) {
        LineItem temp = lineItems.get(itemName);
        totalWeight -= temp.getWeight();
        totalCost -= temp.getCost();
        lineItems.remove(temp);
    }

    /* ======================= GETTERS AND SETTERS ======================= */
    public void setIsComplete(Boolean complete) {
        isComplete = complete;
    }

    public void setDroneID(String id) { droneID = id; }

    public String getOrderID() {
        return orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getStoreID() {
        return storeID;
    }


    public String getDroneID() {
        return droneID;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public Integer getTotalWeight() {
        return totalWeight;
    }
}

