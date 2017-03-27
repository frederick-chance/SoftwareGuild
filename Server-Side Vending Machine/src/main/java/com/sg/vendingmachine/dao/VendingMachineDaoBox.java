/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author frederick
 */
public interface VendingMachineDaoBox {

    void setChangeReturn(List<BigDecimal> coins);
    
    List<BigDecimal> getChangeReturn();
    
    void emptyChangeReturn();

    void depositCoin(BigDecimal coin) throws VendingMachinePersistenceException;

    BigDecimal removeCoin(int index) throws VendingMachinePersistenceException;

    List<BigDecimal> getDepository() throws VendingMachinePersistenceException;

    public void emptyDepository() throws VendingMachinePersistenceException;
}
