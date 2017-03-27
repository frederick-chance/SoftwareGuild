/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.SuperPower;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface SuperPowerDao {
    
    public void addSuperPower(SuperPower superpower);
    
    public void removeSuperPower(int superpowerID);
    
    public void updateSuperPower(SuperPower superpower);
    
    public SuperPower getSuperPower(int superpowerID);
    
    public List<SuperPower> getAllSuperPower();
    
    public List<SuperPower> getAllSuperPowerBySuperBeing(int superbeingID);
    
}
