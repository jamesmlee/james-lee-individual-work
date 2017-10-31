/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperPerson;
import com.sg.superherosightings.model.SuperPersonOrganization;
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
public class SuperPersonOrganizationDaoDbImpl implements SuperPersonOrganizationDao {

    private JdbcTemplate jdbcTemplate;
// setter injection

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static String SQL_INSERT_SUPERPERSON_ORGANIZATION
            = "INSERT INTO superperson_organization(superpersonid, organizationid) VALUES (?, ?)";
    private static String SQL_GET_SUPERPERSON_ORGANIZATION
            = "SELECT * FROM superperson_organization WHERE superperson_organizationid = ?";
    private static String SQL_UPDATE_SUPERPERSON_ORGANIZATION
            = "UPDATE superperson_organization SET superpersonid = ?, organizationid = ? "
            + "WHERE superperson_organizationid = ?";
    private static String SQL_DELETE_SUPERPERSON_ORGANIZATION
            = "DELETE FROM superperson_organization WHERE superperson_organizationid = ?";
    private static String SQL_LIST_SUPERPERSON_ORGANIZATIONS
            = "SELECT * FROM superperson_organization LIMIT ?,?";
    private static String SQL_GET_SUPERPERSONORGANIZATION_BY_SUPERPERSON_AND_ORGANIZATION
            = "SELECT * FROM SuperPerson_Organization WHERE SuperPersonId = ? AND OrganizationId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperPersonOrganization createSuperPersonOrganization(SuperPersonOrganization superPersonOrganization) {

        jdbcTemplate.update(SQL_INSERT_SUPERPERSON_ORGANIZATION,
                superPersonOrganization.getSuperPerson().getSuperPersonId(),
                superPersonOrganization.getOrganization().getOrganizationId()
        );

        int superPersonOrganizationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        superPersonOrganization.setSuperPersonOrganizationId(superPersonOrganizationId);
        return superPersonOrganization;
    }

    @Override
    public SuperPersonOrganization getSuperPersonOrganizationById(Integer superPersonOrganizationId) {
        try {
            SuperPersonOrganization superPersonOrganization
                    = jdbcTemplate.queryForObject(SQL_GET_SUPERPERSON_ORGANIZATION,
                            new SuperPersonOrganizationMapper(), superPersonOrganizationId);
            return superPersonOrganization;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<SuperPersonOrganization> getAllSuperPersonOrganizations(int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_SUPERPERSON_ORGANIZATIONS,
                new SuperPersonOrganizationMapper(), offset, limit);
    }

//    @Override
//    public SuperPersonOrganization updateSuperPersonOrganization(SuperPersonOrganization superPersonOrganization) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public SuperPersonOrganization deleteSuperPersonOrganization(SuperPersonOrganization superPersonOrganization) {
        jdbcTemplate.update(SQL_DELETE_SUPERPERSON_ORGANIZATION, superPersonOrganization.getSuperPersonOrganizationId());
        return superPersonOrganization;
    }

    @Override
    public SuperPersonOrganization getSuperPersonOrganizationBySuperPersonAndOrganization(SuperPerson superPerson, Organization organization) {
        Integer superPersonId = superPerson.getSuperPersonId();
        Integer organizationId = organization.getOrganizationId();
        return jdbcTemplate.queryForObject(SQL_GET_SUPERPERSONORGANIZATION_BY_SUPERPERSON_AND_ORGANIZATION, 
                new SuperPersonOrganizationMapper(), superPersonId, organizationId);
    }

    private static final class SuperPersonOrganizationMapper implements RowMapper<SuperPersonOrganization> {

        @Override
        public SuperPersonOrganization mapRow(ResultSet rs, int i) throws SQLException {
            SuperPersonOrganization spo = new SuperPersonOrganization();
            spo.setSuperPersonOrganizationId(rs.getInt("superPerson_OrganizationId"));
// lazy loading superperson
            SuperPerson superperson = new SuperPerson();
            superperson.setSuperPersonId(rs.getInt("superPersonId"));
            spo.setSuperPerson(superperson);
// lazy loading organization 
            Organization organization = new Organization();
            organization.setOrganizationId(rs.getInt("organizationId"));
            spo.setOrganization(organization);
            return spo;
        }
    }

}
