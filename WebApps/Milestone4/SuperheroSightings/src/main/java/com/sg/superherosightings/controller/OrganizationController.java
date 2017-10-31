/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.commandmodel.OrganizationCommandModel;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperPerson;
import com.sg.superherosightings.service.LocationService;
import com.sg.superherosightings.service.OrganizationService;
import com.sg.superherosightings.service.SuperPersonService;
import com.sg.superherosightings.viewmodel.OrganizationViewModel;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author James
 */
@Controller
public class OrganizationController {

    @Inject
    OrganizationService organizationService;
    @Inject
    LocationService locationService;
    @Inject
    SuperPersonService superPersonService;

    public OrganizationController() {
    }

    @RequestMapping(value = "/organization/organizations", method = RequestMethod.GET)
    public String displaySightingsPage(Model model) {

        List<OrganizationViewModel> ovmList = organizationService.getOrganizationViewModels(0, 10);
        model.addAttribute("ovmList", ovmList);

        return "/organization/organizations";
    }

    @RequestMapping(value = "/organization/chooseOrganization", method = RequestMethod.GET)
    public String displayOrganizationsPageWithSelectedOrganization(Model model, HttpServletRequest request, @RequestParam String organizationClicked) {
        //thinking getSightingViewModelBySightingId -- new method for sightingService
        OrganizationViewModel ovm = organizationService.getOrganizationViewModelByOrganizationId(Integer.parseInt(organizationClicked));
        model.addAttribute("ovm", ovm);

        //when we redirect, how do we send new information to the model?
        List<OrganizationViewModel> ovmList = organizationService.getOrganizationViewModels(0, 10);
        model.addAttribute("ovmList", ovmList);

        return "/organization/organizations";
    }

    @RequestMapping(value = "organization/displayCreateOrganizationPage", method = RequestMethod.POST)
    public String displayCreateOrganizationPage(Model model) {

        model.addAttribute("locations", locationService.getAllLocations(0, Integer.MAX_VALUE));
        List<SuperPerson> sps = superPersonService.getAllSuperPersons(0, Integer.MAX_VALUE);
        model.addAttribute("superPersons", sps);

        return "organization/create_organization";
    }

    @RequestMapping(value = "organization/createOrganization", method = RequestMethod.POST)
    public String createOrganization(@Valid @ModelAttribute("ocm") OrganizationCommandModel ocm,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("locations", locationService.getAllLocations(0, Integer.MAX_VALUE));
            List<SuperPerson> sps = superPersonService.getAllSuperPersons(0, Integer.MAX_VALUE);
            model.addAttribute("superPersons", sps);
            return "organization/create_organization";
        }

        Organization organizationToCreate = new Organization();

        Location organizationLocation = locationService.getLocationById(ocm.getLocationId());
        organizationToCreate.setLocation(organizationLocation);

        organizationToCreate.setPhone(ocm.getPhone());
        organizationToCreate.setName(ocm.getName());
        organizationToCreate.setDescription(ocm.getDescription());

        String reputation = ocm.getIsGood();

        if (reputation == null) {
        } else if (reputation.equals("good")) {
            organizationToCreate.setIsGood(Boolean.TRUE);
        } else {
            organizationToCreate.setIsGood(Boolean.FALSE);
        }

        Organization createdOrganization = organizationService.createOrganization(organizationToCreate);
        for (Integer superPersonId : ocm.getSuperPersons()) {
            superPersonService.addSuperPersonToOrganization(superPersonId, createdOrganization.getOrganizationId());
        }
        return "redirect:/organization/organizations";
    }

    //display the delete page
    @RequestMapping(value = "organization/delete_organization", method = RequestMethod.GET)
    public String displayDeleteOrganizationPage(Model model, HttpServletRequest request, @RequestParam Integer organizationToDelete) {
        model.addAttribute("organizationToDelete", organizationToDelete);
        return "organization/delete_organization";
    }

    //deleteOrganization
    @RequestMapping(value = "organization/deleteOrganization", method = RequestMethod.POST)
    public String deleteOrganization(@RequestParam(value = "organizationToDelete") String organizationToDelete, Model model) {
        organizationService.deleteOrganization(organizationService.getOrganizationById(Integer.parseInt(organizationToDelete)));
        return "redirect:/organization/organizations";
    }

    @RequestMapping(value = "organization/redirectToOrganizationsPage", method = RequestMethod.GET)
    public String redirectToOrganizationsPage(Model model) {
        return "redirect:/organization/organizations";
    }

    @RequestMapping(value = "organization/displayUpdateOrganizationPage", method = RequestMethod.GET)
    public String displayUpdateOrganizationPage(Model model, HttpServletRequest request, @RequestParam Integer organizationToUpdate) {
        OrganizationViewModel ovm = organizationService.getOrganizationViewModelByOrganizationId(organizationToUpdate);
        model.addAttribute("ovm", ovm);

        List<SuperPerson> allSuperPersons = superPersonService.getAllSuperPersons(0, Integer.MAX_VALUE);
        model.addAttribute("allSuperPersons", allSuperPersons);

        List<Location> allLocations = locationService.getAllLocations(0, Integer.MAX_VALUE);
        model.addAttribute("allLocations", allLocations);

        return "organization/update_organization";
    }

    @RequestMapping(value = "organization/updateOrganization", method = RequestMethod.POST)
    public String updateOrganization(@Valid @ModelAttribute("ocm") OrganizationCommandModel ocm,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("locations", locationService.getAllLocations(0, Integer.MAX_VALUE));
            List<SuperPerson> sps = superPersonService.getAllSuperPersons(0, Integer.MAX_VALUE);
            model.addAttribute("superPersons", sps);
            return "organization/update_organization";
        }

        Integer idForUpdate = ocm.getOrganizationId();
        Organization orgToUpdate = organizationService.getOrganizationById(idForUpdate);

        //but should this go before or after the service update method?
        //need to reset the associations btw the org and its superpersons
        //1. get all the superpersons originally on the dto
        OrganizationViewModel ovm = organizationService.getOrganizationViewModelByOrganizationId(idForUpdate);
        List<SuperPerson> originalSuperPersons = ovm.getSuperPersons();
        //2. remove associations with org
        for (SuperPerson currentSp : originalSuperPersons) {
            superPersonService.deleteOrganizationFromSuperPerson(currentSp.getSuperPersonId(), idForUpdate);
        }
        //3. add new assocations with org
        int[] newSuperPersons = ocm.getSuperPersons();
        for (Integer currentId : newSuperPersons) {
            superPersonService.addSuperPersonToOrganization(currentId, idForUpdate);
        }

        Location organizationLocation = locationService.getLocationById(ocm.getLocationId());
        orgToUpdate.setLocation(organizationLocation);

        orgToUpdate.setPhone(ocm.getPhone());
        orgToUpdate.setName(ocm.getName());
        orgToUpdate.setDescription(ocm.getDescription());

        String reputation = ocm.getIsGood();

        if (reputation == null) {
        } else if (reputation.equals("good")) {
            orgToUpdate.setIsGood(Boolean.TRUE);
        } else {
            orgToUpdate.setIsGood(Boolean.FALSE);
        }

        Organization updatedOrganization = organizationService.updateOrganization(orgToUpdate);

        return "redirect:/organization/organizations";
    }
}
