/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author James
 */
public class DVDLibraryDaoLambdaImpl implements DVDLibraryDaoLambda{
    private Map<Integer, DVD> myLibrary = new HashMap<>();
    
    @Override
    public long getDVDAge(DVD dvd) {
        Period p = dvd.getReleaseDate().until(LocalDate.now());
        return p.getYears();
    }

    @Override
    public List<DVD> releasedLastNYears(long years) throws DVDLibraryPersistenceException {
        return myLibrary.values()
                .stream()
                .filter(d -> getDVDAge(d) < years)
                .collect(Collectors.toList());
    }

    @Override
    public List<DVD> moviesByMpaa(String mpaaRating) throws DVDLibraryPersistenceException {
        return myLibrary.values().stream()
                .filter(d -> d.getMpaaRating().equalsIgnoreCase(mpaaRating))
                .collect(Collectors.toList());
    }

    @Override
    public List<DVD> moviesByDirector(String directorName) throws DVDLibraryPersistenceException {
        return myLibrary.values().stream()
                .filter(d -> d.getDirectorName().equalsIgnoreCase(directorName))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<DVD>> getDirectorGroupByMPAA(String directorName) {
        return myLibrary.values().stream()
                .filter(d -> d.getDirectorName().equalsIgnoreCase(directorName))
// MPAA Rating is the key, value is the list of DVDs filtered by director                
                .collect(Collectors.groupingBy(d -> d.getMpaaRating()));
    }

    @Override
    public List<DVD> moviesByStudio(String studio) throws DVDLibraryPersistenceException {
        return myLibrary.values().stream()
                .filter(d -> d.getStudio().equalsIgnoreCase(studio))
                .collect(Collectors.toList());
    }

    @Override
    public double averageAge() throws DVDLibraryPersistenceException {
        return myLibrary.values()
                .stream()
                .mapToLong(d -> getDVDAge(d))
                .average()
                .getAsDouble();
    }
    
// get the min/max, then search DVDs to find which one is the min/max    
//    @Override
//    public List<DVD> newestMovie() {
//        return myLibrary.values().stream()
//                .mapToLong(d -> getDVDAge(d))
//                .min()
//                .getAsLong();
//        
//        
//        
//    }
    
}
