/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.helpers;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Power;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperPerson;
import com.sg.superherosightings.model.SuperPersonOrganization;
import com.sg.superherosightings.model.SuperPersonPower;
import com.sg.superherosightings.model.SuperPersonSighting;

/**
 *
 * @author James
 */
public class CompareObjects {

    public String compareObjects(Address add1, Address add2) {
        String result = "";

        try {
            if (!add1.getAddressId().equals(add2.getAddressId())) {
                result += "AddressId doesn't match, ";
            }
        } catch (NullPointerException e) {
            result += "id was null!";
        }

        try {
            if (!add1.getCity().equals(add2.getCity())) {
                result += "City doesn't match,";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!add1.getState().equals(add2.getState())) {
                result += "State doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!add1.getStreet().equals(add2.getStreet())) {
                result += "Street doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!add1.getZipcode().equals(add2.getZipcode())) {
                result += "Zipcode doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        System.out.println(result);
        return result;
    }

    public String compareObjects(Location loc1, Location loc2) {
        String result = "";

        try {
            if (!loc1.getLocationId().equals(loc2.getLocationId())) {
                result += "LocationId doesn't match, ";
            }
        } catch (NullPointerException e) {
            result += "id is null!";
        }
        try {
            if (!loc1.getAddress().getAddressId().equals(loc2.getAddress().getAddressId())) {
                result += "AddressId doesn't match,";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!loc1.getDescription().equals(loc2.getDescription())) {
                result += "Description doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!loc1.getLatitude().equals(loc2.getLatitude())) {
                result += "Latitude doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!loc1.getLongitude().equals(loc2.getLongitude())) {
                result += "Longitude doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!loc1.getName().equals(loc2.getName())) {
                result += "Name doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        System.out.println(result);
        return result;
    }

    public String compareObjects(Organization orga1, Organization orga2) {
        String result = "";

        try {
            if (!orga1.getOrganizationId().equals(orga2.getOrganizationId())) {
                result += "OrganizationId doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (orga1.getIsGood() != orga2.getIsGood()) {
                result += "IsGood doesn't match,";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!orga1.getDescription().equals(orga2.getDescription())) {
                result += "Description doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!orga1.getLocation().getLocationId().equals(orga2.getLocation().getLocationId())) {
                result += "LocationId doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!orga1.getName().equals(orga2.getName())) {
                result += "Name doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!orga1.getPhone().equals(orga2.getPhone())) {
                result += "Phone doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        System.out.println(result);
        return result;
    }

    public String compareObjects(Power pow1, Power pow2) {
        String result = "";

        try {
            if (!pow1.getPowerId().equals(pow2.getPowerId())) {
                result += "PowerId doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!pow1.getName().equals(pow2.getName())) {
                result += "Name doesn't match,";
            }
        } catch (NullPointerException e) {
        }

        System.out.println(result);
        return result;
    }

    public String compareObjects(Sighting sighting1, Sighting sighting2) {
        String result = "";

        try {
            if (!sighting1.getSightingId().equals(sighting2.getSightingId())) {
                result += "SightingId doesn't match, ";
            }
        } catch (NullPointerException e) {
            result += "Id is null!";
        }
        try {
            if (!sighting1.getDate().equals(sighting2.getDate())) {
                result += "Date doesn't match,";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!sighting1.getDescription().equals(sighting2.getDescription())) {
                result += "Description doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!sighting1.getLocation().getLocationId().equals(sighting2.getLocation().getLocationId())) {
                result += "LocationId doesn't match, ";
            }
        } catch (NullPointerException e) {
        }

        System.out.println(result);
        return result;
    }

    public String compareObjects(SuperPerson sp1, SuperPerson sp2) {
        String result = "";

        try {
            if (!sp1.getSuperPersonId().equals(sp2.getSuperPersonId())) {
                result += "SuperPersonId doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (sp1.getIsGood() != sp2.getIsGood()) {
                result += "isGood doesn't match,";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!sp1.getDescription().equals(sp2.getDescription())) {
                result += "Description doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!sp1.getName().equals(sp2.getName())) {
                result += "Name doesn't match, ";
            }
        } catch (NullPointerException e) {
        }

        System.out.println(result);
        return result;
    }

    public String compareObjects(SuperPersonOrganization spo1, SuperPersonOrganization spo2) {
        String result = "";

        try {
            if (!spo1.getSuperPersonOrganizationId().equals(spo2.getSuperPersonOrganizationId())) {
                result += "SuperPersonOrganizationId doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!spo1.getOrganization().getOrganizationId().equals(spo2.getOrganization().getOrganizationId())) {
                result += "OrganizationId doesn't match,";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!spo1.getSuperPerson().getSuperPersonId().equals(spo2.getSuperPerson().getSuperPersonId())) {
                result += "SuperPersonId doesn't match, ";
            }
        } catch (NullPointerException e) {
        }

        System.out.println(result);
        return result;
    }

    public String compareObjects(SuperPersonPower spp1, SuperPersonPower spp2) {
        String result = "";

        try {
            if (!spp1.getSuperPersonPowerId().equals(spp2.getSuperPersonPowerId())) {
                result += "SuperPersonPowerId doesn't match, ";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!spp1.getSuperPerson().getSuperPersonId().equals(spp2.getSuperPerson().getSuperPersonId())) {
                result += "SuperPersonId doesn't match,";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!spp1.getPower().getPowerId().equals(spp2.getPower().getPowerId())) {
                result += "PowerId doesn't match, ";
            }
        } catch (NullPointerException e) {
        }

        System.out.println(result);
        return result;
    }

    public String compareObjects(SuperPersonSighting sps1, SuperPersonSighting sps2) {
        String result = "";

        try {
            if (!sps1.getSuperPersonSightingId().equals(sps2.getSuperPersonSightingId())) {
                result += "SuperPersonSightingId doesn't match, ";
            }
        } catch (NullPointerException e) {
            result += "id is null!";
        }
        try {
            if (!sps1.getSuperPerson().getSuperPersonId().equals(sps2.getSuperPerson().getSuperPersonId())) {
                result += "SuperPersonId doesn't match,";
            }
        } catch (NullPointerException e) {
        }
        try {
            if (!sps1.getSighting().getSightingId().equals(sps2.getSighting().getSightingId())) {
                result += "SightingId doesn't match, ";
            }
        } catch (NullPointerException e) {
        }

        System.out.println(result);
        return result;
    }
}
