/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.SuperPower;
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
public class SuperPowerDaoImpl implements SuperPowerDao {
    
    private static final String SQL_ADD_SUPERPOWER
            = "Insert into SuperPower (Name, Description) values (?, ?)";
    private static final String SQL_REMOVE_SUPERPOWER
            = "Delete from SuperPower where SuperPowerID = ?";
    private static final String SQL_UPDATE_SUPERPOWER
            = "Update SuperPower set Name = ?, Description = ? where SuperPowerID = ?";
    private static final String SQL_GET_SUPERPOWER
            = "Select * from SuperPower where SuperPowerID = ?";
    private static final String SQL_GET_ALL_SUPERPOWER
            = "Select * from SuperPower";
    // TODO
    private static final String SQL_GET_SUPERPOWER_BY_NAME
            = "Select * from SuperPower where Name = ?";
    
    private JdbcTemplate jt;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jt = jdbcTemplate;
    }

    @Override
    public void addSuperPower(SuperPower superpower) {
        jt.update(SQL_ADD_SUPERPOWER,
                superpower.getName(),
                superpower.getDesc());
        int superpowerID = jt.queryForObject(
                "select LAST_INSERT_ID()", Integer.class);
        superpower.setSuperPowerID(superpowerID);
    }

    @Override
    public void removeSuperPower(int superpowerID) {
        jt.update(SQL_REMOVE_SUPERPOWER, superpowerID);
    }

    @Override
    public void updateSuperPower(SuperPower superpower) {
        jt.update(SQL_UPDATE_SUPERPOWER,
                superpower.getName(),
                superpower.getDesc(),
                superpower.getSuperPowerID());
    }

    @Override
    public SuperPower getSuperPower(int superpowerID) {
        try{
            return jt.queryForObject(SQL_GET_SUPERPOWER, new SuperPowerMapper(), superpowerID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        } 
    }

    @Override
    public List<SuperPower> getAllSuperPower() {
        return jt.query(SQL_GET_ALL_SUPERPOWER, new SuperPowerMapper());
    }

    @Override
    public List<SuperPower> getAllSuperPowerBySuperBeing(int superbeingID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static final class SuperPowerMapper implements RowMapper<SuperPower> {

        @Override
        public SuperPower mapRow(ResultSet rs, int i) throws SQLException {
            SuperPower superpower = new SuperPower();
            superpower.setSuperPowerID(rs.getInt("SuperPowerID"));
            superpower.setName(rs.getString("Name"));
            superpower.setDesc(rs.getString("Description"));
            return superpower;
        }
    } 
}
