/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.PowerDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperPersonDao;
import com.sg.superherosightings.dao.SuperPersonOrganizationDao;
import com.sg.superherosightings.dao.SuperPersonPowerDao;
import com.sg.superherosightings.dao.SuperPersonSightingDao;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Power;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperPerson;
import com.sg.superherosightings.model.SuperPersonOrganization;
import com.sg.superherosightings.model.SuperPersonPower;
import com.sg.superherosightings.model.SuperPersonSighting;
import com.sg.superherosightings.viewmodel.SuperPersonViewModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author James
 */
public class SuperPersonServiceImpl implements SuperPersonService {

    SuperPersonDao superPersonDao;
    OrganizationDao organizationDao;
    PowerDao powerDao;
    SightingDao sightingDao;
    LocationDao locationDao;
    SuperPersonOrganizationDao superPersonOrganizationDao;
    SuperPersonPowerDao superPersonPowerDao;
    SuperPersonSightingDao superPersonSightingDao;

    public SuperPersonServiceImpl(SuperPersonDao superPersonDao, OrganizationDao organizationDao,
            PowerDao powerDao, SightingDao sightingDao, LocationDao locationDao, SuperPersonOrganizationDao superPersonOrganizationDao,
            SuperPersonPowerDao superPersonPowerDao, SuperPersonSightingDao superPersonSightingDao) {
        this.superPersonDao = superPersonDao;
        this.organizationDao = organizationDao;
        this.powerDao = powerDao;
        this.sightingDao = sightingDao;
        this.locationDao = locationDao;
        this.superPersonOrganizationDao = superPersonOrganizationDao;
        this.superPersonPowerDao = superPersonPowerDao;
        this.superPersonSightingDao = superPersonSightingDao;
    }

    @Override
    public SuperPerson createSuperPerson(SuperPerson superPerson) {
        return superPersonDao.createSuperPerson(superPerson);
    }

    @Override
    public SuperPerson getSuperPersonById(Integer superPersonId) {
        return superPersonDao.getSuperPersonById(superPersonId);
    }

    @Override
    public List<SuperPerson> getAllSuperPersons(int offset, int limit) {
        return superPersonDao.getAllSuperPersons(offset, limit);
    }

    @Override
    public SuperPerson updateSuperPerson(SuperPerson superPerson) {
        return superPersonDao.updateSuperPerson(superPerson);
    }

    @Override
    public SuperPerson deleteSuperPerson(SuperPerson superPerson) {
        List<Sighting> sightingsForSuperPerson = sightingDao.getAllSightingsBySuperPerson(superPerson, 0, Integer.MAX_VALUE);
        for (Sighting currentSighting : sightingsForSuperPerson) {
            deleteSightingFromSuperPerson(superPerson, currentSighting);
        }
        List<Organization> organizationsForSuperPerson = organizationDao.getAllOrganizationsBySuperPerson(superPerson, 0, Integer.MAX_VALUE);
        for (Organization currentOrganization : organizationsForSuperPerson) {
            deleteOrganizationFromSuperPerson(superPerson, currentOrganization);
        }
        List<Power> powersForSuperPerson = powerDao.getAllPowersBySuperPerson(superPerson, 0, Integer.MAX_VALUE);
        for (Power currentPower : powersForSuperPerson) {
            deletePowerFromSuperPerson(superPerson, currentPower);
        }
        return superPersonDao.deleteSuperPerson(superPerson);
    }

    @Override
    public List<SuperPerson> getAllSuperPersonsBySighting(Sighting sighting, int offset, int limit) {
        return superPersonDao.getAllSuperPersonsBySighting(sighting, offset, limit);
    }

    @Override
    public List<SuperPerson> getAllSuperPersonsBySightingLocation(Location location, int offset, int limit) {
        return superPersonDao.getAllSuperPersonsBySightingLocation(location, offset, limit);
    }

    @Override
    public List<SuperPerson> getAllSuperPersonsByOrganization(Organization organization, int offset, int limit) {
        return superPersonDao.getAllSuperPersonsByOrganization(organization, offset, limit);
    }

    @Override
    public SuperPersonPower addSuperPersonToPower(SuperPerson superPerson, Power power) {
        SuperPersonPower spp = new SuperPersonPower();
        spp.setSuperPerson(superPerson);
        spp.setPower(power);
        return superPersonPowerDao.createSuperPersonPower(spp);
    }

    @Override
    public SuperPersonPower addSuperPersonToPower(Integer superPersonId, Integer powerId) {
        return addSuperPersonToPower(superPersonDao.getSuperPersonById(superPersonId),
                powerDao.getPowerById(powerId));
    }

    @Override
    public SuperPersonPower deletePowerFromSuperPerson(SuperPerson superPerson, Power power) {
        SuperPersonPower superPersonPower = superPersonPowerDao.getSuperPersonPowerBySuperPersonAndPower(superPerson, power);
        return superPersonPowerDao.deleteSuperPersonPower(superPersonPower);
    }

    @Override
    public SuperPersonPower deletePowerFromSuperPerson(Integer superPersonId, Integer powerId) {
        SuperPerson superPerson = superPersonDao.getSuperPersonById(superPersonId);
        Power power = powerDao.getPowerById(powerId);
        SuperPersonPower superPersonPower = superPersonPowerDao.getSuperPersonPowerBySuperPersonAndPower(superPerson, power);
        return superPersonPowerDao.deleteSuperPersonPower(superPersonPower);
    }

    @Override
    public SuperPersonOrganization addSuperPersonToOrganization(SuperPerson superPerson, Organization organization) {
        SuperPersonOrganization spo = new SuperPersonOrganization();
        spo.setSuperPerson(superPerson);
        spo.setOrganization(organization);
        return superPersonOrganizationDao.createSuperPersonOrganization(spo);
    }

    @Override
    public SuperPersonOrganization addSuperPersonToOrganization(Integer superPersonId, Integer organizationId) {
        return addSuperPersonToOrganization(superPersonDao.getSuperPersonById(superPersonId),
                organizationDao.getOrganizationById(organizationId));
    }

    @Override
    public SuperPersonOrganization deleteOrganizationFromSuperPerson(SuperPerson superPerson, Organization organization) {
        SuperPersonOrganization superPersonOrganization = superPersonOrganizationDao.getSuperPersonOrganizationBySuperPersonAndOrganization(superPerson, organization);
        return superPersonOrganizationDao.deleteSuperPersonOrganization(superPersonOrganization);
    }

    @Override
    public SuperPersonOrganization deleteOrganizationFromSuperPerson(Integer superPersonId, Integer organizationId) {
        SuperPerson superPerson = superPersonDao.getSuperPersonById(superPersonId);
        Organization organization = organizationDao.getOrganizationById(organizationId);
        SuperPersonOrganization superPersonOrganization = superPersonOrganizationDao.getSuperPersonOrganizationBySuperPersonAndOrganization(superPerson, organization);
        return superPersonOrganizationDao.deleteSuperPersonOrganization(superPersonOrganization);
    }

    @Override
    public SuperPersonSighting addSuperPersonToSighting(SuperPerson superPerson, Sighting sighting) {
        SuperPersonSighting sps = new SuperPersonSighting();
        sps.setSuperPerson(superPerson);
        sps.setSighting(sighting);
        return superPersonSightingDao.createSuperPersonSighting(sps);
    }

    @Override
    public SuperPersonSighting addSuperPersonToSighting(Integer superPersonId, Integer sightingId) {
        SuperPerson sp = superPersonDao.getSuperPersonById(superPersonId);
        Sighting si = sightingDao.getSightingById(sightingId);

        //pass into the method above
        SuperPersonSighting sps = addSuperPersonToSighting(sp, si);
        return sps;
    }

    @Override
    public SuperPersonSighting deleteSightingFromSuperPerson(SuperPerson superPerson, Sighting sighting) {
        SuperPersonSighting superPersonSighting = superPersonSightingDao.getSuperPersonSightingBySuperPersonAndSighting(superPerson, sighting);
        return superPersonSightingDao.deleteSuperPersonSighting(superPersonSighting);
    }

    @Override
    public SuperPersonSighting deleteSightingFromSuperPerson(Integer superPersonId, Integer sightingId) {
        SuperPerson superPerson = superPersonDao.getSuperPersonById(superPersonId);
        Sighting sighting = sightingDao.getSightingById(sightingId);
        SuperPersonSighting superPersonSighting = superPersonSightingDao.getSuperPersonSightingBySuperPersonAndSighting(superPerson, sighting);
        return superPersonSightingDao.deleteSuperPersonSighting(superPersonSighting);
    }

    @Override
    public List<SuperPersonViewModel> getSuperPersonViewModels(int offset, int limit) {
        List<SuperPersonViewModel> spvmList = new ArrayList();
        List<SuperPerson> viewSuperPersons = getAllSuperPersons(offset, limit);

        for (int i = 0; i < viewSuperPersons.size(); i++) {
            SuperPersonViewModel currentModel = new SuperPersonViewModel();
            SuperPerson currentSuperPerson = viewSuperPersons.get(i);
            currentModel.setSuperPerson(currentSuperPerson);
            spvmList.add(currentModel);
        }

        return spvmList;
    }

    @Override
    public SuperPersonViewModel getSuperPersonViewModelBySuperPersonId(Integer superPersonId) {
        SuperPersonViewModel spvm = new SuperPersonViewModel();
        SuperPerson superPerson = getSuperPersonById(superPersonId);
        List<Sighting> sightingsForSuperPersonNoLocation = sightingDao.getAllSightingsBySuperPerson(superPerson, 0, 10);
        List<Organization> orgsForSuperPerson = organizationDao.getAllOrganizationsBySuperPerson(superPerson, 0, 10);
        List<Sighting> sightingsForSuperPersonWithLocation = new ArrayList<Sighting>();
        List<Power> powersForSuperPerson = powerDao.getAllPowersBySuperPerson(superPerson, 0, 10);

        for (Sighting currentSighting : sightingsForSuperPersonNoLocation) {
            Location currentLocation = locationDao.getLocationById(currentSighting.getLocation().getLocationId());
            currentSighting.setLocation(currentLocation);
            sightingsForSuperPersonWithLocation.add(currentSighting);
        }

        spvm.setSuperPerson(superPerson);
        spvm.setSightings(sightingsForSuperPersonWithLocation);
        spvm.setOrganizations(orgsForSuperPerson);
        spvm.setPowers(powersForSuperPerson);

        return spvm;
    }

}
