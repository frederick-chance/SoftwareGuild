/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.SuperBeingSighting;

/**
 *
 * @author apprentice
 */
public interface SuperBeingSightingDao {
    
    public void addSuperBeingSightingAssoc(SuperBeingSighting sbs);
    
    public void removeSuperBeingSightingAssoc(int superbeingID, int sightingID);
    
}
