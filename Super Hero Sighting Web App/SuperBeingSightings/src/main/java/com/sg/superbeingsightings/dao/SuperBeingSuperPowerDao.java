/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.SuperBeingSuperPower;

/**
 *
 * @author apprentice
 */
public interface SuperBeingSuperPowerDao {
    
    public void addSuperBeingSuperPowerAssoc(SuperBeingSuperPower sbsp);
    
    public void removeSuperBeingSuperPowerAssoc(int superbeingID, int superpowerID);
  
}
