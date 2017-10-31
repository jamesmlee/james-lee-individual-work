/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperPerson;
import com.sg.superherosightings.viewmodel.OrganizationViewModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author James
 */
public class OrganizationServiceImpl implements OrganizationService {

    OrganizationDao organizationDao;
    SuperPersonService superPersonService;
    LocationService locationService;
    AddressService addressService;

    public OrganizationServiceImpl(OrganizationDao organizationDao, SuperPersonService superPersonService,
            LocationService locationService, AddressService addressService) {
        this.organizationDao = organizationDao;
        this.superPersonService = superPersonService;
        this.locationService = locationService;
        this.addressService = addressService;
    }

    @Override
    public Organization createOrganization(Organization organization) {
        return organizationDao.createOrganization(organization);
    }

    @Override
    public Organization getOrganizationById(Integer organizationId) {
        return organizationDao.getOrganizationById(organizationId);
    }

    @Override
    public List<Organization> getAllOrganizations(int offset, int limit) {
        return organizationDao.getAllOrganizations(offset, limit);
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        return organizationDao.updateOrganization(organization);
    }

    @Override
    public Organization deleteOrganization(Organization organization) {
        List<SuperPerson> superPersonsAtOrganization = superPersonService.
                getAllSuperPersonsByOrganization(organization, 0, Integer.MAX_VALUE);
        for (SuperPerson currentSuperPerson : superPersonsAtOrganization) {
            superPersonService.deleteOrganizationFromSuperPerson(currentSuperPerson, organization);
        }
        return organizationDao.deleteOrganization(organization);
    }

    @Override
    public List<Organization> getAllOrganizationsBySuperPerson(SuperPerson superPerson, int offset, int limit) {
        return organizationDao.getAllOrganizationsBySuperPerson(superPerson, offset, limit);
    }

// similar to Sighting ... no Description, but include Address
    @Override
    public List<OrganizationViewModel> getOrganizationViewModels(int offset, int limit) {
        List<OrganizationViewModel> ovmList = new ArrayList();
        List<Organization> viewOrganizations = getAllOrganizations(offset, limit);

        for (int i = 0; i < viewOrganizations.size(); i++) {
            // Make new Model object for each iteration
            OrganizationViewModel currentModel = new OrganizationViewModel();
            // Get the current organization and set it on the model
            Organization currentOrganization = viewOrganizations.get(i);
            currentModel.setOrganization(currentOrganization);
            // Get the current LocationID
            Integer currentLocationId = currentOrganization.getLocation().getLocationId();
            // Get the current Location and set it on the model
            Location currentLocation = locationService.getLocationById(currentLocationId);
            currentModel.setLocation(currentLocation);
            // Get the current AddressId
            Integer currentAddressId = currentModel.getLocation().getAddress().getAddressId();
            // Get the current Address and set it on the model
            Address currentAddress = addressService.getAddressById(currentAddressId);
            currentModel.setAddress(currentAddress);
            // Get a list of super persons at the organization and set it on the model
            List<SuperPerson> superPersonsAtOrganization = superPersonService.getAllSuperPersonsByOrganization(
                    currentModel.getOrganization(), 0, 10);
            currentModel.setSuperPersons(superPersonsAtOrganization);
            // add the current model to the list of models
            ovmList.add(currentModel);
        }
        // return the list of models
        return ovmList;
    }

    @Override
    public OrganizationViewModel getOrganizationViewModelByOrganizationId(Integer organizationId) {
        OrganizationViewModel ovm = new OrganizationViewModel();
        Organization organization = getOrganizationById(organizationId);

        ovm.setOrganization(organization);
        Integer locationId = organization.getLocation().getLocationId();
        Location location = locationService.getLocationById(locationId);
        ovm.setLocation(location);

        Integer addressId = location.getAddress().getAddressId();
        Address address = addressService.getAddressById(addressId);
        ovm.setAddress(address);

        List<SuperPerson> superPersonsAtOrganization = superPersonService.getAllSuperPersonsByOrganization(
                ovm.getOrganization(), 0, 10);
        ovm.setSuperPersons(superPersonsAtOrganization);

        return ovm;
    }

}
