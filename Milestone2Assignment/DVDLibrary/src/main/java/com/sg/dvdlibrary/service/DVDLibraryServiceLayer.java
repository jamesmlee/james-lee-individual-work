/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author James
 */
public interface DVDLibraryServiceLayer {

// return all DVD objects
    public List<DVD> getAllDVD() throws DVDLibraryPersistenceException;

// adds a DVD and associates it with the given dvdId
// *ClassRoster code along doesn't pass in dvdId as a parameter
    public void addDVD(Integer dvdId, DVD dvd) throws DVDLibraryDuplicateIdException,
            DVDLibraryDataValidationException,
            DVDLibraryPersistenceException;

// takes dvdId to retrieve; returns the DVD object associated with it    
    public DVD getDVD(Integer dvdId) throws DVDLibraryPersistenceException;

// takes dvdId to remove; returns DVD object that was removed    
    public DVD removeDVD(Integer dvdId) throws DVDLibraryPersistenceException;

// takes title to search; returns key with that title
    public Integer getDVDByTitle(String title) throws DVDLibraryPersistenceException;

// writes edits made to file;     
    public void saveEdits() throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException;

    public void updateTitle(DVD dvd, String newTitle) throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException;

    public void updateReleaseDate(DVD dvd, LocalDate newReleaseDate) throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException;

    public void updateMPAA(DVD dvd, String newMPAA) throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException;

    public void updateDirectorName(DVD dvd, String newDirectorName) throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException;

    public void updateStudio(DVD dvd, String newStudio) throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException;

    public void updateNotes(DVD dvd, String newNotes) throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException;

    public void editDVD(DVD dvd) throws
            DVDLibraryDataValidationException,
            DVDLibraryPersistenceException;
    }
