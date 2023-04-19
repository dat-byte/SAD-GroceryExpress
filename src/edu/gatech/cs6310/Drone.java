package edu.gatech.cs6310;

import java.util.TreeMap;

public class Drone {
    private String droneID;

    private int remainingTrips;

    private int numOfOrders;

    private final Integer weightCapacity;

    private Integer remainingCapacity;

    private DronePilot pilot;

    private final TreeMap<String, Order> orders = new TreeMap<String, Order>();

    public Drone(String droneID, int remainingTrips, Integer weightCapacity) {
        this.droneID = droneID;
        this.remainingTrips = remainingTrips;
        this.weightCapacity = weightCapacity;
        this.remainingCapacity = weightCapacity;
        this.pilot = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("droneID:").append(droneID).append(",");
        sb.append("total_cap:").append(weightCapacity).append(",");
        sb.append("num_orders:").append(numOfOrders).append(",");
        sb.append("remaining_cap:").append(remainingCapacity).append(",");
        sb.append("trips_left:").append(remainingTrips);
        if (pilot != null)
            sb.append(",flown_by:").append(pilot.getFirstName()).append("_").append(pilot.getLastName());
        return sb.toString();
    }

    public void increaseNumberOfOrders() {
        numOfOrders += 1;
    }

    public void decreaseNumberOfOrders() { numOfOrders -= 1; }

    public void decreaseRemainingTripCount() { remainingTrips = remainingTrips - 1; }

    public void decreaseRemainingCapacity(Integer amount) { remainingCapacity -= amount; }

    public void increaseRemainingCapacity(Integer amount) { remainingCapacity += amount; }

    public void addOrder(Order order) {
        orders.put(order.getOrderID(), order);
    }

    public void removeOrder(String orderID) { orders.remove(orderID); }

    /* ======================= GETTERS AND SETTERS ======================= */

    public void setPilot(DronePilot pilot) {
        this.pilot = pilot;
    }

    public DronePilot getPilot() {
        return pilot;
    }

    public String getDroneID() {
        return droneID;
    }

    public Integer getNumberOfOrders() { return numOfOrders; }

    public int getRemainingTrips() {
        return remainingTrips;
    }

    public Integer getRemainingCapacity() { return remainingCapacity; }
}
