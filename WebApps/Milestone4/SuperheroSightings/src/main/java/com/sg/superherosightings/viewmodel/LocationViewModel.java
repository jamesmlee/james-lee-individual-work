/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.viewmodel;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.SuperPerson;
import java.util.List;

/**
 *
 * @author James
 */
public class LocationViewModel {
    private Location location;
    private Address address;
    private List<SuperPerson> superPersons;

    public List<SuperPerson> getSuperPersons() {
        return superPersons;
    }

    public void setSuperPersons(List<SuperPerson> superPersons) {
        this.superPersons = superPersons;
    }
  
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
