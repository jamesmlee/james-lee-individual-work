/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperPerson;
import com.sg.superherosightings.service.LocationService;
import com.sg.superherosightings.service.SightingService;
import com.sg.superherosightings.service.SuperPersonService;
import com.sg.superherosightings.viewmodel.SightingViewModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author James
 */
@Controller
public class HomeController {

    SightingService sightingService;
    LocationService locationService;
    SuperPersonService superPersonService;
    //many more beans required....

    @Inject
    public HomeController(SightingService sightingService, LocationService locationService,
            SuperPersonService superPersonService) {
        this.sightingService = sightingService;
        this.locationService = locationService;
        this.superPersonService = superPersonService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHomePage(Model model){
        
        List<SightingViewModel> svmList = sightingService.getSightingViewModels(0,10);
        model.addAttribute("svmList",svmList);
//                model.addAttribute("superPersonsAtSightings", superPersonsAtSightings);
                
        return "/home/home";
    }
}
