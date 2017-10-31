/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author James
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public static final String CONTENTS_FILE = "CONTENTS_FILE.txt";
    public static final String DELIMITER = "::";

    private Map<Integer, Item> myMap = new HashMap();
    
    @Override
    public Item getItemBySlot(Integer itemSlot) throws VendingMachineDaoException {
        return myMap.get(itemSlot);
    }

// return myMap.get(itemSlot);    
    @Override
    public List<Item> getAllItems() throws VendingMachineDaoException {
        loadContents();
        return new ArrayList<Item>(myMap.values());
    }

    @Override
    public Item addItem(Integer itemSlot, Item item) throws VendingMachineDaoException {
        Item newItem = myMap.put(itemSlot, item);
        return newItem;
    }

    @Override
    public Item removeItem(Integer itemSlot) throws VendingMachineDaoException {
        Item removedItem = myMap.remove(itemSlot);
        return removedItem;
    }

    @Override
    public void editItem(Item item) throws VendingMachineDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void loadContents() throws VendingMachineDaoException {
        Scanner scanner;

        try {
// Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(CONTENTS_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoException("Could not load vending machine contents.", e);
        }
// currentLine holds the most recent line read from the file
        String currentLine;
// currentTokens holds each of the parts of the currentLine after it has been split on our DELIMITER
        String[] currentTokens;
// Go through CONTENTS_FILE line by line, decoding each line into an Item object.
// Process while we have more lines in the file
        while (scanner.hasNextLine()) {
// get the next line in the file
            currentLine = scanner.nextLine();
// break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
// Create a new Item object and put it into the map of Items
// need to parseInt because split() splits line into an array of strings
            Item currentItem = new Item(Integer.parseInt(currentTokens[0]));
// Set the remaining values on currentItem manually
            currentItem.setItemName(currentTokens[1]);
            currentItem.setItemPrice(new BigDecimal(currentTokens[2]));
            currentItem.setItemQuantity(Integer.parseInt(currentTokens[3]));
// Put currentItem into the map using itemSlot as the key
            myMap.put(currentItem.getItemSlot(), currentItem);
        }
        scanner.close();
    }

    @Override
    public void writeContents() throws VendingMachineDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(CONTENTS_FILE));
        } catch (IOException e) {
            throw new VendingMachineDaoException(
                    "Could not save vending machine data.", e);
        }
        List<Item> ItemList = this.getAllItems();
        for (Item currentItem : ItemList) {
            out.println(currentItem.getItemSlot() + DELIMITER
                    + currentItem.getItemName()+ DELIMITER
                    + currentItem.getItemPrice()+ DELIMITER
                    + currentItem.getItemQuantity()+ DELIMITER);
            out.flush();
        }
        out.close();
    }

}
