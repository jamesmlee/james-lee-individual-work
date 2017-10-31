/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.controller.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.controller.NoItemInventoryException;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author James
 */
public interface VendingMachineServiceLayer {

    public Integer getUserChoice();

    public void setUserChoice(Integer itemSlot);

    public Change returnChange();
    // in stock         
    // sufficient funds
    // change BigDecimal
    // change in pennies (used

    public BigDecimal insertMoney(BigDecimal moneyInserted);

    public BigDecimal getTotalMoney();

    public void setTotalMoney(BigDecimal inputMoney);

    public Change purchaseItem(Integer itemSelection) throws NoItemInventoryException, InsufficientFundsException;

    public void decrementItem(Item chosenItem);

// pass through methods from DAO
    // decrement quantity in getItemBySlot    
    public Item getItemBySlot(Integer itemSlot);

    public List<Item> getAllItems();

}
