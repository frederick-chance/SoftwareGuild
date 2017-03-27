/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.model.Item;
import java.util.List;

/**
 *
 * @author frederick
 */
public interface VendingMachineDaoStock {
    
    Item addItem(String barcode, Item item) throws VendingMachinePersistenceException;

    Item removeItem(String barcode) throws VendingMachinePersistenceException;

    Item getItem(String barcode) throws VendingMachinePersistenceException;

    List<Item> getAllItems() throws VendingMachinePersistenceException;

}
