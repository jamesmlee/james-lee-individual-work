/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.serverinventory;

import com.sg.serverinventory.dto.Server;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author James
 */
public class App {
    public static void main(String[] args) {
        // pretend we have server data here from code along (see last video)
        
// ask for all of the servers manufactured by Dell
        List<Server> dells = dao.getServersByManufacturer("Dell");
        for(Server currentServer : dells) {
            System.out.println(currentServer.getName());
        }
// with lambdas
        dells.stream()
                .forEach(s -> System.out.println(s.getName()));
        
// get all the servers out of the DAO and group them by manufacturer
        Map<String, List<Server>> serverMap = dao.getAllServersGroupByManufacturer();
// print out name of manuf, and list all servers by that manuf
        Set<String> manufacturers = serverMap.keySet();
// go through manufacturers one at a time        
        manufacturers.stream()
// grab each name and run multiple statements within {}                
                .forEach (name -> { System.out.println("======================");
                                    System.out.println("Manufacturer: " + name);
// get the list associated with this name, and iterate through it ... lambda in a lambda
                                    serverMap.get(name).stream().forEach(s -> System.out.println(s.getName()));
                                  });
    }
}
