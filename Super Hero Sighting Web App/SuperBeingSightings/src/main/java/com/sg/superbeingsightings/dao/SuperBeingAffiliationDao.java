/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.SuperBeingAffiliation;

/**
 *
 * @author apprentice
 */
public interface SuperBeingAffiliationDao {
    
    void addSuperBeingAffiliationAssoc(SuperBeingAffiliation sba);
    
    void removeSuperBeingAffiliationAssoc(int superbeingID, int affiliationID);
    
}
