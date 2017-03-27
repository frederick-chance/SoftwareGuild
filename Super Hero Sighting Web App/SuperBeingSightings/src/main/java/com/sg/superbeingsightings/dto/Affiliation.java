/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dto;

/**
 *
 * @author apprentice
 */
public class Affiliation {
    private int affiliationID;
    private SuperBeing leader;
    private Address address;
    private String name;
    private String desc;

    public int getAffiliationID() {
        return affiliationID;
    }

    public void setAffiliationID(int affiliationID) {
        this.affiliationID = affiliationID;
    }

    public SuperBeing getLeader() {
        return leader;
    }

    public void setLeader(SuperBeing leader) {
        this.leader = leader;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
