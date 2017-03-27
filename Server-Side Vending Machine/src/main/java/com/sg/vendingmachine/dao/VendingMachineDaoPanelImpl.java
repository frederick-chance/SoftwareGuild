/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

/**
 *
 * @author apprentice
 */
public class VendingMachineDaoPanelImpl implements VendingMachineDaoPanel{
    private String message;
    private String selection;

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setSelection(String selection) {
        this.selection = selection;
    }

    @Override
    public String getSelection() {
        return selection;
    }
    
}
