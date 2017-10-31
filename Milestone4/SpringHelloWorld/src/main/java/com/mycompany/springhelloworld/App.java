/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springhelloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author James
 */
public class App {
    
    public static void main(String[] args) {
        
        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
        
        MessageController controller = ctx.getBean("myController", MessageController.class);
        
//        MessageService service = new MessageServiceImpl();
//        MessageController controller = new MessageController(service);
        
        controller.printMessage();
    }
}
