/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */

/*
The Service Layer is not responsible for storing or retrieving Item objects – 
that is the job of the DAO. This means  we don’t want to test the persistence 
of Item objects when testing the Service Layer – we only want to test the business 
rules and the integration between the Service Layer and the DAO. Given this fact, 
we can use stubbed versions of our DAOs to test the functionality of the Service Layer. 
*/

public class VendingMachineDaoStubImpl implements VendingMachineDao {
// member field of type Item – this represents the one and only Item in the DAO   

    private Item onlyItem;
// member field of type List<Item> - this is a List containing the one and only Item    
    private List<Item> itemList = new ArrayList<>();
// Constructor – create and initialize the one and only Item and add the Item to our List   

    public VendingMachineDaoStubImpl() {
        onlyItem = new Item(1);
        onlyItem.setItemName("item1");
        onlyItem.setItemPrice(new BigDecimal(".50"));
        onlyItem.setItemQuantity(5);

        itemList.add(onlyItem);
    }

// If the itemSlot being requested equals that of the one and only Item, then it 
// returns a reference to the one and only Item.  Otherwise, it returns null. 
    @Override
    public Item getItemBySlot(Integer itemSlot) throws VendingMachineDaoException {
        if (itemSlot.equals(onlyItem.getItemSlot())) {
            return onlyItem;
        } else {
            return null;
        }
    }

// returns the List containing the one and only Item 
    @Override
    public List<Item> getAllItems() throws VendingMachineDaoException {
        return itemList;
    }

// if the itemSlot of the Item being added equals that of the one and only Item in the DAO, 
// it returns a reference to the one and only Item in the DAO. Otherwise, it returns null. 
    @Override
    public Item addItem(Integer itemSlot, Item item) throws VendingMachineDaoException {
        if (itemSlot.equals(onlyItem.getItemSlot())) {
            return onlyItem;
        } else {
            return null;
        }
    }

// If the itemSlot passed in equals that of the one and only Item, then it 
// returns a reference to the one and only Item. Otherwise, it returns null
    @Override
    public Item removeItem(Integer itemSlot) throws VendingMachineDaoException {
        if (itemSlot.equals(onlyItem.getItemSlot())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public void editItem(Item item) throws VendingMachineDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadContents() throws VendingMachineDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeContents() throws VendingMachineDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}