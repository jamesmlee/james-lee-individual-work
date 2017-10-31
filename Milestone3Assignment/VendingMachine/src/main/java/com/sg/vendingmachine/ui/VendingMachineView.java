/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author James
 */
public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List All Items");
        io.print("2. Add Money");
        io.print("3. Purchase Item");
        io.print("4. Return Change");
        io.print("5. Walk Away From Vending Machine");

        int menuSelection = io.readInt("Please select from the above choices", 1, 5);

        return menuSelection;
    }

    public void displayItemList(List<Item> itemList, BigDecimal totalMoney) {
        for (Item currentItem : itemList) {
            io.print(currentItem.getItemSlot() + ": "
                    + currentItem.getItemName() + " "
                    + currentItem.getItemPrice() + " "
                    + currentItem.getItemQuantity());
        }
        io.print("Total money entered: " + totalMoney);
        io.readString("Please hit enter to continue.");
    }

    public void printChange(Change returnChange) {
        io.print("Your change is: " + returnChange.getTotalChange());
        io.print("Quarters returned " + returnChange.getNumQuarters());
        io.print("Dimes returned " + returnChange.getNumDimes());
        io.print("Nickels returned " + returnChange.getNumNickels());
        io.print("Pennies returned " + returnChange.getNumPennies());
    }

    public Integer showItemOptions() {
        Integer itemSelection = io.readInt("Please select one of the items ", 1, 6);
        return itemSelection;
    }

    public void displayDisplayAllBanner() {
        io.print("=== All The Stuff ===");
    }

    public void printUnknownCommand() {
        io.print("UNKNOWN COMMAND. Please try again.");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public BigDecimal getMoneyInserted() {
        BigDecimal moneyInserted = io.readBigDecimal("Please insert $ in format x.xx");
        io.print("You have inserted $" + moneyInserted);
        return moneyInserted;
    }

}
