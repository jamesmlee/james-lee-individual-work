/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lambdas.dao;

import com.mycompany.lambdas.dto.Server;
import java.util.List;
import java.util.Map;

/**
 *
 * @author James
 */
public interface ServerDao {
// 4 CRUD methods
    public void addServer(Server s);
    public Server getServer(String name);
    public void removeServer(String name);
    public List<Server> getAllServers();
// realistic methods    
    public List<Server> getServersByManufacturer(String m);
    public List<Server> getServersOlderThan(int years);
// key value pair is manufacturer name : list of servers    
    public Map<String, List<Server>> getAllServersGroupByManufacturer();
    public Map<String, List<Server>> getServersOlderThanGroupByManufacturer(int years);
    
    public double getAverageServerAge();
}
