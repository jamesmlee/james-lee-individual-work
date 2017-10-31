/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.viewmodel.SightingViewModel;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author James
 */
public interface SightingService {
// pass through methods
    public Sighting createSighting(Sighting sighting);

    public Sighting getSightingById(Integer sightingId);

    public List<Sighting> getAllSightings(int offset, int limit);

    public Sighting updateSighting(Sighting sighting);

    public Sighting deleteSighting(Sighting sighting);

    //demo
    public List<Sighting> getAllSightingsByDate(LocalDate date, int offset, int limit);
    
    public List<SightingViewModel> getSightingViewModels(int offset, int limit);
    
    public SightingViewModel getSightingViewModelBySightingId(Integer sightingId);
}
