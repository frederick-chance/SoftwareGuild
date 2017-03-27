/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.dao;

import com.swg.flooring.dto.Product;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface FlooringProductInventoryDao {

    public Product getProduct(String type) throws FlooringPersistenceException;

    public Map<String, Product> getAllProducts() throws FlooringPersistenceException;

}
