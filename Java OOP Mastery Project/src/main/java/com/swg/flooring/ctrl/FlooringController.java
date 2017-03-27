/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.ctrl;

import com.swg.flooring.dao.FlooringExistenceException;
import com.swg.flooring.dao.FlooringOrderArchiveWriter;
import com.swg.flooring.dao.FlooringPersistenceException;
import com.swg.flooring.dto.Order;
import com.swg.flooring.serv.FlooringService;
import com.swg.flooring.view.FlooringView;
import com.swg.flooring.view.MenuOption;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class FlooringController {

    private FlooringView view;
    private FlooringService serv;

    public FlooringController(FlooringView view, FlooringService serv) {
        this.view = view;
        this.serv = serv;
    }

    private MenuOption getMenuOption() {
        view.displayMenuOptions();
        return view.promptMenuOption();
    }

    public void run() {
        boolean isRunning = true;

        try {

            while (isRunning) {
                MenuOption selected = getMenuOption();

                switch (selected) {
                    case DISPLAY_ORDERS:
                        displayOrders();
                        break;
                    case ADD_ORDER:
                        addOrder();
                        break;
                    case UPDATE_ORDER:
                        updateOrder();
                        break;
                    case REMOVE_ORDER:
                        removeOrder();
                        break;
                    case CONFIG_APP:
                        configApp();
                        break;
                    case EXIT_APP:
                        exitApp();
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Unknown Command.");
                }
            }

        } catch (FlooringPersistenceException ex) {
            view.notifyExceptionError(ex.getMessage());
        }
    }

    private void displayOrders() {
        boolean isContinue = false;

        do {

            try {
                LocalDate date = view.getDisplayOrdersDate();
                Map<String, Order> orders = serv.getOrdersOfDate(date);
                view.displayOrdersOfDate(orders);
                isContinue = view.getContinueDisplayOrders();
            } catch (FlooringExistenceException ex) {
                view.notifyExceptionError(ex.getMessage());
                isContinue = view.getContinueDisplayOrders();
            }

        } while (isContinue);
    }

    private void addOrder() throws FlooringPersistenceException {
        boolean isContinue = false;

        do {

            Map states = serv.getStates();
            Map products = serv.getProducts();
            Order newOrder = view.createNewOrder(states, products);

            if (newOrder == null) {
                view.notifyOrderCancellation();
                isContinue = view.getContinueAddOrder();
            } else {
                Order commitOrder = serv.addOrder(newOrder);
                view.displayCompleteOrder(commitOrder);
                view.notifyNewOrderComplete();
                isContinue = view.getContinueAddOrder();
            }

        } while (isContinue);
    }

    private void updateOrder() throws FlooringPersistenceException {
        boolean isContinue = false;

        do {

            try {
                LocalDate date = view.getDateOfOrderToUpdate();
                Map<String, Order> orders = serv.getOrdersOfDate(date);
                Order orderOutdated = view.getOrderToUpdate(orders);

                if (orderOutdated == null) {
                    view.notifyNoOrderUpdated();
                    isContinue = view.getContinueUpdateOrder();
                } else {
                    Map states = serv.getStates();
                    Map products = serv.getProducts();
                    Order orderUpdated = view.getUpdatedOrderInfo(
                            orderOutdated, states, products);

                    if (orderUpdated == null || orderUpdated.equals(orderOutdated) ) {
                        view.notifyNoChangesMadeToOrder();
                        isContinue = view.getContinueUpdateOrder();
                    } else {
                        serv.updateOrder(orderOutdated, orderUpdated);
                        view.notifyOrderUpdateComplete();
                        isContinue = view.getContinueUpdateOrder();
                    }
                }
            } catch (FlooringExistenceException ex) {
                view.notifyExceptionError(ex.getMessage());
                isContinue = view.getContinueUpdateOrder();
            }

        } while (isContinue);
    }

    private void removeOrder() throws FlooringPersistenceException {
        boolean isContinue = false;

        do {

            try {
                LocalDate date = view.getDateOfOrderToRemove();
                Map<String, Order> orders = serv.getOrdersOfDate(date);
                Order eraseOrder = view.getOrderToRemove(orders);

                if (eraseOrder == null) {
                    view.notifyNoOrderRemoved();
                    isContinue = view.getContinueRemoveOrder();
                } else {
                    serv.removeOrder(eraseOrder);
                    view.notifyOrderRemovalComplete();
                    isContinue = view.getContinueRemoveOrder();
                }
            } catch (FlooringExistenceException ex) {
                view.notifyExceptionError(ex.getMessage());
                isContinue = view.getContinueRemoveOrder();
            }

        } while (isContinue);
    }

    private void configApp() {
        FlooringOrderArchiveWriter writer = view.getPersistenceMode();
        serv.configurePersistence(writer);
        view.notifyPersistenceConfigurationComplete(writer);
    }

    private void exitApp() {

    }

}
