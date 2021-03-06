/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.AddressDao;
import com.sg.superherosightings.model.Address;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author James
 */
public class AddressServiceImpl implements AddressService {

    AddressDao addressDao;

    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public Address createAddress(Address address) {
        return addressDao.createAddress(address);
    }

    @Override
    public Address getAddressById(Integer addressId) {
        return addressDao.getAddressById(addressId);
    }

    @Override
    public List<Address> getAllAddresses(int offset, int limit) {
        return addressDao.getAllAddresses(offset, limit);
    }

    @Override
    public Address updateAddress(Address address) {
        return addressDao.updateAddress(address);
    }

    @Override
    public Address deleteAddress(Address address) {
        return addressDao.deleteAddress(address);
    }

}
