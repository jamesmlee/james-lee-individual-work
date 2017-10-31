/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.publishinggood.dao;

import com.sg.publishinggood.dto.BookPublisher;
import java.util.List;

/**
 *
 * @author James
 */
public interface BookPublisherDao {

    public BookPublisher get(Integer id);

    public void update(BookPublisher bookPublisher);

    public void delete(BookPublisher bookPublisher);

    public BookPublisher create(BookPublisher bookPublisher);

    public List<BookPublisher> list();

}
