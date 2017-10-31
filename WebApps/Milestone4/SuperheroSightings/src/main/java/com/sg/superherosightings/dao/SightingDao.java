/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperPerson;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author James
 */
public interface SightingDao {

    public Sighting createSighting(Sighting sighting);

    public Sighting getSightingById(Integer sightingId);

    public List<Sighting> getAllSightings(int offset, int limit);

    public Sighting updateSighting(Sighting sighting);

    public Sighting deleteSighting(Sighting sighting);

    // query
    public List<Sighting> getAllSightingsByDate(LocalDate date, int offset, int limit);
    
    public List<Sighting> getAllSightingsBySuperPerson(SuperPerson sp, int offset, int limit);

}
