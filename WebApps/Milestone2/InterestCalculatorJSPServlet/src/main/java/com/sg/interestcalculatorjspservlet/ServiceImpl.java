/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.interestcalculatorjspservlet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class ServiceImpl implements Service {

    List dtoList = new ArrayList();
    int yearCounter = 1;

// for a year    
    @Override
    public BigDecimal calcEndPrincipal(BigDecimal begPrincipal, BigDecimal quarterlyRate) {
        BigDecimal endPrincipal = begPrincipal;
        BigDecimal interestDecimal = quarterlyRate.divide(new BigDecimal("100"));
        BigDecimal multiplier = new BigDecimal("1").add(interestDecimal);

        for (int quarterCounter = 0; quarterCounter < 4; quarterCounter++) {
            endPrincipal = endPrincipal.multiply(multiplier);
        }

        return endPrincipal;
    }

    @Override
    public BigDecimal calcInterestEarned(BigDecimal begPrincipal, BigDecimal endPrincipal) {
        return endPrincipal.subtract(begPrincipal);
    }

    @Override
    public DTO fillDTO(DTO dto, BigDecimal begPrincipal, BigDecimal quarterlyRate) {
        dto.setYear(yearCounter);
        dto.setBeginningBalance(begPrincipal);
//        report.setStartingPrincipal(startingPrincipal);
//        BigDecimal endingPrincipal = calculateEndingPrincipalForYear(startingPrincipal, quarterlyInterestRate);
//        report.setEndingPrincipal(endingPrincipal);
//        BigDecimal interestEarned = calculateInterestEarnedForYear(startingPrincipal, endingPrincipal);
//        report.setInterestEarned(interestEarned);
//        currentYear++;

        return dto;
    }

    @Override
    public DTO addDTO(DTO filledDTO) {
        dtoList.add(filledDTO);
        return filledDTO;
    }

    @Override
    public List<DTO> getAllDTO() {
        return dtoList;
    }

}

/*
        System.out.println("How much $ do you want to invest");
        float principal = myScanner.nextFloat();
        System.out.println("What is the annual interest rate");
        float annualRate = myScanner.nextFloat();
        System.out.println("How many years do you want to invest for?");
        int numYears = myScanner.nextInt();
        
        float quarterlyRate = annualRate / 4;
        float interestAccrued = 0;
        float currentBalance = principal;
        int yearCounter = 0;
        
        float currPeriodInt = 0;
        
        for (int quarterCounter = 1; quarterCounter <= numYears * 4; quarterCounter++) {
            currPeriodInt = ((currentBalance * (1 + (quarterlyRate/100))) - currentBalance);
            interestAccrued += currPeriodInt;
            currentBalance += currPeriodInt;
            
            if (quarterCounter % 4 == 0) {
                yearCounter++;
                System.out.println("It is the end of year " + yearCounter);                
                System.out.println("Your balance at the beginning of the year was " + (currentBalance - interestAccrued));
                System.out.println("You've accrued " + interestAccrued + " in interest this year");
                System.out.println("Your current balance is " + currentBalance);
                interestAccrued = 0;
            }
        }
 */
