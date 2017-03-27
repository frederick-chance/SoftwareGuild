/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.Address;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface AddressDao {
    
    public void addAddress(Address address);
    
    public void removeAddress(int addressID);
    
    public void updateAddress(Address address);
    
    public Address getAddress(int addressID);
    
    public List<Address> getAllAddress();
    
}
