/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.SuperPower;
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
public class SuperPowerDaoTest {
    
    SuperPowerDao dao;
    
    public SuperPowerDaoTest() {
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
        dao = ctx.getBean("superpowerDao", SuperPowerDao.class);
        
        List<SuperPower> superpowers = dao.getAllSuperPower();
        for (SuperPower superpower : superpowers) {
            dao.removeSuperPower(superpower.getSuperPowerID());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addSuperPower, getSuperPower, removeSuperPower method, of class SuperPowerDao.
     */
    @Test
    public void testAddGetRemoveSuperPower() {
        SuperPower superpower = new SuperPower();
        superpower.setName("Legendary Silver Crystal");
        superpower.setDesc("The Silver Crystal possess tremendous power, and possibly the single most powerful magical artifact in the entire cosmos.");
        
        dao.addSuperPower(superpower);
        
        SuperPower fromDao = dao.getSuperPower(superpower.getSuperPowerID());
        assertTrue(fromDao.equals(superpower));
        
        dao.removeSuperPower(superpower.getSuperPowerID());
        assertNull(dao.getSuperPower(superpower.getSuperPowerID()));
    }

    /**
     * Test of addSuperPower, getSuperPower, updateSuperPower method, of class SuperPowerDao.
     */
    @Test
    public void testAddGetUpdateSuperPower() {
        SuperPower superpower = new SuperPower();
        superpower.setName("Fire");
        superpower.setDesc("It burns.");
        
        dao.addSuperPower(superpower);
        
        SuperPower fromDao = dao.getSuperPower(superpower.getSuperPowerID());
        assertTrue(fromDao.equals(superpower));
        
        SuperPower newSuperPower = new SuperPower();
        newSuperPower.setSuperPowerID(fromDao.getSuperPowerID());
        newSuperPower.setName("Ice");
        newSuperPower.setDesc("Its cold.");
        
        dao.updateSuperPower(newSuperPower);
        
        SuperPower updatedSuperPower = dao.getSuperPower(newSuperPower.getSuperPowerID());
        assertTrue(updatedSuperPower.equals(newSuperPower));
        assertFalse(updatedSuperPower.equals(superpower));
        assertFalse(updatedSuperPower.equals(fromDao));
    }
 

    /**
     * Test of addSuperPower, getAllSuperPower method, of class SuperPowerDao.
     */
    @Test
    public void testAddGetAllSuperPower() {
        SuperPower superpower1 = new SuperPower();
        superpower1.setName("Ice-Fire");
        superpower1.setDesc("Firey Ice");
        
        dao.addSuperPower(superpower1);
        
        SuperPower superpower2 = new SuperPower();
        superpower2.setName("Earth-Water");
        superpower2.setDesc("It's just mud.");
        
        dao.addSuperPower(superpower2);
        
        List<SuperPower> superpowers = dao.getAllSuperPower();
        assertEquals(2,superpowers.size());
    }

    /**
     * Test of getAllSuperPowerBySuperBeing method, of class SuperPowerDao.
     */
    @Test
    public void testGetAllSuperPowerBySuperBeing() {
    }
    
}
