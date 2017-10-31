/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.serverinventory.dao;

import com.sg.serverinventory.dto.Server;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author James
 */
public class ServerDaoInMemImpl implements ServerDao{

    private Map<String, Server> serverMap = new HashMap<>();
    
    
    @Override
    public void addServer(Server server) {
        serverMap.put(server.getName(), server);
    }

    @Override
    public Server getServer(String name) {
        return serverMap.get(name);
    }

    @Override
    public void removeServer(String name) {
        serverMap.remove(name);
    }

    @Override
    public List<Server> getAllServers() {
        return new ArrayList<Server>(serverMap.values());
    }

    @Override
    public Map<String, List<Server>> getAllServersGroupByManufacturer() {
// go to our map and get a stream of all the values
        return serverMap.values()
                .stream()
// terminal operation to transform the collection of servers into the map we return
                                            // for every server, call getManufacturer()
                .collect(Collectors.groupingBy(Server::getManufacturer));
        
    }

    @Override
    public List<Server> getServersByManufacturer(String manufacturer) {
        return serverMap.values()
                .stream()
// for each server, keep it if the manuf equals the string passed in as manuf for this method                 
                .filter(s -> s.getManufacturer().equalsIgnoreCase(manufacturer))
// collect servers into a list and return them                
                .collect(Collectors.toList());
    }

    @Override
    public List<Server> getServersOlderThan(int ageInYears) {
        return serverMap.values()
                .stream()
// keep all servers older than the age passed in           
// if >, return true and keep them                
                .filter(s -> s.getServerAge() > ageInYears)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Server>> getServersOlderThanGroupByManufacturer(int ageInYears) {
                return serverMap.values()
                .stream()           
                .filter(s -> s.getServerAge() > ageInYears)
                .collect(Collectors.groupingBy(Server::getManufacturer));
    }
    
    @Override
    public double getAverageServerAge() {
        return serverMap.values()
// transfer stream of servers into a stream of longs
// pass that into a getAverageServerAge(), and return answer as a double               
                .stream()
                .mapToLong(s -> s.getServerAge())
                .average()
                .getAsDouble();
    }
// what we did: asking serverMap for all the values, which is a collection of server objects
// grab the stream and pass it into an intermediate operation to transfer stream of servers into stream of longs
// take the stream of longs and pass it into average ... get it out and return as a double
    
}
