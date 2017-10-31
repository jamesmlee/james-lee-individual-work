/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Power;
import com.sg.superherosightings.model.SuperPerson;
import com.sg.superherosightings.model.SuperPersonPower;
import java.util.List;

/**
 *
 * @author James
 */
public interface SuperPersonPowerDao {

    public SuperPersonPower createSuperPersonPower(SuperPersonPower superPersonPower);

    public SuperPersonPower getSuperPersonPowerById(Integer superPersonPowerId);

    public List<SuperPersonPower> getAllSuperPersonPowers(int offset, int limit);

//    public SuperPersonPower updateSuperPersonPower(SuperPersonPower superPersonPower);
    public SuperPersonPower deleteSuperPersonPower(SuperPersonPower superPersonPower);

    public SuperPersonPower getSuperPersonPowerBySuperPersonAndPower(SuperPerson superPerson, Power power);

}
