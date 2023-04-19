package edu.gatech.cs6310;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class DeliveryHelper {

    private final TreeMap<String, Customer> customers = new TreeMap<String, Customer>();
    private final TreeMap<String, Store> stores = new TreeMap<String, Store>();
    private final TreeMap<String, DronePilot> pilots = new TreeMap<String, DronePilot>();
    private final Set<String> licenses = new HashSet<>();

    private final String STORE_DNE = "store_identifier_does_not_exist";
    private final String STORE_ALREADY_EXIST = "store_identifier_already_exists";
    private final String CUSTOMER_DNE = "customer_identifier_does_not_exist";
    private final String CUSTOMER_ALREADY_EXIST = "customer_identifier_already_exists";
    private final String DRONE_PILOT_DNE = "pilot_identifier_does_not_exist";
    private final String DRONE_PILOT_ALREADY_EXIST = "pilot_identifier_already_exists";
    private final String DRONE_PILOT_LICENSE_ALREADY_EXIST = "pilot_license_already_exists";
    private final String ITEM_DNE = "item_identifier_does_not_exist";
    private final String ITEM_ALREADY_EXIST = "item_identifier_already_exists";
    private final String ITEM_ALREADY_ORDER = "item_already_ordered";
    private final String ORDER_DNE = "order_identifier_does_not_exist";
    private final String ORDER_ALREADY_EXIST = "order_identifier_already_exists";
    private final String DRONE_DNE = "drone_identifier_does_not_exist";
    private final String DRONE_ALREADY_EXIST = "drone_identifier_already_exists";
    private final String NEW_DRONE_WEIGHT_CAPACITY = "new_drone_does_not_have_enough_capacity";
    private final String DRONE_CHANGE_SUCCESS =  "new_drone_is_current_drone_no_change";

    public static void successMsg() {
        System.out.println("OK:change_completed");
    }

    public static void displaySuccessMsg() { System.out.println("OK:display_completed");}

    public static void errorMsg(String msg) {
        System.out.println("ERROR:" + msg);
    }

    public void makeStore(String storeName, Integer revenue) {
        if (!stores.containsKey(storeName)) {
            stores.put(storeName, new Store(storeName, revenue));
            successMsg();
        } else
            errorMsg(STORE_ALREADY_EXIST);
    }

    public void displayStores() {
        for(String key : stores.keySet())
            System.out.println(stores.get(key));
        displaySuccessMsg();
    }

    public void sellItem(String storeName, String itemName, Integer weight) {
        if (stores.containsKey(storeName)) {
            Store store = stores.get(storeName);
            if (!store.doesItemExist(itemName)) {
                store.addItem(new Item(itemName, weight));
                successMsg();
            }
            else
                errorMsg(ITEM_ALREADY_EXIST);
        } else
            errorMsg(STORE_DNE);
    }

    public void displayItems(String storeName) {
        if (stores.containsKey(storeName)) {
            stores.get(storeName).displayStoreItems();
            displaySuccessMsg();
        } else
            errorMsg(STORE_DNE);
    }

    public void makePilot(String account, String fName, String lName, String phoneNumber, String taxID, String license, Integer exp) {
        if (pilots.containsKey(account)) {
            errorMsg(DRONE_PILOT_ALREADY_EXIST);
            return;
        }

        if (licenses.contains(license)) {
            errorMsg(DRONE_PILOT_LICENSE_ALREADY_EXIST);
            return;
        }

        pilots.put(account, new DronePilot(fName, lName, taxID, phoneNumber, account, license, 0.0, exp));
        licenses.add(license);
        successMsg();
    }

    public void displayPilots() {
        for(String key : pilots.keySet())
            System.out.println(pilots.get(key));
        displaySuccessMsg();
    }

    public void makeDrone(String storeName, String droneID, Integer weightCapacity, Integer trips) {
        if (stores.containsKey(storeName)) {
            Store store = stores.get(storeName);
            if (!store.doesDroneExist(droneID)) {
                store.addDrone(new Drone(droneID, trips, weightCapacity));
                successMsg();
            } else
                errorMsg(DRONE_ALREADY_EXIST);
        } else
            errorMsg(STORE_DNE);
    }

    public void displayDrones(String storeName) {
        if (stores.containsKey(storeName)) {
            Store store = stores.get(storeName);
            store.displayStoreDrones();
            displaySuccessMsg();
        } else
            errorMsg(STORE_DNE);
    }

    public void flyDrone(String storeName, String droneID, String pilotAccount) {
        if (stores.containsKey(storeName)) {
            Store store = stores.get(storeName);
            if (store.doesDroneExist(droneID)) {
                if (pilots.containsKey(pilotAccount)) {
                    DronePilot dp = pilots.get(pilotAccount);
                    Drone d = store.getDrone(droneID);

                    if (d.getPilot() != null) {
                        DronePilot pilotB = d.getPilot();
                        pilotB.setAssignedDrone(null);
                    }

                    if (dp.getAssignedDrone() != null) {
                        Drone droneB = dp.getAssignedDrone();
                        droneB.setPilot(null);
                    }

                    dp.setAssignedDrone(d);
                    d.setPilot(dp);
                    successMsg();
                } else
                    errorMsg(DRONE_PILOT_DNE);
            } else
                errorMsg(DRONE_DNE);
        } else
            errorMsg(STORE_DNE);
    }

    public void makeCustomer(String account, String fName, String lName, String phoneNumber, Integer rating, Integer credit) {
        if (!customers.containsKey(account)) {
            customers.put(account, new Customer(fName, lName, "", phoneNumber, rating, credit));
            successMsg();
        } else
            errorMsg(CUSTOMER_ALREADY_EXIST);
    }

    public void displayCustomers() {
        for(String key : customers.keySet())
            System.out.println(customers.get(key));
        displaySuccessMsg();
    }

    public void startOrder(String storeName, String orderID, String droneID, String customerAccount) {
        if (stores.containsKey(storeName)) {
            Store store = stores.get(storeName);
            if (!store.doesOrderExist(orderID)) {
                if (store.doesDroneExist(droneID)) {
                    if (customers.containsKey(customerAccount)) {
                        Customer customer = customers.get(customerAccount);
                        Drone drone = store.getDrone(droneID);
                        Order newOrder = new Order(orderID, storeName, customerAccount, droneID);

                        // add order to store, customer, and drone list
                        customer.addOrder(newOrder, storeName);
                        store.addOrder(newOrder);
                        drone.addOrder(newOrder);

                        drone.increaseNumberOfOrders();
                        successMsg();
                    } else
                        errorMsg(CUSTOMER_DNE);
                } else
                    errorMsg(DRONE_DNE);
            } else
                errorMsg(ORDER_ALREADY_EXIST);
        } else
            errorMsg(STORE_DNE);
    }

    public void displayOrders(String storeName) {
        if (stores.containsKey(storeName)) {
            stores.get(storeName).displayStoreOrders();
            displaySuccessMsg();
        }
        else
            errorMsg(STORE_DNE);
    }

    public void requestItem(String storeName, String orderID, String itemName, Integer quantity, Integer unit_price) {
        if (stores.containsKey(storeName)) {
            Store store = stores.get(storeName);
            if (store.doesOrderExist(orderID)) {
                if (store.doesItemExist(itemName)) {
                    Order order = store.getOrder(orderID);
                    if (!order.doesItemExistInOrder(itemName)) {
                        Customer customer = customers.get(order.getCustomerID());
                        store.requestItem(orderID,itemName, quantity, unit_price, customer);
                    } else
                        errorMsg(ITEM_ALREADY_ORDER);
                } else
                    errorMsg(ITEM_DNE);
            } else
                errorMsg(ORDER_DNE);
        } else
            errorMsg(STORE_DNE);
    }

    public void purchaseOrder(String storeName, String orderID) {
        if (stores.containsKey(storeName)) {
            Store store = stores.get(storeName);
            if (store.doesOrderExist(orderID)) {
                Customer customer = customers.get(store.getOrder(orderID).getCustomerID());
                DronePilot dp = store.getDrone(store.getOrder(orderID).getDroneID()).getPilot();
                store.purchaseComplete(orderID, customer, dp);
            } else
                errorMsg(ORDER_DNE);
        } else
            errorMsg(STORE_DNE);
    }

    public void cancelOrder(String storeName, String orderID) {
        if (stores.containsKey(storeName)) {
            Store store = stores.get(storeName);
            if (store.doesOrderExist(orderID)) {
                Order order = store.getOrder(orderID);
                Drone drone = store.getDrone(order.getDroneID());

                drone.increaseRemainingCapacity(order.getTotalWeight());
                drone.decreaseNumberOfOrders();
                customers.get(order.getCustomerID()).increaseRemainingCreditLimit(order.getTotalCost());

                store.removeOrder(order);
                store.getDrone(order.getDroneID()).removeOrder(orderID);
                customers.get(order.getCustomerID()).removeOrder(orderID);
                successMsg();
            } else
                errorMsg(ORDER_DNE);
        } else
            errorMsg(STORE_DNE);
    }

    public void transferOrder(String storeName, String orderID, String newDroneID) {
        if (stores.containsKey(storeName)) {
            Store store = stores.get(storeName);
            if (store.doesOrderExist(orderID)) {
                if (store.doesDroneExist(newDroneID)) {
                    Order order = store.getOrder(orderID);
                    Drone newDrone = store.getDrone(newDroneID);
                    Drone oldDrone = store.getDrone(order.getDroneID());

                    if (newDrone.getDroneID() == oldDrone.getDroneID()) {
                        System.out.println("OK:new_drone_is_current_drone_no_change");
                        return;
                    }

                    if (order.getTotalWeight() > newDrone.getRemainingCapacity()) {
                        oldDrone.removeOrder(orderID);
                        oldDrone.increaseRemainingCapacity(order.getTotalWeight());

                        newDrone.addOrder(order);
                        newDrone.decreaseRemainingCapacity(order.getTotalWeight());

                        order.setDroneID(newDroneID);
                        store.increaseTransferOrder();
                        System.out.println("OK:new_drone_is_current_drone_no_change");
                    } else
                        errorMsg(NEW_DRONE_WEIGHT_CAPACITY);
                } else
                    errorMsg(DRONE_DNE);
            } else
                errorMsg(ORDER_DNE);
        } else
            errorMsg(STORE_DNE);
    }

    public void displayEfficiency() {
        for (String key : stores.keySet())
            stores.get(key).displayEfficiency();
        displaySuccessMsg();
    }
}
