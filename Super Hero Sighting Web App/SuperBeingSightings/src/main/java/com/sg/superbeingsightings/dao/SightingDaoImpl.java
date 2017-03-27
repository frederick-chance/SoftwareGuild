/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.Location;
import com.sg.superbeingsightings.dto.Sighting;
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
public class SightingDaoImpl implements SightingDao {
    
    private static final String SQL_ADD_SIGHTING
            = "Insert into Sighting "
            + "(LocationID, Date, Time, Description) "
            + "values (?, ?, ?, ?)";
    private static final String SQL_REMOVE_SIGHTING
            = "Delete from Sighting where SightingID = ?";
    private static final String SQL_UPDATE_SIGHTING
            = "Update Sighting set "
            + "LocationID = ?, "
            + "Date = ?, "
            + "Time = ?, "
            + "Description = ? "
            + "where SightingID = ?";
    private static final String SQL_GET_SIGHTING
            = "Select * from Sighting where SightingID = ?";
    private static final String SQL_GET_ALL_SIGHTING
            = "Select * from Sighting";
    
    private JdbcTemplate jt;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jt = jdbcTemplate;
    }

    @Override
    public void addSighting(Sighting sighting) {
        jt.update(SQL_ADD_SIGHTING,
                sighting.getLocation().getLocationID(),
                sighting.getDate(),
                sighting.getTime(),
                sighting.getDesc());
        int sightingID = jt.queryForObject(
                "select LAST_INSERT_ID()", Integer.class);
        sighting.setSightingID(sightingID);
    }

    @Override
    public void removeSighting(int sightingID) {
        jt.update(SQL_REMOVE_SIGHTING, sightingID);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jt.update(SQL_UPDATE_SIGHTING,
                sighting.getLocation().getLocationID(),
                sighting.getDate(),
                sighting.getTime(),
                sighting.getDesc());
    }

    @Override
    public Sighting getSighting(int sightingID) {
        try {
            return jt.queryForObject(SQL_GET_SIGHTING, new SightingMapper(), sightingID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSighting() {
        return jt.query(SQL_GET_ALL_SIGHTING, new SightingMapper());
    }

    @Override
    public List<Sighting> getAllSightingBySuperBeing(int superbeingID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> getAllSightingByLocation(int locationID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            Sighting sighting = new Sighting();
            
            location.setLocationID(rs.getInt("LocationID"));
            
            sighting.setSightingID(rs.getInt("SightingID"));
            sighting.setLocation(location);
            sighting.setDate(rs.getDate("Date"));
            sighting.setTime(rs.getTime("Time"));
            sighting.setDesc(rs.getString("Description"));
            return sighting;
        } 
    } 
}
