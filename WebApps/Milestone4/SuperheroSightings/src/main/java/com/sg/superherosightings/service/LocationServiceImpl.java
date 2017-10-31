/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.SuperPerson;
import com.sg.superherosightings.viewmodel.LocationViewModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author James
 */
public class LocationServiceImpl implements LocationService {

    LocationDao locationDao;
    SuperPersonService superPersonService;
    AddressService addressService;

    public LocationServiceImpl(LocationDao locationDao, SuperPersonService superPersonService,
            AddressService addressService) {
        this.locationDao = locationDao;
        this.superPersonService = superPersonService;
        this.addressService = addressService;
    }

    @Override
    public Location createLocation(Location location) {
        return locationDao.createLocation(location);
    }

    @Override
    public Location getLocationById(Integer locationId) {
        return locationDao.getLocationById(locationId);
    }

    @Override
    public List<Location> getAllLocations(int offset, int limit) {
        return locationDao.getAllLocations(offset, limit);
    }

    @Override
    public Location updateLocation(Location location) {
        return locationDao.updateLocation(location);
    }

    @Override
    public Location deleteLocation(Location location) {
        return locationDao.deleteLocation(location);
    }

    @Override
    public List<Location> getAllLocationsBySuperPerson(SuperPerson superPerson, int offset, int limit) {
        return locationDao.getAllLocationsBySuperPerson(superPerson, offset, limit);
    }

//    @Override
//    public List<Location> getAllLocationsBySuperPerson(Integer superPersonId, int offset, int limit) {
//        return locationDao.getAllLocationsBySuperPerson(superPersonId, offset, limit);
//    }
    @Override
    public LocationViewModel getLocationViewModelByLocationId(Integer locationClicked) {
        LocationViewModel lvm = new LocationViewModel();
        Location location = getLocationById(locationClicked);

        lvm.setLocation(location);

        Integer addressId = location.getAddress().getAddressId();

        Address address = addressService.getAddressById(addressId);
        lvm.setAddress(address);

        List<SuperPerson> superPersons = superPersonService.getAllSuperPersonsBySightingLocation(location, 0, 10);

        lvm.setSuperPersons(superPersons);

        return lvm;
    }

    @Override
    public List<LocationViewModel> getLocationViewModels(int offset, int limit) {
        List<LocationViewModel> lvmList = new ArrayList();
        List<Location> viewLocations = getAllLocations(offset, limit);

        for (int i = 0; i < viewLocations.size(); i++) {
            LocationViewModel currentModel = new LocationViewModel();
            Location currentLocation = viewLocations.get(i);
            currentModel.setLocation(currentLocation);
            lvmList.add(currentModel);
        }

        return lvmList;
    }

}
