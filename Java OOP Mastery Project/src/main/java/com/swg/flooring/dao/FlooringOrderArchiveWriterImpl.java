/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.dao;

import com.swg.flooring.dto.Order;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author apprentice
 */
public class FlooringOrderArchiveWriterImpl implements FlooringOrderArchiveWriter {
    private final String DELIMITER = ",";
            
    @Override
    public void writeOrder(Order order) throws FlooringPersistenceException {
        
        try {
            String fileName = getFileName(order);
            File file = new File(fileName);
            boolean isCreated = file.createNewFile();
            
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            if (isCreated) {
                String header = createFileHeader();
                pw.println(header);
            }
            
            String record = encodeOrder(order);
            pw.println(record);
            pw.close();
        } catch (IOException ex) {
            throw new FlooringPersistenceException("Write Order Unsuccessful:", ex);
        }
        
    }

    @Override
    public void eraseOrder(Order order) throws FlooringPersistenceException {
        
        try {
            String fileName = getFileName(order);
            String orderData = encodeOrder(order);
            File file = new File(fileName);
            File temp = new File("temp.txt");
            
            Stream<String> stream = Files.lines(Paths.get(fileName));
            
            String content = stream
                    .filter(line -> !line.equalsIgnoreCase(orderData))
                    .collect(Collectors.joining("\n"));
            
            if (content.isEmpty() || content.trim().endsWith(createFileHeader())) {
                file.delete();
            } else {
                file.renameTo(temp);
                
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                
                pw.println(content);
                pw.flush();
                pw.close();
                
                temp.delete();
            }
        } catch (IOException ex) {
            throw new FlooringPersistenceException("Erase Order Unsuccessful:", ex);
        }
    }
    
    private String encodeOrder(Order order) {
        
        String id = order.getId();
        String name = order.getName();
        String stateName = order.getState().getName();
        String stateTax = order.getState().getRate().toString();
        String productType = order.getProduct().getType();
        String area = order.getArea().toString();
        String costSqFt = order.getProduct().getCostSqFt().toString();
        String laborSqFt = order.getProduct().getLaborSqFt().toString();
        String materialCost = order.getCostMaterials().toString();
        String laborCost = order.getCostLabor().toString();
        String orderTax = order.getOrderTax().toString();
        String orderTotal = order.getOrderTotal().toString();

        String record
                = id + DELIMITER
                + name + DELIMITER
                + stateName + DELIMITER
                + stateTax + DELIMITER
                + productType + DELIMITER
                + area + DELIMITER
                + costSqFt + DELIMITER
                + laborSqFt + DELIMITER
                + materialCost + DELIMITER
                + laborCost + DELIMITER
                + orderTax + DELIMITER
                + orderTotal;

        return record;
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


    private String getFileName(Order order) {
        String date = order.getDate().format(DateTimeFormatter.ofPattern("MMddyyyy"));
        return "Orders_" + date + ".txt";
    }
    
}
