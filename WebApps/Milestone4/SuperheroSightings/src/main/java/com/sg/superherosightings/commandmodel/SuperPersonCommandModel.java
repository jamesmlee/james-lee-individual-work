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
public class SuperPersonCommandModel {

    private Integer superPersonId;
    
    @NotEmpty(message = "You must supply a value")
    @Length(max = 50, message = "Must be no more than 50 characters in length.")
    private String name;
    
    @Length(max = 65535, message = "Must be no more than 65,535 characters in length.")
    private String description;
    
    private Boolean isGood;
    
    @NotEmpty(message = "You must supply at least 1 power for this super person")
    private Integer[] powers;
    
    private Integer[] organizations;

    
    public Integer getSuperPersonId() {
        return superPersonId;
    }

    public void setSuperPersonId(Integer superPersonId) {
        this.superPersonId = superPersonId;
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

    public Boolean getIsGood() {
        return isGood;
    }

    public void setIsGood(Boolean isGood) {
        this.isGood = isGood;
    }

    public Integer[] getPowers() {
        return powers;
    }

    public void setPowers(Integer[] powers) {
        this.powers = powers;
    }

    public Integer[] getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Integer[] organizations) {
        this.organizations = organizations;
    }
    
    
}
