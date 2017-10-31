/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

/**
 *
 * @author James
 */
public class VendingMachineDaoException extends Exception {

// allows me to create my own exceptions
    public VendingMachineDaoException(String message) {
// super calls Exception and borrows its constructor
        super(message);
    }

    public VendingMachineDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
