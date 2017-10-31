/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
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
public class VendingMachineDaoTest {

    private VendingMachineDao dao = new VendingMachineDaoFileImpl();

    public VendingMachineDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

// put dao in a known good state. our known good state is empty
// if you already have students, adding another determines total     
    @Before
    public void setUp() throws Exception {
// ask dao for all students        
        List<Item> itemList = dao.getAllItems();
// iterate through and remove all of them, leaving us with an empty dao
        for (Item item : itemList) {
// remove not working? 6 items in text file still there
            dao.removeItem(item.getItemSlot());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getItemBySlot method, of class VendingMachineDao.
     */
    @Test
    public void testGetItemBySlot() throws Exception {
        Item item1 = new Item(1);
        item1.setItemName("Item1");
        item1.setItemPrice(new BigDecimal(".50"));
        item1.setItemQuantity(5);
        dao.addItem(1, item1);

        Item fromDao = dao.getItemBySlot(item1.getItemSlot());
        assertEquals(item1, fromDao);
        
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
// also tests Add ... add 2 items; make sure we get 8 back (6 in file) 
    @Test
    public void testGetAllItems() throws Exception {
        Item item7 = new Item(7);
        item7.setItemName("Item7");
        item7.setItemPrice(new BigDecimal(".50"));
        item7.setItemQuantity(5);
        dao.addItem(7, item7);

        Item item8 = new Item(8);
        item8.setItemName("Item8");
        item8.setItemPrice(new BigDecimal("1.02"));
        item8.setItemQuantity(3);
        dao.addItem(8, item8);
// see if we get 8 back        
        assertEquals(8, dao.getAllItems().size());
    }

    /**
     * Test of removeItem method, of class VendingMachineDao.
     */
// add items, remove them, then check to see if they're there     
    @Test
    public void testRemoveItem() throws Exception {
        Item item7 = new Item(7);
        item7.setItemName("Item7");
        item7.setItemPrice(new BigDecimal(".50"));
        item7.setItemQuantity(5);
        dao.addItem(7, item7);

        Item item8 = new Item(8);
        item8.setItemName("Item8");
        item8.setItemPrice(new BigDecimal("1.02"));
        item8.setItemQuantity(3);
        dao.addItem(8, item8);

        dao.removeItem(item8.getItemSlot());
        assertEquals(7, dao.getAllItems().size());
        assertNull(dao.getItemBySlot(8));

        dao.removeItem(item7.getItemSlot());
        assertEquals(6, dao.getAllItems().size());
        assertNull(dao.getItemBySlot(7));
    }
    
    /**
     * Test of editItem method, of class VendingMachineDao.
     */
    @Test
    public void testEditItem() throws Exception{
    }

}
