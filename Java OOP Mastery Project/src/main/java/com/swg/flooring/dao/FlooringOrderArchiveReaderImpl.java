/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.dao;

import com.swg.flooring.dto.Order;
import com.swg.flooring.dto.Product;
import com.swg.flooring.dto.State;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class FlooringOrderArchiveReaderImpl implements FlooringOrderArchiveReader {

    private final String DELIMITER = ",";

    @Override
    public Map<String, Order> getAllOrdersOfDate(LocalDate date) throws FlooringExistenceException {
        try {
            String fileName = getFileName(date);
            Path path = Paths.get(fileName);
            BufferedReader stream = Files.newBufferedReader(path);

            List<String> content = stream
                    .lines()
                    .filter(line -> !line.equalsIgnoreCase(createFileHeader()))
                    .collect(Collectors.toList());

            Map<String, Order> orders = new HashMap<>();

            content
                    .stream()
                    .map((line) -> decodeOrder(line, date))
                    .forEachOrdered((order) -> {
                        orders.put(order.getId(), order);
                    });

            return orders;

        } catch (IOException ex) {
            throw new FlooringExistenceException("No Orders Exist for that Date:", ex);
        }
    }

    @Override
    public Order getOrder(Map<String, Order> orders, String id) throws FlooringExistenceException {
        return orders.get(id);
    }

    private Order decodeOrder(String data, LocalDate date) {
        String[] token = data.split(DELIMITER);

        State state = new State(token[2]);
        state.setRate(new BigDecimal(token[3]));

        Product flooring = new Product(token[4]);
        flooring.setCostSqFt(new BigDecimal(token[6]));
        flooring.setLaborSqFt(new BigDecimal(token[7]));

        Order order = new Order(token[0]);
        order.setName(token[1]);
        order.setState(state);
        order.setProduct(flooring);
        order.setArea(new BigDecimal(token[5]));
        order.setDate(date);

        return order;
    }

    private LocalDate getFileDate(String fileName) {
        String[] dateArr = fileName.split("Orders_|.txt");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        return LocalDate.parse(dateArr[1], formatter);
    }
    
    
    private String getFileName(LocalDate ld) {
        String date = ld.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        return "Orders_" + date + ".txt";
    }

    private String createFileHeader() {
        String header
                = "OrderNumber,"
                + "CustomerName,"
                + "State,"
                + "TaxRate,"
                + "ProductType,"
                + "Area,"
                + "CostPerSquareFoot,"
                + "LaborCostPerSquareFoot,"
                + "MaterialCost,"
                + "LaborCost,"
                + "Tax,"
                + "Total";
        return header;
    }
}
