/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.service.LocationService;
import com.sg.superherosightings.viewmodel.LocationViewModel;
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
public class LocationController {

    @Inject
    LocationService locationService;

    public LocationController() {
    }

    @RequestMapping(value = "/location/locations", method = RequestMethod.GET)
    public String displayLocationsPage(Model model) {
        List<LocationViewModel> lvmList = locationService.getLocationViewModels(0, 10);
        model.addAttribute("lvmList", lvmList);

        return "/location/locations";
    }

    @RequestMapping(value = "/location/chooseLocation", method = RequestMethod.GET)
    public String displayLocationsPageWithSelectedLocation(Model model, HttpServletRequest request, @RequestParam Integer locationClicked) {
        LocationViewModel lvm = locationService.getLocationViewModelByLocationId(locationClicked);
        model.addAttribute("lvm", lvm);

        List<LocationViewModel> lvmList = locationService.getLocationViewModels(0, 10);
        model.addAttribute("lvmList", lvmList);

        return "/location/locations";
    }

    @RequestMapping(value = "location/displayCreateLocationPage", method = RequestMethod.POST)
    public String displayCreateLocationPage(Model model) {

        return "location/create_location";
    }
}
