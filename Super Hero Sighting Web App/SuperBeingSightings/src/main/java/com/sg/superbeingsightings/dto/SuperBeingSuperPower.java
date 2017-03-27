/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dto;

import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class SuperBeingSuperPower {
    private int superbeingSuperPower;
    private SuperBeing superbeing;
    private SuperPower superpower;

    public int getSuperbeingSuperPower() {
        return superbeingSuperPower;
    }

    public void setSuperbeingSuperPower(int superbeingSuperPower) {
        this.superbeingSuperPower = superbeingSuperPower;
    }

    public SuperBeing getSuperbeing() {
        return superbeing;
    }

    public void setSuperbeing(SuperBeing superbeing) {
        this.superbeing = superbeing;
    }

    public SuperPower getSuperpower() {
        return superpower;
    }

    public void setSuperpower(SuperPower superpower) {
        this.superpower = superpower;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.superbeingSuperPower;
        hash = 37 * hash + Objects.hashCode(this.superbeing);
        hash = 37 * hash + Objects.hashCode(this.superpower);
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
        final SuperBeingSuperPower other = (SuperBeingSuperPower) obj;
        if (this.superbeingSuperPower != other.superbeingSuperPower) {
            return false;
        }
        if (!Objects.equals(this.superbeing, other.superbeing)) {
            return false;
        }
        if (!Objects.equals(this.superpower, other.superpower)) {
            return false;
        }
        return true;
    }
    
    
}
