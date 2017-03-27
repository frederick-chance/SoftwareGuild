/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.dao;

import com.swg.flooring.dto.State;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface FlooringStateRegistryDao {
    
    public State getState(String type) throws FlooringPersistenceException;

    public Map<String, State> getAllStates() throws FlooringPersistenceException;
    
}
