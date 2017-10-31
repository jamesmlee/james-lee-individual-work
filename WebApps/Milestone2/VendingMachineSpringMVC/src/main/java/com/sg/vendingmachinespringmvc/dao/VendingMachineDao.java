/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.util.List;

/**
 *
 * @author James
 */
public interface VendingMachineDao {

    public Item getItemBySlot(Integer itemSlot);

    public List<Item> getAllItems();

// 3 CRUD methods    
    public Item addItem(Integer itemSlot, Item item);

    public Item removeItem(Integer itemSlot);

    public Item editItem(Item item);
    
}
