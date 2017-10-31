/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.interestcalculatorjspservlet;

import java.math.BigDecimal;

/**
 *
 * @author James
 */
public class DTO {
  
    Integer year;
    BigDecimal beginningBalance;
    BigDecimal interestForYear;
    BigDecimal endingBalance; 

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getBeginningBalance() {
        return beginningBalance;
    }

    public void setBeginningBalance(BigDecimal beginningBalance) {
        this.beginningBalance = beginningBalance;
    }

    public BigDecimal getInterestForYear() {
        return interestForYear;
    }

    public void setInterestForYear(BigDecimal interestForYear) {
        this.interestForYear = interestForYear;
    }

    public BigDecimal getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(BigDecimal endingBalance) {
        this.endingBalance = endingBalance;
    }
    
    
}
