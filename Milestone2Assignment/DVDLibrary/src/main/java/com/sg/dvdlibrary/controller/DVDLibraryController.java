/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.service.DVDLibraryDataValidationException;
import com.sg.dvdlibrary.service.DVDLibraryDuplicateIdException;
import com.sg.dvdlibrary.service.DVDLibraryServiceLayer;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author James
 */
public class DVDLibraryController {
    
    DVDLibraryView view;
    DVDLibraryServiceLayer service;

// dependency injection ... pass service & view as params
    public DVDLibraryController(DVDLibraryServiceLayer service, DVDLibraryView view) {
        this.service = service;
        this.view = view;
    }
    
    private UserIO io = new UserIOConsoleImpl();
    
    public void run() throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException,
            DVDLibraryDuplicateIdException {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                
                menuSelection = getMenuSelection();
                
                switch (menuSelection) {
                    case 1:
                        listDVD();
                        break;
                    case 2:
                        addDVD();
                        break;
                    case 3:
                        viewDVD();
                        break;
                    case 4:
                        removeDVD();
                        break;
                    case 5:
                        editDVD();
                        break;
                    case 6:
                        searchDVDByTitle();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DVDLibraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }        
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void addDVD() throws DVDLibraryPersistenceException,
            DVDLibraryDuplicateIdException, DVDLibraryDataValidationException {
// need to ask for input at least once
// if we encounter any kind of exception, we must tell user and re-prompt to fix
        view.displayAddDVDBanner();
        boolean hasErrors = false;
        
        do {
            DVD newDVD = view.getNewDVDInfo();
// now we have a DVD object, so must add it to service layer
// persisting the DVD to service layer could result in errors
            try {
                service.addDVD(newDVD.getDVDId(), newDVD);
                view.displayAddDVDSuccessBanner();
// setting to false is redundant the 1st time, but we need this break in case we have exceptions                    
                hasErrors = false;
// not catching Persistence -- there's nothing we or the user can do to fix it; just report it                    
            } catch (DVDLibraryDuplicateIdException | DVDLibraryDataValidationException e) {
// something went wrong, so we need to re-prompt                    
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }            
        } while (hasErrors);
    }
    
    private void listDVD() throws DVDLibraryPersistenceException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = service.getAllDVD();
        view.displayDVDList(dvdList);
    }
    
    private void viewDVD() throws DVDLibraryPersistenceException {
        view.displayDisplayDVDBanner();
        Integer dvdId = view.getDVDChoice();
        DVD dvd = service.getDVD(dvdId);
        view.displayDVD(dvd);
    }
    
    private void removeDVD() throws DVDLibraryPersistenceException {
        view.displayRemoveDVDBanner();
        Integer dvdId = view.getDVDChoice();
        service.removeDVD(dvdId);
        view.displayRemoveSuccessBanner();
    }

// show the object that had a key with title match
    private void searchDVDByTitle() throws DVDLibraryPersistenceException {
        view.displaySearchTitleBanner();
        String title = view.getTitleChoice();
        Integer searchKey = service.getDVDByTitle(title);
        DVD dvd = service.getDVD(searchKey);
        view.displayDVD(dvd);
    }
    
    public void editDVD() throws DVDLibraryPersistenceException,
        DVDLibraryDataValidationException {
        view.displayEditDVDBanner();
        boolean hasErrors = false;
        Integer dvdId = view.getDVDChoice();
        DVD dvd = service.getDVD(dvdId);
        view.displayDVD(dvd);
        do {
            DVD updatedDVD = view.getUpdatedDVDInfo(dvd);
            try {
                service.editDVD(updatedDVD);
                
            } catch (DVDLibraryDataValidationException ex) {
                hasErrors = true;
                view.displayErrorMessage(ex.getMessage());
            }
        } while (hasErrors);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
}
