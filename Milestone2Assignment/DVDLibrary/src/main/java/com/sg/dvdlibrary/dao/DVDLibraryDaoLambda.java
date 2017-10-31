/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;
import java.util.Map;

/**
 *
 * @author James
 */
public interface DVDLibraryDaoLambda {

    public long getDVDAge(DVD dvd);

    public List<DVD> releasedLastNYears(long years) throws DVDLibraryPersistenceException;

    public List<DVD> moviesByMpaa(String mpaaRating) throws DVDLibraryPersistenceException;

    public List<DVD> moviesByDirector(String directorName) throws DVDLibraryPersistenceException;

    public Map<String, List<DVD>> getDirectorGroupByMPAA(String directorName);

    public List<DVD> moviesByStudio(String studio) throws DVDLibraryPersistenceException;

    public double averageAge() throws DVDLibraryPersistenceException;

//    public List<DVD> newestMovie();
}
