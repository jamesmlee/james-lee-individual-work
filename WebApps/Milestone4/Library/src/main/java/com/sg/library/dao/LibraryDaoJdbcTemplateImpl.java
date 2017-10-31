/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.library.dao;

import com.sg.library.model.Author;
import com.sg.library.model.Book;
import com.sg.library.model.Publisher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author James
 */
public class LibraryDaoJdbcTemplateImpl implements LibraryDao {

    private JdbcTemplate jdbcTemplate;
// setter injection
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

// CRUD statements for AUTHORS table
    private static final String SQL_INSERT_AUTHOR
            = "insert into authors (first_name, last_name, street, city, "
            + "state, zip, phone) values (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_AUTHOR
            = "delete from authors where author_id = ?";

    private static final String SQL_UPDATE_AUTHOR
            = "update authors set first_name = ?, last_name = ?, street = ?, "
            + "city = ?, state = ?, zip = ?, phone = ? where author_id =  ?";

    private static final String SQL_SELECT_AUTHOR
            = "select * from authors where author_id = ?";

    private static final String SQL_SELECT_ALL_AUTHORS
            = "select * from authors";

// JOIN of authors and books_authors tables; allows us to find all authors for a given book
    private static final String SQL_SELECT_AUTHORS_BY_BOOK_ID
            = "select au.author_id, au.first_name, au.last_name, au.street, "
            + "au.city, au.state, au.zip, au.phone from authors au "
            + "join books_authors ba on au.author_id = ba.author_id where "
            + "ba.book_id = ?";

    // BOOKS AND BOOKS_AUTHORS
    private static final String SQL_INSERT_BOOK
            = "insert into books "
            + "(isbn, title, publisher_id, price, publish_date) "
            + "values (?, ?, ?, ?, ?)";

    private static final String SQL_INSERT_BOOKS_AUTHORS
            = "insert into books_authors (book_id, author_id) values(?, ?)";

    private static final String SQL_DELETE_BOOK
            = "delete from books where book_id = ?";

    private static final String SQL_DELETE_BOOKS_AUTHORS
            = "delete from books_authors where book_id = ?";

    private static final String SQL_UPDATE_BOOK
            = "update books set isbn = ?, title = ?, publisher_id = ?, "
            + "price = ?, publish_date = ? "
            + "where book_id = ?";

    private static final String SQL_SELECT_BOOK
            = "select * from books where book_id = ?";

    private static final String SQL_SELECT_ALL_BOOKS
            = "select * from books";

    private static final String SQL_SELECT_BOOKS_AUTHORS_AUTHOR_ID_BY_BOOK_ID
            = "select author_id from books_authors where book_id = ?";

// JOIN of the books and books_authors tables; allows us to find all books for a given author    
    private static final String SQL_SELECT_BOOKS_BY_AUTHOR_ID
            = "select b.book_id, b.isbn, b.title, b.publisher_id, b.price, "
            + "b.publish_date from books b join books_authors ba on author_id "
            + "where b.book_id = ba.book_id "
            + "and ba.author_id  =  ?;";

    // PUBLISHER
    private static final String SQL_SELECT_BOOKS_BY_PUBLISHER_ID
            = "select * from books where publisher_id = ?";

    private static final String SQL_INSERT_PUBLISHER
            = "insert into publishers (name, street, city, state, zip, phone) "
            + "values (?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_PUBLISHER
            = "delete from publishers where publisher_id = ?";

    private static final String SQL_UPDATE_PUBLISHER
            = "update publishers set name = ?, street = ?, city = ?, "
            + "state = ?, zip = ?, phone = ? where publisher_id  =  ?";

    private static final String SQL_SELECT_PUBLISHER
            = "select * from publishers where publisher_id = ?";

    private static final String SQL_SELECT_ALL_PUBLISHERS
            = "select * from publishers";

// JOIN of the publishers and books tables; allows us to find publisher of a given book
    private static final String SQL_SELECT_PUBLISHER_BY_BOOK_ID
            = "select pub.publisher_id, pub.name, pub.street, pub.city, "
            + "pub.state, pub.zip, pub.phone from publishers pub "
            + "join books on pub.publisher_id = books.publisher_id where "
            + "books.book_id = ?";

// =============
// AUTHOR METHODS
// =============
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addAuthor(Author author) {
        jdbcTemplate.update(SQL_INSERT_AUTHOR,
                author.getFirstName(),
                author.getLastName(),
                author.getStreet(),
                author.getCity(),
                author.getState(),
                author.getZip(),
                author.getPhone());

        int authorId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);

        author.setAuthorId(authorId);
    }

    @Override
    public void deleteAuthor(int authorId) {
        jdbcTemplate.update(SQL_DELETE_AUTHOR, authorId);
    }

    @Override
    public void updateAuthor(Author author) {
        jdbcTemplate.update(SQL_UPDATE_AUTHOR,
                author.getFirstName(),
                author.getLastName(),
                author.getStreet(),
                author.getCity(),
                author.getState(),
                author.getZip(),
                author.getPhone(),
                author.getAuthorId());
    }

    @Override
    public Author getAuthorById(int authorId) {
// if we got nothing back on query, it throws an exception.
// we should be able to ask for something that's not there, and get null back
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_AUTHOR,
                    new AuthorMapper(),
                    authorId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        return jdbcTemplate.query(SQL_SELECT_ALL_AUTHORS,
                new AuthorMapper());
    }
// =============
// PUBLISHER METHODS
// =============

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPublisher(Publisher publisher) {
        jdbcTemplate.update(SQL_INSERT_PUBLISHER,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getZip(),
                publisher.getPhone());

        int publisherId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);

        publisher.setPublisherId(publisherId);
    }

    @Override
    public void deletePublisher(int publisherId) {
        jdbcTemplate.update(SQL_DELETE_PUBLISHER, publisherId);
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        jdbcTemplate.update(SQL_UPDATE_PUBLISHER,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getZip(),
                publisher.getPhone(),
                publisher.getPublisherId());
    }

    @Override
    public Publisher getPublisherById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_PUBLISHER,
                    new PublisherMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PUBLISHERS, new PublisherMapper());
    }
// =============
// BOOK METHODS
// =============

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addBook(Book book) {
        // first insert into books table and get newly generated book_id
        jdbcTemplate.update(SQL_INSERT_BOOK,
                book.getIsbn(),
                book.getTitle(),
                book.getPublisher().getPublisherId(),
                book.getPrice(),
                book.getPublishDate().toString());
        book.setBookId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
        // now update the books_authors table
        insertBooksAuthors(book);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteBook(int bookId) {
        // delete books_authors relationship for this book
        jdbcTemplate.update(SQL_DELETE_BOOKS_AUTHORS, bookId);
        // delete book
        jdbcTemplate.update(SQL_DELETE_BOOK, bookId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateBook(Book book) {
        // update books table
        jdbcTemplate.update(SQL_UPDATE_BOOK,
                book.getIsbn(),
                book.getTitle(),
                book.getPublisher().getPublisherId(),
                book.getPrice(),
                book.getPublishDate().toString(),
                book.getBookId());
        // delete books_authors relationships and then reset them
        jdbcTemplate.update(SQL_DELETE_BOOKS_AUTHORS, book.getBookId());
        insertBooksAuthors(book);
    }

    @Override
    public Book getBookById(int id) {
        try {
            // get the properties from the books table
            Book book = jdbcTemplate.queryForObject(SQL_SELECT_BOOK,
                    new BookMapper(),
                    id);
            // get the Authors for this book and set list on the book
            book.setAuthors(findAuthorsForBook(book));
            // get the Publisher for this book
            book.setPublisher(findPublisherForBook(book));
            return book;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Book> getBooksByAuthorId(int authorId) {
        // get the books written/cowritten by this author
        List<Book> bookList
                = jdbcTemplate.query(SQL_SELECT_BOOKS_BY_AUTHOR_ID,
                        new BookMapper(),
                        authorId);
        // set the publisher and list of Authors for each book
        return associatePublisherAndAuthorsWithBooks(bookList);
    }

    @Override
    public List<Book> getBooksByPublisherId(int publisherId) {
        // get the books published by this publisher
        List<Book> bookList
                = jdbcTemplate.query(SQL_SELECT_BOOKS_BY_PUBLISHER_ID,
                        new BookMapper(),
                        publisherId);
        // set the publisher and list of Authors for each book
        return associatePublisherAndAuthorsWithBooks(bookList);
    }

    @Override
    public List<Book> getAllBooks() {
        // get all the books
        List<Book> bookList = jdbcTemplate.query(SQL_SELECT_ALL_BOOKS,
                new BookMapper());
        // set the publisher and list of Authors for each book
        return associatePublisherAndAuthorsWithBooks(bookList);
    }

// =============
// HELPER METHODS
// =============   
// we will use helper methods to associate Authors and Publishers with each Book.
// 2 step approach when constructing a Book object from a row in the database:
// 1. use the BookMapper to create the Book DTO and set all the values from the books table.
// 2. use the helper methods below to find the Authors and Publisher of the Book, 
// and then set those values on the Book DTO.
    
// we take a similar 2-step approach when creating, updating, or deleting a Book.
// 1. create/update/delete associated row in the books table.
// 2. create/update/delete entries in books_authors bridge table accordingly
// creates an entry in book_authors, associating the Book with each of its Authors    
    private void insertBooksAuthors(Book book) {
        final int bookId = book.getBookId();
        final List<Author> authors = book.getAuthors();

        // Update the books_authors bridge table with an entry for 
        // each author of this book
        for (Author currentAuthor : authors) {
            jdbcTemplate.update(SQL_INSERT_BOOKS_AUTHORS,
                    bookId,
                    currentAuthor.getAuthorId());
        }
    }
// retrieves all the Authors for a Book by joining the authors & books_authors tables

    private List<Author> findAuthorsForBook(Book book) {
        return jdbcTemplate.query(SQL_SELECT_AUTHORS_BY_BOOK_ID,
                new AuthorMapper(),
                book.getBookId());
    }
// retrieves Publisher for a Book by joining publishers and books tables

    private Publisher findPublisherForBook(Book book) {
        return jdbcTemplate.queryForObject(SQL_SELECT_PUBLISHER_BY_BOOK_ID,
                new PublisherMapper(),
                book.getBookId());
    }
// uses the previous 2 methods to associate Author and Publisher with each Book in the List

    private List<Book> associatePublisherAndAuthorsWithBooks(List<Book> bookList) {
        // set the complete list of author ids for each book
        for (Book currentBook : bookList) {
            // add Authors to current book
            currentBook.setAuthors(findAuthorsForBook(currentBook));
            // add the Publisher to current book
            currentBook.setPublisher(findPublisherForBook(currentBook));
        }
        return bookList;
    }

// ========
// MAPPERS
// ========
    private static final class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            Author au = new Author();
            au.setFirstName(rs.getString("first_name"));
            au.setLastName(rs.getString("last_name"));
            au.setStreet(rs.getString("street"));
            au.setCity(rs.getString("city"));
            au.setState(rs.getString("state"));
            au.setZip(rs.getString("zip"));
            au.setPhone(rs.getString("phone"));
            au.setAuthorId(rs.getInt("author_id"));
            return au;
        }
    }

    private static final class PublisherMapper implements RowMapper<Publisher> {

        @Override
        public Publisher mapRow(ResultSet rs, int i) throws SQLException {
            Publisher pub = new Publisher();
            pub.setPublisherId(rs.getInt("publisher_id"));
            pub.setName(rs.getString("name"));
            pub.setStreet(rs.getString("street"));
            pub.setCity(rs.getString("city"));
            pub.setState(rs.getString("state"));
            pub.setZip(rs.getString("zip"));
            pub.setPhone(rs.getString("phone"));
            return pub;
        }
    }

    private static final class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int i) throws SQLException {
            Book b = new Book();
            b.setBookId(rs.getInt("book_id"));
            b.setIsbn(rs.getString("isbn"));
            b.setTitle(rs.getString("title"));
            b.setPrice(rs.getBigDecimal("price"));
// how we convert timestamp in DB to a LocalDate            
            b.setPublishDate(rs.getTimestamp("publish_date").toLocalDateTime().toLocalDate());
            return b;
        }
    }

}
