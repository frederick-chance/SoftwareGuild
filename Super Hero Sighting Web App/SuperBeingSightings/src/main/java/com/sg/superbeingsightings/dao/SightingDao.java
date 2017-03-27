/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.Sighting;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface SightingDao {
    
    public void addSighting(Sighting sighting);
    
    public void removeSighting(int sightingID);
    
    public void updateSighting(Sighting sighting);
    
    public Sighting getSighting(int sightingID);
    
    public List<Sighting> getAllSighting();
    
    public List<Sighting> getAllSightingBySuperBeing(int superbeingID);
    
    public List<Sighting> getAllSightingByLocation(int locationID);
}
