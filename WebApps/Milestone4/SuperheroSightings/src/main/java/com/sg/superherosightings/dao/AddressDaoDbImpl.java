/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Address;
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
public class AddressDaoDbImpl implements AddressDao {

    private JdbcTemplate jdbcTemplate;
// setter injection

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static String SQL_INSERT_ADDRESS
            = "INSERT INTO address(street, city, state, zipcode) VALUES (?, ?, ?, ?)";
    private static String SQL_GET_ADDRESS
            = "SELECT * FROM address WHERE addressid = ?";
    private static String SQL_UPDATE_ADDRESS
            = "UPDATE address SET street = ?, city = ?, state = ?, zipcode = ? "
            + "WHERE addressid = ?";
    private static String SQL_DELETE_ADDRESS
            = "DELETE FROM address WHERE addressid = ?";
    private static String SQL_LIST_ADDRESSES
            = "SELECT * FROM address LIMIT ?, ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Address createAddress(Address address) {
        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getStreet(), address.getCity(), address.getState(), address.getZipcode());

        int addressId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        address.setAddressId(addressId);

        return address;
    }

    @Override
    public Address getAddressById(Integer addressId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_ADDRESS, new AddressMapper(), addressId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Address> getAllAddresses(int offset, int limit) {
        return jdbcTemplate.query(SQL_LIST_ADDRESSES,
                new AddressMapper(), offset, limit);
    }

    @Override
    public Address updateAddress(Address address) {
        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZipcode(),
                address.getAddressId());

        return address;
    }

    @Override
    public Address deleteAddress(Address address) {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, address.getAddressId());
        return address;
    }

    private static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {
            Address ad = new Address();
            ad.setAddressId(rs.getInt("addressId"));
            ad.setStreet(rs.getString("street"));
            ad.setCity(rs.getString("city"));
            ad.setState(rs.getString("state"));
            ad.setZipcode(rs.getString("zipcode"));
            return ad;
        }
    }

}
