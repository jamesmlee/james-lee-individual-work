/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.publishinggood.dao;

import com.sg.publishinggood.dto.Author;
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
public class AuthorDaoImpl implements AuthorDao {

    private static String SQL_INSERT_AUTHOR = "INSERT INTO author (name) VALUES (?)";
    private static String SQL_GET_AUTHOR = "SELECT * FROM author WHERE id = ?";
    private static String SQL_UPDATE_AUTHOR = "UPDATE author SET name = ? WHERE id = ?";
    private static String SQL_DELETE_AUTHOR = "DELETE FROM author WHERE id = ?";
    private static String SQL_LIST_AUTHORS = "SELECT * FROM author";
    
    private static String SQL_LIST_AUTHORS_BY_BOOK = "SELECT * FROM author a" 
            + "INNER JOIN author_book ab ON ab.author_id = a.id"
            + "WHERE ab.book_id = ? limit ?,?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public Author get(Integer id) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_AUTHOR, new AuthorMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Author author) {
        jdbcTemplate.update(SQL_UPDATE_AUTHOR,
                author.getName(),
                author.getId());
    }
    
    @Override
    public void delete(Author author) {
        jdbcTemplate.update(SQL_DELETE_AUTHOR, author.getId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Author create(Author author) {
        jdbcTemplate.update(SQL_INSERT_AUTHOR, author.getName());

        int authorId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        author.setId(authorId);

        return author;
    }

    @Override
    public List<Author> list() {
        return jdbcTemplate.query(SQL_LIST_AUTHORS, new AuthorMapper());
    }

    @Override
    public List<Author> getAuthorsByBook(Book book, int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_AUTHORS_BY_BOOK, new AuthorMapper()
                , book.getId(), offset, limit);
    }

    private static final class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            Author au = new Author();
            au.setName(rs.getString("name"));
            au.setId(rs.getInt("id"));
            return au;
        }
    }

}
