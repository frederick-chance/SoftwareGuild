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
public class SuperBeing {
    private int superbeingID;
    private String name;
    private String alias;
    private String desc;

    public int getSuperBeingID() {
        return superbeingID;
    }

    public void setSuperBeingID(int superbeingID) {
        this.superbeingID = superbeingID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.superbeingID;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.alias);
        hash = 47 * hash + Objects.hashCode(this.desc);
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
        final SuperBeing other = (SuperBeing) obj;
        if (this.superbeingID != other.superbeingID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.alias, other.alias)) {
            return false;
        }
        if (!Objects.equals(this.desc, other.desc)) {
            return false;
        }
        return true;
    }
    
}
