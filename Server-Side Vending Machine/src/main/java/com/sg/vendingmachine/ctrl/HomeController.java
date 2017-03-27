/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ctrl;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.model.Item;
import com.sg.vendingmachine.serv.VendingMachineServ;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    private VendingMachineServ serv;

    @Inject
    public HomeController(VendingMachineServ serv) {
        this.serv = serv;
    }

    @RequestMapping
    public String runVendingMachine() throws VendingMachinePersistenceException {
        return "redirect:home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String displayHome(Model model) throws VendingMachinePersistenceException {
        
        List<Item> inventory = serv.getInventory();
        model.addAttribute("inventory", inventory);

        String total = serv.getDeposit().toString();
        model.addAttribute("total", total);

        String message = serv.getMessage();
        model.addAttribute("message", message);

        String selection = serv.getSelection();
        model.addAttribute("selection", selection);

        String change = serv.getChange();
        model.addAttribute("change", change);
        return "home";
    }
    

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String selectItem(HttpServletRequest request) throws VendingMachinePersistenceException {
        String barcode = request.getParameter("barcode");
        serv.selectItem(barcode);
        return "redirect:home";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String depositCoin(HttpServletRequest request) throws VendingMachinePersistenceException {
        String amount = request.getParameter("deposit");
        serv.depositFunds(amount);
        return "redirect:home";
    }

    @RequestMapping(value = "/vend", method = RequestMethod.POST)
    public String vendItem(HttpServletRequest request) throws VendingMachinePersistenceException {
        serv.vendItem();
        return "redirect:home";
    }
    
    @RequestMapping(value = "/change", method = RequestMethod.GET)
    public String returnChange() throws VendingMachinePersistenceException {
        serv.returnChange();
        return "redirect:home";
    }
}
