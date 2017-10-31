/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author James
 */
public class DvdDaoTest {

    private DvdDao dao;

    public DvdDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

// getting a new instance of a dao every time we run a test    
    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
// name of bean must match what's in spring-persistence.xml
        dao = ctx.getBean("dvdLibraryDao", DvdDao.class);

        // delete all books
        List<Dvd> dvds = dao.getAllDvds();
        for (Dvd currentDvd : dvds) {
            dao.deleteDvd(currentDvd.getId());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDvd() {
        Dvd dvd = new Dvd();
        dvd.setTitle("Movie1");
        dvd.setReleaseDate(LocalDate.parse("2016-01-01"));
        dvd.setMpaaRating("PG");
        dvd.setDirector("Spielberg");
        dvd.setStudio("Disney");
        dvd.setNotes("good movie");

        dao.addDvd(dvd);

        Dvd fromDao = dao.getDvdById(dvd.getId());
        assertEquals(fromDao, dvd);
    }

    @Test
    public void deleteDvd() {
        Dvd dvd = new Dvd();
        dvd.setTitle("Movie1");
        dvd.setReleaseDate(LocalDate.parse("2016-01-01"));
        dvd.setMpaaRating("PG");
        dvd.setDirector("Spielberg");
        dvd.setStudio("Disney");
        dvd.setNotes("good movie");

        dao.addDvd(dvd);

        Dvd fromDao = dao.getDvdById(dvd.getId());
        assertEquals(fromDao, dvd);
        dao.deleteDvd(dvd.getId());
        assertNull(dao.getDvdById(dvd.getId()));
    }

}
