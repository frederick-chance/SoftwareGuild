/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.Affiliation;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface AffiliationDao {
    
    public void addAffiliation(Affiliation affiliation);
    
    public void removeAffiliation(int affiliationID);
    
    public void updateAffiliation(Affiliation affiliation);
    
    public Affiliation getAffiliation(int affiliationID);
    
    public List<Affiliation> getAllAffiliation();
    
    public List<Affiliation> getAllAffiliationBySuperBeing(int superbeingID);
    
}
