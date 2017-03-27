/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author frederick
 */
public class VendingMachineDaoBoxImpl implements VendingMachineDaoBox {

    public String depositoryFile = "depository.txt";
    
    private List<BigDecimal> depository = new ArrayList<>();
    private List<BigDecimal> changeReturn = new ArrayList<>();
    
    
    public void setFile(String file) {
        this.depositoryFile = file;
    }

    @Override
    public void depositCoin(BigDecimal coin)
            throws VendingMachinePersistenceException {

        depository.add(coin);
        writeDepository();
    }

    @Override
    public List<BigDecimal> getDepository() 
            throws VendingMachinePersistenceException {
        
        readDepository();
        return depository;
    }

    @Override
    public BigDecimal removeCoin(int index)
            throws VendingMachinePersistenceException {

        BigDecimal coin = depository.remove(index);
        writeDepository();
        return coin;
    }
    
    @Override
    public void emptyDepository() throws 
            VendingMachinePersistenceException{
        
        depository.clear();
        writeDepository();
    }
    
    @Override
    public void setChangeReturn(List<BigDecimal> coins) {
        changeReturn.addAll(coins);        
    }
    
    @Override
    public List<BigDecimal> getChangeReturn() {
        return changeReturn;
    }
    
    @Override
    public void emptyChangeReturn() {
        changeReturn.clear();
    }

    private void readDepository() throws VendingMachinePersistenceException {
        String contact = "Please contact customer service at ptoner@thesoftwareguild.com";
        String message = "Read Error: " + contact;
        Scanner read;
        
        depository.clear();

        try {
            read = new Scanner(new BufferedReader(new FileReader(depositoryFile)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(message, e);
        }

        String coinValue;

        while (read.hasNextLine()) {

            coinValue = read.nextLine();

            switch (coinValue) {
                case "1.00":
                    BigDecimal dollar = new BigDecimal("1.00");
                    depository.add(dollar);
                    break;
                case "0.25":
                    BigDecimal quarter = new BigDecimal("0.25");
                    depository.add(quarter);
                    break;
                case "0.10":
                    BigDecimal dime = new BigDecimal("0.10");
                    depository.add(dime);
                    break;
                case "0.05":
                    BigDecimal nickel = new BigDecimal("0.05");
                    depository.add(nickel);
                    break;
                default:
                    System.out.println("Attempted to add unknown coin to depository.");
            }
        }
        
        read.close();
    }

    private void writeDepository() throws VendingMachinePersistenceException {
        List<BigDecimal> depositoryLog = depository;
        String contact = "Please contact customer service at ptoner@thesoftwareguild.com";
        String message = "Write Error: " + contact;
        PrintWriter write;

        try {
            write = new PrintWriter(new FileWriter(depositoryFile));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(message, e);
        }

        depositoryLog.stream().map((coin) -> {
            write.println(coin.toString());
            return coin;
        }).forEachOrdered((_item) -> {
            write.flush();
        });
        write.close();
    }
}
