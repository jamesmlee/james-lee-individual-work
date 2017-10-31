/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperPerson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class SightingDaoDbImpl implements SightingDao {

    private JdbcTemplate jdbcTemplate; //declare object so we can use query, queryForObject, update methods later

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    } //constructor, we'll use this with beans later
    private static String SQL_INSERT_SIGHTING = 
            "INSERT INTO Sighting (Date, LocationId, Description) VALUES (?, ?, ?)";
    private static String SQL_GET_SIGHTING = 
            "SELECT * FROM Sighting WHERE SightingId = ?";
    private static String SQL_UPDATE_SIGHTING = 
            "UPDATE Sighting SET Date = ?, Locationid = ?, Description = ? WHERE SightingId = ?";
    private static String SQL_DELETE_SIGHTING = 
            "DELETE FROM Sighting WHERE SightingId = ?";
    private static String SQL_LIST_SIGHTINGS = 
            "SELECT * FROM Sighting ORDER BY `Sighting`.`Date` DESC LIMIT ?, ?";
    private static String SQL_LIST_SIGHTINGS_BY_DATE = 
            "SELECT `Sighting`.* FROM `Sighting` \n"
            + "WHERE `date`= ? LIMIT ?, ?;";
    private static String SQL_LIST_SIGHTINGS_BY_SUPERPERSON = "Select s.* from Sighting s\n"
            + "inner join SuperPerson_Sighting sps on sps.sightingId = s.sightingId\n"
            + "inner join SuperPerson sp on sp.SuperPersonId = sps.SuperPersonId\n"
            + "where sp.SuperPersonId = ? ORDER BY `s`.`Date` DESC LIMIT ?,?;";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting createSighting(Sighting sighting) {
        Location location = sighting.getLocation();

        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                java.sql.Date.valueOf(sighting.getDate()),
                location.getLocationId(),
                sighting.getDescription());

        int sightingId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(sightingId);
        return sighting;
    }

    @Override
    public Sighting getSightingById(Integer sightingId) {
        try {
            Sighting sighting
                    = jdbcTemplate.queryForObject(SQL_GET_SIGHTING, new SightingMapper(), sightingId);
            return sighting;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings(int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_SIGHTINGS, new SightingMapper(), offset, limit);
    }

    @Override
    public Sighting updateSighting(Sighting sighting) {
        Location sightingLocation = sighting.getLocation();

        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                java.sql.Date.valueOf(sighting.getDate()),
                sightingLocation.getLocationId(),
                sighting.getDescription(),
                sighting.getSightingId()
        );
        return sighting;
    }

    @Override
    public Sighting deleteSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sighting.getSightingId());
        return sighting;
    }

    @Override
    public List<Sighting> getAllSightingsByDate(LocalDate date, int offset, int limit) {
        java.sql.Date convertedDate = java.sql.Date.valueOf(date);
        return jdbcTemplate.query(SQL_LIST_SIGHTINGS_BY_DATE, new SightingMapper(), convertedDate, offset, limit);
    }
    
    public List<Sighting> getAllSightingsBySuperPerson(SuperPerson sp, int offset, int limit){
        return jdbcTemplate.query(SQL_LIST_SIGHTINGS_BY_SUPERPERSON, 
                new SightingMapper(), sp.getSuperPersonId(), offset, limit);
    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();
            s.setSightingId(rs.getInt("SightingId"));
            java.sql.Date dbSqlDate = rs.getDate("Date");
            s.setDate(dbSqlDate.toLocalDate());
            s.setDescription(rs.getString("Description"));

            //lazy loading FKs -- the service fills in the rest when in use
            Location location = new Location();
            location.setLocationId(rs.getInt("LocationId"));
            s.setLocation(location);

            return s;
        }
    }

}
