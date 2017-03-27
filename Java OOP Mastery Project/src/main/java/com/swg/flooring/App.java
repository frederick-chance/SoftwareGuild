/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring;

import com.swg.flooring.ctrl.FlooringController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class App {

    public static void main(String[] args) {
//        FlooringOrderArchiveConfig configuration = new FlooringOrderArchiveConfigImpl();
//        FlooringIDGeneratorDao generator = new FlooringIDGeneratorDaoImpl();
//        FlooringStateRegistryDao registry = new FlooringStateRegistryDaoImpl();
//        FlooringProductInventoryDao inventory = new FlooringProductInventoryDaoImpl();
//        FlooringOrderArchiveWriter writer = new FlooringOrderArchiveWriterImpl();
//        FlooringOrderArchiveReader reader = new FlooringOrderArchiveReaderImpl();
//        FlooringOrderArchiveDao archive = new FlooringOrderArchiveDaoImpl(writer, reader);
//        
//        UserIO io = new UserIOImpl();
//        FlooringView view = new FlooringView(io);
//        
//        FlooringService serv = new FlooringServiceImpl(
//                archive, inventory, registry, generator, configuration, audit);
//        
//        FlooringController ctrl = new FlooringController(view, serv);    

        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringController ctrl = 
                ctx.getBean("ctrl", FlooringController.class);
        
        ctrl.run();
    }

}
