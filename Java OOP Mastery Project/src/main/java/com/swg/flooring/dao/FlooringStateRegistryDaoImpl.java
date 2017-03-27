/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.dao;

import com.swg.flooring.dto.State;
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
public class FlooringStateRegistryDaoImpl implements FlooringStateRegistryDao {

    private final String FILE_NAME = "Taxes.txt";
    private final String DELIMITER = ",";

    @Override
    public State getState(String name) throws FlooringPersistenceException {
        return getAllStates().get(name);
    }

    @Override
    public Map<String, State> getAllStates() throws FlooringPersistenceException {
        try {
            Path path = Paths.get(FILE_NAME);
            BufferedReader stream = Files.newBufferedReader(path);

            Map<String, State> states = new HashMap<>();
            stream
                    .lines()
                    .filter(line -> !line.equalsIgnoreCase(getStateFileHeader()))
                    .filter(line -> !line.isEmpty())
                    .map(line -> decodeState(line.trim()))
                    .forEach(state -> {
                        states.put(state.getName(), state);
                    });

            return states;
        } catch (IOException ex) {
            throw new FlooringPersistenceException("Reading " + FILE_NAME + " Unsuccessful:", ex);
        }
    }

    private State decodeState(String data) {

        String[] token = data.split(DELIMITER);
        State state = new State(token[0]);
        state.setRate(new BigDecimal(token[1]).setScale(2, RoundingMode.HALF_UP));
        return state;
    }

    private String getStateFileHeader() {
        return "State,TaxRate";
    }
}
