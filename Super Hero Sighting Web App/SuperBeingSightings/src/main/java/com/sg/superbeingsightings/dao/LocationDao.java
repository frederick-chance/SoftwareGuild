/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.Location;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface LocationDao {
    
    public void addLocation(Location location);
    
    public void removeLocation(int locationID);
    
    public void updateLocation(Location location);
    
    public Location getLocation(int locationID);
    
    public List<Location> getAllLocation();
    
}
