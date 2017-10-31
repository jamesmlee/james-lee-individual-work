/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.commandmodel;

import com.sg.superherosightings.model.Location;
import java.time.LocalDate;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author James
 */
public class SightingCommandModel {

    private Integer sightingId;
    @NotEmpty(message = "You must supply a value for date.")
    private String date;
    private Integer locationId;
    @Length(max = 65535, message = "Description must be no more than 65,535 characters in length.")
    private String description;

    @NotEmpty(message = "You must supply the name of at least one super person at this sighting.")
    Integer[] superPersons;

    public Integer getSightingId() {
        return sightingId;
    }

    public void setSightingId(Integer sightingId) {
        this.sightingId = sightingId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer[] getSuperPersons() {
        return superPersons;
    }

    public void setSuperPersons(Integer[] superPersons) {
        this.superPersons = superPersons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
