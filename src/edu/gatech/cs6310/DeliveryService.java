package edu.gatech.cs6310;
import java.util.Scanner;

public class DeliveryService {
    DeliveryHelper dh = new DeliveryHelper();

    public void commandLoop() {
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";

        while (true) {
            try {
                // Determine the next command and echo it to the monitor for testing purposes
                wholeInputLine = commandLineInput.nextLine();
                tokens = wholeInputLine.split(DELIMITER);
                System.out.println("> " + wholeInputLine);

                if (wholeInputLine.startsWith("//")) continue;

                if (tokens[0].equals("make_store")) {
                    // System.out.println("store: " + tokens[1] + ", revenue: " + tokens[2]);
                    dh.makeStore(tokens[1], Integer.parseInt(tokens[2]));
                } else if (tokens[0].equals("display_stores")) {
                    // System.out.println("no parameters needed");
                    dh.displayStores();
                } else if (tokens[0].equals("sell_item")) {
                    // System.out.println("store: " + tokens[1] + ", item: " + tokens[2] + ", weight: " + tokens[3]);
                    dh.sellItem(tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                } else if (tokens[0].equals("display_items")) {
                    // System.out.println("store: " + tokens[1]);
                    dh.displayItems(tokens[1]);
                } else if (tokens[0].equals("make_pilot")) {
                    // System.out.print("account: " + tokens[1] + ", first_name: " + tokens[2] + ", last_name: " + tokens[3]);
                    // System.out.println(", phone: " + tokens[4] + ", tax: " + tokens[5] + ", license: " + tokens[6] + ", experience: " + tokens[7]);
                    dh.makePilot(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], Integer.parseInt(tokens[7]));
                } else if (tokens[0].equals("display_pilots")) {
                    // System.out.println("no parameters needed");
                    dh.displayPilots();
                } else if (tokens[0].equals("make_drone")) {
                    // System.out.println("store: " + tokens[1] + ", drone: " + tokens[2] + ", capacity: " + tokens[3] + ", fuel: " + tokens[4]);
                    dh.makeDrone(tokens[1], tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
                } else if (tokens[0].equals("display_drones")) {
                    // System.out.println("store: " + tokens[1]);
                    dh.displayDrones(tokens[1]);
                } else if (tokens[0].equals("fly_drone")) {
                    // System.out.println("store: " + tokens[1] + ", drone: " + tokens[2] + ", pilot: " + tokens[3]);
                    dh.flyDrone(tokens[1], tokens[2], tokens[3]);
                } else if (tokens[0].equals("make_customer")) {
                    // System.out.print("account: " + tokens[1] + ", first_name: " + tokens[2] + ", last_name: " + tokens[3]);
                    // System.out.println(", phone: " + tokens[4] + ", rating: " + tokens[5] + ", credit: " + tokens[6]);
                    dh.makeCustomer(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
                } else if (tokens[0].equals("display_customers")) {
                    // System.out.println("no parameters needed");
                    dh.displayCustomers();
                } else if (tokens[0].equals("start_order")) {
                    // System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", drone: " + tokens[3] + ", customer: " + tokens[4]);
                    dh.startOrder(tokens[1], tokens[2], tokens[3], tokens[4]);
                } else if (tokens[0].equals("display_orders")) {
                    // System.out.println("store: " + tokens[1]);
                    dh.displayOrders(tokens[1]);
                } else if (tokens[0].equals("request_item")) {
                    // System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", item: " + tokens[3] + ", quantity: " + tokens[4] + ", unit_price: " + tokens[5]);
                    dh.requestItem(tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
                } else if (tokens[0].equals("purchase_order")) {
                    // System.out.println("store: " + tokens[1] + ", order: " + tokens[2]);
                    dh.purchaseOrder(tokens[1], tokens[2]);
                } else if (tokens[0].equals("cancel_order")) {
                    // System.out.println("store: " + tokens[1] + ", order: " + tokens[2]);
                    dh.cancelOrder(tokens[1], tokens[2]);
                } else if (tokens[0].equals("transfer_order")) {
                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", new_drone: " + tokens[3]);
                    dh.transferOrder(tokens[1], tokens[2], tokens[3]);
                } else if (tokens[0].equals("display_efficiency")) {
                    // System.out.println("no parameters needed");
                    dh.displayEfficiency();
                } else if (tokens[0].equals("stop")) {
                    System.out.println("stop acknowledged");
                    break;
                } else {
                    System.out.println("command " + tokens[0] + " NOT acknowledged");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        System.out.println("simulation terminated");
        commandLineInput.close();
    }
}
