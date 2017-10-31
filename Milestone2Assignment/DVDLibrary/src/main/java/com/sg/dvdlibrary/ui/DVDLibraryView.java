/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author James
 */
public class DVDLibraryView {

    UserIO io;

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {

        io.print("Main Menu");
        io.print("1. List DVDs");
        io.print("2. Add DVD");
        io.print("3. View DVD Info");
        io.print("4. Remove DVD");
        io.print("5. Edit DVD Info");
        io.print("6. Search DVD By Title");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public DVD getNewDVDInfo() {
        Integer dvdId = io.readInt("Please enter DVD ID");
        String title = io.readString("Please enter Title");
        LocalDate releaseDate = io.readLocalDate("Please enter Release Date");
        String mpaaRating = io.readString("Please enter MPAA Rating");
        String directorName = io.readString("Please enter Director Name");
        String studio = io.readString("Please enter Studio Name");
        String notes = io.readString("Enter any notes/comments you have about the DVD");
        DVD currentDVD = new DVD(dvdId);
        currentDVD.setTitle(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setNotes(notes);
        return currentDVD;
    }

    public DVD getUpdatedDVDInfo(DVD currentDVD) {
        boolean keepEditing = true;
        int editChoice = 0;
        while (keepEditing) {
            editChoice = getEditMenuChoice();
            switch (editChoice) {
                case 1:
                    String newTitle = getEditedTitle();
                    currentDVD.setTitle(newTitle);
                    break;
                case 2:
                    LocalDate newReleaseDate = getEditedDate();
                    currentDVD.setReleaseDate(newReleaseDate);
                    break;
                case 3:
                    String newMPAA = getEditedMpaa();
                    currentDVD.setMpaaRating(newMPAA);
                    break;
                case 4:
                    String newDirectorName = getEditedDirector();
                    currentDVD.setDirectorName(newDirectorName);
                    break;
                case 5:
                    String newStudio = getEditedStudio();
                    currentDVD.setStudio(newStudio);
                    break;
                case 6:
                    String newNote = getEditedNote();
                    currentDVD.setNotes(newNote);
                    break;
                case 7:
                    keepEditing = false;
                    break;
                default:      
                    displayUnknownCommandBanner();
            }
        }
        return currentDVD;
    }

    public void displayAddDVDBanner() {
        io.print("=== Add DVD ===");
    }

    public void displayAddDVDSuccessBanner() {
        io.readString("DVD successfully added. Please hit enter to continue");
    }

    public void displayDVDList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            io.print(currentDVD.getTitle() + ": "
                    + currentDVD.getReleaseDate() + " "
                    + currentDVD.getMpaaRating() + " "
                    + currentDVD.getDirectorName() + " "
                    + currentDVD.getStudio() + " "
                    + currentDVD.getNotes());
        }
        io.readString("Please hit enter to continue");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayDisplayDVDBanner() {
        io.print("=== Display DVD ===");
    }

    public Integer getDVDChoice() {
        return io.readInt("Please enter the DVD ID");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print("Title: " + dvd.getTitle());
            io.print("Release date: " + dvd.getReleaseDate());
            io.print("MPAA Rating: " + dvd.getMpaaRating());
            io.print("Director Name: " + dvd.getDirectorName());
            io.print("Studio: " + dvd.getStudio());
            io.print("Notes: " + dvd.getNotes());
            io.print("");
        } else {
            io.print("No such DVD");
        }
        io.readString("Please hit enter to continue");
    }

    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("DVD successfully removed. Please hit enter to continue.");
    }

    public void displaySearchTitleBanner() {
        io.print("=== Search For Title ===");
    }

    public String getTitleChoice() {
        return io.readString("Please enter the title to search for");
    }

    public void displayExitBanner() {
        io.print("Good Bye");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command");
    }

    public void displayEditDVDBanner() {
        io.print("=== Edit DVD ===");
    }

    public int getEditMenuChoice() {
        io.print("Edit Menu");
        io.print("1. Edit Title");
        io.print("2. Edit Release Date");
        io.print("3. Edit MPAA Rating");
        io.print("4. Edit Director Name");
        io.print("5. Edit Studio");
        io.print("6. Edit Notes");
        io.print("7. Done Editing");

        return io.readInt("Please select from the above choices", 1, 7);
    }

    public LocalDate getEditedDate() {
        return io.readLocalDate("Enter a new date");
    }

    public String getEditedTitle() {
        return io.readString("Enter new title");
    }

    public LocalDate readLocalDate() {
        String input = io.readString("Enter new release date");
        LocalDate date = LocalDate.parse(input);
        return date;
    }

    public String getEditedMpaa() {
        return io.readString("Enter new MPAA rating");
    }

    public String getEditedDirector() {
        return io.readString("Enter new director name");
    }

    public String getEditedStudio() {
        return io.readString("Enter new studio");
    }

    public String getEditedNote() {
        return io.readString("Enter new notes");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
