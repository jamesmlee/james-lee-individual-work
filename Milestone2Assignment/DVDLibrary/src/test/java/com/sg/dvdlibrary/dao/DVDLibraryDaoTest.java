/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
public class DVDLibraryDaoTest {

    private DVDLibraryDao dao = new DVDLibraryDaoFileImpl();

    public DVDLibraryDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

// need to use setUp to get to known good state since we have stateful code
// an empty DVD Library is a good state    
    @Before
    public void setUp() throws Exception {
// get all the DVDs        
        List<DVD> DVDList = dao.getAllDVD();
// remove them
        for (DVD dvd : DVDList) {
            dao.removeDVD(dvd.getDVDId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllDVD method, of class DVDLibraryDao.
     */
// add 2 DVDs and make sure we get 2 back    
    @Test
    public void testGetAllDVD() throws Exception {
        DVD dvd1 = new DVD(1);
        dvd1.setTitle("Movie1");
        dvd1.setStudio("Studio1");
        dvd1.setReleaseDate(LocalDate.parse("2005-01-01"));
        dvd1.setMpaaRating("PG");
        dvd1.setDirectorName("Director");
        dao.addDVD(dvd1.getDVDId(), dvd1);

        DVD dvd2 = new DVD(2);
        dvd2.setTitle("Movie2");
        dvd2.setStudio("Studio2");
        dvd2.setReleaseDate(LocalDate.parse("2007-01-01"));
        dvd2.setMpaaRating("G");
        dvd2.setDirectorName("Director2");
        dao.addDVD(dvd2.getDVDId(), dvd2);
// see if we get 2 back        
        assertEquals(2, dao.getAllDVD().size());
    }

    /**
     * Test of addDVD method, of class DVDLibraryDao.
     */
// Arrange, Act: add DVD into dao, get it out and make sure they're equal    
    @Test
    public void testAddGetDVD() throws Exception {
// create dvd
        DVD dvd = new DVD(1);
        dvd.setTitle("Movie1");
        dvd.setStudio("Studio");
        dvd.setReleaseDate(LocalDate.parse("2005-01-01"));
        dvd.setMpaaRating("PG");
        dvd.setDirectorName("Director");
// add to dao        
        dao.addDVD(dvd.getDVDId(), dvd);
// see if they're equal        
        DVD fromDao = dao.getDVD(dvd.getDVDId());
        assertEquals(dvd, fromDao);
    }

    /**
     * Test of removeDVD method, of class DVDLibraryDao.
     */
// add DVDs, remove them, then check to see if they're there  
    @Test
    public void testRemoveDVD() throws Exception {
        DVD dvd1 = new DVD(1);
        dvd1.setTitle("Movie1");
        dvd1.setStudio("Studio1");
        dvd1.setReleaseDate(LocalDate.parse("2005-01-01"));
        dvd1.setMpaaRating("PG");
        dvd1.setDirectorName("Director");
        dao.addDVD(dvd1.getDVDId(), dvd1);

        DVD dvd2 = new DVD(2);
        dvd2.setTitle("Movie2");
        dvd2.setStudio("Studio2");
        dvd2.setReleaseDate(LocalDate.parse("2007-01-01"));
        dvd2.setMpaaRating("G");
        dvd2.setDirectorName("Director2");
        dao.addDVD(dvd2.getDVDId(), dvd2);

        dao.removeDVD(dvd1.getDVDId());
        assertEquals(1, dao.getAllDVD().size());
        assertNull(dao.getDVD(dvd1.getDVDId()));

        dao.removeDVD(dvd2.getDVDId());
        assertEquals(0, dao.getAllDVD().size());
        assertNull(dao.getDVD(dvd2.getDVDId()));
    }

    /**
     * Test of getDVDByTitle method, of class DVDLibraryDao.
     */
    @Test
    public void testGetDVDByTitle() throws Exception {
    }

    /**
     * Test of saveEdits method, of class DVDLibraryDao.
     */
    @Test
    public void testSaveEdits() throws Exception {
    }

    @Test
    public void testMoviesByMpaa() throws Exception {
        DVD dvd1 = new DVD(1);
        dvd1.setTitle("Movie1");
        dvd1.setStudio("Studio1");
        dvd1.setReleaseDate(LocalDate.parse("2005-01-01"));
        dvd1.setMpaaRating("PG");
        dvd1.setDirectorName("Director");
        dao.addDVD(dvd1.getDVDId(), dvd1);

        DVD dvd2 = new DVD(2);
        dvd2.setTitle("Movie2");
        dvd2.setStudio("Studio2");
        dvd2.setReleaseDate(LocalDate.parse("2007-01-01"));
        dvd2.setMpaaRating("G");
        dvd2.setDirectorName("Director2");
        dao.addDVD(dvd2.getDVDId(), dvd2);

        assertEquals(1, dao.moviesByMpaa("PG").size());
        assertEquals(1, dao.moviesByMpaa("G").size());
        assertEquals(0, dao.moviesByMpaa("R").size());
    }

    @Test
    public void testMoviesByDirector() throws Exception {

    }

    @Test
    public void testMoviesByStudio() throws Exception {

    }

}
