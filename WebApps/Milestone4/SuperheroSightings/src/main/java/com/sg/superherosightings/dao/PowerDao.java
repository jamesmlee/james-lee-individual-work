/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Power;
import com.sg.superherosightings.model.SuperPerson;
import java.util.List;

/**
 *
 * @author James
 */
public interface PowerDao {
    
    public Power createPower(Power power);
    
    public Power getPowerById(Integer powerId);
    
    public List<Power> getAllPowers(int offset, int limit);
    
    public Power updatePower(Power power);
    
    public Power deletePower(Power power);
    
    public List<Power> getAllPowersBySuperPerson(SuperPerson superPerson, int offset, int limit);
    
}
