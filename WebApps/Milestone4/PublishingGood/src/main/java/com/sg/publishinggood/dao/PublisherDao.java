/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.publishinggood.dao;

import com.sg.publishinggood.dto.Publisher;
import java.util.List;

/**
 *
 * @author James
 */
public interface PublisherDao {

    public Publisher get(Integer id);

    public void update(Publisher publisher);

    public void delete(Publisher publisher);

    public Publisher create(Publisher publisher);

    public List<Publisher> list();

}
