/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.publishinggood.service;

import com.sg.publishinggood.dao.BookDao;
import com.sg.publishinggood.dto.Author;
import com.sg.publishinggood.dto.Book;
import java.util.List;

/**
 *
 * @author James
 */
public class BookService {

    BookDao bookDao;

    public List<Book> getBooksByAuthor(Author author, int offset, int limit) {
        return bookDao.getBooksByAuthor(author, offset, limit);
    }

}
