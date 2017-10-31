/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Director;
import java.util.List;

/**
 *
 * @author James
 */
public interface DirectorDao {

    public void addDirector(Director director);

    public void deleteDirector(int directorId);

    public void updateDirector(Director director);

    public Director getDirectorById(int id);

    public List<Director> getAllDirector();

}
