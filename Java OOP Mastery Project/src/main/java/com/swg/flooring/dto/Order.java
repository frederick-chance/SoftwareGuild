/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class Order {

    String id;
    
    String name;
    State state;
    Product product;
    BigDecimal area;
    
    LocalDate date;

    public Order(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    // Derived from Order Properties
    public String getStateName() {
        return state.getName();
    }

    public BigDecimal getStateTax() {
        return state.getRate().setScale(2, RoundingMode.HALF_UP);
    }

    public String getFloorType() {
        return product.getType();
    }

    public BigDecimal getCostSqFt() {
        return product.getCostSqFt().setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getLaborSqFt() {
        return product.getLaborSqFt().setScale(2, RoundingMode.HALF_UP);
    }

    // Calculated from Order Properties
    public BigDecimal getCostMaterials() {
        return product.costSqFt.multiply(area).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getCostLabor() {
        return product.laborSqFt.multiply(area).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getOrderSubTotal() {
        return getCostMaterials().add(getCostLabor()).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getOrderTax() {
        return getStateTax().divide(new BigDecimal("100.00")).multiply(getOrderSubTotal()).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getOrderTotal() {
        return getOrderSubTotal().add(getOrderTax()).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.date);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.state);
        hash = 67 * hash + Objects.hashCode(this.product);
        hash = 67 * hash + Objects.hashCode(this.area);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        return true;
    }

}
