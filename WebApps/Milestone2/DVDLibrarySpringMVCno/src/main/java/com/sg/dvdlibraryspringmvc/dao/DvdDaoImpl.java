/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Director;
import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author James
 */
public class DvdDaoImpl implements DvdDao {

    private static String SQL_INSERT_DVD
            = "INSERT INTO dvd (title, release_date, director_id, studio, mpaa_rating, notes) VALUES (?, ?, ?, ?, ?, ?)";
    private static String SQL_GET_DVD = "SELECT * FROM dvd WHERE id = ?";
    private static String SQL_UPDATE_DVD
            = "UPDATE dvd SET title = ?, release_date = ?, director_id = ?, studio = ?, mpaa_rating = ?, notes = ? WHERE id = ?";
    private static String SQL_DELETE_DVD = "DELETE FROM dvd WHERE id = ?";
    private static String SQL_LIST_DVD = "SELECT * FROM dvd";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getTitle(),
                dvd.getReleaseDate(),
                dvd.getDirectorName().getId(),
                dvd.getStudio(),
                dvd.getMpaaRating(),
                dvd.getNotes());

        int dvdId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);

        dvd.setId(dvdId);
    }

    @Override
    public void deleteDvd(int dvdId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDVD(Dvd dvd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dvd getDvdById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dvd> getAllDvd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static final class DvdMapper implements RowMapper<Dvd> {

        @Override
        public Dvd mapRow(ResultSet rs, int i) throws SQLException {
            Dvd dvd = new Dvd();
            dvd.setId(rs.getInt("id"));
            dvd.setTitle(rs.getString("title"));
            dvd.setReleaseDate(rs.getTimestamp("release_date").toLocalDateTime().toLocalDate());
            dvd.setStudio("studio");
            dvd.setMpaaRating("mpaa_rating");
            dvd.setNotes("notes");

// lazy load director_id ... returning an object that just has an id
            Director director = new Director();
            director.setId(rs.getInt("director_id"));

            return dvd;
        }
    }

}
