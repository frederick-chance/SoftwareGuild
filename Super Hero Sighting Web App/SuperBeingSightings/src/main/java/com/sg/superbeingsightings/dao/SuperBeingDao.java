/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.SuperBeing;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface SuperBeingDao {
    
    public void addSuperBeing(SuperBeing superbeing);
    
    public void removeSuperBeing(int superbeingID);
    
    public void updateSuperBeing(SuperBeing superbeing);
    
    public SuperBeing getSuperBeing(int superbeingID);
    
    public List<SuperBeing> getAllSuperBeing();
    
    public List<SuperBeing> getAllSuperBeingByAffiliation(int affiliationID);
    
    public List<SuperBeing> getAllSuperBeingBySuperPower(int superpowerID);
    
    public List<SuperBeing> getAllSuperBeingBySighting(int sightingID);
    
}
