/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.Address;
import com.sg.superbeingsightings.dto.Location;
import com.sg.superbeingsightings.dto.Sighting;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class SightingDaoTest {
    SightingDao sightingDao;
    LocationDao locationDao;
    AddressDao addressDao;
    
    public SightingDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        addressDao = ctx.getBean("addressDao", AddressDao.class);
        
        List<Sighting> sightings = sightingDao.getAllSighting();
        for (Sighting sighting : sightings) {
            sightingDao.removeSighting(sighting.getSightingID());
        }
        
        List<Location> locations = locationDao.getAllLocation();
        for (Location location : locations) {
            locationDao.removeLocation(location.getLocationID());
        }
        
        List<Address> addresses = addressDao.getAllAddress();
        for (Address address : addresses) {
            addressDao.removeAddress(address.getAddressID());
        }
    }
    
    @After
    public void tearDown() {
        List<Sighting> sightings = sightingDao.getAllSighting();
        for (Sighting sighting : sightings) {
            sightingDao.removeSighting(sighting.getSightingID());
        }
        
        List<Location> locations = locationDao.getAllLocation();
        for (Location location : locations) {
            locationDao.removeLocation(location.getLocationID());
        }
        
        List<Address> addresses = addressDao.getAllAddress();
        for (Address address : addresses) {
            addressDao.removeAddress(address.getAddressID());
        }
    }

    /**
     * Test of addSighting, getSighting and removeSighting method, of class SightingDao.
     */
    @Test
    public void testAddGetRemoveSighting() {
        Address address = new Address();
        address.setStreet("401 S Main St");
        address.setCity("Akron");
        address.setState("OH");
        address.setZip("12345");
        
        addressDao.addAddress(address);
        
        Location location = new Location();
        location.setAddress(address);
        location.setName("401 Loft");
        location.setDesc("Its Lovely.");
        location.setLatitude(new BigDecimal("-23.2347"));
        location.setLongitude(new BigDecimal("123.1234"));
        
        locationDao.addLocation(location);
        
        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        sighting.setDate(java.sql.Date.valueOf("2017-3-27"));
        sighting.setTime(Time.valueOf("13:37:00"));
        sighting.setDesc("Plastic bag floated in the wind.");
        
        sightingDao.addSighting(sighting);
        
        Sighting sightingFromDao = sightingDao.getSighting(sighting.getSightingID());
        Location locationFromDao = locationDao.getLocation(location.getLocationID());
        Address addressFromDao = addressDao.getAddress(locationFromDao.getAddress().getAddressID());
        locationFromDao.setAddress(addressFromDao);
        sightingFromDao.setLocation(locationFromDao);
        assertTrue(sightingFromDao.equals(sighting));
    }

    /**
     * Test of addSighting, getSighting, updateSighting method, of class SightingDao.
     */
    @Test
    public void testRemoveSighting() {
    }

    /**
     * Test of updateSighting method, of class SightingDao.
     */
    @Test
    public void testUpdateSighting() {
    }

    /**
     * Test of getSighting method, of class SightingDao.
     */
    @Test
    public void testGetSighting() {
    }

    /**
     * Test of getAllSighting method, of class SightingDao.
     */
    @Test
    public void testGetAllSighting() {
    }

    /**
     * Test of getAllSightingBySuperBeing method, of class SightingDao.
     */
    @Test
    public void testGetAllSightingBySuperBeing() {
    }

    /**
     * Test of getAllSightingByLocation method, of class SightingDao.
     */
    @Test
    public void testGetAllSightingByLocation() {
    }
    
}
