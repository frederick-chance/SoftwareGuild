/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.Address;
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
public class AddressDaoTest {
    
    AddressDao dao;
    
    public AddressDaoTest() {
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
        dao = ctx.getBean("addressDao", AddressDao.class);
        
        List<Address> addresses = dao.getAllAddress();
        for (Address address : addresses) {
            dao.removeAddress(address.getAddressID());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addAddress, getAddress and removeAddress method, of class AddressDao.
     */
    @Test
    public void testAddGetRemoveAddress() {
        Address address = new Address();
        address.setStreet("123 Somewhere St.");
        address.setCity("Overhere");
        address.setState("ST");
        address.setZip("00000");
        
        dao.addAddress(address);
        
        Address fromDao = dao.getAddress(address.getAddressID());
        assertTrue(fromDao.equals(address));
        
        dao.removeAddress(address.getAddressID());
        assertNull(dao.getAddress(address.getAddressID()));
    }

    /**
     * Test of addAddress, getAddress and updateAddress method, of class AddressDao.
     */
    @Test
    public void testAddGetUpdateAddress() {
        Address address = new Address();
        address.setStreet("123 Somewhere St.");
        address.setCity("Overhere");
        address.setState("ST");
        address.setZip("00000");
        
        dao.addAddress(address);
        
        Address fromDao = dao.getAddress(address.getAddressID());
        assertTrue(fromDao.equals(address));
        
        Address newAddress = new Address();
        newAddress.setAddressID(fromDao.getAddressID());
        newAddress.setStreet("123 Somewhere St.");
        newAddress.setCity("Nowhere");
        newAddress.setState("ST");
        newAddress.setZip("01111");
        
        dao.updateAddress(newAddress);
        
        Address updatedAddress = dao.getAddress(newAddress.getAddressID());
        assertTrue(updatedAddress.equals(newAddress));
        assertFalse(updatedAddress.equals(address));
        assertFalse(updatedAddress.equals(fromDao));        
    }

    /**
     * Test of addAddress and getAllAddress method, of class AddressDao.
     */
    @Test
    public void testAddGetAllAddress() {
        Address address1 = new Address();
        address1.setStreet("123 Somewhere St.");
        address1.setCity("Overhere");
        address1.setState("ST");
        address1.setZip("00000");
        
        dao.addAddress(address1);
        
        Address address2 = new Address();
        address2.setStreet("123 Anywhere St.");
        address2.setCity("Nowhere");
        address2.setState("ST");
        address2.setZip("01110");
        
        dao.addAddress(address2);
        
        List<Address> addresses = dao.getAllAddress();
        assertEquals(2,addresses.size());
    }
    
}
