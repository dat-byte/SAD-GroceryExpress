package edu.gatech.cs6310;

import java.util.TreeMap;

public class Store {
    private String storeID;
    private Integer revenue;
    private Integer transferOrders;
    private Integer numPurchases;
    private Integer overloads;
    private final TreeMap<String, Order> orders = new TreeMap<String, Order>();
    private final TreeMap<String, Item> items = new TreeMap<String, Item>();
    private final TreeMap<String, Drone> drones = new TreeMap<String, Drone>();
    private final String DRONE_CANT_CARRY = "drone_cant_carry_new_item";
    private final String CUSTOMER_CANT_AFFORD = "customer_cant_afford_new_item";
    private final String DRONE_NEEDS_PILOT = "drone_needs_pilot";
    private final String DRONE_NEEDS_FUEL = "drone_needs_fuel";
    public Store(String storeName, Integer revenue) {
        this.storeID = storeName;
        this.revenue = revenue;
        this.transferOrders = 0;
        this.numPurchases = 0;
        this.overloads = 0;

    }

    @Override
    public String toString() {
        return "name:" + storeID + ",revenue:" + revenue;
    }

    public void displayEfficiency() {
        System.out.println("name:" + storeID + "," + "purchases:" + numPurchases + "," +
                "overloads:" + overloads + "," + "transfers:" + transferOrders);
    }

    public void increaseTransferOrder() { transferOrders += 1; }

    public void requestItem(String orderID, String itemName, Integer quantity, Integer price, Customer customer) {
        Item item = items.get(itemName);
        item.setPrice(price);
        Order order = orders.get(orderID);
        Drone drone = drones.get(order.getDroneID());
        LineItem lineItem = new LineItem(item, quantity);

        if (lineItem.getCost() > customer.getRemainingCredit())
            DeliveryHelper.errorMsg(CUSTOMER_CANT_AFFORD);
        else
            if (lineItem.getWeight() > drone.getRemainingCapacity())
                DeliveryHelper.errorMsg(DRONE_CANT_CARRY);
            else {
                order.addLineItem(lineItem);
                drone.decreaseRemainingCapacity(lineItem.getWeight());
                customer.decreaseRemainingCreditLimit(lineItem.getCost());
                DeliveryHelper.successMsg();
            }
    }

    public void purchaseComplete(String orderID, Customer customer, DronePilot dronePilot) {
        Order order = orders.get(orderID);
        Drone drone = drones.get(order.getDroneID());

        if (drone.getPilot() != null) {
            if (drone.getRemainingTrips() >= 1) {
                Integer remainingCredit = customer.getCredit() - order.getTotalWeight() - customer.getRemainingCredit();
                customer.decreaseCreditLimit(order.getTotalCost());
                customer.decreaseRemainingCreditLimit(remainingCredit);
                customer.removeOrder(orderID + "_" + storeID);

                drone.increaseRemainingCapacity(order.getTotalWeight());
                drone.decreaseRemainingTripCount();
                drone.decreaseNumberOfOrders();
                drone.removeOrder(orderID);

                orders.remove(orderID);
                dronePilot.increaseExperience();

                numPurchases += 1;
                overloads += drone.getNumberOfOrders();
                revenue += order.getTotalCost();
                DeliveryHelper.successMsg();
            } else
                DeliveryHelper.errorMsg(DRONE_NEEDS_FUEL);
        }else
            DeliveryHelper.errorMsg(DRONE_NEEDS_PILOT);
    }

    /* ======================= CODE FOR ORDERS ======================= */

    public Boolean doesOrderExist(String orderID) {
        return orders.containsKey(orderID);
    }

    public Order getOrder(String orderID) {
        return orders.get(orderID);
    }

    public void addOrder(Order order) {
        orders.put(order.getOrderID(), order);
    }

    public void removeOrder(Order order) {
        orders.remove(order.getOrderID());
    }

    public void displayStoreOrders() {
        for (String key : orders.keySet())
            System.out.println(orders.get(key));
    }

    /* ======================= CODE FOR DRONES ======================= */

    public Boolean doesDroneExist(String droneID) {
        return drones.containsKey(droneID);
    }

    public Drone getDrone(String droneID) {
        return drones.get(droneID);
    }

    public void addDrone(Drone drone) {
        drones.put(drone.getDroneID(), drone);
    }

    public void removeDrone(Drone drone) { drones.remove(drone); }

    public void displayStoreDrones() {
        for (String key : drones.keySet())
            System.out.println(drones.get(key));
    }

    /* ======================= CODE FOR ITEMS ======================= */

    public Boolean doesItemExist(String itemName) { return items.containsKey(itemName); }

    public void addItem(Item item) { items.put(item.getName(), item); }

    public void removeItem(Item item) { items.remove(item); }

    public void displayStoreItems() {
        for(String key : items.keySet())
            System.out.println(items.get(key));
    }

    /* ======================= GETTERS AND SETTERS ======================= */

    public String getStoreID() {
        return storeID;
    }

    public Integer getRevenue() {
        return revenue;
    }
}

