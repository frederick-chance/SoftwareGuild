/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.adv;

import com.swg.flooring.dao.FlooringAuditDao;
import com.swg.flooring.dao.FlooringPersistenceException;
import com.swg.flooring.dto.Order;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author apprentice
 */
public class LoggingAdvice {
    
    FlooringAuditDao auditDao;
    
    public LoggingAdvice(FlooringAuditDao auditDao) {
        
        this.auditDao = auditDao;
        
    }
      
    public void createAuditEntry(JoinPoint jp, Object output) {
        String str = " | ";
        Object[] args = jp.getArgs();
        Order order = (Order) output;
        
        String auditEntry = jp.getSignature().getName().toUpperCase() + "] ";
        auditEntry += order.getId() + str
                + order.getDate().toString() + str
                + order.getName() + str
                + order.getStateName() + str
                + order.getFloorType() + str
                + order.getOrderTotal().toString();
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringPersistenceException e) {
            System.err.println(
            "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
    public void createAuditEntryEdit(JoinPoint jp) {
        String str = " | ";
        Object[] args = jp.getArgs();
        Order oldOrder = (Order) args[0];
        Order newOrder = (Order) args[1];
        
        String auditEntry = jp.getSignature().getName().toUpperCase() + "] ";
        auditEntry += "(removed) ";
        auditEntry += oldOrder.getId() + str
                + oldOrder.getDate().toString() + str
                + oldOrder.getName() + str
                + oldOrder.getStateName() + str
                + oldOrder.getFloorType() + str
                + oldOrder.getOrderTotal().toString();
        
        auditEntry += "\n";
        auditEntry += jp.getSignature().getName().toUpperCase() + "] ";
        auditEntry += "(added) ";
        auditEntry += newOrder.getId() + str
                + newOrder.getDate().toString() + str
                + newOrder.getName() + str
                + newOrder.getStateName() + str
                + newOrder.getFloorType() + str
                + newOrder.getOrderTotal().toString();
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringPersistenceException e) {
            System.err.println(
            "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
