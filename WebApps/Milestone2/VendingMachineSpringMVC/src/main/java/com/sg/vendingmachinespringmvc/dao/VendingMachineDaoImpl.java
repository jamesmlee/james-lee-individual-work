/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author James
 */
public class VendingMachineDaoImpl implements VendingMachineDao {

    private Map<Integer, Item> myMap = new HashMap<>();

    public VendingMachineDaoImpl() {

        Item item1 = new Item(1);
        item1.setItemName("Snickers");
        item1.setItemPrice(new BigDecimal("1.50"));
        item1.setItemQuantity(10);
        myMap.put(item1.getItemSlot(), item1);

        Item item2 = new Item(2);
        item2.setItemName("M&Ms");
        item2.setItemPrice(new BigDecimal("1.25"));
        item2.setItemQuantity(8);
        myMap.put(item2.getItemSlot(), item2);

        Item item3 = new Item(3);
        item3.setItemName("Almond Joy");
        item3.setItemPrice(new BigDecimal("1.25"));
        item3.setItemQuantity(11);
        myMap.put(item3.getItemSlot(), item3);

        Item item4 = new Item(4);
        item4.setItemName("Milky Way");
        item4.setItemPrice(new BigDecimal("1.65"));
        item4.setItemQuantity(3);
        myMap.put(item4.getItemSlot(), item4);

        Item item5 = new Item(5);
        item5.setItemName("Payday");
        item5.setItemPrice(new BigDecimal("1.75"));
        item5.setItemQuantity(0);
        myMap.put(item5.getItemSlot(), item5);

        Item item6 = new Item(6);
        item6.setItemName("Reese's");
        item6.setItemPrice(new BigDecimal("1.50"));
        item6.setItemQuantity(5);
        myMap.put(item6.getItemSlot(), item6);

        Item item7 = new Item(7);
        item7.setItemName("Pringles");
        item7.setItemPrice(new BigDecimal("2.35"));
        item7.setItemQuantity(4);
        myMap.put(item7.getItemSlot(), item7);

        Item item8 = new Item(8);
        item8.setItemName("Cheezits");
        item8.setItemPrice(new BigDecimal("2.00"));
        item8.setItemQuantity(6);
        myMap.put(item8.getItemSlot(), item8);

        Item item9 = new Item(9);
        item9.setItemName("Doritos");
        item9.setItemPrice(new BigDecimal("1.95"));
        item9.setItemQuantity(7);
        myMap.put(item9.getItemSlot(), item9);
    }

    @Override
    public Item getItemBySlot(Integer itemSlot) {
        return myMap.get(itemSlot);
    }

// return myMap.get(itemSlot);    
    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(myMap.values());
    }

    @Override
    public Item addItem(Integer itemSlot, Item item) {
        Item newItem = myMap.put(itemSlot, item);
        return newItem;
    }

    @Override
    public Item removeItem(Integer itemSlot) {
        Item removedItem = myMap.remove(itemSlot);
        return removedItem;
    }

    @Override
    public Item editItem(Item item) {
        Item editedItem = myMap.put(item.getItemSlot(), item);
        return editedItem;
    }

}
