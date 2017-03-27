/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class AddressDaoImpl implements AddressDao {
    
    private static final String SQL_ADD_ADDRESS
            = "Insert into Address (Street, City, State, Zip) values (?, ?, ?, ?)";
    private static final String SQL_REMOVE_ADDRESS
            = "Delete from Address where AddressID = ?";
    private static final String SQL_UPDATE_ADDRESS
            = "Update Address set Street = ?, City = ?, State = ?, Zip = ? where AddressID = ?";
    private static final String SQL_GET_ADDRESS
            = "Select * from Address where AddressID = ?";
    private static final String SQL_GET_ALL_ADDRESS
            = "Select * from Address";
    // TODO
    private static final String SQL_GET_ADDRESS_BY_CITY
            = "Select * from Address where City = ?";
    private static final String SQL_GET_ADDRESS_BY_STATE
            = "Select * from Address where State = ?";    
    private static final String SQL_GET_ADDRESS_BY_ZIP
            = "Select * from Address where Zip = ?";

    private JdbcTemplate jt;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jt = jdbcTemplate;
    }
    
    @Override
    public void addAddress(Address address) {
        jt.update(SQL_ADD_ADDRESS,
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip());
        int addressID = jt.queryForObject(
                "select LAST_INSERT_ID()",Integer.class);
        address.setAddressID(addressID);
    }

    @Override
    public void removeAddress(int addressID) {
       jt.update(SQL_REMOVE_ADDRESS, addressID);
    }

    @Override
    public void updateAddress(Address address) {
        jt.update(SQL_UPDATE_ADDRESS,
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip(),
                address.getAddressID());
    }

    @Override
    public Address getAddress(int addressID) {
        try {
            return jt.queryForObject(SQL_GET_ADDRESS, new AddressMapper(), addressID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Address> getAllAddress() {
        return jt.query(SQL_GET_ALL_ADDRESS, new AddressMapper());
    }
    
    private static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {
            Address address = new Address();
            address.setAddressID(rs.getInt("AddressID"));
            address.setStreet(rs.getString("Street"));
            address.setCity(rs.getString("City"));
            address.setState(rs.getString("State"));
            address.setZip(rs.getString("Zip"));
            return address;
        }
    }  
}
