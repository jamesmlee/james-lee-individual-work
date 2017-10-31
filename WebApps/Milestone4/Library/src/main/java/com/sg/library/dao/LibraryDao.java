/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.library.dao;

import com.sg.library.model.Author;
import com.sg.library.model.Book;
import com.sg.library.model.Publisher;
import java.util.List;

/**
 *
 * @author James
 */
public interface LibraryDao {

    public void addAuthor(Author author);
    public void deleteAuthor(int authorId);
    public void updateAuthor(Author author);
    public Author getAuthorById(int id);
    public List<Author> getAllAuthors();
    
    public void addBook(Book book);
    public void deleteBook(int bookId);
    public void updateBook(Book book);
    public Book getBookById(int id);
    public List<Book> getAllBooks();
    
// represents the Authors can write many Books side of the many-to-many relationship between 
// Authors and Books (the Books can have many Authors side of this relationship is modeled as 
// the List of Authors in the Book object)     
    public List<Book> getBooksByAuthorId(int authorId);
    
// models the Publishers can publish many books side of the one-to-many relationship between 
// Publishers and Books (the Books can have one Publisher side of the relationship is modeled 
// as the Publisher reference in the Book object)
    public List<Book> getBooksByPublisherId(int publisherId);

    public void addPublisher(Publisher publisher);
    public void deletePublisher(int publisherId);
    public void updatePublisher(Publisher publisher);
    public Publisher getPublisherById(int id);
    public List<Publisher> getAllPublishers();
}
