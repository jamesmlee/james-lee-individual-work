/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.dao.VendingMachineNoItemInventoryException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author James
 */
public class VendingMachineController {

// these are members of VendingMachineController
    private VendingMachineView view;
    private VendingMachineServiceLayer service;
// ***get rid of auditDao after bringing in Spring***
// auditDao temporarily placed in Controller to see if audit worked as expected    
//    private VendingMachineAuditDao auditDao;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view/*,
            VendingMachineAuditDao auditDao*/){      
        this.service = service;
        this.view = view;
//        this.auditDao = auditDao;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
            
                loadContents();    
                view.displayItemList(service.getAllItems(), service.getTotalMoney());
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listAllItems();
                        break;
                    case 2:
                        addMoney();
                        break;
                    case 3:
                        makePurchase();
                        break;
                    case 4:
                        returnChange();
                    case 5:
                        // exit
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
// needs to catch every exception            
        } catch (VendingMachineDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void loadContents() throws VendingMachineDaoException {
        service.loadContents();
    }

    private int getMenuSelection() throws VendingMachineDaoException {
        return view.printMenuAndGetSelection();
    }

    private void listAllItems() throws VendingMachineDaoException {
        view.displayDisplayAllBanner();
        List<Item> itemList = service.getAllItems();
        view.displayItemList(itemList, service.getTotalMoney());
    }

    private void addMoney() throws VendingMachineDaoException {
        BigDecimal insertedMoney = view.getMoneyInserted();
        service.insertMoney(insertedMoney);
    }
    
    private void makePurchase() throws VendingMachineDaoException {
        view.displayItemList(service.getAllItems(), service.getTotalMoney());
        Integer itemSelection = view.showItemOptions();
        try {    
        service.purchaseItem(itemSelection);
        service.returnChange();
        } catch
            (VendingMachineNoItemInventoryException e) {
                view.displayErrorMessage(e.getMessage()); 
//                auditDao.writeAuditEntry("ERROR. VendingMachineNoItemInventoryException was thrown. "
//                        + "Attempted to purchase: " );
        } catch
            (VendingMachineInsufficientFundsException e) {
                view.displayErrorMessage(e.getMessage());
//               auditDao.writeAuditEntry("ERROR. VendingMachineInsufficientFundsException was thrown. "
//                        + "Attempted to purchase: ");
            }
        }
    
    private void returnChange() throws VendingMachineDaoException {
        Change returnChange = service.returnChange();
        view.printChange(returnChange);
    // set totalMoney to 0 after returning change
        service.setTotalMoney(new BigDecimal("0"));
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
    
}