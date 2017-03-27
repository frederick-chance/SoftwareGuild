/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.sg.vendingmachine.model.Item;

/**
 *
 * @author frederick
 */
public class VendingMachineDaoStockImpl implements VendingMachineDaoStock {

    public String inventoryFile = "inventory.txt";
    public static final String DELIMITER = "::";

    private Map<String, Item> inventory = new HashMap<>();
    
    public void setFile(String file) {
        this.inventoryFile = file;
    }

    @Override
    public Item addItem(String barcode, Item item)
            throws VendingMachinePersistenceException {

        Item newItem = inventory.put(barcode, item);

        writeInventory();
        return newItem;
    }

    @Override
    public Item removeItem(String barcode)
            throws VendingMachinePersistenceException {

        Item removedItem = inventory.remove(barcode);

        writeInventory();
        return removedItem;
    }

    @Override
    public Item getItem(String barcode)
            throws VendingMachinePersistenceException {

        readInventory();
        return inventory.get(barcode);
    }

    @Override
    public List<Item> getAllItems()
            throws VendingMachinePersistenceException {

        readInventory();
        return new ArrayList<>(inventory.values());
    }

    private void readInventory() throws VendingMachinePersistenceException {
        String contact = "Please contact customer service at ptoner@thesoftwareguild.com";
        String message = "Read Error: " + contact;
        Scanner read;

        try {
            read = new Scanner(new BufferedReader(new FileReader(inventoryFile)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(message, e);
        }

        String line;
        String[] properties;

        while (read.hasNextLine()) {

            line = read.nextLine();

            properties = line.split(DELIMITER);

            Item item = new Item(properties[2]);

            item.setName(properties[3]);
            item.setQuantity(Integer.parseInt(properties[1]));
            item.setValue(new BigDecimal(properties[0]));
            
            inventory.put(item.getBarcode(), item);
        }
    }

    private void writeInventory() throws VendingMachinePersistenceException {
        List<Item> inventoryLog = new ArrayList(inventory.values());
        String contact = "Please contact customer service at ptoner@thesoftwareguild.com";
        String message = "Write Error: " + contact;
        PrintWriter write;

        try {
            write = new PrintWriter(new FileWriter(inventoryFile));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(message, e);
        }

        for (Item item : inventoryLog) {
            write.println(
                    item.getValue().toString() + DELIMITER
                    + String.valueOf(item.getQuantity()) + DELIMITER
                    + item.getBarcode() + DELIMITER
                    + item.getName());
            write.flush();
        }
        write.close();
    }
}
