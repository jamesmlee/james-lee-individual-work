/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.dao.VendingMachineNoItemInventoryException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author James
 */
// At a minimum you should have the following application specific exceptions 
// thrown by your Service Layer: InsufficientFundsException, NoItemInventoryException
public interface VendingMachineServiceLayer {

    public Change returnChange() throws VendingMachineDaoException;
    // in stock         
    // sufficient funds
    // change BigDecimal
    // change in pennies (used

    public BigDecimal insertMoney(BigDecimal moneyInserted) throws VendingMachineDaoException;

    public BigDecimal getTotalMoney() throws VendingMachineDaoException;
    
    public void setTotalMoney(BigDecimal inputMoney) throws VendingMachineDaoException;
    
    public void purchaseItem(Integer itemSelection) throws VendingMachineDaoException, 
            VendingMachineNoItemInventoryException, VendingMachineInsufficientFundsException;
    
    public void decrementItem(Item chosenItem) throws VendingMachineDaoException;

// pass through methods from DAO
    // decrement quantity in getItemBySlot    
    public Item getItemBySlot(Integer itemSlot) throws VendingMachineDaoException;

    public List<Item> getAllItems() throws VendingMachineDaoException;
    
    public void loadContents() throws VendingMachineDaoException;

}
