/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author James
 */
public class LoggingAdvice {

    VendingMachineAuditDao auditDao;
// constructor injection ... Spring framework will hand us our dependency 
    public LoggingAdvice(VendingMachineAuditDao auditDao) {
        this.auditDao = auditDao;
    }

// grab name of method we're auditing, and the string representation of all of its parameters
// put this into a string, and have audit dao write it to the audit log    
    public void createAuditEntry(JoinPoint jp) {
// asking jp to getArgs, which come back as an Object array
        Object[] args = jp.getArgs();
// ask auditDao to write auditEntry String  
        String auditEntry = jp.getSignature().getName() + ": ";
// loop through arguments. concatenate them to auditEntry
// will have name of method and string representation of each argument ... write this to audit log
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachineDaoException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

// for InsufficientFunds and NoItemInventory exceptions
// uses an Apache Commons utility for manipulating Throwable objects  
    public void createAuditEntryForExceptions(JoinPoint jp, Throwable ex) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }

        auditEntry += " |EXCEPTION: "
                + ExceptionUtils.getRootCauseMessage(ex);

        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachineDaoException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
// could alternatively use a separate method for each exception type
// then update the applicationContext.xml with the proper methods     

}