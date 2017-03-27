/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.dao;

import com.swg.flooring.dto.IDGenerator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class FlooringIDGeneratorDaoImpl implements FlooringIDGeneratorDao {

    public static final String GENERATOR = "id_generator.txt";
    public IDGenerator daoGenerator;

    @Override
    public String generateOrderNumber() throws FlooringPersistenceException {
        try {
            FileReader fr = new FileReader(GENERATOR);
            BufferedReader br = new BufferedReader(fr);
            Scanner sr = new Scanner(br);

            String line;

            line = sr.nextLine();

            IDGenerator generator = new IDGenerator();

            generator.setId(Integer.parseInt(line));

            int id = generator.getId();

            sr.close();

            FileWriter fw = new FileWriter(GENERATOR);
            PrintWriter pw = new PrintWriter(fw);

            int newID = generator.getId() + 1;

            pw.println(Integer.toString(newID));

            pw.flush();

            pw.close();

            return Integer.toString(id);
        } catch (IOException ex) {
            throw new FlooringPersistenceException("Reading " + GENERATOR + " Unsuccessful:", ex);
        }
    }

}
