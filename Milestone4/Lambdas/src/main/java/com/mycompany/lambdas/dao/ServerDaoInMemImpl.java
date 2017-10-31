/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lambdas.dao;

import com.mycompany.lambdas.dto.Server;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author James
 */
public class ServerDaoInMemImpl implements ServerDao {

    private Map<String, Server> serverMap = new HashMap();

    @Override
    public void addServer(Server s) {
        serverMap.put(s.getName(), s);
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
// make a List and get the values out of the Map        
        return new ArrayList(serverMap.values());
    }

    @Override
    public List<Server> getServersByManufacturer(String m) {       
// make a List to put the results in
  //        List<Server> results = new ArrayList();
// get a List of all the servers        
  //        List<Server> allServers = getAllServers();
// loop through and add matches to the List of results
// can turn into stream with lightbulb, or go back with Ctrl+Z
  //        allServers.stream().filter((s) -> (m.equals(s.getManufacturer()))).forEachOrdered((s) -> {
  //            results.add(s);
  //        });
        /*
        for (Server s : allServers) {
        if(m.equals(s.getManufacturer())) {
        results.add(s);
        }
        }
         */
//        return results;

// trying it another way
        List<Server> results = null;
        List<Server> allServers = getAllServers();
// ***1***        
//        Predicate<Server> myFilterPredicate = new Predicate<Server>() {
//            @Override
//            public boolean test(Server t) {
//// see if manufacturer is the same as the manufacturer passed in                
//                return m.equals(t.getManufacturer());
//            }
//        };
//
//// ***2*** using a Lambda        
//// Lambda is a shortcut to creating a predicate; this is the same as above        
//        Predicate<Server> myBetterFilterPredicate = (Server t) -> {
//            return m.equals(t.getManufacturer());
//        };
// ***3*** another Lambda        
// could replace (Server t) with t ...         
//        Predicate<Server> myEvenBetterFilterPredicate = (Server t) -> {
//            return m.equals(t.getManufacturer());
//        };
        
// stream
        results = allServers.stream()
// filtered stream ... can have mutiple filters
            .filter(t -> m.equals(t.getManufacturer()))
// terminal operation collect gives us a list of what's left            
            .collect(Collectors.toList());
        
        return results;

    }

/* condensed version of above    
//    return getAllServers().stream()
        .filter(t-> m.equals(t.getManufacturer()))
        .collect(Collectors.toList());
/*     
    

    @Override
    public List<Server> getServersOlderThan(int years) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, List<Server>> getAllServersGroupByManufacturer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, List<Server>> getServersOlderThanGroupByManufacturer(int years) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getAverageServerAge() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
