/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.publishinggood.service;

import com.sg.publishinggood.dao.AuthorBookDao;
import com.sg.publishinggood.dao.AuthorDao;
import com.sg.publishinggood.dao.BookDao;
import com.sg.publishinggood.dto.Author;
import com.sg.publishinggood.dto.AuthorBook;
import com.sg.publishinggood.dto.Book;
import java.util.List;

/**
 *
 * @author James
 */
public class AuthorService {
    
    AuthorDao authorDao;
    BookDao bookDao;
// need to save the relationship between Author and Book 
    AuthorBookDao authorBookDao;
    
    public List<Author> getAuthorsByBook(Book book, int offset, int limit) {
        return authorDao.getAuthorsByBook(book, offset, limit);
    }
    
// create relationship    
    public AuthorBook addAuthorToBook(Author author, Book book) {
        AuthorBook authorBook = new AuthorBook();
        authorBook.setAuthor(author);
        authorBook.setBook(book);
        
        return authorBookDao.create(authorBook);
    }
    
    public AuthorBook addAuthorToBook(int authorId, int bookId) {
        Author author = authorDao.get(authorId);
        Book book = bookDao.get(bookId);
        return addAuthorToBook(author, book);
    }
    
}
