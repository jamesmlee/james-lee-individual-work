/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.util.List;

/**
 *
 * @author James
 */
public interface DvdDao {

    public void addDvd(Dvd dvd);

    public void deleteDvd(int dvdId);

    public void updateDVD(Dvd dvd);

    public Dvd getDvdById(int id);

    public List<Dvd> getAllDvd();

}
