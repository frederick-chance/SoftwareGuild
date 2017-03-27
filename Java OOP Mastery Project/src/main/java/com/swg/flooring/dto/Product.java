/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class Product {

    String type;
    BigDecimal costSqFt;
    BigDecimal laborSqFt;

    public Product(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getCostSqFt() {
        return costSqFt;
    }

    public void setCostSqFt(BigDecimal costSqFt) {
        this.costSqFt = costSqFt;
    }

    public BigDecimal getLaborSqFt() {
        return laborSqFt;
    }

    public void setLaborSqFt(BigDecimal laborSqFt) {
        this.laborSqFt = laborSqFt;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.type);
        hash = 53 * hash + Objects.hashCode(this.costSqFt);
        hash = 53 * hash + Objects.hashCode(this.laborSqFt);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.costSqFt, other.costSqFt)) {
            return false;
        }
        if (!Objects.equals(this.laborSqFt, other.laborSqFt)) {
            return false;
        }
        return true;
    }

}
