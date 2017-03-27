/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.serv;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author frederick
 */
public interface VendingMachineServ {
    
    void setMessage(Item item);
    
    void setMessage(String messsage);
    
    String getMessage();
    
    void setSelection(Item item);
    
    void setSelection(String selection);
    
    String getSelection();
    
    
    
    void returnChange() throws VendingMachinePersistenceException;
    
    String getChange();
        
    void selectItem(String barcode) throws VendingMachinePersistenceException;
    
    void depositFunds(String amount) throws VendingMachinePersistenceException;
    
    BigDecimal getDeposit() throws VendingMachinePersistenceException;
    
    String[] getCoins() throws VendingMachinePersistenceException;
    
    List<Item> getInventory() throws VendingMachinePersistenceException;
    
    Item getItem(String barcode) throws VendingMachinePersistenceException;

    void vendItem() throws VendingMachinePersistenceException;
    
    boolean isInStock(Item item) throws VendingMachineNoItemInventoryException;
    
    boolean isFundsSufficient(Item item) throws 
            VendingMachineInsufficientFundsException,
            VendingMachinePersistenceException;
}
