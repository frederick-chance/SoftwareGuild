/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.dao;

/**
 *
 * @author apprentice
 */
public class FlooringOrderArchiveConfigImpl implements FlooringOrderArchiveConfig {

    @Override
    public void setWriter(FlooringOrderArchiveDao dao, FlooringOrderArchiveWriter writer) {
        dao.setWriter(writer);
    }
    
}
