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
public class FlooringOrderArchiveDaoImpl implements FlooringOrderArchiveDao {

    private FlooringOrderArchiveWriter writer;
    private FlooringOrderArchiveReader reader;

    public FlooringOrderArchiveDaoImpl(
            FlooringOrderArchiveWriter writer, 
            FlooringOrderArchiveReader reader) {
        
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public Order addOrder(Order order) throws FlooringPersistenceException{
        writer.writeOrder(order);
        return order;
    }

    @Override
    public Order subOrder(Order order) throws FlooringPersistenceException {
        writer.eraseOrder(order);
        return order;
    }

    @Override
    public Order editOrder(Order oldOrder, Order newOrder) throws FlooringPersistenceException{
            writer.writeOrder(newOrder);
            writer.eraseOrder(oldOrder);
        return oldOrder;
    }

    @Override
    public Order getOrder(LocalDate date, String id) throws FlooringExistenceException {
        Map orders = getAllOrdersOfDate(date);
        return reader.getOrder(orders, id);
    }

    @Override
    public Map<String, Order> getAllOrdersOfDate(LocalDate date) throws FlooringExistenceException {
        return reader.getAllOrdersOfDate(date);
    }

    @Override
    public FlooringOrderArchiveWriter getWriter() {
        return writer;
    }

    @Override
    public void setWriter(FlooringOrderArchiveWriter writer) {
        this.writer = writer;
    }

    @Override
    public FlooringOrderArchiveReader getReader() {
        return reader;
    }

    @Override
    public void setReader(FlooringOrderArchiveReader reader) {
        this.reader = reader;
    }

}
