/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dao.VendingMachineDaoStubImpl;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
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
public class VendingMachineServiceLayerTest {

    private VendingMachineServiceLayer service;
    private VendingMachineDao dao;    
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerTest() {
//        dao = new VendingMachineDaoStubImpl();
//        auditDao = new VendingMachineAuditDaoStubImpl();
//        service = new VendingMachineServiceLayerImpl(dao, auditDao);

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        service
                = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
    }

    /*  
    By using stubbed DAOs for these tests, we have essentially defined the state 
    of system because we can’t change the state of the stubbed DAOs. That means 
    our code is already in a known good state – in this case, it means that the 
    VendingMachineDao contains exactly one Item.    
     */
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws VendingMachineDaoException {
        service.setTotalMoney(new BigDecimal("0"));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of returnChange method, of class VendingMachineServiceLayer.
     */
// put in a BigDecimal, return a Change object, and make sure it's what you expect  
    @Test
    public void testReturnChange() throws VendingMachineDaoException {
        service.setTotalMoney(new BigDecimal(".41"));
        Change change = service.returnChange();
        int q = change.getNumQuarters();
        assertEquals(q, 1);
        int d = change.getNumDimes();
        assertEquals(d, 1);
        int n = change.getNumNickels();
        assertEquals(n, 1);
        int p = change.getNumPennies();
        assertEquals(p, 1);
    }

    /**
     * Test of insertMoney method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testInsertMoney() throws VendingMachineDaoException {
        service.insertMoney(new BigDecimal(".50"));
        BigDecimal moneyInserted = service.getTotalMoney();
        assertEquals(moneyInserted, new BigDecimal(".50"));

        service.insertMoney(new BigDecimal("1.00"));
        moneyInserted = service.getTotalMoney();
        assertEquals(moneyInserted, new BigDecimal("1.50"));
    }

    /**
     * Test of getItemBySlot method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItemBySlot() throws VendingMachineDaoException {
    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAllItems() throws VendingMachineDaoException {
    }

    /**
     * Test of addItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testAddItem() throws VendingMachineDaoException {
    }

    /**
     * Test of removeItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testRemoveItem() throws VendingMachineDaoException {
    }

    /**
     * Test of editItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testEditItem() throws VendingMachineDaoException {
    }

}
