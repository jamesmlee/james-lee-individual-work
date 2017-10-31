/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vid2;

/**
 *
 * @author James
 */
public class App {
    public static void main(String[] args) {
        int count = 19;
        changeMyValue(count);
    // prints 19 because value and count are not connected    
        System.out.println("Count = " + count);
        
        Person myPerson = new Person();
        myPerson.setAge(24);
        myPerson.setName("Joe");
        System.out.println("Age = " + myPerson.getAge());
        System.out.println("Name = " + myPerson.getName());
        
        changeMyName(myPerson);
        
        System.out.println("Age = " + myPerson.getAge());
    // there's only 1 myPerson, so the name changes    
        System.out.println("Name = " + myPerson.getName());
    }
    
    public static void changeMyValue(int value) {
        value = 21;
    }
    
    public static void changeMyName(Person person) {
        person.setName("Steve");
    }
}
