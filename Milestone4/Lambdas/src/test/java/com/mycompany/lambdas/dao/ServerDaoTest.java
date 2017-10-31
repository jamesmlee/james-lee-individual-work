/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lambdas.dao;

import com.mycompany.lambdas.dto.Server;
import java.util.List;
import java.util.Map;
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
public class ServerDaoTest {
    
    public ServerDaoTest() {
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

    /**
     * Test of getServersByManufacturer method, of class ServerDao.
     */
    @Test
    public void testGetServersByManufacturer() {
// ARRANGE: need data to work on
        ServerDao dao = new ServerDaoInMemImpl();
// create 3 different Server objects        
        Server s1 = new Server();
        s1.setManufacturer("Dell");
        s1.setName("Server1");
        
        Server s2 = new Server();
        s2.setManufacturer("HP");
        s2.setName("Server2");

        Server s3 = new Server();
        s3.setManufacturer("Dell");
        s3.setName("Server3");
// add them to DAO        
        dao.addServer(s1);
        dao.addServer(s2);
        dao.addServer(s3);
// ACT      
        List<Server> results = dao.getServersByManufacturer("Dell");
// ASSERT
// 2 servers with Dell as manufacturer
        assertTrue(results.size() == 2);
// try looking through resuls and seeing if they're all Dell        
        for(Server s : results) {
            assertEquals("Dell", s.getManufacturer());
        }
        
    }

}
