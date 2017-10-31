/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shapesandperimeters;

/**
 *
 * @author James
 */
public class App {
    public static void main(String[] args) {
        Square mySquare = new Square();
        mySquare.setSide(2);
        System.out.println(mySquare.getArea());
        System.out.println(mySquare.getPerimeter());
        
    }
}
