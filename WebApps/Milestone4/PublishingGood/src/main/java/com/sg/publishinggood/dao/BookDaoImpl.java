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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author James
 */
public class BookDaoImpl implements BookDao {
    
    private static String SQL_LIST_BOOKS_BY_AUTHOR = "SELECT b.* FROM book b" 
            + "INNER JOIN author_book ab ON ab.book_id = b.id"
            + "WHERE ab.book_id = ? limit ?,?";
    
    private JdbcTemplate jdbcTemplate;

    @Override
    public Book get(Integer id) {
        return null;
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public Book create(Book book) {
        return null;
    }

    @Override
    public List<Book> list() {
        return null;
    }

    @Override
    public List<Book> getBooksByAuthor(Author author, int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_BOOKS_BY_AUTHOR, new BookMapper(),
                author.getId(), offset, limit);
    }

    private static final class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int i) throws SQLException {
            Book b = new Book();
            b.setName(rs.getString("name"));
            b.setId(rs.getInt("id"));
            return b;
        }
    }

}
