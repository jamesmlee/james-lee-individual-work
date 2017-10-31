/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author James
 */
public class DVDLibraryDaoStubImpl implements DVDLibraryDao {

    private DVD onlyDVD;
    private List<DVD> dvdList = new ArrayList<>();

    public DVDLibraryDaoStubImpl() {
        onlyDVD = new DVD(1);
        onlyDVD.setTitle("Movie1");
        onlyDVD.setReleaseDate(LocalDate.parse("2000-01-01"));
        onlyDVD.setMpaaRating("PG");
        onlyDVD.setDirectorName("Director");
        onlyDVD.setStudio("Studio");
        onlyDVD.setNotes("these are some notes");
    }

    @Override
    public List<DVD> getAllDVD() throws DVDLibraryPersistenceException {
        return dvdList;
    }

    @Override
    public DVD addDVD(Integer dvdId, DVD dvd) throws DVDLibraryPersistenceException {
        if (dvdId.equals(onlyDVD.getDVDId())) {
            return onlyDVD;
        } else {
            return null;
        }
    }

    @Override
    public DVD getDVD(Integer dvdId) throws DVDLibraryPersistenceException {
        if (dvdId.equals(onlyDVD.getDVDId())) {
            return onlyDVD;
        } else {
            return null;
        }
    }

    @Override
    public DVD removeDVD(Integer dvdId) throws DVDLibraryPersistenceException {
        if (dvdId.equals(onlyDVD.getDVDId())) {
            return onlyDVD;
        } else {
            return null;
        }
    }

    @Override
    public Integer getDVDByTitle(String title) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveEdits() throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editDVD(DVD dvd) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getDVDAge(DVD dvd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DVD> releasedLastNYears(long years) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DVD> moviesByMpaa(String mpaaRating) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DVD> moviesByDirector(String directorName) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, List<DVD>> getDirectorGroupByMPAA(String directorName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DVD> moviesByStudio(String studio) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double averageAge() throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
