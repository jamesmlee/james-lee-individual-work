/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author James
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";

    private Map<Integer, DVD> myLibrary = new HashMap<>();

    @Override
    public List<DVD> getAllDVD()
            throws DVDLibraryPersistenceException {
        loadLibrary();
        return new ArrayList<DVD>(myLibrary.values());
    }

    @Override
    public DVD addDVD(Integer dvdId, DVD dvd)
            throws DVDLibraryPersistenceException {
        DVD newDVD = myLibrary.put(dvdId, dvd);
        writeLibrary();
        return newDVD;
    }

    @Override
    public DVD getDVD(Integer dvdId)
            throws DVDLibraryPersistenceException {
        loadLibrary();
        return myLibrary.get(dvdId);
    }

    @Override
    public DVD removeDVD(Integer dvdId)
            throws DVDLibraryPersistenceException {
        DVD removedDVD = myLibrary.remove(dvdId);
        writeLibrary();
        return removedDVD;
    }

// find the key that has a match for title    
    Set<Integer> keys = myLibrary.keySet();

    @Override
    public Integer getDVDByTitle(String title)
            throws DVDLibraryPersistenceException {
        String searchTitle = title;
        Integer searchKey = 0;

        for (Integer k : keys) {
            DVD searchedDVD = myLibrary.get(k);
            if (searchedDVD.getTitle().equals(searchTitle)) {
                searchKey = searchedDVD.getDVDId();
            }
        }
        loadLibrary();
        return searchKey;
    }

    private void loadLibrary() throws DVDLibraryPersistenceException {
        Scanner scanner;

        try {
// Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryPersistenceException("Could not load roster data into memory.", e);
        }
// currentLine holds the most recent line read from the file
        String currentLine;
// currentTokens holds each of the parts of the currentLine after it has been split on our DELIMITER
        String[] currentTokens;
// Go through LIBRARY_FILE line by line, decoding each line into a DVD object.
// Process while we have more lines in the file
        while (scanner.hasNextLine()) {
// get the next line in the file
            currentLine = scanner.nextLine();
// break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
// Create a new DVD object and put it into the map of DVDs
// need to parseInt because split() splits line into an array of strings
            DVD currentDVD = new DVD(Integer.parseInt(currentTokens[0]));
// Set the remaining values on currentDVD manually
            currentDVD.setTitle(currentTokens[1]);
            currentDVD.setReleaseDate(LocalDate.parse(currentTokens[2]));
            currentDVD.setMpaaRating(currentTokens[3]);
            currentDVD.setDirectorName(currentTokens[4]);
            currentDVD.setStudio(currentTokens[5]);
            currentDVD.setNotes(currentTokens[6]);
// Put currentDVD into the map using dvdId as the key
            myLibrary.put(currentDVD.getDVDId(), currentDVD);
        }
        scanner.close();
    }

    private void writeLibrary() throws DVDLibraryPersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryPersistenceException(
                    "Could not save student data.", e);
        }
        List<DVD> DVDList = this.getAllDVD();
        for (DVD currentDVD : DVDList) {
            out.println(currentDVD.getDVDId() + DELIMITER
                    + currentDVD.getTitle() + DELIMITER
                    + currentDVD.getReleaseDate() + DELIMITER
                    + currentDVD.getMpaaRating() + DELIMITER
                    + currentDVD.getDirectorName() + DELIMITER
                    + currentDVD.getStudio() + DELIMITER
                    + currentDVD.getNotes() + DELIMITER);
            out.flush();
        }
        out.close();
    }

    @Override
    public void saveEdits() throws DVDLibraryPersistenceException {
        writeLibrary();
    }

    @Override
    public void editDVD(DVD dvd) throws DVDLibraryPersistenceException {
        DVD newDVD = myLibrary.put(dvd.getDVDId(), dvd);
        writeLibrary();
    }

    
// Lambda    
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
