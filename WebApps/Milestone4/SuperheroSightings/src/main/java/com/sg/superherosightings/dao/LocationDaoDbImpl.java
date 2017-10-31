/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.SuperPerson;
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
public class LocationDaoDbImpl implements LocationDao {

    private JdbcTemplate jdbcTemplate;
// setter injection

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static String SQL_INSERT_LOCATION
            = "INSERT INTO location(name, description, latitude, longitude, addressid) "
            + "VALUES (?, ?, ?, ?, ?)";
    private static String SQL_GET_LOCATION
            = "SELECT * FROM location WHERE locationid = ?";
    private static String SQL_UPDATE_LOCATION
            = "UPDATE location "
            + "SET name = ?, description = ?, latitude = ?, longitude = ?, addressid = ? "
            + "WHERE locationid = ?";
    private static String SQL_DELETE_LOCATION
            = "DELETE FROM location WHERE locationid = ?";
    private static String SQL_LIST_LOCATIONS
            = "SELECT * FROM location LIMIT ?, ?";
    private static String SQL_LIST_LOCATIONS_BY_SUPERPERSON = "SELECT `location`.* FROM `location` "
            + "INNER JOIN `sighting` "
            + "ON `sighting`.`LocationId` = `location`.`LocationId` "
            + "INNER JOIN `superperson_sighting` "
            + "ON `superperson_sighting`.`SightingId` = `sighting`.`SightingId` "
            + "INNER JOIN `superperson` "
            + "ON `superperson_sighting`.`SuperpersonId` = `superperson`.`SuperPersonId` "
            + "WHERE `superperson`.`SuperPersonId` = ? ORDER BY `location`.`Name` LIMIT ?,?;";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location createLocation(Location location) {
        Address address = location.getAddress();

        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getLatitude(),
                location.getLongitude(),
                address.getAddressId());

        int locationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        location.setLocationId(locationId);
        return location;
    }

    @Override
    public Location getLocationById(Integer locationId) {
        try {
            Location location
                    = jdbcTemplate.queryForObject(SQL_GET_LOCATION, new LocationMapper(), locationId);
            return location;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations(int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_LOCATIONS, new LocationMapper(), offset, limit);
    }

    @Override
    public Location updateLocation(Location location) {
        Address address = location.getAddress();

        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getLatitude(),
                location.getLongitude(),
                address.getAddressId(),
                location.getLocationId());
        return location;
    }

    @Override
    public Location deleteLocation(Location location) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, location.getLocationId());
        return location;
    }

    @Override
    public List<Location> getAllLocationsBySuperPerson(SuperPerson superPerson, int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_LOCATIONS_BY_SUPERPERSON,
                new LocationMapper(), superPerson.getSuperPersonId(), offset, limit);
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location loc = new Location();
            loc.setLocationId(rs.getInt("locationId"));
            loc.setName(rs.getString("name"));
            loc.setDescription(rs.getString("description"));
// lazy loading address
            Address address = new Address();
            address.setAddressId(rs.getInt("addressId"));
            loc.setAddress(address);
            loc.setLatitude(rs.getString("latitude"));
            loc.setLongitude(rs.getString("longitude"));
            return loc;
        }
    }

}
