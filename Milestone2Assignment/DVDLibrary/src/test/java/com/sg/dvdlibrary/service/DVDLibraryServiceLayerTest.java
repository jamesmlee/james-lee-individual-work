/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryAuditDaoStubImpl;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoStubImpl;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.util.List;
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
public class DVDLibraryServiceLayerTest {

    private DVDLibraryServiceLayer service;

    public DVDLibraryServiceLayerTest() {
//        DVDLibraryDao dao = new DVDLibraryDaoStubImpl();
//        DVDLibraryAuditDao auditDao = new DVDLibraryAuditDaoStubImpl();
//
//        service = new DVDLibraryServiceLayerImpl(dao, auditDao);

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        service
                = ctx.getBean("serviceLayer", DVDLibraryServiceLayer.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddDVDDuplicateId() throws Exception {
        DVD dvd = new DVD(1);
        dvd.setTitle("Movie1");
        dvd.setStudio("Studio1");
        dvd.setReleaseDate(LocalDate.parse("2000-01-01"));
        dvd.setMpaaRating("R");
        dvd.setDirectorName("Director");

        try {
            service.addDVD(1, dvd);
            fail("Expected ClassRosterDuplicateIdException was not thrown.");
        } catch (DVDLibraryDuplicateIdException e) {
            return;
        }

    }

    /**
     * Test of getAllDVD method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testGetAllDVD() throws Exception {
    }

    /**
     * Test of addDVD method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testAddDVD() throws Exception {
    }

    /**
     * Test of getDVD method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testGetDVD() throws Exception {
    }

    /**
     * Test of removeDVD method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testRemoveDVD() throws Exception {
    }

    /**
     * Test of getDVDByTitle method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testGetDVDByTitle() throws Exception {
    }

    /**
     * Test of saveEdits method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testSaveEdits() throws Exception {
    }

    /**
     * Test of updateTitle method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testUpdateTitle() throws Exception {
    }

    /**
     * Test of updateReleaseDate method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testUpdateReleaseDate() throws Exception {
    }

    /**
     * Test of updateMPAA method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testUpdateMPAA() throws Exception {
    }

    /**
     * Test of updateDirectorName method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testUpdateDirectorName() throws Exception {
    }

    /**
     * Test of updateStudio method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testUpdateStudio() throws Exception {
    }

    /**
     * Test of updateNotes method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testUpdateNotes() throws Exception {
    }

    /**
     * Test of editDVD method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testEditDVD() throws Exception {
    }

}
