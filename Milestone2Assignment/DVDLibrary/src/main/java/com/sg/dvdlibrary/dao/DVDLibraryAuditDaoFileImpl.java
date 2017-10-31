/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author James
 */
public class DVDLibraryAuditDaoFileImpl implements DVDLibraryAuditDao {
    
    public static final String AUDIT_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String entry) throws DVDLibraryPersistenceException {
        PrintWriter out;
        try {
// AUDIT_FILE, true --> the true allows us to append to the audit trail
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
// translate IOException into a Persistence exception and throw it
        } catch (IOException ex) {
            throw new DVDLibraryPersistenceException("Could not persist audit information", ex);
        }
// if everything is ok, create a timestamp and write out to file        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
    
}
