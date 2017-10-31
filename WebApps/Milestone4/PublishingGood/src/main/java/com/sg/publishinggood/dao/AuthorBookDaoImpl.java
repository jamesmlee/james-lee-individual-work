/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.publishinggood.dao;

import com.sg.publishinggood.dto.Author;
import com.sg.publishinggood.dto.AuthorBook;
import com.sg.publishinggood.dto.Book;
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
public class AuthorBookDaoImpl implements AuthorBookDao {

    private JdbcTemplate jdbcTemplate;

    private static String SQL_INSERT_AUTHOR_BOOK
            = "INSERT INTO author_book (author_id, book_id) VALUES (?, ?)";
    private static String SQL_GET_AUTHOR_BOOK = "SELECT * FROM author_book WHERE id = ?";
// won't actually use UPDATE
    private static String SQL_UPDATE_AUTHOR_BOOK
            = "UPDATE author_book SET author_id = ?, set book_id = ?";
    private static String SQL_DELETE_AUTHOR_BOOK = "DELETE FROM author_book WHERE id = ?";
    private static String SQL_LIST_AUTHOR_BOOKS = "SELECT * FROM author_book";

    public AuthorBook get(Integer id) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_AUTHOR_BOOK,
                    new AuthorBookMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public void update(AuthorBook authorBook) {

// probably want to write an exception instead        
        if (authorBook.getAuthor() == null) {
            return;
        }
        if (authorBook.getBook() == null) {
            return;
        }

        jdbcTemplate.update(SQL_UPDATE_AUTHOR_BOOK,
                authorBook.getAuthor().getId(),
                authorBook.getBook().getId(),
                authorBook.getId());
    }

    public void delete(AuthorBook authorBook) {
        jdbcTemplate.update(SQL_DELETE_AUTHOR_BOOK, authorBook.getId());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public AuthorBook create(AuthorBook authorBook) {
        jdbcTemplate.update(SQL_INSERT_AUTHOR_BOOK, 
                authorBook.getAuthor().getId(),
                authorBook.getBook().getId());
// the table has an id that's automatically incremented for what we inserted
// LAST_INSERT_ID() is a MySQL function that lets us know what that id was
        int authorBookId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        authorBook.setId(authorBookId);

        return authorBook;
    }

    public List<AuthorBook> list() {
        return jdbcTemplate.query(SQL_LIST_AUTHOR_BOOKS, new AuthorBookMapper());
    }

    private static final class AuthorBookMapper implements RowMapper<AuthorBook> {

        @Override
        public AuthorBook mapRow(ResultSet rs, int i) throws SQLException {
            AuthorBook authorBook = new AuthorBook();
// are we going to load the actual author and book (i.e., eager loading), 
// or leave room for them to be populated later by query (i.e., lazy loading)
// go with lazy loading by default

// lazy load author
            Author author = new Author();
            author.setId(rs.getInt("author_id"));
// lazy load book
            Book book = new Book();
            book.setId(rs.getInt("book_id"));

            authorBook.setId(rs.getInt("id"));

            return authorBook;
        }
    }

}
