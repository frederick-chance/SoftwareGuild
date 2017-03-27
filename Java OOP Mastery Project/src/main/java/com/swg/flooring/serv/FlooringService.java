/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.serv;

import com.swg.flooring.dao.FlooringExistenceException;
import com.swg.flooring.dao.FlooringOrderArchiveWriter;
import com.swg.flooring.dao.FlooringPersistenceException;
import com.swg.flooring.dto.Order;
import com.swg.flooring.dto.Product;
import com.swg.flooring.dto.State;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface FlooringService {
    
    public Map<String, Product> getProducts() throws FlooringPersistenceException; 

    public Map<String, State> getStates() throws FlooringPersistenceException;
    
    public Order addOrder(Order order) throws FlooringPersistenceException;
    
    public Order updateOrder(Order outdated, Order updated) throws FlooringPersistenceException;
    
    public Order removeOrder(Order order) throws FlooringPersistenceException;
    
    public Map<String, Order> getOrdersOfDate(LocalDate date) throws FlooringExistenceException;
    
    public void configurePersistence(FlooringOrderArchiveWriter writer);
            
}
