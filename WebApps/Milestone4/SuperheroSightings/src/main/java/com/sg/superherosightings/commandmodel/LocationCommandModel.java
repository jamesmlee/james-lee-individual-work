/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.commandmodel;

import com.sg.superherosightings.model.Address;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author James
 */
public class LocationCommandModel {

    private Integer locationId;

    @NotEmpty(message = "You must supply a name.")
    @Length(max = 50, message = "Name must be no more than 50 characters in length.")
    private String name;

    @Length(max = 65585, message = "Character limit exceeded.")
    private String description;

    @NotEmpty(message = "You must supply an address.")
    private AddressCommandModel address;

    @NotEmpty(message = "You must supply a latitude.")
    @Length(min = 9, max = 15, message = "Latitude must be between nine and 15 characters in length.")
    private String latitude;

    @NotEmpty(message = "You must supply a longitude.")
    @Length(min = 9, max = 15, message = "Longitude must be between nine and 15 characters in length.")
    private String longitude;

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddressCommandModel getAddress() {
        return address;
    }

    public void setAddress(AddressCommandModel address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


}
