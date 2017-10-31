/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.util.List;
import java.util.Map;

/**
 *
 * @author James
 */
public interface DvdDao {

    public void addDvd(Dvd dvd);

    public void deleteDvd(Integer id);

    public void updateDvd(Dvd dvd);

    public Dvd getDvdById(Integer id);

    public List<Dvd> getAllDvds();

    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria);
    
}
