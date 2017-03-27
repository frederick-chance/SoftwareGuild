/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.dao;

/**
 *
 * @author apprentice
 */
public class FlooringExistenceException extends Exception {
    
    public FlooringExistenceException (String message) {
        super(message);
    }
    
    public FlooringExistenceException (String message, Throwable cause) {
        super(message, cause);
    }
    
}
