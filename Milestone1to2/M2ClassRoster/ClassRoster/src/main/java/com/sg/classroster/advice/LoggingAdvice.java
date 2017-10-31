/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.advice;

import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author James
 */
public class LoggingAdvice {

    ClassRosterAuditDao auditDao;

    public LoggingAdvice(ClassRosterAuditDao auditDao) {
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
        } catch (ClassRosterPersistenceException e) {
// System.err.println            
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
