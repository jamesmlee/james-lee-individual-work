/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author James
 */

public class Item {
// key
    private Integer itemSlot;
// values
    private String itemName;
    private BigDecimal itemPrice;
    private Integer itemQuantity;

// @Override b/c toString() is a method on the base class Object    
    @Override
// only logging purchaseItem() and key
// b/c purchaseItem() doesn't take in an Item object as a param, nor return one
// the proxy object (that Spring creates when a opintcut is hit) couldn't see anything about an Item
// A proxy object is an object that intercepts the call from the call stack
    public String toString() {
        return "Item Requested: " + itemName;
    }

// pass itemSlot as param to constructor; itemSlot is read-only, so no setter 
    public Item(Integer itemSlot) {
        this.itemSlot = itemSlot;
    }

    public Integer getItemSlot() {
        return itemSlot;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.itemSlot);
        hash = 29 * hash + Objects.hashCode(this.itemName);
        hash = 29 * hash + Objects.hashCode(this.itemPrice);
        hash = 29 * hash + Objects.hashCode(this.itemQuantity);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemSlot, other.itemSlot)) {
            return false;
        }
        if (!Objects.equals(this.itemPrice, other.itemPrice)) {
            return false;
        }
        if (!Objects.equals(this.itemQuantity, other.itemQuantity)) {
            return false;
        }
        return true;
    }
    
}
