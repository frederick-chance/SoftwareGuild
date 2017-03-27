/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superbeingsightings.dao;

import com.sg.superbeingsightings.dto.SuperBeing;
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
public class SuperBeingDaoTest {
    
    SuperBeingDao dao;
    
    public SuperBeingDaoTest() {
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
        dao = ctx.getBean("superbeingDao", SuperBeingDao.class);
        
        List<SuperBeing> superbeings = dao.getAllSuperBeing();
        for (SuperBeing superbeing : superbeings) {
            dao.removeSuperBeing(superbeing.getSuperBeingID());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addSuperBeing, getSuperBeing, removeSuperBeing method, of class SuperBeingDao.
     */
    @Test
    public void testAddGetRemoveSuperBeing() {
        SuperBeing superbeing = new SuperBeing();
        superbeing.setName("Neo-Queen Serenity");
        superbeing.setAlias("Usagi Tsukino");
        superbeing.setDesc("Queen of Crystal Tokyo");
        
        dao.addSuperBeing(superbeing);
        
        SuperBeing fromDao = dao.getSuperBeing(superbeing.getSuperBeingID());
        assertTrue(fromDao.equals(superbeing));
        
        dao.removeSuperBeing(superbeing.getSuperBeingID());
        assertNull(dao.getSuperBeing(superbeing.getSuperBeingID()));
    }

    /**
     * Test of addSuperBeing, getSuperBeing and updateSuperBeing method, of class SuperBeingDao.
     */
    @Test
    public void testAddGetUpdateSuperBeing() {
        SuperBeing superbeing = new SuperBeing();
        superbeing.setName("Black Panther");
        superbeing.setAlias("Luke Charles");
        superbeing.setDesc("King and Protector of Wakanda.");
        
        dao.addSuperBeing(superbeing);
        
        SuperBeing fromDao = dao.getSuperBeing(superbeing.getSuperBeingID());
        assertTrue(fromDao.equals(superbeing));
        
        SuperBeing newSuperBeing = new SuperBeing();
        newSuperBeing.setSuperBeingID(fromDao.getSuperBeingID());
        newSuperBeing.setName("Storm");
        newSuperBeing.setAlias("Ororo Iquadi T'Challa");
        newSuperBeing.setDesc("The Weather Witch");
        
        dao.updateSuperBeing(newSuperBeing);
        
        SuperBeing updatedSuperBeing = dao.getSuperBeing(newSuperBeing.getSuperBeingID());
        assertTrue(updatedSuperBeing.equals(newSuperBeing));
        assertFalse(updatedSuperBeing.equals(superbeing));
        assertFalse(updatedSuperBeing.equals(fromDao));
    }

    /**
     * Test of addSuperBeing and getAllSuperBeing method, of class SuperBeingDao.
     */
    @Test
    public void testAddGetAllSuperBeing() {
        SuperBeing superbeing1 = new SuperBeing();
        superbeing1.setName("Black Panther");
        superbeing1.setAlias("Luke Charles");
        superbeing1.setDesc("King and Protector of Wakanda.");
        
        dao.addSuperBeing(superbeing1);
        
        SuperBeing superbeing2 = new SuperBeing();
        superbeing2.setName("Storm");
        superbeing2.setAlias("Ororo Iquadi T'Challa");
        superbeing2.setDesc("The Weather Witch");
        
        dao.addSuperBeing(superbeing2);
        
        List<SuperBeing> superbeings = dao.getAllSuperBeing();
        assertEquals(2,superbeings.size());
    }


    /**
     * Test of getAllSuperBeingByAffiliation method, of class SuperBeingDao.
     */
    @Test
    public void testGetAllSuperBeingByAffiliation() {
    }

    /**
     * Test of getAllSuperBeingBySuperPower method, of class SuperBeingDao.
     */
    @Test
    public void testGetAllSuperBeingBySuperPower() {
    }

    /**
     * Test of getAllSuperBeingBySighting method, of class SuperBeingDao.
     */
    @Test
    public void testGetAllSuperBeingBySighting() {
    }
    
}
