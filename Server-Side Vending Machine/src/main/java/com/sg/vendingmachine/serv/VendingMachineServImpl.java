/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.serv;

import com.sg.vendingmachine.dao.VendingMachineDaoBox;
import com.sg.vendingmachine.dao.VendingMachineDaoPanel;
import com.sg.vendingmachine.dao.VendingMachineDaoStock;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.model.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author frederick
 */
public class VendingMachineServImpl implements VendingMachineServ {

    private VendingMachineDaoBox box;
    private VendingMachineDaoStock stock;
    private VendingMachineDaoPanel panel;

    final int DOLLAR = 1;
    final int QUARTER = 2;
    final int DIME = 3;
    final int NICKEL = 4;
    final int NOTHING = 5;

    @Inject
    public VendingMachineServImpl(VendingMachineDaoBox box, VendingMachineDaoStock stock, VendingMachineDaoPanel panel) {
        this.box = box;
        this.stock = stock;
        this.panel = panel;
    }

    @Override
    public void selectItem(String barcode) throws VendingMachinePersistenceException {
        Item item = getItem(barcode);
        setMessage(item);
        setSelection(item);
    }

    @Override
    public void depositFunds(String amount) throws VendingMachinePersistenceException {
        BigDecimal coin = new BigDecimal(amount);
        box.depositCoin(coin);
        box.emptyChangeReturn();
    }

    @Override
    public void returnChange() throws VendingMachinePersistenceException {
        box.setChangeReturn(box.getDepository());
        box.emptyDepository();
    }

    @Override
    public String getChange() {
        List<BigDecimal> totalChange = box.getChangeReturn();
        Map<String, Integer> changeReturned = new HashMap<>();
        changeReturned.put("Quarter", 0);
        changeReturned.put("Dime", 0);
        changeReturned.put("Nickel", 0);
        changeReturned.put("Penny", 0);

        for (BigDecimal coin : totalChange) {
            String key = getDenomination(coin);
            int count = getCount(coin);
            count = changeReturned.get(key) + count;
            changeReturned.put(key, count);
        }
        
        String change = "";
        change += countChange("Quarter", changeReturned.get("Quarter"));
        change += countChange("Dime", changeReturned.get("Dime"));
        change += countChange("Nickel", changeReturned.get("Nickel"));
        change += countChange("Penny", changeReturned.get("Penny"));

        return change;
        
    }
    
    private String countChange(String coin, int count) {
        if (count == 1) {
            return count + " " + coin + " ";
        } else if (count > 1) {
            return count + " " + coin + "s ";
        } else {
            return "";
        }
    }

    private String getDenomination(BigDecimal coin) {
        switch (coin.toString()) {
            case "1.00":
                return "Quarter";
            case "0.25":
                return "Quarter";
            case "0.10":
                return "Dime";
            case "0.05":
                return "Penny";
        }
        return null;
    }

    private int getCount(BigDecimal coin) {
        if (coin.toString().equalsIgnoreCase("Dollar")) {
            return 4;
        }
        return 1;
    }

    @Override
    public BigDecimal getDeposit() throws VendingMachinePersistenceException {
        List<BigDecimal> totalDeposited = box.getDepository();
        BigDecimal totalAmountDeposited = new BigDecimal("0");

        for (BigDecimal coin : totalDeposited) {
            totalAmountDeposited = totalAmountDeposited.add(coin);
        }

        return totalAmountDeposited.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String[] getCoins() throws VendingMachinePersistenceException {
        int coinsReturned = box.getDepository().size();
        BigDecimal total = new BigDecimal("0.00");
        int quarter = 0;
        int dime = 0;
        int nickel = 0;

        for (int i = coinsReturned - 1; i >= 0; i--) {
            BigDecimal coin = box.removeCoin(i);

            switch (coin.toString()) {
                case "1.00":
                    total = total.add(coin);
                    quarter += 4;
                    break;
                case "0.25":
                    total = total.add(coin);
                    quarter += 1;
                    break;
                case "0.10":
                    total = total.add(coin);
                    dime += 1;
                    break;
                case "0.05":
                    total = total.add(coin);
                    nickel += 1;
                    break;
            }
        }

        String strTotal = total.toString();
        String strQuarter = String.valueOf(quarter);
        String strDime = String.valueOf(dime);
        String strNickel = String.valueOf(nickel);

        String[] change = {strTotal, strQuarter, strDime, strNickel};
        return change;
    }

    @Override
    public void vendItem() throws VendingMachinePersistenceException {
        if (isItemSelected() && isItemStocked() && isFundSufficient()) {
            String barcode = panel.getSelection();
            Item item = stock.getItem(barcode);

            reduceStock(item);
            calculateChange(item);
            returnChange();
            panel.setMessage("Thank You!!");
        }
    }

    private boolean isItemSelected() {
        boolean isEmpty = panel.getSelection().isEmpty();
        boolean isNull = panel.getSelection() == null;
        boolean isSelected = !(isEmpty && isNull);

        if (!isSelected) {
            panel.setMessage("Please Select Item.");
        }
        return isSelected;
    }

    private boolean isItemStocked() throws VendingMachinePersistenceException {
        String barcode = panel.getSelection();
        Item item = stock.getItem(barcode);
        boolean isStocked = item.getQuantity() > 0;

        if (!isStocked) {
            panel.setMessage("Sold Out!!!");
        }
        return isStocked;
    }

    private boolean isFundSufficient() throws VendingMachinePersistenceException {
        String barcode = panel.getSelection();
        Item item = stock.getItem(barcode);
        BigDecimal funds = getDeposit();
        BigDecimal price = item.getValue();
        boolean isSufficient = funds.compareTo(price) > -1;
        String msg = "Please deposit: $" + price.subtract(funds).toString();

        if (!isSufficient) {
            panel.setMessage(msg);
        }
        return isSufficient;
    }

    private void reduceStock(Item item) throws VendingMachinePersistenceException {
        int buy = item.getQuantity() - 1;
        item.setQuantity(buy);
        stock.addItem(item.getBarcode(), item);
    }

    private void calculateChange(Item item) throws VendingMachinePersistenceException {
        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.10");
        BigDecimal nickel = new BigDecimal("0.05");
        BigDecimal penny = new BigDecimal("0.01");

        BigDecimal cash = getDeposit();
        BigDecimal cost = item.getValue();

        BigDecimal change = cash.subtract(cost);
        box.emptyDepository();

        while (change.compareTo(quarter) >= 0) {
            box.depositCoin(quarter);
            change = change.subtract(quarter);
        }

        while (change.compareTo(dime) >= 0) {
            box.depositCoin(dime);
            change = change.subtract(dime);
        }

        while (change.compareTo(nickel) >= 0) {
            box.depositCoin(nickel);
            change = change.subtract(nickel);
        }

        while (change.compareTo(penny) >= 0) {
            box.depositCoin(penny);
            change = change.subtract(penny);
        }

    }

    private void reduceQuantity(Item item) throws
            VendingMachinePersistenceException {

        Item updateItem = item;
        int quantity = updateItem.getQuantity();
        String barcode = updateItem.getBarcode();

        updateItem.setQuantity(quantity - 1);

        stock.addItem(barcode, updateItem);
    }

    @Override
    public List<Item> getInventory() throws VendingMachinePersistenceException {
        return stock.getAllItems();
    }

    @Override
    public boolean isInStock(Item item) throws
            VendingMachineNoItemInventoryException {

        String message = "\n" + item.getName() + ": Not in Stock";

        boolean inStock = item.getQuantity() > 0;

        if (inStock) {
            return true;
        } else {
            throw new VendingMachineNoItemInventoryException(message);
        }
    }

    @Override
    public boolean isFundsSufficient(Item item) throws
            VendingMachinePersistenceException,
            VendingMachineInsufficientFundsException {

        String message = "\nInsufficient Funds";
        BigDecimal cost = item.getValue();
        BigDecimal cash = getDeposit();

        boolean sufficient = cash.compareTo(cost) >= 0;

        if (sufficient) {
            return true;
        } else {
            throw new VendingMachineInsufficientFundsException(message);
        }
    }

    @Override
    public Item getItem(String barcode) throws VendingMachinePersistenceException {
        return stock.getItem(barcode);
    }

    // Panel Setters and Getters
    @Override
    public void setMessage(String message) {
        panel.setMessage(message);
    }

    @Override
    public void setMessage(Item item) {
        panel.setMessage(item.getName());
    }

    @Override
    public String getMessage() {
        return panel.getMessage();
    }

    @Override
    public void setSelection(String selection) {
        panel.setSelection(selection);
    }

    @Override
    public void setSelection(Item item) {
        panel.setSelection(item.getBarcode());
    }

    @Override
    public String getSelection() {
        return panel.getSelection();
    }
}
