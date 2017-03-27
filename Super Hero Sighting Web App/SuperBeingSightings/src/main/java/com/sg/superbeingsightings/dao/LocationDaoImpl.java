/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.Address;
import com.sg.superbeingsightings.dto.Location;
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
public class LocationDaoImpl implements LocationDao {
    
    private static final String SQL_ADD_LOCATION
            = "Insert into Location "
            + "(AddressID, Name, Description, Latitude, Longitude) "
            + "values (?, ?, ?, ?, ?)";
    private static final String SQL_REMOVE_LOCATION
            = "Delete from Location where LocationID = ?";
    private static final String SQL_UPDATE_LOCATION
            = "Update Location set "
            + "AddressID = ?, "
            + "Name = ?, "
            + "Description = ?, "
            + "Latitude = ?, "
            + "Longitude = ? "
            + "where LocationID = ?";
    private static final String SQL_GET_LOCATION
            = "Select * from Location where LocationID = ?";
    private static final String SQL_GET_ALL_LOCATION
            = "Select * from Location";

    private JdbcTemplate jt;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jt = jdbcTemplate;
    }
    
    @Override
    public void addLocation(Location location) {
        jt.update(SQL_ADD_LOCATION,
                location.getAddress().getAddressID(),
                location.getName(),
                location.getDesc(),
                location.getLatitude(),
                location.getLongitude());
        int locationID = jt.queryForObject(
                "select LAST_INSERT_ID()", Integer.class);
        location.setLocationID(locationID);
    }

    @Override
    public void removeLocation(int locationID) {
        jt.update(SQL_REMOVE_LOCATION, locationID);
    }

    @Override
    public void updateLocation(Location location) {
        jt.update(SQL_UPDATE_LOCATION,
                location.getAddress().getAddressID(),
                location.getName(),
                location.getDesc(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationID());
    }

    @Override
    public Location getLocation(int locationID) {
        try {
            return jt.queryForObject(SQL_GET_LOCATION, new LocationMapper(), locationID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocation() {
        return jt.query(SQL_GET_ALL_LOCATION, new LocationMapper());
    }
    
    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Address address = new Address();
            Location location = new Location();
            
            address.setAddressID(rs.getInt("AddressID"));
            
            location.setLocationID(rs.getInt("LocationID"));
            location.setAddress(address);
            location.setName(rs.getString("Name"));
            location.setDesc(rs.getString("Description"));
            location.setLatitude(rs.getBigDecimal("Latitude"));
            location.setLongitude(rs.getBigDecimal("Longitude"));            
            return location;
        }
    }  
}
