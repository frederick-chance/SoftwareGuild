/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.model;

import java.math.BigDecimal;

/**
 *
 * @author frederick
 */
public class Item {
    private String barcode;
    private String name;
    private int quantity;
    private BigDecimal value;
    
    public Item(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "Item: " + barcode + " | Name: " + name + " | Qty: " + quantity + " | Price: " + value;
    }
    
}
