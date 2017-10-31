/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author James
 */
@Controller
public class VendingMachineController {

    VendingMachineServiceLayer service;

    @Inject
    public VendingMachineController(VendingMachineServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String displayIndex(Model model) {
        BigDecimal totalMoney = service.getTotalMoney();
        List<Item> itemList = service.getAllItems();
        Integer userChoice = service.getUserChoice();
        model.addAttribute("totalMoney", totalMoney);
        model.addAttribute("itemList", itemList);
        model.addAttribute("userChoice", userChoice);
        return "index";
    }

    @RequestMapping(value = {"/addMoney"}, method = RequestMethod.POST)
    public String addMoney(@RequestParam String money, HttpServletRequest request) {
        BigDecimal addAmount = new BigDecimal(money);
        service.insertMoney(addAmount);
        return "redirect:/";
    }

    @RequestMapping(value = {"/buyItem"}, method = RequestMethod.POST)
    public String buyItem(Model model, @RequestParam Integer itemNumber, HttpServletRequest request) {
        try {
            Change userChange = service.purchaseItem(itemNumber);
            int quarters = userChange.getNumQuarters();
            int dimes = userChange.getNumDimes();
            int nickels = userChange.getNumNickels();
            int pennies = userChange.getNumPennies();
            model.addAttribute("quarters", "Quarters: " + quarters);
            model.addAttribute("dimes", "Dimes: " + dimes);
            model.addAttribute("nickels", "Nickels: " + nickels);
            model.addAttribute("pennies", "Pennies: " + pennies);
            String messageText = "Thank you!";
            model.addAttribute("messageText", messageText);
        } catch (NoItemInventoryException e) {
            model.addAttribute("messageText", e.getMessage());
        } catch (InsufficientFundsException e) {
            model.addAttribute("messageText", e.getMessage());
        }

        return "redirect:/";
    }

    @RequestMapping(value = {"/changeReturn"}, method = RequestMethod.GET)
    public String changeReturn(Model model, HttpServletRequest request) {
        Change userChange = service.returnChange();
        int quarters = userChange.getNumQuarters();
        int dimes = userChange.getNumDimes();
        int nickels = userChange.getNumNickels();
        int pennies = userChange.getNumPennies();
        model.addAttribute("quarters", "Quarters: " + quarters);
        model.addAttribute("dimes", "Dimes: " + dimes);
        model.addAttribute("nickels", "Nickels: " + nickels);
        model.addAttribute("pennies", "Pennies: " + pennies);
        return "redirect:/";
    }

    @RequestMapping(value = {"/setNumber"}, method = RequestMethod.GET)
    public String setNumber(@RequestParam Integer userChoice, HttpServletRequest request) {
        service.setUserChoice(userChoice);
        return "redirect:/";
    }

}
