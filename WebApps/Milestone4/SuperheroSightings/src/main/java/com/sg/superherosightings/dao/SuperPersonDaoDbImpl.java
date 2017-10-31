/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
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
public class SuperPersonDaoDbImpl implements SuperPersonDao {

    private JdbcTemplate jdbcTemplate;
// setter injection

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static String SQL_INSERT_SUPERPERSON
            = "INSERT INTO superperson(name, description, isgood) VALUES (?, ?, ?)";
    private static String SQL_GET_SUPERPERSON
            = "SELECT * FROM superperson WHERE superpersonid = ?";
    private static String SQL_UPDATE_SUPERPERSON
            = "UPDATE superperson SET name = ?, description = ?, isgood = ? "
            + "WHERE superpersonid = ?";
    private static String SQL_DELETE_SUPERPERSON
            = "DELETE FROM superperson WHERE superpersonid = ?";
    private static String SQL_LIST_SUPERPERSONS
            = "SELECT * FROM superperson LIMIT ?, ?";
    private static String SQL_LIST_SUPERPERSONS_BY_SIGHTING
            = "SELECT `superperson`.* "
            + "FROM `superperson` "
            + "INNER JOIN `superperson_sighting` "
            + "ON `superperson`.`SuperPersonId` = `superperson_sighting`.`SuperPersonId` "
            + "INNER JOIN `sighting` "
            + "ON `superperson_sighting`.`SightingId` = `sighting`.`SightingId` "
            + "WHERE `sighting`.`sightingId` = ? "
            + "ORDER BY `superperson`.`name` LIMIT ?, ?; ";
    private static String SQL_LIST_SUPERPERSONS_BY_SIGHTING_LOCATION
            = "SELECT `superperson`.* "
            + "FROM `superperson` "
            + "INNER JOIN `superperson_sighting` "
            + "ON `superperson`.`SuperPersonId` = `superperson_sighting`.`SuperPersonId` "
            + "INNER JOIN `sighting` "
            + "ON `superperson_sighting`.`SightingId` = `sighting`.`SightingId` "
            + "INNER JOIN `location` "
            + "ON `sighting`.`LocationId` = `location`.`LocationId` "
            + "WHERE `location`.`LocationId` = ? "
            + "ORDER BY `superperson`.`name` LIMIT ?, ?; ";
    private static String SQL_LIST_SUPERPERSONS_BY_ORGANIZATION
            = "SELECT `superperson`.* "
            + "FROM `superperson` "
            + "INNER JOIN `superperson_organization` "
            + "ON `superperson`.`superpersonId` = `superperson_organization`.`superpersonId` "
            + "INNER JOIN `organization` "
            + "ON `superperson_organization`.`OrganizationId` = `organization`.`OrganizationId` "
            + "WHERE `organization`.`organizationId` = ? "
            + "ORDER BY `superperson`.`name` LIMIT ?, ?; ";

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SuperPerson createSuperPerson(SuperPerson superPerson) {
        jdbcTemplate.update(SQL_INSERT_SUPERPERSON,
                superPerson.getName(),
                superPerson.getDescription(),
                superPerson.getIsGood());

        int addressId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        superPerson.setSuperPersonId(addressId);

        return superPerson;
    }

    @Override
    public SuperPerson getSuperPersonById(Integer superPersonId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_SUPERPERSON, new SuperPersonMapper(), superPersonId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperPerson> getAllSuperPersons(int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_SUPERPERSONS,
                new SuperPersonMapper(), offset, limit);
    }

    @Override
    public SuperPerson updateSuperPerson(SuperPerson superPerson) {
        jdbcTemplate.update(SQL_UPDATE_SUPERPERSON,
                superPerson.getName(),
                superPerson.getDescription(),
                superPerson.getIsGood(),
                superPerson.getSuperPersonId());

        return superPerson;
    }

    @Override
    public SuperPerson deleteSuperPerson(SuperPerson superPerson) {
        jdbcTemplate.update(SQL_DELETE_SUPERPERSON, superPerson.getSuperPersonId());
        return superPerson;
    }

    @Override
    public List<SuperPerson> getAllSuperPersonsBySighting(Sighting sighting, int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_SUPERPERSONS_BY_SIGHTING, new SuperPersonMapper(), sighting.getSightingId(), offset, limit);
    }

    @Override
    public List<SuperPerson> getAllSuperPersonsBySightingLocation(Location location, int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_SUPERPERSONS_BY_SIGHTING_LOCATION,
                new SuperPersonMapper(), location.getLocationId(), offset, limit);
    }

    @Override
    public List<SuperPerson> getAllSuperPersonsByOrganization(Organization organization, int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_SUPERPERSONS_BY_ORGANIZATION,
                new SuperPersonMapper(), organization.getOrganizationId(), offset, limit);
    }

    private static final class SuperPersonMapper implements RowMapper<SuperPerson> {

        @Override
        public SuperPerson mapRow(ResultSet rs, int i) throws SQLException {
            SuperPerson sp = new SuperPerson();
            sp.setSuperPersonId(rs.getInt("superPersonId"));
            sp.setName(rs.getString("name"));
            sp.setDescription(rs.getString("description"));
            sp.setIsGood(rs.getBoolean("isGood"));
            return sp;
        }
    }

}
