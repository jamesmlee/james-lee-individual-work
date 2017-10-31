/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author James
 */
public interface DVDLibraryDao {
// return all DVD objects
    List<DVD> getAllDVD()
     throws DVDLibraryPersistenceException;

// adds a DVD and associates it with the given dvdId    
    DVD addDVD(Integer dvdId, DVD dvd)
     throws DVDLibraryPersistenceException;

// takes dvdId to retrieve; returns the DVD object associated with it    
    DVD getDVD(Integer dvdId)
     throws DVDLibraryPersistenceException;

// takes dvdId to remove; returns DVD object that was removed    
    DVD removeDVD(Integer dvdId)
     throws DVDLibraryPersistenceException;

// takes title to search; returns key with that title
    Integer getDVDByTitle(String title)
     throws DVDLibraryPersistenceException;

// writes edits made to file;     
    public void saveEdits() throws DVDLibraryPersistenceException;

    public void editDVD(DVD dvd) throws DVDLibraryPersistenceException;
    
// methods using Lambda, Stream, and Aggregate
    public long getDVDAge(DVD dvd);
    
    public List<DVD> releasedLastNYears(long years) throws DVDLibraryPersistenceException;
    
    public List<DVD> moviesByMpaa(String mpaaRating) throws DVDLibraryPersistenceException;
    
    public List<DVD> moviesByDirector(String directorName) throws DVDLibraryPersistenceException;
    
    public Map<String, List<DVD>> getDirectorGroupByMPAA(String directorName);
    
    public List<DVD> moviesByStudio (String studio) throws DVDLibraryPersistenceException;
    
    public double averageAge() throws DVDLibraryPersistenceException;
    
//    public List<DVD> newestMovie();

}
