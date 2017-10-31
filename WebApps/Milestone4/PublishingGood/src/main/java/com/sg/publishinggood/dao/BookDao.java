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
public interface BookDao {

    public Book get(Integer id);

    public void update(Book book);

    public void delete(Book book);

    public Book create(Book book);

    public List<Book> list();

    public List<Book> getBooksByAuthor(Author author, int offset, int limit);

}
