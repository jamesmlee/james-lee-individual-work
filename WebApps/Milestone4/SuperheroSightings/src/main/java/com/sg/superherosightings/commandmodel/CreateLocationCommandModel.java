/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.commandmodel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author James
 */
public class CreateLocationCommandModel {

    private Integer locationId;

    @NotEmpty(message = "You must supply a name.")
    @Length(max = 50, message = "Name must be no more than 50 characters in length.")
    private String name;

    @Length(max = 65585, message = "Character limit exceeded.")
    private String description;

// address
    @NotEmpty(message = "You must supply a street.")
    @Length(max = 50, message = "Street must be no more than 50 characters in length.")
    private String street;
    @NotEmpty(message = "You must supply a city.")
    @Length(max = 50, message = "City must be no more than 50 characters in length.")
    private String city;
    @NotEmpty(message = "You must supply a state.")
    @Length(min = 2, max = 2, message = "State must be 2 characters in length.")
    private String state;
    @NotEmpty(message = "You must supply a zipcode.")
    @Length(min = 5, max = 10, message = "Zipcode must be between 5 and 10 characters in length.")
    private String zipcode;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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
