/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import java.util.List;

/**
 *
 * @author James
 */
public interface DvdDirector {

    public void addDvdDirector(DvdDirector dvdDirector);

    public void deleteDvdDirector(int dvdDirectorId);

    public void updateDvdDirector(DvdDirector dvdDirector);

    public DvdDirector getDvdDirectorById(int id);

    public List<DvdDirector> getAllDvdDirector();

}
