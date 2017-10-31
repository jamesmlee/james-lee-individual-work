/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.publishinggood.dao;

import com.sg.publishinggood.dto.Author;
import com.sg.publishinggood.dto.Book;
import java.util.List;

/**
 *
 * @author James
 */
public interface AuthorDao {

    public Author get(Integer id);

    public void update(Author author);

    public void delete(Author author);

    public Author create(Author author);

    public List<Author> list();

    public List<Author> getAuthorsByBook(Book book, int offset, int limit);
}
