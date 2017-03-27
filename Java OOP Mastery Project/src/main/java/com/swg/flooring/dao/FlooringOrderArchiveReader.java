/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.dao;

import com.swg.flooring.dto.Order;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface FlooringOrderArchiveReader {
    
    public Map<String, Order> getAllOrdersOfDate(LocalDate date) throws FlooringExistenceException;
    
    public Order getOrder(Map<String, Order> orders, String id) throws FlooringExistenceException;
    
}
