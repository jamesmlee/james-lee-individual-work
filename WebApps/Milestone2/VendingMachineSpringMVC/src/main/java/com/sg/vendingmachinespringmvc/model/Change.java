/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;

/**
 *
 * @author James
 */
public class Change {

    Integer numQuarters;
    Integer numDimes;
    Integer numNickels;
    Integer numPennies;
    BigDecimal totalChange;

    public BigDecimal getTotalChange() {
        return totalChange;
    }

    public void setTotalChange(BigDecimal totalChange) {
        this.totalChange = totalChange;
    }

    public Integer getNumQuarters() {
        return numQuarters;
    }

    public void setNumQuarters(Integer numQuarters) {
        this.numQuarters = numQuarters;
    }

    public Integer getNumDimes() {
        return numDimes;
    }

    public void setNumDimes(Integer numDimes) {
        this.numDimes = numDimes;
    }

    public Integer getNumNickels() {
        return numNickels;
    }

    public void setNumNickels(Integer numNickels) {
        this.numNickels = numNickels;
    }

    public Integer getNumPennies() {
        return numPennies;
    }

    public void setNumPennies(Integer numPennies) {
        this.numPennies = numPennies;
    }
}
