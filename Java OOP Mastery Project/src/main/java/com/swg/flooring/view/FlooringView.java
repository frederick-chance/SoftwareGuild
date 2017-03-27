/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.view;

import com.swg.flooring.dao.FlooringOrderArchiveTrainingImpl;
import com.swg.flooring.dao.FlooringOrderArchiveWriter;
import com.swg.flooring.dao.FlooringOrderArchiveWriterImpl;
import com.swg.flooring.dto.Order;
import com.swg.flooring.dto.Product;
import com.swg.flooring.dto.State;
import static com.swg.flooring.view.MenuOption.ADD_ORDER;
import static com.swg.flooring.view.MenuOption.CONFIG_APP;
import static com.swg.flooring.view.MenuOption.DISPLAY_ORDERS;
import static com.swg.flooring.view.MenuOption.EXIT_APP;
import static com.swg.flooring.view.MenuOption.REMOVE_ORDER;
import static com.swg.flooring.view.MenuOption.UPDATE_ORDER;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class FlooringView {

    private UserIO io;
    private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public void displayMenuOptions() {
        io.println("___________________________");
        io.println("");
        io.println("SWG Corp. Flooring Database");
        io.println("___________________________");
        io.println("");
        io.println("\t[D]isplay Orders");
        io.println("");
        io.println("\t[A]dd Order");
        io.println("");
        io.println("\t[U]pdate Order");
        io.println("");
        io.println("\t[R]emove Order");
        io.println("");
        io.println("\t[C]onfigure");
        io.println("");
        io.println("\t[E]xit Program");
        io.println("");
    }

    public MenuOption promptMenuOption() {
        io.println("___________________________");
        io.println("");
        String option = io.readString("Enter Selection: ").toUpperCase();
        switch (option) {
            case "D":
            case "DISPLAY":
            case "DISPLAY ORDERS":
                return DISPLAY_ORDERS;
            case "A":
            case "ADD":
            case "ADD ORDER":
                return ADD_ORDER;
            case "U":
            case "UPDATE":
            case "UPDATE ORDER":
                return UPDATE_ORDER;
            case "R":
            case "REMOVE":
            case "REMOVE ORDER":
                return REMOVE_ORDER;
            case "C":
            case "CONFIGURE":
                return CONFIG_APP;
            case "E":
            case "EXIT":
            case "EXIT APP":
            case "EXIT PROGRAM":
                return EXIT_APP;
            default:
                io.println("Unknown Command.");
        }
        return DISPLAY_ORDERS;
    }

    public LocalDate getDisplayOrdersDate() {
        io.println("");
        return io.readDate("Enter Date of Orders: ");
    }

    public void displayOrdersOfDate(Map<String, Order> orders) {
        ArrayList<Order> listOrders = new ArrayList(orders.values());
        io.println("");
        for (int i = 0; i < listOrders.size(); i++) {
            io.println("==============================================================");
            displayCompleteOrder(listOrders.get(i));
        }
    }

    public void displayNoOrdersToDisplay() {
        io.println("No Orders to Display for Date Entered.");
    }

    public boolean getContinueDisplayOrders() {
        return io.readYesNo("Would you like to enter another date to search [Y]es or [N]o: ");
    }

    public Order createNewOrder(
            Map<String, State> stateMap,
            Map<String, Product> productMap) {

        List<String> states = new ArrayList<>(stateMap.keySet());
        List<String> products = new ArrayList<>(productMap.keySet());

        HashMap<String, String> info = new HashMap();
        info.put("date", "");
        info.put("name", "");
        info.put("state", "");
        info.put("type", "");
        info.put("area", "");

        displayNewOrder(info);
        LocalDate date = io.readDate("Enter Order Date: ");
        info.put("date", date.format(FORMAT));

        displayNewOrder(info);
        String name = io.readString("Enter Customer Name: ");
        info.put("name", name);

        displayNewOrder(info);
        String state = getSelection("Select Customer State: ", states);
        info.put("state", state);

        displayNewOrder(info);
        String type = getSelection("Select Flooring Type: ", products);
        info.put("type", type);

        displayNewOrder(info);
        BigDecimal area = io.readBigDecimal("Enter Area Square Footage: ").setScale(2, RoundingMode.HALF_UP);
        info.put("area", area.toString());

        Order newOrder = new Order("0");
        newOrder.setDate(date);
        newOrder.setName(name);
        newOrder.setState(stateMap.get(state));
        newOrder.setProduct(productMap.get(type));
        newOrder.setArea(area);

        displayCompleteNewOrder(newOrder);

        boolean isCommit = io.readYesNo("Would you like to commit this order, [Y]es or [N]o: ");

        if (isCommit) {
            return newOrder;
        } else {
            return null;
        }
    }

    public void displayCompleteOrder(Order order) {
        io.println("");
        io.println("Order Number: " + order.getId());
        io.println("Date of Order: " + order.getDate().format(FORMAT));
        io.println("Customer's Name: " + order.getName());
        io.println("State: " + order.getStateName());
        io.println("Product Selected: " + order.getFloorType());
        io.println("Area in SqFt: " + order.getArea().toString());
        io.println("");
        io.print("Material Cost Per Sq Ft: $" + order.getCostSqFt().toString() + "\t\t");
        io.println("Labor Cost Per Sq Ft: $" + order.getLaborSqFt().toString());
        io.print("Total Material Cost: $" + order.getCostMaterials().toString() + "\t\t");
        io.println("Total Labor Cost: $" + order.getCostLabor().toString());
        io.print("State Tax: " + order.getStateTax().toString() + "%\t\t\t");
        io.println("Total Tax: $" + order.getOrderTax().toString());
        io.println("");
        io.println("Order Total: $" + order.getOrderTotal());
        io.println("");
    }

    public void displayCompleteNewOrder(Order order) {
        io.println("");
        io.println("Date of Order: " + order.getDate().format(FORMAT));
        io.println("Customer's Name: " + order.getName());
        io.println("State: " + order.getStateName());
        io.println("Product Selected: " + order.getFloorType());
        io.println("Area in SqFt: " + order.getArea().toString());
        io.println("");
        io.print("Material Cost Per Sq Ft: $" + order.getCostSqFt().toString() + "\t\t");
        io.println("Labor Cost Per Sq Ft: $" + order.getLaborSqFt().toString());
        io.print("Total Material Cost: $" + order.getCostMaterials().toString() + "\t\t");
        io.println("Total Labor Cost: $" + order.getCostLabor().toString());
        io.print("State Tax: " + order.getStateTax().toString() + "%\t\t\t");
        io.println("Total Tax: $" + order.getOrderTax().toString());
        io.println("");
        io.println("Order Total: $" + order.getOrderTotal());
        io.println("");
    }

    public void displayNewOrder(HashMap<String, String> info) {
        io.println("");
        io.println("Date of Order: " + info.get("date"));
        io.println("Customer's Name: " + info.get("name"));
        io.println("State: " + info.get("state"));
        io.println("Product Selected: " + info.get("type"));
        io.println("Area in SqFt: " + info.get("area"));
        io.println("");
    }

    public String getSelection(String prompt, List<String> options) {
        for (int i = 0; i < options.size(); i++) {
            io.println(i + 1 + ": " + options.get(i));
        }
        int selection = io.readInt(prompt, 1, options.size());
        return options.get(selection - 1);
    }

    public void notifyOrderCancellation() {
        io.println("Order Canceled: The order was not committed.");
    }

    public void notifyNewOrderComplete() {
        io.println("Order Committed: The order was successfully committed.");
    }

    public boolean getContinueAddOrder() {
        return io.readYesNo("Would you like to add a new order, [Y]es or [N]o: ");
    }

    public LocalDate getDateOfOrderToUpdate() {
        io.println("");
        return io.readDate("Enter Date of Order to Update: ");
    }

    public Order getOrderToUpdate(Map<String, Order> orders) {
        ArrayList<Order> listOrders = new ArrayList(orders.values());
        io.println("");
        io.println("\t\tID\t\tSt\t\tCustomer Name");
        io.println("==============================================================");
        io.println("");
        for (int i = 0; i < listOrders.size(); i++) {
            displaySummaryOrder(listOrders.get(i));
        }
io.println("");
        int selection = io.readInt("Enter Order Number to update Order: ");
        String key = Integer.toString(selection);

        if (orders.containsKey(key)) {
            Order outdatedOrder = orders.get(key);
            displayCompleteOrder(outdatedOrder);
            boolean choice = io.readYesNo("Would you like to update order listed above, [Y]es or [N]o: ");

            if (choice) {
                return outdatedOrder;
            }
        }

        return null;
    }

    public Order getUpdatedOrderInfo(
            Order outdated,
            Map<String, State> stateMap,
            Map<String, Product> productMap) {

        boolean isValid = false;
        List<String> states = new ArrayList<>(stateMap.keySet());
        List<String> products = new ArrayList<>(productMap.keySet());

        HashMap<String, String> info = new HashMap();
        info.put("date", outdated.getDate().format(FORMAT));
        info.put("name", outdated.getName());
        info.put("state", outdated.getStateName());
        info.put("type", outdated.getFloorType());
        info.put("area", outdated.getArea().toString());

        do {

            displayCompleteOrder(outdated);
            displayNewOrder(info);
            String dateStr = io.readAnyKey("Enter Order Date (" + info.get("date") + "): ");

            if (dateStr.isEmpty()) {
                isValid = true;
            } else {
                try {
                    LocalDate date = LocalDate.parse(dateStr, FORMAT);
                    info.put("date", date.format(FORMAT));
                    isValid = true;
                } catch (DateTimeParseException e) {
                    io.println("Please enter date as MM/DD/YYYY.");
                    isValid = false;
                }
            }

        } while (!isValid);

        do {

            displayCompleteOrder(outdated);
            displayNewOrder(info);
            String nameStr = io.readAnyKey("Enter Customer Name (" + info.get("name") + "): ");

            if (nameStr.isEmpty()) {
                isValid = true;
            } else {
                info.put("name", nameStr);
                isValid = true;
            }

        } while (!isValid);

        displayCompleteOrder(outdated);
        displayNewOrder(info);
        String state = getSelection("Select Customer State (" + info.get("state") + "): ", states);
        info.put("state", state);

        displayCompleteOrder(outdated);
        displayNewOrder(info);
        String type = getSelection("Select Flooring Type (" + info.get("type") + "): ", products);
        info.put("type", type);

        do {

            displayCompleteOrder(outdated);
            displayNewOrder(info);
            String areaStr = io.readAnyKey("Enter Area Square Footage (" + info.get("area") + "): ");

            if (areaStr.isEmpty()) {
                isValid = true;
            } else {
                try {
                    BigDecimal area = new BigDecimal(areaStr).setScale(2, RoundingMode.HALF_UP);
                    info.put("area", areaStr);
                    isValid = true;
                } catch (NumberFormatException e) {
                    io.println("Please enter a number or press [Enter].");
                    isValid = false;
                }
            }

        } while (!isValid);

        Order updated = new Order(outdated.getId());
        updated.setDate(LocalDate.parse(info.get("date"), FORMAT));
        updated.setName(info.get("name"));
        updated.setState(stateMap.get(info.get("state")));
        updated.setProduct(productMap.get(info.get("type")));
        updated.setArea(new BigDecimal(info.get("area")).setScale(2, RoundingMode.HALF_UP));

        displayCompleteOrder(outdated);
        displayCompleteOrder(updated);

        boolean isUpdate = io.readYesNo("Would you like to update this order, [Y]es or [N]o: ");

        if (isUpdate) {
            return updated;
        } else {
            return null;
        }
    }

    public void notifyNoOrderUpdated() {
        io.println("Order Update Cancelled: No order was updated.");
    }

    public void notifyNoChangesMadeToOrder() {
        io.println("No changes were made to order.");
    }

    public void notifyOrderUpdateComplete() {
        io.println("Order Update: The order was successfully updated.");
    }

    public boolean getContinueUpdateOrder() {
        return io.readYesNo("Would you like to update another order, [Y]es or [N]o: ");
    }

    public LocalDate getDateOfOrderToRemove() {
        io.println("");
        return io.readDate("Enter Date of Order to Remove: ");
    }

    public Order getOrderToRemove(Map<String, Order> orders) {
        ArrayList<Order> listOrders = new ArrayList(orders.values());
        io.println("");
        io.println("\t\tID\t\tSt\t\tCustomer Name");
        io.println("==============================================================");
        io.println("");
        for (int i = 0; i < listOrders.size(); i++) {
            displaySummaryOrder(listOrders.get(i));
        }
        
        io.println("");
        int selection = io.readInt("Enter Order Number to remove Order: ");
        String key = Integer.toString(selection);

        if (orders.containsKey(key)) {
            Order eraseOrder = orders.get(key);
            displayCompleteOrder(eraseOrder);
            boolean choice = io.readYesNo(
                    "Would you like to remove order listed above, [Y]es or [N]o: ");

            if (choice) {
                return eraseOrder;
            }
        }

        return null;
    }

    public void notifyNoOrderRemoved() {
        io.println("Order Removal Cancelled: No order was remove.");
    }

    public void notifyOrderRemovalComplete() {
        io.println("Order Removed: The order was successfully removed.");
    }

    public boolean getContinueRemoveOrder() {
        return io.readYesNo("Would you like to remove another order, [Y]es or [N]o: ");
    }

    public void displaySummaryOrder(Order order) {
        io.println("\t\t" + order.getId()
                + "\t\t" + order.getStateName()
                + "\t\t" + order.getName()
        );
    }

    public void continueMainMenu() {
        io.readAnyKey("Press Any Key to Continue.");
        io.println("");
    }

    public FlooringOrderArchiveWriter getPersistenceMode() {
        io.println("");        
        io.println("[P]roduction Mode: data WILL persist to files.");
        io.println("[T]raining Mode: data WILL NOT persist to files.");
        io.println("");
        String selection = io.readString("Enter Selection: ").toUpperCase();

        switch (selection) {
            case "P":
            case "PROD":
            case "PRODUCTION":
            case "PRODUCTION MODE":
                return new FlooringOrderArchiveWriterImpl();
            case "T":
            case "TRAIN":
            case "TRAINING":
            case "TRAINING MODE":
                return new FlooringOrderArchiveTrainingImpl();
            default:
                io.println("Command unknown. Default setting - Training Mode - selected.");
        }

        return new FlooringOrderArchiveTrainingImpl();
    }

    public void notifyExceptionError(String error) {
        io.print(error);
        io.readAnyKey("\nPress [Enter] to continue.\n");
    }

    public void notifyPersistenceConfigurationComplete(
            FlooringOrderArchiveWriter writer) {

        io.println("Persistence Configuration Complete: "
                + writer.getClass().getSimpleName()
                + " is active.");
    }

}
