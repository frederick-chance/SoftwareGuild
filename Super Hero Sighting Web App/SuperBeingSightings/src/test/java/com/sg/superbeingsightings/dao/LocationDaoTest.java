/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.Address;
import com.sg.superbeingsightings.dto.Location;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
public class LocationDaoTest {
    
    LocationDao locationDao;
    AddressDao addressDao;
    
    public LocationDaoTest() {
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
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        addressDao = ctx.getBean("addressDao", AddressDao.class);
        
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
        List<Location> locations = locationDao.getAllLocation();
        for (Location location : locations) {
            locationDao.removeLocation(location.getLocationID());
        }
        
        List<Address> addresses = addressDao.getAllAddress();
        for (Address address : addresses) {
            addressDao.removeAddress(address.getAddressID());
        }
    }
    
    @Test
    public void testCoordinatePrecision() {
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
        
        Location locFromDao = locationDao.getLocation(location.getLocationID());
        Address addFromDao = addressDao.getAddress(locFromDao.getAddress().getAddressID());
        locFromDao.setAddress(addFromDao);
        assertTrue(locFromDao.equals(location));
    }

    /**
     * Test of addLocation, getLocation, removeLocation method, of class LocationDao.
     */
    @Test
    public void testAddGetRemoveLocation() {
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
        location.setLatitude(new BigDecimal("100.0000"));
        location.setLongitude(new BigDecimal("5.0000"));
        
        locationDao.addLocation(location);
        
        Location locFromDao = locationDao.getLocation(location.getLocationID());
        Address addFromDao = addressDao.getAddress(locFromDao.getAddress().getAddressID());
        locFromDao.setAddress(addFromDao);
        assertTrue(locFromDao.equals(location));   
        
        locationDao.removeLocation(location.getLocationID());
        addressDao.removeAddress(location.getAddress().getAddressID());
        assertNull(locationDao.getLocation(location.getLocationID()));
        assertNull(addressDao.getAddress(location.getAddress().getAddressID()));
    }

    /**
     * Test of addLocation, getLocation and updateLocation method, of class LocationDao.
     */
    @Test
    public void testAddGetUpdateLocation() {
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
        location.setLatitude(new BigDecimal("100.0000"));
        location.setLongitude(new BigDecimal("5.0000"));
        
        locationDao.addLocation(location);
        
        Location locFromDao = locationDao.getLocation(location.getLocationID());
        Address addFromDao = addressDao.getAddress(locFromDao.getAddress().getAddressID());
        locFromDao.setAddress(addFromDao);
        
        assertTrue(locFromDao.equals(location));  
        
        Address newAddress = new Address();
        newAddress.setAddressID(addFromDao.getAddressID());
        newAddress.setStreet("123 Somewhere St.");
        newAddress.setCity("Nowhere");
        newAddress.setState("ST");
        newAddress.setZip("01111");
        
        addressDao.updateAddress(newAddress);
        
        Location newLocation = new Location();
        newLocation.setLocationID(locFromDao.getLocationID());
        newLocation.setAddress(newAddress);
        newLocation.setName(locFromDao.getName());
        newLocation.setDesc(locFromDao.getDesc());
        newLocation.setLatitude(locFromDao.getLatitude());
        newLocation.setLongitude(locFromDao.getLongitude());
        
        locationDao.updateLocation(newLocation);
        
        Location updatedLocation = locationDao.getLocation(newLocation.getLocationID());
        Address updatedAddress = addressDao.getAddress(updatedLocation.getAddress().getAddressID());
        updatedLocation.setAddress(updatedAddress);
        
        assertTrue(updatedLocation.equals(newLocation));
        assertFalse(updatedLocation.equals(location));
        assertFalse(updatedLocation.equals(locFromDao));       
    }

    /**
     * Test of addLocation and getAllLocation method, of class LocationDao.
     */
    @Test
    public void testAddGetAllLocation() {
        Address address1 = new Address();
        address1.setStreet("401 S Main St");
        address1.setCity("Akron");
        address1.setState("OH");
        address1.setZip("12345");
        
        addressDao.addAddress(address1);
        
        Location location1 = new Location();
        location1.setAddress(address1);
        location1.setName("401 Loft");
        location1.setDesc("Its Lovely.");
        location1.setLatitude(new BigDecimal("100.0000"));
        location1.setLongitude(new BigDecimal("5.0000"));
        
        locationDao.addLocation(location1);
        
        Address address2 = new Address();
        address2.setStreet("123 Anywhere St.");
        address2.setCity("Nowhere");
        address2.setState("ST");
        address2.setZip("01110");
        
        addressDao.addAddress(address2);
        
        Location location2 = new Location();
        location2.setAddress(address2);
        location2.setName("Test Loft");
        location2.setDesc("Tested. Its Lovely.");
        location2.setLatitude(new BigDecimal("-10.0000"));
        location2.setLongitude(new BigDecimal("5.0000"));
        
        locationDao.addLocation(location2);
        
        List<Location> locations = locationDao.getAllLocation();
        assertEquals(2,locations.size());
    }
    
}
