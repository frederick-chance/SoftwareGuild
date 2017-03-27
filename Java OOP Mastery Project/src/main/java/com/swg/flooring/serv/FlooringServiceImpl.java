/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.serv;

import com.swg.flooring.dao.FlooringAuditDao;
import com.swg.flooring.dao.FlooringExistenceException;
import com.swg.flooring.dao.FlooringIDGeneratorDao;
import com.swg.flooring.dao.FlooringOrderArchiveConfig;
import com.swg.flooring.dao.FlooringOrderArchiveDao;
import com.swg.flooring.dao.FlooringOrderArchiveWriter;
import com.swg.flooring.dao.FlooringPersistenceException;
import com.swg.flooring.dao.FlooringProductInventoryDao;
import com.swg.flooring.dao.FlooringStateRegistryDao;
import com.swg.flooring.dto.Order;
import com.swg.flooring.dto.Product;
import com.swg.flooring.dto.State;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class FlooringServiceImpl implements FlooringService {
    
    private FlooringOrderArchiveDao archive;
    private FlooringProductInventoryDao inventory;
    private FlooringStateRegistryDao registry;
    private FlooringIDGeneratorDao generator;
    private FlooringOrderArchiveConfig configuration;
    private FlooringAuditDao audit;
    
    public FlooringServiceImpl(
            FlooringOrderArchiveDao archive,
            FlooringProductInventoryDao inventory,
            FlooringStateRegistryDao registry,
            FlooringIDGeneratorDao generator,
            FlooringOrderArchiveConfig configuration,
            FlooringAuditDao audit) {
        
        this.archive = archive;
        this.inventory = inventory;
        this.registry = registry;
        this.generator = generator;
        this.configuration = configuration;
        this.audit = audit;
    }

    @Override
    public Map<String, Product> getProducts() throws FlooringPersistenceException {
        return inventory.getAllProducts();
    }
    
    @Override
    public Map<String, State> getStates() throws FlooringPersistenceException {
        return registry.getAllStates();
    }
    
    @Override
    public Order addOrder(Order order) throws FlooringPersistenceException {
        String orderNumber = generator.generateOrderNumber();
        
        Order newOrder = new Order(orderNumber);
        
        newOrder.setName(order.getName());
        newOrder.setState(order.getState());
        newOrder.setProduct(order.getProduct());
        newOrder.setArea(order.getArea());
        newOrder.setDate(order.getDate());
        
        return archive.addOrder(newOrder);
    }
    
    @Override
    public Order updateOrder(Order outdated, Order updated) throws FlooringPersistenceException {
        return archive.editOrder(outdated, updated);
    }
    
    @Override
    public Order removeOrder(Order order) throws FlooringPersistenceException {
        return archive.subOrder(order);
    }

    @Override
    public Map<String, Order> getOrdersOfDate(LocalDate date) throws FlooringExistenceException {
        return archive.getAllOrdersOfDate(date);
    }
    
    @Override
    public void configurePersistence(FlooringOrderArchiveWriter writer) {
        configuration.setWriter(this.archive, writer);
    }
    
}
