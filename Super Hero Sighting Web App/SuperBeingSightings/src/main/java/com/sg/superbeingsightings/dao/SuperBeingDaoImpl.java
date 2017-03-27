/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.SuperBeing;
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
public class SuperBeingDaoImpl implements SuperBeingDao {
    
    private static final String SQL_ADD_SUPERBEING
            = "Insert into SuperBeing (Name, Alias, Description) values (?, ?, ?)";
    private static final String SQL_REMOVE_SUPERBEING
            = "Delete from SuperBeing where SuperBeingID = ?";
    private static final String SQL_UPDATE_SUPERBEING
            = "Update SuperBeing set Name = ?, Alias = ?, Description = ? where SuperBeingID = ?";
    private static final String SQL_GET_SUPERBEING
            = "Select * from SuperBeing where SuperBeingID = ?";
    private static final String SQL_GET_ALL_SUPERBEING
            = "Select * from SuperBeing";
    
    private JdbcTemplate jt;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jt = jdbcTemplate;
    }

    @Override
    public void addSuperBeing(SuperBeing superbeing) {
        jt.update(SQL_ADD_SUPERBEING,
                superbeing.getName(),
                superbeing.getAlias(),
                superbeing.getDesc());
        int superbeingID = jt.queryForObject(
                "select LAST_INSERT_ID()", Integer.class);
        superbeing.setSuperBeingID(superbeingID);
    }

    @Override
    public void removeSuperBeing(int superbeingID) {
        jt.update(SQL_REMOVE_SUPERBEING, superbeingID);
    }

    @Override
    public void updateSuperBeing(SuperBeing superbeing) {
        jt.update(SQL_UPDATE_SUPERBEING,
                superbeing.getName(),
                superbeing.getAlias(),
                superbeing.getDesc(),
                superbeing.getSuperBeingID());
    }

    @Override
    public SuperBeing getSuperBeing(int superbeingID) {
        try {
            return jt.queryForObject(SQL_GET_SUPERBEING, new SuperBeingMapper(), superbeingID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperBeing> getAllSuperBeing() {
        return jt.query((SQL_GET_ALL_SUPERBEING), new SuperBeingMapper());
    }

    @Override
    public List<SuperBeing> getAllSuperBeingByAffiliation(int affiliationID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SuperBeing> getAllSuperBeingBySuperPower(int superpowerID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SuperBeing> getAllSuperBeingBySighting(int sightingID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     private static final class SuperBeingMapper implements RowMapper<SuperBeing> {

        @Override
        public SuperBeing mapRow(ResultSet rs, int i) throws SQLException {
            SuperBeing superbeing = new SuperBeing();
            superbeing.setSuperBeingID(rs.getInt("SuperBeingID"));
            superbeing.setName(rs.getString("Name"));
            superbeing.setAlias(rs.getString("Alias"));
            superbeing.setDesc(rs.getString("Description"));
            return superbeing;
        }
    } 
}
