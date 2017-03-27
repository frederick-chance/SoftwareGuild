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
public class SuperBeingAffiliation {
    private int superbeingAffiliationID;
    private SuperBeing superbeing;
    private Affiliation affiliation;

    public int getSuperbeingAffiliationID() {
        return superbeingAffiliationID;
    }

    public void setSuperbeingAffiliationID(int superbeingAffiliationID) {
        this.superbeingAffiliationID = superbeingAffiliationID;
    }

    public SuperBeing getSuperbeing() {
        return superbeing;
    }

    public void setSuperbeing(SuperBeing superbeing) {
        this.superbeing = superbeing;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.superbeingAffiliationID;
        hash = 97 * hash + Objects.hashCode(this.superbeing);
        hash = 97 * hash + Objects.hashCode(this.affiliation);
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
        final SuperBeingAffiliation other = (SuperBeingAffiliation) obj;
        if (this.superbeingAffiliationID != other.superbeingAffiliationID) {
            return false;
        }
        if (!Objects.equals(this.superbeing, other.superbeing)) {
            return false;
        }
        if (!Objects.equals(this.affiliation, other.affiliation)) {
            return false;
        }
        return true;
    }
    
}
