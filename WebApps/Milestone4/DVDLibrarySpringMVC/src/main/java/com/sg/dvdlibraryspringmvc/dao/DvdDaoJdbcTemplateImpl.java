/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author James
 */
public class DvdDaoJdbcTemplateImpl implements DvdDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_DVD
            = "INSERT INTO dvd (title, releaseDate, mpaaRating, "
            + "director, studio, notes) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_DVD
            = "DELETE FROM dvd WHERE id = ?";
    private static final String SQL_UPDATE_DVD
            = "UPDATE dvd SET title = ?, releaseDate = ?, mpaaRating = ?, "
            + "director = ?, studio = ?, notes = ?, WHERE id =  ?";
    private static final String SQL_SELECT_DVD
            = "SELECT * FROM dvd WHERE id = ?";
    private static final String SQL_SELECT_ALL_DVDS
            = "SELECT * FROM dvd";
//    private static final String SQL_SEARCH_DVDS
//            = "SELECT * FROM dvd WHERE id = ? OR title = ? OR releaseDate = ?"
//            + "OR mpaaRating = ? OR director = ? OR studio = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getTitle(),
                java.sql.Date.valueOf(dvd.getReleaseDate()),
                dvd.getMpaaRating(),
                dvd.getDirector(),
                dvd.getStudio(),
                dvd.getNotes());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        dvd.setId(id);
    }

    @Override
    public void deleteDvd(Integer id) {
        jdbcTemplate.update(SQL_DELETE_DVD, id);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_UPDATE_DVD,
                dvd.getTitle(),
                java.sql.Date.valueOf(dvd.getReleaseDate()),
                dvd.getMpaaRating(),
                dvd.getDirector(),
                dvd.getStudio(),
                dvd.getNotes(),
                dvd.getId());
    }

    @Override
    public Dvd getDvdById(Integer id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD, new DvdMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Dvd> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS,
                new DvdMapper());
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        if (criteria.isEmpty()) {
            return getAllDvds();
        } else {
// build a prepared statement based on the user's search terms
            StringBuilder sQuery
                    = new StringBuilder("SELECT * FROM dvd WHERE ");
// build the where clause
            int numParams = criteria.size();
            int paramPosition = 0;
// we'll put the positional parameters into an array. order of the parameters 
// will match the order in which we get the search criteria from the map
            String[] paramVals = new String[numParams];
            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();
            // build up the where clause based on the key/value pairs in 
            // the map build where clause and positional parameter array
            while (iter.hasNext()) {
                SearchTerm currentKey = iter.next();
                // if we are not the first one in, we must add an AND to 
                // the where clause
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }
                // now append our criteria name
                sQuery.append(currentKey);
                sQuery.append(" = ? ");
                // grab the value for this search criteria and put it into 
                // the paramVals array
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }

            return jdbcTemplate.query(sQuery.toString(),
                    new DvdMapper(),
                    paramVals);
        }
    }

    private static final class DvdMapper implements RowMapper<Dvd> {

        @Override
        public Dvd mapRow(ResultSet rs, int i) throws SQLException {
            Dvd dvd = new Dvd();
            dvd.setId(rs.getInt("id"));
            dvd.setTitle(rs.getString("title"));
            dvd.setReleaseDate(rs.getTimestamp("releaseDate").
                    toLocalDateTime().toLocalDate());
            dvd.setMpaaRating("mpaaRating");
            dvd.setDirector(rs.getString("director"));
            dvd.setStudio(rs.getString("studio"));
            dvd.setNotes(rs.getString("notes"));
            return dvd;
        }
    }

}
