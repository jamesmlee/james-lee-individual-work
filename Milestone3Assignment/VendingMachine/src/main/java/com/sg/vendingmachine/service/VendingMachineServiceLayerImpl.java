/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
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

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
// this *might* set totalMoney to 0 when I call service in the controller
    private BigDecimal totalMoney = new BigDecimal("0");
    private VendingMachineDao dao;    
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }  
    
// assume enough $ and sufficient stock    
    @Override
    public Change returnChange() throws VendingMachineDaoException {
        Change change = new Change();
        BigDecimal moneyInPennies = totalMoney.multiply(new BigDecimal("100"));
        Integer moneyInPenniesInt = moneyInPennies.intValueExact();
        Integer remainingPennies = moneyInPenniesInt;

// to keep track of how much $ will be returned in change        
        change.setTotalChange(totalMoney);
        
        change.setNumQuarters(remainingPennies / 25);
        remainingPennies = remainingPennies % 25;
        change.setNumDimes(remainingPennies / 10);
        remainingPennies = remainingPennies % 10;
        change.setNumNickels(remainingPennies / 5);
        remainingPennies = remainingPennies % 5;
        change.setNumPennies(remainingPennies);
        
        return change;
    }
    
// need another method later: set $ to 0 after returning change        
//        moneyInPenniesInt = 0;
//        currentMoney = new BigDecimal("0");    

    @Override
    public BigDecimal insertMoney(BigDecimal moneyInserted) throws VendingMachineDaoException {
        totalMoney = totalMoney.add(moneyInserted);
        return totalMoney;
    }
    
    @Override
    public BigDecimal getTotalMoney() throws VendingMachineDaoException {
        return totalMoney;
    }

// used to set amount of $ user has
// call method to set to 0 after returning change
// call method to set new total after adding more $    
    @Override
    public void setTotalMoney(BigDecimal inputMoney) throws VendingMachineDaoException {
        totalMoney = inputMoney;
    }

//    @Override
//    public void purchaseItem(Integer itemSelection) throws VendingMachineDaoException,
//            VendingMachineNoItemInventoryException, VendingMachineInsufficientFundsException {
//        Item chosenItem = getItemBySlot(itemSelection);
//        BigDecimal moneyShort = chosenItem.getItemPrice().subtract(totalMoney);
    
    @Override
    public void purchaseItem(Integer itemSelection) throws VendingMachineDaoException, 
            VendingMachineNoItemInventoryException, VendingMachineInsufficientFundsException {
        Item chosenItem = getItemBySlot(itemSelection);
        BigDecimal moneyShort = chosenItem.getItemPrice().subtract(totalMoney);
        
        if(chosenItem.getItemQuantity() < 1) {
            throw new VendingMachineNoItemInventoryException ("ERROR. " + chosenItem.getItemName() + " is out of stock");
// -1 ... if totalMoney < ItemPrice --> not enough $            
        } 
        
        if (totalMoney.compareTo(chosenItem.getItemPrice()) == -1 ) {
            throw new VendingMachineInsufficientFundsException ("ERROR. Insufficient funds. Please enter $" + moneyShort + " to purchase " + chosenItem.getItemName());
// 1 ... if totalMoney > ItemPrice --> buy
        }
        if (totalMoney.compareTo(chosenItem.getItemPrice()) == 1 ) {
            totalMoney = totalMoney.subtract(chosenItem.getItemPrice());
            decrementItem(chosenItem);
        }
    }
    
    @Override
    public void decrementItem(Item chosenItem) throws VendingMachineDaoException {
        chosenItem.setItemQuantity(chosenItem.getItemQuantity() - 1);
        dao.writeContents();
    }
    
// pass through methods from DAO
    @Override
    public Item getItemBySlot(Integer itemSlot) throws VendingMachineDaoException{
        return dao.getItemBySlot(itemSlot);
    }

    @Override
    public List<Item> getAllItems() throws VendingMachineDaoException{
        return dao.getAllItems();
    }

    @Override
    public void loadContents() throws VendingMachineDaoException {
        dao.loadContents();
    }

}
