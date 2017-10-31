/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.controller.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.controller.NoItemInventoryException;
import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author James
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    Integer userChoice = null;


    @Override
    public Integer getUserChoice() {
        return userChoice;
    }

    @Override
    public void setUserChoice(Integer userChoice) {
        this.userChoice = userChoice;
    }

    private BigDecimal totalMoney = new BigDecimal("0");
    private VendingMachineDao dao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

// assume enough $ and sufficient stock    
    @Override
    public Change returnChange() {
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

        totalMoney = new BigDecimal("0");
        userChoice = null;
        return change;
    }

// need another method later: set $ to 0 after returning change        
//        moneyInPenniesInt = 0;
//        currentMoney = new BigDecimal("0");    
    @Override
    public BigDecimal insertMoney(BigDecimal moneyInserted) {
        totalMoney = totalMoney.add(moneyInserted);
        return totalMoney;
    }

    @Override
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

// used to set amount of $ user has
// call method to set to 0 after returning change
// call method to set new total after adding more $    
    @Override
    public void setTotalMoney(BigDecimal inputMoney) {
        totalMoney = inputMoney;
    }

    @Override
    public Change purchaseItem(Integer itemSelection) throws NoItemInventoryException, InsufficientFundsException {
        Item chosenItem = getItemBySlot(itemSelection);
        BigDecimal moneyShort = chosenItem.getItemPrice().subtract(totalMoney);

        if (chosenItem.getItemQuantity() < 1) {
            throw new NoItemInventoryException("SOLD OUT!");
        } // -1 ... if totalMoney < ItemPrice --> not enough $   
        else if (totalMoney.compareTo(chosenItem.getItemPrice()) == -1) {
            throw new InsufficientFundsException("Please enter $" + moneyShort);
        } // 1 ... if totalMoney > ItemPrice --> buy        
        else if (totalMoney.compareTo(chosenItem.getItemPrice()) == 1
                || totalMoney.compareTo(chosenItem.getItemPrice()) == 0) {
            totalMoney = totalMoney.subtract(chosenItem.getItemPrice());
            decrementItem(chosenItem);
        }

        return returnChange();
    }

    @Override
    public void decrementItem(Item chosenItem) {
        chosenItem.setItemQuantity(chosenItem.getItemQuantity() - 1);
    }

// pass through methods from DAO
    @Override
    public Item getItemBySlot(Integer itemSlot) {
        return dao.getItemBySlot(itemSlot);
    }

    @Override
    public List<Item> getAllItems() {
        return dao.getAllItems();
    }

}
