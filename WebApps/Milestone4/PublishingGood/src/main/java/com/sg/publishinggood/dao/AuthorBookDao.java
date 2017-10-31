/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.publishinggood.dao;

import com.sg.publishinggood.dto.Author;
import com.sg.publishinggood.dto.AuthorBook;
import com.sg.publishinggood.dto.Book;
import java.util.List;

/**
 *
 * @author James
 */
public interface AuthorBookDao {

    public AuthorBook get(Integer id);

    public void update(AuthorBook authorBook);

    public void delete(AuthorBook authorBook);

    public AuthorBook create(AuthorBook authorBook);

    public List<AuthorBook> list();

}
