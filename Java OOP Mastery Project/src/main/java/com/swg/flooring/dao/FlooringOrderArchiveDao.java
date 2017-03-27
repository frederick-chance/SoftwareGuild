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
public interface FlooringOrderArchiveDao {

    public Order addOrder(Order order) throws FlooringPersistenceException;

    public Order subOrder(Order order) throws FlooringPersistenceException;

    public Order editOrder(Order oldOrder, Order newOrder) throws FlooringPersistenceException;

    public Order getOrder(LocalDate date, String id) throws FlooringExistenceException;

    public Map<String, Order> getAllOrdersOfDate(LocalDate date) throws FlooringExistenceException;

    public FlooringOrderArchiveWriter getWriter();
    
    public FlooringOrderArchiveReader getReader();
    
    public void setWriter(FlooringOrderArchiveWriter writer);
    
    public void setReader(FlooringOrderArchiveReader reader);
    
}
