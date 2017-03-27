/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.dao;

import com.swg.flooring.dto.Order;

/**
 *
 * @author apprentice
 */
public interface FlooringOrderArchiveWriter {

    public void writeOrder(Order order) throws FlooringPersistenceException;

    public void eraseOrder(Order order) throws FlooringPersistenceException;

}
