/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.dao;

import com.swg.flooring.dto.Product;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class FlooringProductInventoryDaoImpl implements FlooringProductInventoryDao {

    private final String FILE_NAME = "Products.txt";
    private final String DELIMITER = ",";

    @Override
    public Product getProduct(String type) throws FlooringPersistenceException {
        return getAllProducts().get(type);
    }

    @Override
    public Map<String, Product> getAllProducts() throws FlooringPersistenceException {
        try {
            Path path = Paths.get(FILE_NAME);
            BufferedReader stream = Files.newBufferedReader(path);

            Map<String, Product> products = new HashMap<>();
            stream
                    .lines()
                    .filter(line -> !line.equalsIgnoreCase(getProductFileHeader()))
                    .filter(line -> !line.isEmpty())
                    .map(line -> decodeProduct(line.trim()))
                    .forEach(product -> {
                        products.put(product.getType(), product);
                    });

            return products;
        } catch (IOException ex) {
            throw new FlooringPersistenceException("Reading " + FILE_NAME + " Unsuccessful:", ex);
        }
    }

    private Product decodeProduct(String data) {

        String[] token = data.split(DELIMITER);
        Product product = new Product(token[0]);
        product.setCostSqFt(new BigDecimal(token[1]).setScale(2, RoundingMode.HALF_UP));
        product.setLaborSqFt(new BigDecimal(token[2]).setScale(2, RoundingMode.HALF_UP));
        return product;
    }

    private String getProductFileHeader() {
        return "ProductType,CostPerSquareFoot,LaborCostPerSquareFoot";
    }

}
