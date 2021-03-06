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
public class OrganizationCommandModel {

    public Integer organizationId;

    @NotEmpty(message = "You must supply a name.")
    @Length(max = 50, message = "Name must be no more than 50 characters in length.")
    public String name;

    @Length(max = 65585, message = "Character limit exceeded.")
    public String description;

    @NotEmpty(message = "You must supply a phone number.")
    @Length(min = 3, max = 15, message = "Phone number must be no more than 50 characters in length.")
    public String phone;

    public String isGood;

    //not seen in corresponding model
    public Integer locationId;

    public int[] superPersons;

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
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

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsGood() {
        return isGood;
    }

    public void setIsGood(String isGood) {
        this.isGood = isGood;
    }

    public int[] getSuperPersons() {
        return superPersons;
    }

    public void setSuperPersons(int[] superPersons) {
        this.superPersons = superPersons;
    }

}
