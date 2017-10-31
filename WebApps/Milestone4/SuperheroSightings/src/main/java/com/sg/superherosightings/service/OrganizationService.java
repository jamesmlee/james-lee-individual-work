/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperPerson;
import com.sg.superherosightings.viewmodel.OrganizationViewModel;
import java.util.List;

/**
 *
 * @author James
 */
public interface OrganizationService {
// pass through methods

    public Organization createOrganization(Organization organization);

    public Organization getOrganizationById(Integer organizationId);

    public List<Organization> getAllOrganizations(int offset, int limit);

    public Organization updateOrganization(Organization organization);

    public Organization deleteOrganization(Organization organization);
// query

    public List<Organization> getAllOrganizationsBySuperPerson(SuperPerson superPerson, int offset, int limit);

    public List<OrganizationViewModel> getOrganizationViewModels(int offset, int limit);
    
    public OrganizationViewModel getOrganizationViewModelByOrganizationId(Integer sightingId);
}
