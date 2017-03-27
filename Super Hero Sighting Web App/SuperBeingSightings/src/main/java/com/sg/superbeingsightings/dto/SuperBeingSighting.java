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
public class SuperBeingSighting {
    private int superbeingSightingID;
    private SuperBeing superbeing;
    private Sighting sighting;

    public int getSuperbeingSightingID() {
        return superbeingSightingID;
    }

    public void setSuperbeingSightingID(int superbeingSightingID) {
        this.superbeingSightingID = superbeingSightingID;
    }

    public SuperBeing getSuperbeing() {
        return superbeing;
    }

    public void setSuperbeing(SuperBeing superbeing) {
        this.superbeing = superbeing;
    }

    public Sighting getSighting() {
        return sighting;
    }

    public void setSighting(Sighting sighting) {
        this.sighting = sighting;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.superbeingSightingID;
        hash = 59 * hash + Objects.hashCode(this.superbeing);
        hash = 59 * hash + Objects.hashCode(this.sighting);
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
        final SuperBeingSighting other = (SuperBeingSighting) obj;
        if (this.superbeingSightingID != other.superbeingSightingID) {
            return false;
        }
        if (!Objects.equals(this.superbeing, other.superbeing)) {
            return false;
        }
        if (!Objects.equals(this.sighting, other.sighting)) {
            return false;
        }
        return true;
    }
    
    
}
