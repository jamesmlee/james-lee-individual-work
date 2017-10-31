/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author James
 */
public interface VendingMachineDao {
    public Item getItemBySlot(Integer itemSlot) throws VendingMachineDaoException;
    
    public List<Item> getAllItems() throws VendingMachineDaoException;
    
    public void loadContents() throws VendingMachineDaoException;
    
    public void writeContents() throws VendingMachineDaoException;

// 3 CRUD methods    
    public Item addItem(Integer itemSlot, Item item) throws VendingMachineDaoException;
    
    public Item removeItem(Integer itemSlot) throws VendingMachineDaoException;
    
    public void editItem(Item item) throws VendingMachineDaoException;
}
