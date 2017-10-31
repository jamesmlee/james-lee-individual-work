/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author James
 */
public class DVDLibraryServiceLayerImpl implements DVDLibraryServiceLayer {

    private DVDLibraryDao dao;
    private DVDLibraryAuditDao auditDao;

    public DVDLibraryServiceLayerImpl(DVDLibraryDao dao, DVDLibraryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<DVD> getAllDVD() throws DVDLibraryPersistenceException {
        return dao.getAllDVD();
    }

    @Override
    public void addDVD(Integer dvdId, DVD dvd) throws DVDLibraryDuplicateIdException,
            DVDLibraryDataValidationException, DVDLibraryPersistenceException {
// if it already exists, throw DVDLibaryDuplicateIdException
        if (dao.getDVD(dvd.getDVDId()) != null) {
            throw new DVDLibraryDuplicateIdException(
                    "ERROR: Could not create DVD. DVD ID"
                    + dvd.getDVDId()
                    + " already exists");
        }
// validate that the rest of the DVD data is ok
        validateDVDData(dvd);
// add DVD if validation passes
        dao.addDVD(dvd.getDVDId(), dvd);
// write to auditDao        
        auditDao.writeAuditEntry("DVD " + dvd.getDVDId() + " CREATED");
    }

    @Override
    public DVD getDVD(Integer dvdId) throws DVDLibraryPersistenceException {
        return dao.getDVD(dvdId);
    }

    @Override
    public DVD removeDVD(Integer dvdId) throws DVDLibraryPersistenceException {
// removeDVD potentially returns something, so need to write to audit log before returning
        DVD removedDVD = dao.removeDVD(dvdId);
// if there was a DVD removed, return it        
        auditDao.writeAuditEntry("DVD " + dvdId + " REMOVED");
        return removedDVD;
    }

    @Override
    public Integer getDVDByTitle(String title) throws DVDLibraryPersistenceException {
        return dao.getDVDByTitle(title);
    }

    @Override
    public void saveEdits() throws DVDLibraryPersistenceException, DVDLibraryDataValidationException {
        dao.saveEdits();
    }

    private void validateDVDData(DVD dvd) throws DVDLibraryDataValidationException {
// if null or empty
        if (dvd.getTitle() == null
                || dvd.getTitle().trim().length() == 0
                || dvd.getReleaseDate() == null
                || dvd.getMpaaRating() == null
                || dvd.getMpaaRating().trim().length() == 0
                || dvd.getDirectorName() == null
                || dvd.getDirectorName().trim().length() == 0
                || dvd.getStudio() == null
                || dvd.getStudio().trim().length() == 0
                || dvd.getNotes() == null
                || dvd.getNotes().trim().length() == 0) {

            throw new DVDLibraryDataValidationException(
                    "Error: All fields [Title, Release Date, MPAA Rating, Director's Name, Studio, and Notes] are required.");
        }
    }

    @Override
    public void updateTitle(DVD dvd, String newTitle) throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException {
        dvd.setTitle(newTitle);
        saveEdits();
    }

    @Override
    public void updateReleaseDate(DVD dvd, LocalDate newReleaseDate) throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException {
        dvd.setReleaseDate(newReleaseDate);
        saveEdits();
    }

    @Override
    public void updateMPAA(DVD dvd, String newMPAA) throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException {
        dvd.setMpaaRating(newMPAA);
        saveEdits();
    }

    @Override
    public void updateDirectorName(DVD dvd, String newDirectorName) throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException {
        dvd.setDirectorName(newDirectorName);
        saveEdits();
    }

    @Override
    public void updateStudio(DVD dvd, String newStudio) throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException {
        dvd.setStudio(newStudio);
        saveEdits();
    }

    @Override
    public void updateNotes(DVD dvd, String newNotes) throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException {
        dvd.setNotes(newNotes);
        saveEdits();
    }

    @Override
    public void editDVD(DVD dvd) throws
            DVDLibraryDataValidationException,
            DVDLibraryPersistenceException {

// Now validate all the fields on the given Student object. This method will
// throw an exception if any of the validation rules are violated.
        validateDVDData(dvd);
        
        dao.editDVD(dvd);
// We passed all our business rules checks so go ahead and persist the Student object
//        dao.addStudent(student.getStudentId(), student);
// The student was successfully created, now write to the audit log        
//        auditDao.writeAuditEntry("Student " + student.getStudentId() + " CREATED");
    }

// create update method that takes a DVD object. passes it to DAO to save it
// input is the new version of the DVD object to update
}
