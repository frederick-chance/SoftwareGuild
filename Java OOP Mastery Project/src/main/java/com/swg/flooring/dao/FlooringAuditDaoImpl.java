/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 *
 * @author apprentice
 */
public class FlooringAuditDaoImpl implements FlooringAuditDao {

    private static final String AUDIT_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String entry)
            throws FlooringPersistenceException {

        try {
            
            File file = new File(AUDIT_FILE);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            LocalDate timestamp = LocalDate.now();
            pw.println("[" + timestamp + " " +entry);
            pw.flush();
            pw.close();
            
        } catch (IOException ex) {
            
            throw new FlooringPersistenceException(
                    "Could not persits audit entry.", ex);
            
        }
    }
}
